// ProductCatalogWidget.js

	//Public constants
	CATALOG_ID_PREFIX = "Catalog:";
	ACTION_ID_PREFIX = "Action:";
	CATALOG_URI_PROPERTY_NAME = "CatalogHierarchy";
	CATALOG_TRANSFORMATION_URI_PROPERTY_NAME = "CatalogToTreeTransformationSchema";
	PRODUCT_CATALOG_FORM_NAME = "ProductCatalogForm";
	DELETE_CAPTION = "DeleteCaption";
	TREEDEL_CAPTION = "DeleteTree";
	UP_CAPTION = "Up";
	DOWN_CAPTION = "Down";
	NEW_CAPTION = "New";
	INSERT_CAPTION = "Insert";
	RENAME_CAPTION = "Rename";
	COPY_CAPTION = "Copy";
	MOVE_CAPTION = "Move";
	SEARCH_CAPTION = "Search";	
	NAME_CAPTION = "  Name";
	TARGETPATH_CAPTION = "TargetPath";
	FIND_CAPTION = "Find";
	FIND_IN_CAPTION = "FindInCaption";
	
	SELECTED_ELEMENT_NAME_ID = "SelectedCatalogElementNameId";
	SELECTED_ELEMENT_DESCRIPTION_ID = "SelectedCatalogElementDescriptionId";
	SELECTED_ELEMENT_IMAGES_ID = "SelectedCatalogElementImageId";
	SELECTED_ELEMENT_DOCUMENTS_ID = "SelectedCatalogElementDocumentsId";
	
function ProductCatalogWidget( theController, theCatalogDataUri, theName ) {
	//check parameter assertions
	AssertUtil.assertParamNotNull( theController, "theController" );
	AssertUtil.assertParamNotNull( theCatalogDataUri, "theCatalogDataUri");

	//Private constants
	SELECTED_SEPARATOR = ".";
	CATALOG_ELEMENT_CATALOG = "ProductCatalog";
	CATALOG_ELEMENT_ROOT_CATEGORY = "RootCategory";
	CATALOG_ELEMENT_COMPOSITE_CATEGORY = "CompositeCategory";
	CATALOG_ELEMENT_PRODUCT_GROUP = "ProductGroup";
	
	//private instance variables
	var logger = null;
	var containerElement = null;
	var self = this;
	var name = ( theName != null && theName != "" ) ? theName : "ProductCatalogWidget";
	var uiController = theController;
	var i18Resource = uiController.getResourceBundle();
	var configuration = uiController.getApplicationConfiguration();
	var catalogDataUri = theCatalogDataUri;
	var catalogXML = null;
	var selectedCatalogElementType = null;
	var pictureFolder = "images";
	var catalogFormElement = null;
	var propertiesArea = null;
	var subCategoriesArea = null;
	var subCategoriesTableContainer = null;
	var subCategoriesTable = null;
	var searchResultTableContainer = null;	
	var searchResultArea = null;		
	var searchResultTable = null;
	var selectedCatalogItemSpanElement = null;
	var selectedCatalogItemAnchorElement = null;
	var selectedCatalogItemTextElement = null;
	var selectedCatalogItemId = null;
	var selectedCatalogItemName = null;
	var catalogTreeWidget = null;
	var isVisible = false;
	
	//---bo-new html tag vars
	
	var deleteButton = null;
	var deleteTreeButton = null;
	var upButton = null;
	var downButton = null;

	var newButton = null;
	var insertButton = null;
	var renameButton = null;
	var copyButton = null;
	var moveButton = null;
	
	var nameEditBox = null;
	
	var collapsibleElement = null;
	var fieldSet = null;
	var editDiv = null;

	var collapsibleSearchElement = null;
	var searchFieldSet = null;
	var searchDiv = null;
	var searchEditBox = null;
	var searchButton = null;
	
	var collapsibleQuickSearchElement = null;
	var quickSearchFieldSet = null;
	var quickSearchDiv = null;
	var quickSearchEditBox = null;
	var quickSearchSelect = null;
	var quickSearchButton = null;

	// initialization
	if( theName != null && theName != "" ) containerElement = document.getElementById( theName );

	// inherit from BrowserWidget
	var parent = new BrowserWidget( document, containerElement, theController.getResourceBundle() );
  	inheritFrom(this, parent);
	
	//public accessor methods
	this.isVisible = function () { return isVisible; }
	this.getCatalogTreeWidget = function () {return catalogTreeWidget;}
	
	//public mutator methods
	this.setPictureFolder = function( folder ) { pictureFolder = folder; }
	this.show = _Show;
	this.hide = _Hide;
	
	_Constructor();
	
	//Constructors
	function _Constructor() {
		configureRootLogger();
		logger = log4javascript.getLogger( ROOT_LOGGER_NAME + ".productCatalogWidget" );
		logger.debug( "New 'ProductCatalogWidget' was instantiated. name:" + name );
	}
	
	//private methods
	function _Show () {
		_ConstructProductCatalogForm();
		logger.trace( "Show with images from:'" + pictureFolder + "'." );
		isVisible = true;
	}
	
	function _Hide () {
		_RemoveTreeWidget();
		isVisible = false;
	}
	
	//HTML construction methods
	function _ConstructCatalogTreeRow( readOnlyContainer ) {
		var label = self.createRowLabelElement( "ui.PlanManagerWidget.Tree.Label" );
		var value = self.createRowValueElement();
		
        var area = self.createElement("div");
        area.id="CatalogTreeWidget";
        area.style.border="1px dotted";
        value.appendChild(area);
		self.insertRow( readOnlyContainer, label, value );
        catalogTreeWidget = new TreeWidget( area, self.getHtmlDOMDocument() );
	}

	function _ConstructEditDiv(readOnlyContainer) {
		collapsibleElement = self.createCollapsibleElement(readOnlyContainer);
		fieldSet = self.createFieldSet(collapsibleElement, "imageIdentifier")
		editDiv = self.createHiddenDiv(fieldSet, "division");
		
		var img = document.getElementById("imageIdentifier");
		img.onclick = function() {
						var collapsibleDiv = document.getElementById(editDiv.id);
						if (collapsibleDiv.style.display == 'none') {
							collapsibleDiv.style.display = 'block';
							img.src = pictureFolder + "/images/collver.png";
						}
						else {
							collapsibleDiv.style.display = 'none';
							img.src = pictureFolder + "/images/expver.png";
						}
					}
		img.setAttribute("src", pictureFolder + "/images/collver.png");

		collapsibleElement.appendChild(fieldSet);
		fieldSet.appendChild(editDiv);
		
		deleteButton = self.createButton( DELETE_CAPTION,  _OnDeleteActionClick );
		deleteTreeButton = self.createButton( TREEDEL_CAPTION,  _OnDeleteActionClick );
		upButton = self.createButton( UP_CAPTION,  _OnDeleteActionClick );
		downButton = self.createButton( DOWN_CAPTION,  _OnDeleteActionClick );
		newButton = self.createButton( NEW_CAPTION,  _OnNewActionClick );
		insertButton = self.createButton( INSERT_CAPTION,  _OnInsertActionClick );
		renameButton = self.createButton( RENAME_CAPTION,  _OnRenameActionClick );
		copyButton = self.createButton( COPY_CAPTION,  _OnCopyActionClick );
		moveButton = self.createButton( MOVE_CAPTION,  _OnMoveActionClick );

		var label1 = self.createRowLabelElement("");
		var row1 = self.createRowValueElement();
		row1.appendChild( copyButton );
		row1.appendChild( moveButton );
		row1.appendChild( upButton );
		row1.appendChild( downButton );
		self.insertRow(editDiv, label1, row1);

		var nameLabel = self.createRowLabelElement(NAME_CAPTION);
		var row = self.createRowValueElement();
		nameEditBox = self.createElement( "INPUT" );
		nameEditBox.type = "text";
		row.appendChild( nameEditBox );
		row.appendChild(newButton);
		row.appendChild(insertButton);
		row.appendChild(renameButton);
		self.insertRow(editDiv, nameLabel, row);

		var label2 = self.createRowLabelElement("");
		var row2 = self.createRowValueElement();
		row2.appendChild( deleteButton );
		row2.appendChild( deleteTreeButton );
		self.insertRow(editDiv, label2, row2);
		
		editDiv.appendChild(self.createElement("BR"));
		editDiv.appendChild(self.createElement("BR"));
		
		self.insertReadOnlyContainer(collapsibleElement);
	}
	
	function _ConstructProductCatalogForm () {
		var catalogFormElement = self.appendFormElement ( PRODUCT_CATALOG_FORM_NAME, "POST" );
		var readOnlyContainer = self.insertReadOnlyContainer( catalogFormElement );

		_ConstructQuickSearchForm( readOnlyContainer );
		//_ConstructSearchForm(readOnlyContainer);
		_ConstructCatalogTreeRow( readOnlyContainer );
		_ConstructPropertiesArea( readOnlyContainer );
		_ConstructSubCategoriesArea( readOnlyContainer );
		_ConstructEditDiv( readOnlyContainer );
		
		if( uiController != null && uiController != "" ) catalogTreeWidget.setResourceBundle( uiController.getResourceBundle() );
		catalogTreeWidget.setSourceXmlFileName( catalogDataUri );
		catalogTreeWidget.setTransformationXslFileName( configuration.getProperty( CATALOG_TRANSFORMATION_URI_PROPERTY_NAME ));
		catalogTreeWidget.setPictureFolder( pictureFolder );
		catalogTreeWidget.loadWidget();
		catalogTreeWidget.showWidget();
		catalogTreeWidget.functionInSelect = _OnCatalogClick;
		
		catalogXML = catalogTreeWidget.getSourceXML();
		_RefreshSelectedCatalogElementProperties();		
		return readOnlyContainer;
	}
	
	function _ConstructPropertiesArea( readOnlyContainer ) {
		propertiesArea = self.createDivisionElement( readOnlyContainer );
		
		var label = self.createRowLabelElement( "ui.PlanManagerWidget.SelectedItem.Label" );
		selectedCatalogItemSpanElement = self.createRowValueElement();
		selectedCatalogItemAnchorElement = self.createAnchor( 0, "", _OnSelectedCatalogItemClick );
		selectedCatalogItemTextElement = selectedCatalogItemAnchorElement.firstChild;
		selectedCatalogItemSpanElement.appendChild( selectedCatalogItemAnchorElement );
		selectedCatalogItemSpanElement.appendChild( self.createElement( "BR" ));
		self.insertRow( propertiesArea, label, selectedCatalogItemSpanElement );

		self.appendStaticRow( propertiesArea, "ui.PlanManagerWidget.SelectedItem.Name.Label", "-", SELECTED_ELEMENT_NAME_ID );
		self.appendStaticRow( propertiesArea, "ui.PlanManagerWidget.SelectedItem.Description.Label", "-", SELECTED_ELEMENT_DESCRIPTION_ID );
		self.appendStaticRow( propertiesArea, "ui.PlanManagerWidget.SelectedItem.Images.Label", "-", SELECTED_ELEMENT_IMAGES_ID );
		self.appendStaticRow( propertiesArea, "ui.PlanManagerWidget.SelectedItem.Documents.Label", "-", SELECTED_ELEMENT_DOCUMENTS_ID );
	}
	
	function _ConstructSearchResultArea( readOnlyContainer ) {
		var label = self.createRowLabelElement( "ui.PlanManagerWidget.SearchResultList.Label" );
		searchResultTableContainer = self.createRowValueElement();	
		searchResultArea = self.insertRow( readOnlyContainer, label, searchResultTableContainer );		
	}
	
	function _ConstructSubCategoriesArea( readOnlyContainer ) {
		var label = self.createRowLabelElement( "ui.PlanManagerWidget.CatalogEntryList.Label" );
		subCategoriesTableContainer = self.createRowValueElement();		
		subCategoriesArea = self.insertRow( readOnlyContainer, label, subCategoriesTableContainer );
	}
	
	function _ConstructQuickSearchForm( readOnlyContainer ) {
		collapsibleQuickSearchElement = self.createCollapsibleElement( readOnlyContainer );
		quickSearchFieldSet = self.createFieldSet( collapsibleQuickSearchElement, "quickSearchFSImage" );
		quickSearchDiv = self.createHiddenDiv( quickSearchFieldSet, "quickSearchDiv" );
		
		var img = document.getElementById("quickSearchFSImage");
		img.onclick = function() {
						var collapsibleDiv = document.getElementById( quickSearchDiv.id );
						if (collapsibleDiv.style.display == 'none') {
							collapsibleDiv.style.display = 'block';
							img.src = pictureFolder + "/images/collver.png";
						}
						else {
							collapsibleDiv.style.display = 'none';
							img.src = pictureFolder + "/images/expver.png";
						}
					}
		img.setAttribute("src", pictureFolder + "/images/collver.png");

		collapsibleQuickSearchElement.appendChild(quickSearchFieldSet);
		quickSearchFieldSet.appendChild(quickSearchDiv);
		
		//---
		searchButton = self.createButton( SEARCH_CAPTION,  _OnFreeTextSearchActionClick );

		var searchLabel = self.createRowLabelElement( FIND_CAPTION );
		var searchRow = self.createRowValueElement();
		searchEditBox = self.createElement( "INPUT" );
		
		searchEditBox.type = "text";
		searchRow.appendChild( searchEditBox );
		searchRow.appendChild(searchButton);
		self.insertRow(quickSearchDiv, searchLabel, searchRow);
		quickSearchDiv.appendChild(self.createElement("BR"));
		quickSearchDiv.appendChild(self.createElement("BR"));
		quickSearchDiv.appendChild(self.createElement("BR"));
		//---
		
		var findInLabel = self.createRowLabelElement( FIND_IN_CAPTION );
		var findInRow = self.createRowValueElement();
		quickSearchSelect = self.createElement( "SELECT" );
		var option0 = new Option( "Select...", 0 );
    	var option1 = new Option( "Product Id", 1 );
    	var option2 = new Option( "Manufacturer code", 2 );
    	var option3 = new Option( "EAN", 3 );
		quickSearchSelect.options[0] = option0;
		quickSearchSelect.options[1] = option1;
		quickSearchSelect.options[2] = option2;
		quickSearchSelect.options[3] = option3;
		findInRow.appendChild( quickSearchSelect );
		self.insertRow(quickSearchDiv, findInLabel, findInRow);

		quickSearchButton = self.createButton( SEARCH_CAPTION,  _OnQuickSearchActionClick );

		var quickSearchLabel = self.createRowLabelElement(FIND_CAPTION);
		var quickSearchRow = self.createRowValueElement();
		quickSearchEditBox = self.createElement( "INPUT" );
		quickSearchEditBox.type = "text";
		quickSearchRow.appendChild( quickSearchEditBox );
		quickSearchRow.appendChild(quickSearchButton);
		self.insertRow(quickSearchDiv, quickSearchLabel, quickSearchRow);

		quickSearchDiv.appendChild(self.createElement("BR"));
		quickSearchDiv.appendChild(self.createElement("BR"));
		
		self.insertReadOnlyContainer(collapsibleQuickSearchElement);
		//quickSearchDiv.style.display = "block";
		
		_ConstructSearchResultArea( quickSearchDiv );
	}

	function _ConstructSearchForm(readOnlyContainer) {
		collapsibleSearchElement = self.createCollapsibleElement(readOnlyContainer);
		searchFieldSet = self.createFieldSet(collapsibleSearchElement, "searchFSImage")
		searchDiv = self.createHiddenDiv(searchFieldSet, "searchDiv");
		
		var img = document.getElementById("searchFSImage");
		img.onclick = function() {
						var collapsibleDiv = document.getElementById(searchDiv.id);
						if (collapsibleDiv.style.display == 'none') {
							collapsibleDiv.style.display = 'block';
							img.src = pictureFolder + "/images/collver.png";
						}
						else {
							collapsibleDiv.style.display = 'none';
							img.src = pictureFolder + "/images/expver.png";
						}
					}

		collapsibleSearchElement.appendChild(searchFieldSet);
		searchFieldSet.appendChild(searchDiv);
		
		searchButton = self.createButton( SEARCH_CAPTION, _OnSearchActionClick );

		var searchLabel = self.createRowLabelElement(FIND_CAPTION);
		var searchRow = self.createRowValueElement();
		searchEditBox = self.createElement( "INPUT" );
		searchEditBox.setAttribute("id", "searchme");
		
		searchEditBox.type = "text";
		searchRow.appendChild( searchEditBox );
		searchRow.appendChild(searchButton);
		self.insertRow(searchDiv, searchLabel, searchRow);

		searchDiv.appendChild(self.createElement("BR"));
		searchDiv.appendChild(self.createElement("BR"));
		
		self.insertReadOnlyContainer(collapsibleSearchElement);
	}
	
	function _CreateCatalogEntryListHeader( tableDefinition ) {
		tableDefinition.addColumn( "ui.PlanManagerWidget.CatalogEntryList.Column.Identifier" );
		tableDefinition.addColumn( "ui.PlanManagerWidget.CatalogEntryList.Column.Designation" );
		tableDefinition.addColumn( "ui.PlanManagerWidget.CatalogEntryList.Column.Properties" );
		tableDefinition.addColumn( "ui.PlanManagerWidget.CatalogEntryList.Column.Price" );
	}
		
	function _CreateCatalogEntryListRows( catalogEntries, tableDefinition ) {
		logger.trace( "Creating " + catalogEntries.length + " number of CatalogEntry rows.");
		for( var i=0; i < catalogEntries.length; i++ ) {
			var catalogEntryProperties = new Array(4);
			var catalogEntry = catalogEntries[i];
			var catalogEntryXML = new XML( catalogEntry );
			catalogEntryProperties[0] = catalogEntryXML.getNodeValue( "catalogEntryIdentifier" );
			catalogEntryProperties[1] = catalogEntryXML.getNodeValue( "designation" );
			catalogEntryProperties[2] = catalogEntryXML.getNodeValue( "featureDescription" );
			catalogEntryProperties[3] = catalogEntryXML.getNodeValue( "price" );
			
			tableDefinition.addRow( catalogEntryProperties );
		}				
	}
	
	function _DetermineCurrentSelectionBase( selectedTreeNode ) {
		var selectionPath = "";
		
		if( selectedTreeNode != null ) {
			var selectedNodeID = selectedTreeNode.getID();
			logger.trace( "Selected catalog element's id is:", selectedNodeID );
			
			selectionPath = "//catalog[@id=\"" + selectedNodeID + "\"]";
			var selectedCatalogElement = catalogXML.getNode( selectionPath );
			if( selectedCatalogElement != null ) {
				selectedCatalogElementType = CATALOG_ELEMENT_CATALOG;
			}else {
				selectionPath = "//rootCategory[@id=\"" + selectedNodeID + "\"]";
				selectedCatalogElement = catalogXML.getNode( selectionPath );
				if( selectedCatalogElement != null ) {
					selectedCatalogElementType = CATALOG_ELEMENT_ROOT_CATEGORY;
				}else {
					selectionPath = "//compositeCategory[@id=\"" + selectedNodeID + "\"]";
					selectedCatalogElement = catalogXML.getNode( selectionPath );
					if( selectedCatalogElement != null ) {
						selectedCatalogElementType = CATALOG_ELEMENT_COMPOSITE_CATEGORY;
					}else {
						selectionPath = "//productGroup[@id=\"" + selectedNodeID + "\"]";
						selectedCatalogElement = catalogXML.getNode( selectionPath );
						if( selectedCatalogElement != null ) {
							selectedCatalogElementType = CATALOG_ELEMENT_PRODUCT_GROUP;
						}else throw( "The selected tree node:'" + selectedNodeID + "' dosn't exist in catalog XML." );

					}
				}
			}
		} else selectionPath = "//actionResponse/catalog";
		logger.trace("Selected element's type is:", selectedCatalogElementType );
		logger.trace("Selected element's path is:", selectionPath );
		return selectionPath;
	}
		
	function _DetermineProductGroupTableRows( tableDefinition, xpathSelectorBase ) {
		var xPathQuery = xpathSelectorBase + "/catalogEntries/catalogEntry";
		var catalogEntries = catalogXML.getNodes( xPathQuery );
		_CreateCatalogEntryListRows( catalogEntries, tableDefinition );
	}
	
	function _DetermineSearchResultTableRows( tableDefinition, xPathQuery ) {
		try{
			var catalogEntries = catalogXML.getNodes( xPathQuery );
			logger.trace( "XPath query: " + xPathQuery + " returned " + catalogEntries.lenght + " number of rows" );
			_CreateCatalogEntryListRows( catalogEntries, tableDefinition );
		} catch(e) {}
	}
	
	function _DetermineSelectedElementDescription( currentSelectionBase ) {
		var determinedDescription = catalogXML.getNodeValue( currentSelectionBase + "/description" );
		return determinedDescription;
	}
		
	function _DetermineSelectedElementName( currentSelectionBase ) {
		var determinedName = catalogXML.getNodeValue( currentSelectionBase + "/name" );
		return determinedName;
	}
		
	function _DetermineSelectedElementType() {
		return "Type";
	}
		
	function _OnCatalogClick() {
		selectedCatalogItemName = catalogTreeWidget.getSelectedNodeFullCaption();
		var newTextElement = self.createTextNode( selectedCatalogItemName );
		selectedCatalogItemAnchorElement.replaceChild( newTextElement, selectedCatalogItemTextElement );
		selectedCatalogItemTextElement = newTextElement;
		
		selectedCatalogItemId = catalogTreeWidget.getSelectedNode().getID();
		selectedCatalogItemName = catalogTreeWidget.getSelectedNode().getName();
		
		_RefreshSelectedCatalogElementProperties( catalogTreeWidget.getSelectedNode() );
	}

//------TreeEditor----------------------------

	function _OnCopyActionClick() {}
	
	function _OnDeleteActionClick() {
	}
	
	function _OnDeleteTreeActionClick() {}

	function _OnDownActionClick() {}
	
	function _OnInsertActionClick() {}
	
	function _OnFreeTextSearchActionClick() {
		logger.trace( "Searching for CatalogEntry which contains: '" + searchEditBox.value + "'" );
		var xPathQuery = "//catalogEntry[contains( designation/text(), '" + searchEditBox.value + "')]";
		_RefreshSearchResultTable( xPathQuery );
	}

	function _OnMoveActionClick() {}

	function _OnNewActionClick() {}

	function _OnQuickSearchActionClick() {
		var selectedOption = quickSearchSelect.value;
		var xPathQuery = "";
		
		logger.trace( "Searching by:", selectedOption );
		if( selectedOption == 1 ) {
			xPathQuery = "//catalogEntry[@id='" + quickSearchEditBox.value + "']";
		}
		if( selectedOption == 2 ) {
			xPathQuery = "//catalogEntry[producerCode='" + quickSearchEditBox.value + "']";
		} 
		if( selectedOption == 3 ) {
			xPathQuery = "//catalogEntry[eanCode='" + quickSearchEditBox.value + "']";
		}
		
		_RefreshSearchResultTable( xPathQuery );		
	}
		
	function _OnRenameActionClick() {
		logging( "Searching by ProductTypeIdentifier." );
	}
		
		
	function _OnSelectedCatalogItemClick() {
    }
    
	function _OnUpActionClick() {}

	function _RefreshSearchResultTable( xPathQuery ) {
		logger.trace( "Refreshing search result table with results of query: ", xPathQuery );
		
		self.removeChild( searchResultTableContainer, searchResultTable );
		searchResultTable = null;

		var tableDefinition = new TableDefinition();
		_CreateCatalogEntryListHeader( tableDefinition );
		_DetermineSearchResultTableRows( tableDefinition, xPathQuery );
		searchResultTable = self.createTable( tableDefinition );
		self.appendChild( searchResultTableContainer, searchResultTable );		
	}
	
	function _RefreshSelectedCatalogElementProperties( selectedTreeNode ) {
    	var xpathSelectorBase = _DetermineCurrentSelectionBase( selectedTreeNode );
    	
    	_RefreshSelectedElementName( xpathSelectorBase );
    	_RefreshSelectedElementDescription( xpathSelectorBase );
   		_RefreshSelectedProductGroupEntries( xpathSelectorBase );
    }
    
	function _RefreshSelectedElementName( xpathSelectorBase ) {
		var selectedElementName = _DetermineSelectedElementName( xpathSelectorBase );
		self.updateTextNode( propertiesArea, SELECTED_ELEMENT_NAME_ID, selectedElementName );
	}
	
	function _RefreshSelectedElementDescription( xpathSelectorBase ) {
		var selectedElementDescription = _DetermineSelectedElementDescription( xpathSelectorBase );
		self.updateTextNode( propertiesArea, SELECTED_ELEMENT_DESCRIPTION_ID, selectedElementDescription );
	}
	
	function _RefreshSelectedProductGroupEntries( xpathSelectorBase ) {
		self.removeChild( subCategoriesTableContainer, subCategoriesTable );
		subCategoriesTable = null;

		logger.trace( subCategoriesTableContainer, subCategoriesTable );
		
		if( selectedCatalogElementType == CATALOG_ELEMENT_PRODUCT_GROUP ) {
			var tableDefinition = new TableDefinition();
			_CreateCatalogEntryListHeader( tableDefinition );
			_DetermineProductGroupTableRows( tableDefinition, xpathSelectorBase );
			subCategoriesTable = self.createTable( tableDefinition );
			self.appendChild( subCategoriesTableContainer, subCategoriesTable );
		}
	}
		
	function _RemoveTreeWidget () {
		catalogTreeWidget.hideWidget();
		self.removeWidget();
	}
	
    function _SearchIndex() { // search the index (duh!)
     // most current browsers support document.implementation
     	if (document.implementation && document.implementation.createDocument) {
     		xmlDoc = document.implementation.createDocument("", "", null);
     		xmlDoc.load("TestCatalog.xml");
     	}
    // MSIE uses ActiveX
    	else if (window.ActiveXObject) {
    		xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
    		xmlDoc.async = "false";
    		xmlDoc.load("TestCatalog.xml");
    	}

    	if (!xmlDoc) {
    		loadIndex();
    	}
    	// get the search term from a form field with id 'searchme'
    
    	var searchterm = document.getElementById("searchme").value;
    	var allitems = xmlDoc.getElementsByTagName("name");
    	results = new Array;
    	if (searchterm.length < 3) {
    		alert("Enter at least three characters");
    	} else {
    		for (var i=0;i<allitems.length;i++) {
    			var name = allitems[i].lastChild.nodeValue;
    			var exp = new RegExp(searchterm,"i");
    			if ( name.match(exp) != null) {
    				results.push(allitems[i]);
    			}
    		}
    	_ShowResults( results, searchterm );
    	}
    }
    
    function _ShowCatalogProperties( elementId ) {
		var label = self.createRowLabelElement( "Kiválasztott katalógus elem:" );
		selectedCatalogSpanElement = self.createRowValueElement();
		selectedCatalogAnchorElement = self.createAnchor( 0, "", _OnSelectedCatalogElementClick );
		selectedCatalogTextElement = selectedCatalogAnchorElement.firstChild;
		selectedCatalogSpanElement.appendChild( selectedCatalogAnchorElement );
		selectedCatalogSpanElement.appendChild( self.createElement( "BR" ));

		self.insertRow( propertiesArea, label, selectedCatalogSpanElement );
    }
    
    function _ShowResults( results, searchterm ) {
    	if (results.length > 0) {
    		var resultshere = document.getElementById("resultshere");
    		var resultFieldSet = document.createElement("fieldSet");
    		resultFieldSet.style.width = "65%";
    		var legend = document.createElement("legend");
    		var textN = document.createTextNode("Keresési eredmény");
    		legend.appendChild(textN);
    		resultFieldSet.appendChild(legend);

    		var header = document.createElement("h5");
    		var list = document.createElement("ul");
    		var searchedfor = document.createTextNode("You've searched for "+searchterm);
    		resultshere.appendChild(header);
    		header.appendChild(searchedfor);
    		resultshere.appendChild(list);
    		resultshere.appendChild(resultFieldSet);
    		for (var i=0;i<results.length;i++) {
    			var listitem = document.createElement("li");
    			var item = document.createTextNode(results[i].lastChild.nodeValue);
    			list.appendChild(listitem);
    			listitem.appendChild(item);
    		}
    	} else {
			alert("Search returned with no result for expression: " + searchterm + "!");
    	}
    }
}	

