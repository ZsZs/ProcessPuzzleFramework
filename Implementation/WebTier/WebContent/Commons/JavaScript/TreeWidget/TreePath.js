var TreePath = new Class({
	NODE_PATH_SEPARATOR : '/',
	
	initialize : function() {
		this.nameList = new Array();
	},
	
	//public accessor and mutator methods
	getPath : function() {
		var path = "";
		this.nameList.each( function( nodeName, index ) {
			path += nodeName + this.NODE_PATH_SEPARATOR;
		}, this );
		
		return path;
	},

	insertNameInto : function( nodeName, nodeLevel ) {
		this.nameList[nodeLevel] = nodeName;
	}
	
});
