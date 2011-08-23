// UndoMaintenanceCommand.js

function UndoMaintenanceCommand() {

	this.execute = _Execute;

	function _Execute() {
		webUIController.loadDocument(null, "undoMaintenanceName", "./CommandControllerServlet?action=ShowUndoMaintenance");
	}
}