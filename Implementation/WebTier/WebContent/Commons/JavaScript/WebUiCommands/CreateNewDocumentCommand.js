// CreateNewDocumentCommand.js

// CreateNewDocumentCommand
function CreateNewDocumentCommand(theTypeList,  theController, theStylesPath ) {
	//check parameter assertions

	// inherit from WebUiCommand to make this object a specialized command
  	inheritFrom(this, new WebUiCommand( theController ));
  	inheritFrom(this, new ModalWindowBase( theStylesPath ));

	//private instance variables
	var typeList = theTypeList;
	var documentType;
	var self = this;

	//public accessors methods
	this.execute = _Execute;

	//public mutators methods

	//private methods

	function _EventHandler(selectedFolder,newName) {
		var commandLine = "./CommandControllerServlet?action=CreateArtifact"
		commandLine += "&artifactTypeName="+documentType;
		commandLine += "&artifactName="+newName;
		commandLine += "&artifactFolderName="+selectedFolder;
		//alert("processing: "+commandLine);
		var xmlError=false;
		try { xmlFile = new XmlResource( commandLine ); }
		catch(e) {xmlError=true;}
		if (!xmlError) {
			var resultTag = xmlFile.getElementsByTagName("actionOutcomeStatus")[0];
			if (resultTag!=null) {
				var result = resultTag.firstChild.nodeValue;
				if(result == "false") alert(xmlFile.getElementsByTagName("errorDescription")[0].firstChild.nodeValue);
				else self.getController().loadDocument(documentType,newName);
			} else alert("internal error.");
		} else alert("Internal error(example:not logged in,...).");
	}

	function _Execute () {
		if(typeList==null || !typeList.length || typeList.length==0) return;
		//if typeList.length>0 then ....
		documentType = typeList[0];
		var fmw = new FolderManagerWidget("NewArtifact",0,self.getController());
		fmw.setSeparator("/");
		fmw.setFolderTreeDefFileName("./CommandControllerServlet?action=RetrieveArtifactFolderStructure");
		fmw.setTransformationDefFileName("./JavaScript/TreeWidget/AFSConverterToTWT_artifactFolders.xsl");
		fmw.setPictureFolder("./ModalWindow/");
		fmw.setCssFolder("./ModalWindow/");
		fmw.setEventHandler(_EventHandler);
		fmw.createNewNode();
	}
}