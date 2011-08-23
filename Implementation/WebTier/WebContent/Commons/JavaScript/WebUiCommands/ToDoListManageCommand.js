// ToDoListManageCommand.js

// ToDoListManageCommand
function ToDoListManageCommand() {
	//check parameter assertions

	// inherit from WebUiCommand to make this object a specialized command
  	inheritFrom(this, new WebUiCommand());

	//private instance variables
	toDoListName = "ToDoListName";
	var self = this;

	//public accessors methods
	this.execute = _Execute;

	//public mutators methods

	//private methods
	function _Execute(isOn) {

		var controller = self.getController();
		if(controller != null) {

			if(isOn != null && isOn == true) {
				controller.loadInfoPanelDocumentByName('PersonalTodoList', toDoListName);
			} else 
			{
				var documentSelector = controller.getInfoPanelManager().getDocumentSelector();
				var activeDocument = controller.getInfoPanelManager().getActiveDocument();
				if(activeDocument != null && documentSelector != null) {
					if(activeDocument.getDocumentName() == toDoListName) documentSelector.onCloseClick();
					else
					{
						documentSelector.removeTab(toDoListName);
						controller.getInfoPanelManager().unLoadDocument(toDoListName);
					}
				}
			}
		}
	}
}