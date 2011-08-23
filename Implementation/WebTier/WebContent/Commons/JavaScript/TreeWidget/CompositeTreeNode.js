var CompositeTreeNode = new Class({
	Extends : TreeNode,
	
	options: {
		state : this.CLOSED_STATE
	},

	//Constructor
	initialize: function( nodeId, nodeType, webUiController, options ) {
		this.options.state = nodeType.CLOSED_STATE;
		this.parent( nodeId, nodeType, webUiController, options );
		this.childs = new Array();
	},

	//public accessor and mutator methods
	appendChild : function( childNode ) {
		childNode.parentNode = this;

		if( this.childs.length != 0 ) {
			childNode.prevNode = this.childs[this.childs.length - 1];
			this.childs[this.childs.length - 1].nextNode = childNode;
		}	
		
		this.childs[this.childs.length] = childNode;
		return 1;
	},
	
	collapse : function() {
		this.childs.each( function( childNode, serialNumber ){
			childNode.hide();
		});
		this.options.state = this.nodeType.getStateNameWhenClosed();
	},

	expand : function() {
		if( this.parentNode && !this.parentNode.isVisible() ) 
			throw new IllegalMethodCallException( "Node '" + this.nodeID + "' can't be expanded if parent is invisible." ); 
		
		this.childs.each( function( childNode, serialNumber ){
			childNode.show();
		});
		this.options.state = this.nodeType.getStateNameWhenOpened();
	},

	findChildNodeByName : function( name ) {
		for( i = 0; i < childs.length; i++ ) {
			if( this.childs[i].getName() == name ) 
				return this.childs[i];
		}
		return null;
	},
	
	findNodeByPath : function(  path ) {
		if( path.indexOf( this.NODE_PATH_SEPARATOR ) > 0 ) {
			var nodeName = path.substring( 0, path.indexOf( this.NODE_PATH_SEPARATOR ));
			var childNode = this.findChildNodeByName( nodeName );
			if( childNode != null )
				return this.childNode.findNodeByPath( path.substring( path.indexOf( this.NODE_PATH_SEPARATOR ) +1 ));
			else return null;
		} else return this.findChildNodeByName( path );
	},

	onFolderClickHandler : function( theEvent ) {
		switch( this.options.state ) {
		case CLOSED_STATE:
			state = OPENED_STATE;
			_expand();
			break;
		case OPENED_STATE:
			state = CLOSED_STATE;
			_Collapse();
			break;
		}
	},

	show : function() {
		this.parent();
		
		if( this.options.state == this.nodeType.OPENED_STATE ){
			this.childs.each( function( childNode, serialNumber ){
				childNode.show();
			}, this );
		}
	},
	
	removeChild : function( childNode ) {
		var found = false;
		for( var i=0; i < this.childs.length; i++ ) {
			if( found ) { this.childs[i] = this.childs[i + 1]; }
			if( this.childs[i] == childNode ) {
				if( i == ( this.childs.length - 1 )) this.childs[i] = null;
				else this.childs[i] = this.childs[i + 1];
				found = true;
			}
		}
		if( found ) this.childs.length = this.childs.length-1;
		return found;
	},
	
	//Properties
	getChildCount : function() { return this.childs.length; },
	getChilds : function() { return this.childs; },
	getFirstChild : function() { if(this.hasChilds()) return this.childs[0]; return null; },
	getLastChild : function() { if(this.hasChilds()) return this.childs[this.childs.length-1]; return null; },
	getState : function() { return this.options.state; },
	hasChilds : function() { return (this.childs.length > 0); },
	
	//private methods
	appendNodeImage : function() {
		this.parent();
		this.nodeImageElement.set( "src", this.nodeType.determineNodeImage( this.options.state ) );
		this.nodeImageElement.addEvent( 'click', this.onFolderClickHandler );
	}.protect(),
	
	determineLineIntersectionImage : function() {
		if( this.hasNext() )
			return this.nodeType.getLineImageWhenHasNext( this.getState() );
		else
			return this.nodeType.getLineImageWhenLast( this.getState() );
	}.protect()

});


/*
state = CLOSED_STATE;
if(type == folder) {
	folderPicElement.set( "src", pictureFolder + imageClosed);
		if(!nextNode)
			if(!prevNode)
				if(self != rootNode)
					if(parentNode == rootNode && !rootNode.isVisible())
						selectPicElement.set( "src", pictureFolder+dPlus_last_no_rootPicture);
					else
						selectPicElement.set( "src", pictureFolder+dPlus_lastPicture);
				else 
					selectPicElement.set( "src", pictureFolder+dPlus_nolinesPicture);
			else
				selectPicElement.set( "src", pictureFolder+dPlus_lastPicture);
		else
			if(!prevNode)
				if (parentNode == rootNode && !rootNode.isVisible())
					selectPicElement.set( "src", pictureFolder+dPlus_no_rootPicture);
				else
					selectPicElement.set( "src", pictureFolder+dPlusPicture);
			else
				selectPicElement.set( "src", pictureFolder+dPlusPicture);
}

for(i=0; i < childs.length; i++) {
	childs[i].hide();
}
*/