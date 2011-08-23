// DocumentManager.js

function DocumentManager(theController, theTabWidget, theInvTabWidget, theTargetFrame, theMenuWidget, theEmptyPagesHref) {

//check parameter assertions
	if(theTabWidget == null) throw new InvalidParameterException();		//theTabWidget can't be null
	if(theController == null) throw new InvalidParameterException();	//theController can't be null

//private instance variables
	var logger = null;
	var loadedDocuments = new Collection();
	var activeDocument = null;
	var self = this;
	var controller = theController;
	var documentSelector = theTabWidget;
	var invTabWidget = theInvTabWidget;
	var menuWidget = theMenuWidget;
	var compositeItemNameInMenu = null;
	var targetFrame = (theTargetFrame == null ? "" : theTargetFrame);
	var emptyPagesHref = theEmptyPagesHref;

//public accessors methods
	this.loadedDocuments = function() {return loadedDocuments;}
	this.getActiveDocument = function() {return activeDocument;}
	this.getDocumentCount = function() {return loadedDocuments.getCountOfObjects();}
	this.getDocumentSelector = function() {return documentSelector;}
	this.setCompositeItemNameInMenu = function(itemName) {compositeItemNameInMenu = itemName;}

//public mutators operations
	this.showDocumentSelector = _ShowDocumentSelector;
	this.hideDocumentSelector = _HideDocumentSelector;
	this.loadDocumentById = _LoadDocumentById;
	this.loadDocumentByName = _LoadDocumentByName;
	this.loadDocumentByUri = _LoadDocumentByUri;
	this.unLoadDocument = _UnLoadDocument;
	this.activateDocument = _ActivateDocument;
	this.observe = _EventHandler;
	this.changeCaptions = _ChangeCaptions;

	_Constructor();
	
//Constructor methods
	function _Constructor() {
		logger = log4javascript.getLogger( ROOT_LOGGER_NAME + ".documentManager" );
		logger.debug( "DocumentManager created.", controller, documentSelector, targetFrame, menuWidget, emptyPagesHref );
	}
		
//private operations
	function _GetText(key,defaultValue) {
		return controller.getText(key,defaultValue);
	}
	
	function _CloseDocument( theDocumentName ) {
		if( activeDocument.getDocumentName() == theDocumentName ) {
			logger.trace( "The active document should be closed by simulating click event." );
			documentSelector.onCloseClick();
		}else {
			logger.trace( "Non active document should be closed by removing tab and menus." );
			documentSelector.removeTab( theDocumentName );
			_UnloadDocument( theDocumentName );
		}
	}
	
	function _ShowDocumentSelector(showClose,showPrint) {
		documentSelector.addObserver(self);
		if(showClose && showClose == true) documentSelector.showCloseButton(true);
		if(showPrint && showPrint == true) documentSelector.showPrintButton(true);
		documentSelector.show();
	}

	function _HideDocumentSelector() {
		if(documentSelector) documentSelector.hide();
	}
	
	function _InstantiateAndConfigureNewDocument( documentType, documentName, url, target, viewNameToActivate, id ) {
		var tFrame = (target == null ? targetFrame : target);
		var aDocument = new Document( controller, documentType, documentName, url, invTabWidget, tFrame, id );
		loadedDocuments.add( documentName, aDocument );
		if( viewNameToActivate ) aDocument.setViewNameToActivate( viewNameToActivate );			

		if( menuWidget != null && compositeItemNameInMenu != null ) {
			if( !menuWidget.exists( compositeItemNameInMenu ) ) 
				menuWidget.addCompositMenu(compositeItemNameInMenu, _GetText( compositeItemNameInMenu ), false, 0 );//0,1,2,...
			menuWidget.addSubMenuToCompositMenu(compositeItemNameInMenu, documentName, _GetText(aDocument.getNameToShow()), new SwitchToDocumentCommand(documentName));
		}

		if( menuWidget != null ) aDocument.addObserver( self );	
		return aDocument;
	}
	
	function _LoadDocument( documentType, documentName, url, target, viewNameToActivate, id ) {
		logger.group( "Starting to load document." );
		if( !loadedDocuments.exists( documentName ) ) {
			logger.debug( "Loading new document. type: " + documentType + ", name: " + documentName + ", url:" + url + ", target: " + target + ", view name:" + viewNameToActivate + ", id:" + id );
			if( activeDocument && !activeDocument.mayDeActivate() ) return;

			var newDocument = _InstantiateAndConfigureNewDocument( documentType, documentName, url, target, viewNameToActivate, id );
			documentSelector.addNewTab( documentName, _GetText( documentName ), newDocument );
		} else { 
			if( logger.getLevel() == log4javascript.Level.TRACE ) 
				logger.trace( "Active document's type is: '" + activeDocument.getDocumentType() + "'. Requested type is: '" + documentType + "'." );
			
			if( activeDocument.getDocumentType() != documentType ) {
				logger.trace( "New document type was given with already existing name.", documentType, documentName, url, target, viewNameToActivate, id );
				_CloseDocument( documentName );
				_LoadDocument( documentType, documentName, url, target, viewNameToActivate, id );
			} else if( activeDocument.mayDeActivate() ) {
				logger.debug( "Activating existing document. type: " + documentType + ", name: " + documentName + ", url:" + url + ", target: " + target + ", view name:" + viewNameToActivate + ", id:" + id );
				var documentToReload = loadedDocuments.item( documentName );
				logger.trace( "Document to reload:", documentToReload.getDocumentName(), documentToReload.getDocumentType() );
				
				if( id != documentToReload.getDocumentId() ) documentToReload.setDocumentId( id );
				
				if( documentType == null || documentType == "" ) {
					if( url != documentToReload.getUrl() ) documentToReload.setUrl( url );
				}
				documentSelector.activateTab( documentName );
			}
		}
		logger.groupEnd();
	}
	
	function _LoadDocumentById( documentType, name, id, viewNameToActivate ) {
		_LoadDocument( documentType, name, null, null, viewNameToActivate, id );
	}
	
	function _LoadDocumentByName( documentType, name, viewNameToActivate ) {
		_LoadDocument( documentType, name, null, null, viewNameToActivate, null );
	}

	function _LoadDocumentByUri( documentType, name, url, viewNameToActivate ) {
		_LoadDocument( documentType, name, url, null, viewNameToActivate, null );
	}
		
	function _UnLoadDocument( documentName, documentType ) {
		logger.trace( "Unloading document:", documentName );
		
		if( menuWidget != null && compositeItemNameInMenu != null ) {
			menuWidget.removeMenuItemFromCompositMenu( compositeItemNameInMenu, documentName );
		}
		
		if( loadedDocuments.item( documentName )) loadedDocuments.remove( documentName );
		
		if( loadedDocuments.getCountOfObjects() == 0 ) { // show emptyPagesHref
			var containingFrame = window;
			if( emptyPagesHref )
				if( parent.frames.length != 0 && parent.frames[targetFrame] != undefined ) {
					containingFrame = parent.frames[targetFrame];
					containingFrame.location.href = emptyPagesHref;
				}
				else containingFrame = window.open( emptyPagesHref, "newWindow" );
		}
	}

	function _ActivateDocument( documentName ) {
		activeDocument = loadedDocuments.item( documentName );
	}

	function _EventHandler( theEvent ) {
		if( theEvent.getEventType ) { //if we using frames, instanceof doesnt work
			if( theEvent.getEventType() == "TabActivationEvent" ) {
				var theDocument = theEvent.getTab().getObjectToSelect();
				logger.trace( "'TabActivationEvent' was received.", theDocument.getDocumentName() );
				_ActivateDocument(theDocument.getDocumentName());
			} else if(theEvent.getEventType() == "TabClosedEvent") {
				var theDocument = theEvent.getTab().getObjectToSelect();
				logger.trace( "'TabCloseEvent' was received.", theDocument.getDocumentName() );
				_UnLoadDocument( theDocument.getDocumentName() );
			}
		}else if( theEvent instanceof ViewActivationEvent ) { // a Document dobja, kell legyen benne document
			logger.trace( "'ViewActivationEvent' was received." );
			if( menuWidget != null ) {
				var theView = theEvent.getDocumentView();
				var documentName = theEvent.getDocument().getDocumentName();
				var documentListedType = theEvent.getDocument().getListedDocumentType();
				var documentNameToShow = theEvent.getDocument().getNameToShow();

				menuWidget.addCompositMenu(documentName,_GetText(documentNameToShow),true,1);//0,1,2,...
				menuWidget.addSubMenuToCompositMenu(documentName, "CloseActiveDocumentCommand", _GetText("CloseActiveDocumentCommand"), new CloseActiveDocumentCommand());
				switch(theView.getClassName()) {
					case 'NativeView':
						break;
					case 'BrowseView':
						menuWidget.addSubMenuToCompositMenu(documentName, "ShowEditSelectedDocument", _GetText("ShowEditSelectedDocument"), null);
						var typeList = new Array(); typeList[0]=documentListedType;
						var createNDC = new CreateNewDocumentCommand(typeList);
						menuWidget.addSubMenuToCompositMenu(documentName, "CreateNewDocument", _GetText("CreateNewDocument"),createNDC);
						menuWidget.addSubMenuToCompositMenu(documentName, "DeleteSelectedDocument", _GetText("DeleteSelectedDocument"), null);
						break;
					case 'PrintView':
						menuWidget.addSubMenuToCompositMenu(documentName, "Print", _GetText("Print"), null);
						break;
					case 'EditableHtmView':
						break;
					case 'CustomFormView':
						menuWidget.addSubMenuToCompositMenu(documentName,"Save", _GetText("Save"),null);
						menuWidget.addSubMenuToCompositMenu(documentName,"Cancel", _GetText("Cancel"),null);
						break;
					default:
				}

				var menus = theView.getPossibleMenus();
				menus.moveFirst();
				var aMenu;
				while(aMenu = menus.getNext()) {
					menuWidget.addSubMenuToCompositMenu(documentName,aMenu.name,_GetText(aMenu.name),aMenu.command);
				}
			}
		} else
		if( theEvent instanceof ViewDeActivationEvent ) { // a Document dobja, kell legyen benne document
			logger.trace( "'ViewDeActivationEvent' was received." );
			if( menuWidget != null ) {
				var theDocument = theEvent.getDocument();
				menuWidget.removeCompositMenu(theDocument.getDocumentName());
			}
		}
	}

	function _ChangeCaptions(aController) {
		if(documentSelector.changeCaptions != null)
			documentSelector.changeCaptions(aController);
		if(invTabWidget != null && invTabWidget.changeCaptions != null)
			invTabWidget.changeCaptions(aController);

		if(activeDocument == null || (activeDocument.mayDeActivate && !activeDocument.mayDeActivate())) return false;
		else documentSelector.activateTab(activeDocument.getDocumentName());
	}
}