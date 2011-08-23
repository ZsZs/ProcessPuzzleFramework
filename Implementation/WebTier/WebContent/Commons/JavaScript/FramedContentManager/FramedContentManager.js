//Manages content pages within a framed context.
// - Enhances URL when a content pages is loaded. 
// - Loads content based on enhanced URL parameters.

var FramedContentManager = new Class.Singleton({
	Implements: [Log, Options], 
	options: {
		contentFrameName : "contentFrame",
		menuItemElmentTagName : "LI",
		selectedMenuItemClass : "selectedMenuItem",
		stateRestoreDelay : 2000,
		urlRefreshPeriod : 5000
	},
	
	//constructor
	initialize: function( options ) {
		this.setOptions( options );

		//Private instance variables
		this.frameSet = window.top;
		this.contentFrame = window.top.frames[this.options.contentFrameName];
		this.framesetUrl = this.substringBeforeLast( this.substringBeforeLast( this.frameSet.document.URL, "#" ), "?");
		this.contextRoot = this.determineContextRoot( this.framesetUrl );
		this.frameSetName = this.framesetUrl.substring( this.contextRoot.length );
		this.recentHash = null;
		this.storeStateInUrl.periodical( this.options.urlRefreshPeriod, this );
		this.isInitialized = true;
		this.enableLog().log('FramedContentManager is initialized.');
	},
		
	//Public accessor and mutator methods
	deselectCurrentMenuItem: function( givenDocument ) {
		var subjectDocument = givenDocument == null ? document : givenDocument;
		var menuItems = subjectDocument.getElements( this.options.menuItemElmentTagName + "." + this.options.selectedMenuItemClass );
		var currentMenuItem = menuItems[0];
		currentMenuItem.toggleClass( this.options.selectedMenuItemClass ); 
	},

	determineContextRoot : function( aFramesetUrl ) {
		var urlDelimiter = "/";
		if( aFramesetUrl.indexOf( "file:" ) >= 0 && aFramesetUrl.indexOf( "\\" ) > 0 ) urlDelimiter = "\\";
		var truncatedUrl = aFramesetUrl;
		if( aFramesetUrl.indexOf( "#" ) > 0 ) truncatedUrl = aFramesetUrl.substring( 0, aFramesetUrl.indexOf( "#" ));
		return truncatedUrl.substring( 0, truncatedUrl.lastIndexOf( urlDelimiter ) +1);
	},
	
	determinePageName : function( aUrl ) {
		var urlWithoutState = this.determineRealUrl( aUrl );
		var pageName = this.substringAfterLast( urlWithoutState, this.contextRoot );
		return pageName;
	},
	
	determineRealUrl : function( anEnhancedUrl ) {
		var indexOfHashMark = anEnhancedUrl.indexOf( "#" );
		if( indexOfHashMark > 0 ) return this.contextRoot + anEnhancedUrl.substring( indexOfHashMark + 1 );
		else return anEnhancedUrl;
	},
	
	determineUrl : function( pageName ) {
		var strippedPageName = pageName.indexOf( "/" ) == 0 ? pageName.substring( 1 ) : pageName;
		return this.contextRoot + pageName;
	},
		
	enhanceUrl : function( subjectDocument ) {
		this.log( 'EnhanceUrl of document: ' + subjectDocument.URL );
		if( subjectDocument.URL.indexOf( this.contextRoot ) == 0 ) {	//Check if the given document is within the context
			this.recentHash = subjectDocument.URL.substring( this.contextRoot.length );
			if( this.recentHash != "" ) {
				var enhancedUrl = this.framesetUrl + "#" + this.recentHash;
				window.top.location.hash = this.recentHash;
				return enhancedUrl;
			} 
		}
		
		throw new OutOfContextException( subjectDocument.url, this.framesetUrl );
	},
	
	loadContentPage : function( contentPageName ) {
		var contentPageUrl = this.determineUrl( contentPageName );
		this.contentFrame.location.assign( contentPageUrl );
	},
	
	redirectToFramedUrl : function() {
		var relativePath = null;
		var contextRoot = null;
		var contextRootStartPosition = document.location.href.indexOf( "Hungarian") ;
		if( contextRootStartPosition == -1 ) {
			contextRootStartPosition = document.location.href.indexOf( "English" );
			if( contextRootStartPosition != -1 ) contextRootStartPosition += 8;
		}
		else contextRootStartPosition += 10;

		if( contextRootStartPosition >= 0 ) {
			contextRoot = document.location.href.substring( 0, contextRootStartPosition );
			relativePath = document.location.href.substring( contextRootStartPosition );
			window.top.location.assign( contextRoot + this.frameSetName + relativePath );
		}
	},
	
	restoreStateFromUrl : function(){
		this.log( 'Restore state form Url: ' + window.top.location.href );
		if( window.top.location.hash != this.recentHash ) {
			var realUrl = this.determineRealUrl( window.location.href );
			if( realUrl != null) {
				window.top.frames[this.options.contentFrameName].location.assign( realUrl );
				this.recentHash = window.top.location.hash; 
			}
		}
	},
	
	selectMenuItem : function( menuItemIdToSelect, menuContainerDocument ) {
		var subjectDocument = menuContainerDocument == null ? document : menuContainerDocument;
		var menuItemElementToSelect = subjectDocument.getElementById( menuItemIdToSelect );
		menuItemElementToSelect = $(menuItemElementToSelect);
		menuItemElementToSelect.addClass( this.options.selectedMenuItemClass );
	},

	selectMenuItemAndLoadPage : function( menuItemToSelect, pageToLoad, menuContainerDocument, frameName ) {
		var frameToUse = frameName ? frameName : this.options.contentFrameName;
		this.swapMenuItemSelection( menuItemToSelect, menuContainerDocument );
		this.loadContentPage( pageToLoad, frameToUse );
	},
	 
	storeStateInUrl : function() {
		return this.enhanceUrl( window.top.frames[this.options.contentFrameName].document );
	},
	
	swapMenuItemSelection : function( menuItemToSelect, menuContainerDocument ) {
		this.deselectCurrentMenuItem( menuContainerDocument );
		this.selectMenuItem( menuItemToSelect, menuContainerDocument );	
	},
	
	substringAfterLast : function( subjectString, separator ){
		var lastOccurenceOfSeparator = subjectString.lastIndexOf( separator, 0 ); 
		if( lastOccurenceOfSeparator >= 0 ){
			return subjectString.substring( lastOccurenceOfSeparator + separator.length );
		}else return subjectString;
	},
		
	substringBeforeLast : function( subjectString, separator ){
		var lastOccurenceOfSeparator = subjectString.lastIndexOf( separator ); 
		if( lastOccurenceOfSeparator >= 0 ){
			return subjectString.substring( 0, lastOccurenceOfSeparator );
		}else return subjectString;
	},
		
	//Properties
	getFrameSetUrl : function() { return this.framesetUrl; },
	getContextRoot : function() {return this.contextRoot;},
	getContentFrameName : function() { return this.options.contentFrameName; }
}); 

function OutOfContextException ( url, context ) {
	this.error = new Error("The given url: '" + url + "' is out of context: '" + context + "'.");
   this.error.name = "OutOfContextException";
}

function UninitializedContentManagerException ( message ) {
	this.error = new Error("FramedContentManager is uninitialized. " + message );
	this.error.name = "UninitializedContentManagerException";
}