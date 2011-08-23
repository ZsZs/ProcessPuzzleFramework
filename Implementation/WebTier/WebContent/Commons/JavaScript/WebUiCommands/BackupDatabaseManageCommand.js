// BackupDatabaseManageCommand.js

function BackupDatabaseManageCommand() {

	this.execute = _Execute;

	function _Execute() {
		var openedDocuments = webUIController.getDocumentManager().getDocumentSelector().tabCount();
	
		message = webUIController.getText("databaseAdminWarningMessage", "Please close all opened page to start Database Administration!");
		warningText = webUIController.getText("warningText", "Warning!");
		infoText = webUIController.getText("warningInfoText", " pages is still open!");

		if(openedDocuments > 0) {
			alert(warningText+" "+openedDocuments+" "+infoText);
			alert(message);
		} else
		{
			webUIController.loadDocument(null, "backupName", "./CommandControllerServlet?action=ShowDatabaseAdmin");
		}
	}
}