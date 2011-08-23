var RootTreeNode = new Class({
	Extends : CompositeTreeNode,
	
	//Constructor
	initialize: function( nodeId, nodeType, webUiController, options ) {
		this.parent( nodeId, nodeType, webUiController, options );

		//private instance variables
		var parent = null;
		var widgetElement = null;						// document element where the tree widget should placed
		var treeWidget = null;
	},
	
	//public accessor and mutator methods
	show : function() {
		if(widgetElement) {
			if(treeWidget) {
				if (treeWidget.getShowRootNode()) {return parent.show(); }
			}
			else {
				exception = new UserException("The tree widget is not defined.", "RootNode.show()", "UndefinedWidget");
				throw exception;
			}
		}
		else {
			exception = new UserException("The tree widget is not difined.", "RootNode.show()", "UndefinedWidget");
			throw exception;
		}
	},
	
	//Properties
	getWidgetElement : function () { return widgetElement; },
	setTreeWidget : function( widget ) { treeWidget = widget; }, 
	setWidgetId : function( id ) {
		widgetElement = document.getElementById(id);
		if (!widgetElement) {
			exception = new UserException("Can't find the given: " + id + " id in the document." , "RootNode.setWidgetId()", "UndefinedWidgetId");
			throw exception;
		}
	}

});