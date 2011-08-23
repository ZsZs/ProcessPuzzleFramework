// DeleteArtifactCommand.js

// newFunction
function DeleteArtifactCommand( hideFolderManager, artifactId ) {
	var m_hideFolderManager = hideFolderManager;
	var m_artifactId = artifactId;

	this.execute = _Execute;
	
	function _Execute(){
		if( hideFolderManager ) {
			AssertUtil.assertParamNotNull( m_artifactId, "artifactId");
			var commandLine = "./CommandControllerServlet?action=DeleteArtifact&artifactId=" + m_artifactId;
			var xmlError = false;
			try { xmlFile = new XmlResource( commandLine ); }
			catch(e) { xmlError = true; }
			if ( !xmlError ) {
				var resultTag = xmlFile.getElementsByTagName("actionOutcomeStatus")[0];
				if ( resultTag != null ) {
					var result = resultTag.firstChild.nodeValue;
					if( result == "false" ) alert(xmlFile.getElementsByTagName("errorDescription")[0].firstChild.nodeValue);
				} else alert("Internal error occured during 'DeleteArtifactCommand' execution.");
			} else alert("Internal error.");
		}else {
			var fmw = new FolderManagerWidget("NewArtifact",0,webUIController);
			fmw.setFolderTreeDefFileName("./CommandControllerServlet?action=RetrieveArtifactFolderStructure");
			fmw.setTransformationDefFileName("JavaScript/TreeWidget/AFSConverterToTWT_artifacts.xsl");
			fmw.setPictureFolder("ModalWindow/");
			fmw.setCssFolder("ModalWindow/");
	//		fmw.setEventHandler(myEventHandler);
			fmw.deleteNode();			
			self.closeModalWindow();
		}
	}
}