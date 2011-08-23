// MoveArtifactCommand.js

// newFunction
function MoveArtifactCommand() {

	this.execute = _Execute;
	
	function _Execute(){
		var fmw = new FolderManagerWidget("NewArtifact",0,webUIController);
		fmw.setFolderTreeDefFileName("./CommandControllerServlet?action=RetrieveArtifactFolderStructure");
		fmw.setTransformationDefFileName("JavaScript/TreeWidget/AFSConverterToTWT_artifacts.xsl");
		fmw.setTransformationDefFileName2("JavaScript/TreeWidget/AFSConverterToTWT_artifactFolders.xsl");
		fmw.setPictureFolder("ModalWindow/");
		fmw.setCssFolder("ModalWindow/");
//		fmw.setEventHandler(myEventHandler);
		fmw.moveNode();
	}
}