/*** TreeNode class definition. ***/
var TreeNode = new Class({
	//Public constants
	NODE_PATH_SEPARATOR : '/',
	
	Implements: [Options],
		options: {
			selectable : false,
			target : '_blank',
			type : pageNodeType,
			url : null
	},
	
	//constructor
	initialize: function( nodeId, nodeType, webUiController, options ) {
		// parameter assertions
		AssertUtil.assertParamNotNull( nodeId, "nodeId");
		AssertUtil.assertParamNotNull( nodeType, "nodeType");
		AssertUtil.assertParamNotNull( webUiController, "webUiController");

		this.setOptions( options );

		//private instance variables
		this.caption = webUiController.getText( nodeId );
		this.captionContainer = null;
		this.captionTextNode = null;
		this.containerElement = null;	//this nodes div Element
		this.nodeImageElement = null;	//this nodes main picture Element (for the fast access)
		this.htmlDOMDocument = document;
		this.nextNode = null;
		this.nodeID = nodeId;
		this.nodeType = nodeType;
		this.parentNode = null;			//this TreeNode's parent TreeNode
		this.prevNode = null;
		this.rootNode = this;			//the root TreeNode
		this.self = this;					//used to make the object available for private methods
		this.selectPicElement = null;	//this nodes +/- picture Element (for the fast access)
		this.visible = false;
		this.webUiController = webUiController;
	},

	//public accessor and mutator methods
	bubbleUpNames : function( list, index ) {
		if( this == this.rootNode ) {
			this.widget.setSelectedNameList( list, index );
		} else {
			list[index] = this.name;
			this.parentNode.bubbleUpNames( list, index+1 );
		}
	},
	
	changeCaption : function() {
		this.caption = this.webUiController.getText( this.name );
		
		if( this.captionContainer != null && this.captionTextNode != null) {
			this.captionContainer.removeChild( this.captionTextNode );
			this.captionTextNode = document.createTextNode( this.caption );
			this.captionContainer.appendChild( this.captionTextNode );
		}
		
		for( var i=0; i < this.childs.length; i++ ) {
			this.childs[i].changeCaption( this.webUiController );
		}
	},
	
	determineLevel : function() {
		if( this.parentNode ) return this.parentNode.determineLevel() +1;
		else return 0;
	},
	
	determinePath : function( treePath ) {
		var treePath = treePath == null ? new TreePath() : treePath;
		treePath.insertNameInto( this.nodeID, this.determineLevel() );
		if( this.parentNode ) treePath = this.parentNode.determinePath( treePath );
		
		return treePath;
	},

	determineTrailingImage : function() {
		if( this.hasNext() )
			return this.nodeType.getTrailingImageWhenHasNext();
		else
			return this.nodeType.getTrailingImageWhenLast();
	},

	hide : function() {
		this.containerElement.removeClass( this.nodeType.getNodeClassWhenVisible() );
		this.containerElement.addClass( this.nodeType.getNodeClassWhenHidden() );
		this.visible = false;
	},
	
	removeDivElement : function() {
		var parent = this.containerElement.parentNode;
		parent.removeChild( this.containerElement );
	},
	
	show : function() {
		if( this.parentNode && !this.parentNode.isVisible() ) 
			throw new IllegalMethodCallException( "Node '" + this.nodeID + "' can't be shown if parent is invisible." ); 
			
		if( this.containerElement == null ) 
			this.buildHtmlRepresentation();
		else 
			this.containerElement.removeClass( this.nodeType.getNodeClassWhenHidden() );
		
		this.containerElement.addClass( this.nodeType.getNodeClassWhenVisible() );
		this.visible = true;
	},
	
	//Properties
	getCaption : function() { return this.caption; },
	getCaptionContainer : function() { return this.captionContainer; },
	getContainerElement : function() { return this.containerElement; },
	getID : function() { return this.nodeID; },
	getNodeImageElement : function() { return this.nodeImageElement; },
	getParentNode : function() { return this.parentNode; },
	getRootNode : function() { return this.rootNode; },	
	hasNext : function() { return !(this.nextNode == null); },
	isVisible : function() { return this.visible; },

	//private methods
	appendLineImage : function() {
		var lineImagElement = this.createImageElement( this.determineLineIntersectionImage() );
		this.containerElement.appendChild( lineImagElement );
	}.protect(),
	
	appendNodeCaption : function( nobrElement ) {
		this.captionContainer = new Element( "span" );
		
		if( this.options.selectable ) {
			this.captionContainer.addClass( this.nodeType.getCaptionClassWhenSelectable() );
			this.captionContainer.addEvent( this.onCaptionClickHandler );
		}else {
			this.captionContainer.addClass( this.nodeType.getCaptionClass() );
		}
		
		if( this.options.url ){
			var nodeLink = new Element( 'a' );
			nodeLink.set( 'href', this.options.url );
			nodeLink.set( 'target', this.options.target );
			nodeLink.addClass( this.nodeType.getCaptionLinkClass() );
			nodeLink.appendText( this.caption );
			this.captionContainer.appendChild( nodeLink );
		}else {
			this.captionContainer.appendText( this.caption );
		}
		
		this.containerElement.appendChild( this.captionContainer );
	}.protect(),

	appendNodeImage : function() {
		this.nodeImageElement = this.createImageElement( this.nodeType.determineNodeImage() );
		this.containerElement.appendChild( this.nodeImageElement );
	}.protect(),

	appendTrailingImages : function( nobrElement, thePrefix ) {
		var aParentNode = this.getParentNode();
		while( aParentNode ){
			trailingImageElement = this.createImageElement( aParentNode.determineTrailingImage() );
			this.containerElement.insertBefore( trailingImageElement, this.containerElement.getFirst() );
			aParentNode = aParentNode.getParentNode();
		}
	}.protect(),

	buildHtmlRepresentation : function() {
		this.createContainerElement();
		this.appendTrailingImages();
		this.appendLineImage();
		this.appendNodeImage();
		this.appendNodeCaption();
	}.protect(),

	createContainerElement : function() {
		this.containerElement = new Element( 'div' );
		this.containerElement.set( 'id', this.nodeID );
	}.protect(),
	
	createImageElement : function( imagePath ){
		var imageElement = new Element( 'img' );
		imageElement.set( 'src', imagePath );
		imageElement.addClass( this.nodeType.getNodeImageClass() );
		return imageElement;
	}.protect(),
	
	determineLineIntersectionImage : function() {
		if( this.hasNext() )
			return this.nodeType.getLineImageWhenHasNext();
		else
			return this.nodeType.getLineImageWhenLast();
	}.protect(),
	
	onCaptionClickHandler : function( theEvent ) {
		this.bubbleUpNames( new Array(), 0 );
	}.protect()

});

/*
	setContainerElement : function( element ) { this.containerElement = element; },
	setHtmlDOMDocument : function( newHtmlDOMDocument ) { this.htmlDOMDocument = newHtmlDOMDocument; },
	setNextNode : function( theNode ) { this.nextNode = theNode; },
	setParentNode : function( theParentNode ) { this.parentNode = theParentNode; this.rootNode = theParentNode.getRootNode(); },
	setPictureFolder : function( newPictureFolderName ) { this.pictureFolder = newPictureFolderName; },
	setPrevNode : function( theNode ) { this.prevNode = theNode; },
	setSelectable : function( logicalExpr ) { this.selectable = logicalExpr; if( this.options.url == null ) url = "javascript:void(0);"; },
	setToVisible : function() { this.visible = true; },
	
	addImageToSpan : function( spanTag, imageToAdd ) {
		var imgElement = new Element( "img" );
		imgElement.set( "src", pictureFolder + imageToAdd );
		imgElement.className = this.NODE_IMAGE_CLASS;
		spanTag.appendChild( imgElement );
	}.protect(),
	
	appendNobrElement : function( divElement ) {
		var nobrElement = new Element( "nobr" );
		divElement.appendChild( nobrElement );
		return nobrElement;
	}.protect(),

 */
