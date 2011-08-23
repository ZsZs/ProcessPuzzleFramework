
var TreeWidget = new Class({
	//public constants
	NODE_PATH_SEPARATOR : ".",
	
	Extends : BrowserWidget,
	
	// constructor
	initialize: function( domDocument, widgetContainerId, resourceBundle, locale ) {
	
		/***********************************************************************
		* Configuration variables.
		************************************************************************/
		this.showRootNode = false;			// Should the rootNode be displayed.
		this.documentID = window.location.href;	// This is IMPORTANT... use an unique id for each document you use the tree in. (else they'll get mixed up).
		this.rootNode = null;				// RootNode of the tree.
	
		/************************************************************************
		* The following are just instancevariables.
		************************************************************************/
		this.logger = null;
		this.states = '';
		this.statearray = new Array();
		this.isLoaded = false;
		this.isVisible = false;
		this.treeDefinitionFileName = "";
		this.sourceXmlFileName = "";
		this.sourceXML = null;
		this.treeDefinitionXML = null;
		this.transformationXslFileName = "";
		this.externalTreeDefinition = null;
		this.pictureFolder = "";
		this.resourceBundle = null;
		this.selectedNameList = null;
		this.selectedNameListSize = 0;

		//Initialization
		this.parent( domDocument, widgetContainerId, resourceBundle, locale );
		this.configureRootLogger();
		this.logger = log4javascript.getLogger( ROOT_LOGGER_NAME + ".treeWidget" );
		this.logger.debug( "New 'TreeWidget' was instantiated." );
	},
	
	//public accessor and mutator methods
	changeCaptions : function() {
		if( this.rootNode != null ) this.rootNode.changeCaption( this.controller );
	},
	
	clearContainerElement : function() {
		while( this.containerElement.hasChildNodes() ) 
			this.containerElement.removeChild( containerElement.childNodes[0] );
	},
	
	getSelectedNode : function() {
		if( this.getSelectedNameListSize() == 0 ) return null;
		return this.rootNode.findNodeByPath( this.getSelectedNodeFullCaption() );
	},
	
	getSelectedNodeFullCaption : function() {
		var list = this.getSelectedNameList();
		var index = this.getSelectedNameListSize();
		var selectedNodeCaption = "";
		while( index > 1 ) { 
			selectedNodeCaption += list[index-1] + this.NODE_PATH_SEPARATOR;
			index--;
		}
		selectedNodeCaption += list[0];
		return selectedNodeCaption;
	},
	
	getText : function ( key ) {
		if( this.resourceBundle != null ) {
			var value = null;
			try {
				value = this.resourceBundle.getText( key );
			}
			catch( e ) {
				value = key;
			}
			return value;
		}
		return key;
	},
	
	hideWidget : function() {
		if( this.rootNode != null ) {
			this.rootNode.hide();
			this.isVisible = false;
		}
	},
	
	loadWidget : function() {
		var treeDefinition = this.getTreeDefinitionXML();
		this.removeWhitespaceNodesFromXML( treeDefinition );
		var rootXmlElements = treeDefinition.getElementsByTagName("RootNode");
		if( rootXmlElements ) {
			this.rootNode = new TreeNode( 1,"RootNode","RootNode","folder","opened" );
			this.rootNode.setHtmlDOMDocument( this.htmlDOMDocument );
			this.rootNode.setPictureFolder( this.pictureFolder );
			this.rootNode.widget = self;
			if( rootXmlElements[0].getAttribute( "visible" ) == "true" ) this.rootNode.setToVisible();
			this.rootNode.setContainerElement(containerElement);
			this.rootNode.hide();
			this.loadNodeFromXML(rootXmlElements[0], rootNode);
			this.makeWidgetTree();
		}
	},
	
	showWidget : function() {
		if( this.rootNode != null ) {
			this.rootNode.show();
			this.isVisible = true;
		}
	},
	
	removeWidgetTree : function() {
		if( this.rootNode != null ) {
			var rootChilds = this.rootNode.getChilds();
			for( var i=0; i < rootChilds.length; i++) {
				var child = rootChilds[i];
				child.removeDivElement();
			}
			this.rootNode = null;
		}
	},
	
	//Properties
	getSelectedNameList : function() { return this.selectedNameList; },
	getSelectedNameListSize : function() { return this.selectedNameListSize; },
	getShowRootNode : function() { return this.showRootNode; },
	getSourceXML : function() { return this.sourceXML; },
	getTreeDefinitionXML : function() { return this.treeDefinitionXML; },
	isLoaded : function() { return this.isLoaded;},
	isVisible : function() { return this.isVisible; },

	setHtmlDOMDocument : function( newHtmlDOMDocument ) { htmlDOMDocument = newHtmlDOMDocument; },
	setResourceBundle : function( newResourceBundle ) { resourceBundle = newResourceBundle; },
	setShowRootNode : function (value) { showRootNode = value; },
	setDefinitionFileName : function ( fileName ) { treeDefinitionFileName = fileName; },
	setSourceXmlFileName : function ( fileName ) { sourceXmlFileName = fileName; },
	setTransformationXslFileName : function ( fileName ) { transformationXslFileName = fileName; },
	setExternalTreeDefinition : function ( extTreeDef ) { externalTreeDefinition = extTreeDef; },
	setPictureFolder : function ( newPictureFolderName ) { pictureFolder = newPictureFolderName; },
	setSelectedNameList : function( list, size ) { 
			selectedNameList = list; 
			selectedNameListSize = size;
			if( this.functionInSelect != null ) this.functionInSelect();
	},
	
	//private methods
	createSpanTagForPrefix : function() {
		return htmlDOMDocument.createElement("span");
	},

	getTreeDefinitionXML : function() {
		if(treeDefinitionFileName != null && treeDefinitionFileName !="")
			return new XmlResource( treeDefinitionFileName );
		else
		if(sourceXmlFileName != null && sourceXmlFileName != "" &&
			transformationXslFileName != null && transformationXslFileName != "") {
			var transformator = new XMLTransformator( sourceXmlFileName, transformationXslFileName );
			treeDefinitionXML = transformator.transform();
			sourceXML = transformator.getSourceXML();
			return treeDefinitionXML;
		}
		else if( externalTreeDefinition != null && externalTreeDefinition != "" ) return externalTreeDefinition;
		else throw new UndefinedTreeDefinitionException();
	},

	//Adds a series of nodes to a given tree node based on xml tree definition
	loadNodeFromXML : function( xmlElement, treeNodeToAdd ) {
		for(var n = 0; n < xmlElement.childNodes.length; n++) { //a "var"-ok fontosak a rekurzio miatt!!!
			var element = xmlElement.childNodes[n];
			if (element.attributes!=null) {
				var theUrl = element.getAttribute("url");
				var caption = element.getAttribute("caption");
				var captionText = this.getText( caption );
				var childNode = new TreeNode( element.getAttribute("nodeId"), caption, captionText, element.getAttribute("type"), element.getAttribute("state"), theUrl, element.getAttribute("target"), element.getAttribute("image"), element.getAttribute("image2"));
				childNode.setHtmlDOMDocument(htmlDOMDocument);
				childNode.setPictureFolder(pictureFolder);
				if (element.getAttribute("selectable")=="true") childNode.setSelectable(true);
				treeNodeToAdd.addChild(childNode);
			}
			if( element.hasChildNodes ) this.loadNodeFromXML( element, childNode );
		}
		return;
	},

	makeWidgetTree : function() {
		if (rootNode!=null) {
			if(rootNode.isVisible()) 
				rootNode.make(null);
			var prefix = createSpanTagForPrefix();
			for(i=0; i<rootNode.getChildCount(); i++) { rootNode.getChilds()[i].make(prefix); }
		}
	}
});