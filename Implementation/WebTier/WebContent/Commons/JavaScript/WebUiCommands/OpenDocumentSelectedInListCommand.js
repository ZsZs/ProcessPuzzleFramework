// OpenDocumentSelectedInListCommand.js

// OpenDocumentSelectedInListCommand
function OpenDocumentSelectedInListCommand( ) {
	//check parameter assertions

	// inherit from WebUiCommand to make this object a specialized command
  	inheritFrom(this, new WebUiCommand( )); 

	//private instance variables
	var self = this;
	
	//public accessors methods
	this.execute = _Execute;
	
	//public mutators methods

	//private methods
	function _Execute () {

		var controller = self.getController();
		
		if (controller) {
			var view = controller.getDocumentManager().getActiveDocument().getActiveView();
			if (view.getClassName() == "BrowseView") {
				var selectedID = view.getSelectedId();
				//...
			}
		}
	}
}