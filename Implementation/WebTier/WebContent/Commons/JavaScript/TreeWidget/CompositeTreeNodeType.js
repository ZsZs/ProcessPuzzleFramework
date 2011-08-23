var CompositeTreeNodeType = new Class ({
	Extends : TreeNodeType,
	
	//states
	CLOSED_STATE: "closed",
	OPENED_STATE: "opened",

	options: {
		lineImageWhenHasNextAndOpened : 'minus.gif',
		lineImageWhenHasNextAndClosed : 'plus.gif',
		lineImageWhenLastAndOpened : 'minus_last.gif',
		lineImageWhenLastAndCloseed : 'plus_last.gif'
	},
	
	//Constructor
	initialize: function( name, nodeImage, imageOpen, options ) {
		this.parent( name, nodeImage, options );
		this.imageOpen = imageOpen;
		this.options.imageClass = 'nodeimagefolder'; 
	},
	
	//public accessor and mutator methods
	determineNodeImage : function( currentState ) {
		var stateDependentImage = currentState == this.OPENED_STATE ? this.imageOpen : this.nodeImage;
		return this.options.imagesFolder + stateDependentImage;
	},
	
	//Properties
	getLineImageWhenLast : function( nodeState ) { 
		if( nodeState == this.getStateNameWhenClosed() )
			return this.getImagesFolder() + this.options.lineImageWhenLastAndCloseed; 
		else
			return this.getImagesFolder() + this.options.lineImageWhenLastAndOpened; 
	},
	
	getLineImageWhenHasNext : function( nodeState ) { 
		if( nodeState == this.getStateNameWhenClosed() )
			return this.getImagesFolder() + this.options.lineImageWhenHasNextAndClosed; 
		else
			return this.getImagesFolder() + this.options.lineImageWhenHasNextAndOpened; 
	},
	
	getStateNameWhenClosed : function() { return this.CLOSED_STATE; },
	getStateNameWhenOpened : function() { return this.OPENED_STATE; }
});

/*** TreeNodeType instances ***/
folderNodeType = new CompositeTreeNodeType( "folder", "folder_closed.gif", "folder_open.gif" );
userNodeType = new TreeNodeType( "user", "user_16x16.gif" );
pageNodeType = new TreeNodeType( "page", "page16x16.gif" );
helpNodeType = new TreeNodeType( "help", "help_16x16.gif" );
