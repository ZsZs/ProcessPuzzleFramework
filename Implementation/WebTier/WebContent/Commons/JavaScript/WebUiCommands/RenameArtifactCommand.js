// RenameArtifactCommand.js

// newFunction
function RenameArtifactCommand() {

	this.execute = _Execute;
	
	function _Execute(){
		var fmw = new FolderManagerWidget("NewArtifact",0,webUIController);
		fmw.setFolderTreeDefFileName("./CommandControllerServlet?action=RetrieveArtifactFolderStructure");
		fmw.setTransformationDefFileName("JavaScript/TreeWidget/AFSConverterToTWT_artifacts.xsl");
		fmw.setPictureFolder("ModalWindow/");
		fmw.setCssFolder("ModalWindow/");
//		fmw.setEventHandler(myEventHandler);
		fmw.renameNode();
	}
}