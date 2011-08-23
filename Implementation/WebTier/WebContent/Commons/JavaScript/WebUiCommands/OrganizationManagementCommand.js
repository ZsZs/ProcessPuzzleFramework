// OrganizationManagementCommand.js

function OrganizationManagementCommand() {

	this.execute = _Execute;

	function _Execute() {
		webUIController.loadDocument(null, "organizationManagementName", "./CommandControllerServlet?action=ShowOrganizationManagement");
	}
}