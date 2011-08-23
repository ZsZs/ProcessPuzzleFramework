// UserCommand.js

function UserManagementCommand() {

	this.execute = _Execute;

	function _Execute() {
		webUIController.loadDocument(null, "userManagementName", "./CommandControllerServlet?action=ShowUserManagement");
	}
}