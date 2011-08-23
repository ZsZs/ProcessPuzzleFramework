/*** TreeNodeType class definition ***/

var TreeNodeType = new Class({
	//default images
	dClosedPicture: "folder_closed.gif",
	dOpenedPicture: "folder_open.gif",
	dHelpPicture: "help_16x16.gif",
	dPagePicture: "page16x16.gif",
	dUserPicture: "user_16x16.gif",

	//node images
	dMinusPicture: "minus.gif",
	dPlusPicture: "plus.gif",
	dMinus_lastPicture: "minus_last.gif",
	dPlus_lastPicture: "plus_last.gif",

	//line images
	dMinus_nolinesPicture: "minus_nolines.gif",
	dMinus_no_rootPicture: "minus_no_root.gif",
	dMinus_last_no_rootPicture: "minus_last_no_root.gif",
	dPlus_nolinesPicture: "plus_nolines.gif",
	dPlus_no_rootPicture: "plus_no_root.gif",
	dPlus_last_no_rootPicture: "plus_last_no_root.gif",
	dT_no_rootPicture: "t_no_root.gif",

	Implements: [Options],
	options: {
		captionClass : 'treetitle',
		captionClassWhenSelectable : 'selectedHover', 
		captionLinkClass : 'treetitleurl', 
		imageClass : 'nodeimage',
		imagesFolder : 'images/',
		lineImageWhenHasNext : 't.gif',
		lineImageWhenLast : 'lastnode.gif',
		nodeClassWhenHidden : 'hidden', 
		nodeClassWhenVisible : 'trigger',
		trailingImageWhenBlank : 'white.gif',
		trailingImageWhenHasNext : 'line.gif'
	},

	//Constructor
	initialize: function( name, nodeImage, options ) {
		this.setOptions( options );
		this.name = name;
		this.nodeImage = nodeImage;
	},
	
	//public accessor and mutator methods
	determineNodeImage : function() {
		return this.options.imagesFolder + this.nodeImage;
	},
	
	//Properties
	getCaptionClass : function() { return this.options.captionClass; },
	getCaptionClassWhenSelectable : function() { return this.options.captionClassWhenSelectable; },
	getCaptionLinkClass : function() { return this.options.captionLinkClass; },
	getImagesFolder : function() { return this.options.imagesFolder; },
	getLineImageWhenLast : function() { return this.getImagesFolder() + this.options.lineImageWhenLast; },
	getLineImageWhenHasNext : function() { return this.getImagesFolder() + this.options.lineImageWhenHasNext; },
	getNodeClass : function( isVisible ) { isVisible ? this.options.nodeClassWhenVisible : this.options.nodeClassWhenHidden; },
	getNodeClassWhenHidden : function() { return this.options.nodeClassWhenHidden; },
	getNodeClassWhenVisible : function() { return this.options.nodeClassWhenVisible; },
	getNodeImageClass : function() { return this.options.imageClass; },
	getTrailingImageWhenLast : function() { return this.getImagesFolder() + this.options.trailingImageWhenBlank; },
	getTrailingImageWhenHasNext : function() { return this.getImagesFolder() + this.options.trailingImageWhenHasNext; }
});