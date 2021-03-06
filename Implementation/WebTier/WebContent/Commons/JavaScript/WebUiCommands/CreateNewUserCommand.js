// CreateNewUserCommand.js

// CreateNewUserCommand
function CreateNewUserCommand( theController, theStylesPath ) {
	//check parameter assertions

	// inherit from WebUiCommand to make this object a specialized command
  	inheritFrom(this, new WebUiCommand(theController));
  	inheritFrom(this, new ModalWindowBase( theStylesPath ));

  	//private constants
  	var WINDOW_TITLE = "CreateNewUser";
  	var USER_NAME_LABEL = "UserNameLabel";
  	var USER_NAME_INPUT_ID = "UserNameInputID";

	//private instance variables
	var self = this;
	var userNameInput;
	var controller = self.getController();

	//public accessors methods
	this.execute = _Execute;

	//public mutators methods

	//private methods
	function _Execute () {
		if(controller == null) controller = parent.browserFrame.webUIController;

		self.createModalWindow(controller.getText(WINDOW_TITLE));

		_SetUpFormElements();
	}

	// creating window content
	function _SetUpFormElements () {
		var formRow = self.createFormRow(controller.getText(USER_NAME_LABEL), "input", USER_NAME_INPUT_ID);
		
		self.addElement(formRow);
			
		userNameInput = self.getElementInWindowById(USER_NAME_INPUT_ID);

		self.addElement(self.createElement("br"));
		self.addElement(self.createElement("br"));

		var buttonContainer = self.createElement("div");
		buttonContainer.className = "readOnlyContainer";
		var createButton = self.constructButton(controller.getText("CreateNewDocument"));
		createButton.style.marginLeft = "150px";
		createButton.onclick = _OnCreateClickHandler;
		buttonContainer.appendChild(createButton);
		var cancelButton = self.constructButton(controller.getText("Cancel"));
		cancelButton.onclick = _OnCancelClickHandler;
		buttonContainer.appendChild(cancelButton);
		self.addElement(buttonContainer);
		self.setUpInputTextsAndSelects();
	}
	
	//event handlers
	function _OnCreateClickHandler() {
		var commandLine = "./CommandControllerServlet?action=CreateArtifact"
		commandLine += "&artifactTypeName=UserDataSheet";
		commandLine += "&artifactName="+userNameInput.value;
		commandLine += "&userName="+userNameInput.value;
		commandLine += "&artifactFolderName=SystemAdministration";

		var xmlError=false;
		try { xmlFile = new XmlResource( commandLine ); }
		catch(e) {xmlError=true;}
		if (!xmlError) {
			var resultTag = xmlFile.getElementsByTagName("actionOutcomeStatus")[0];
			if (resultTag!=null) {
				var result = resultTag.firstChild.nodeValue;
				if(result == "false") alert(xmlFile.getElementsByTagName("errorDescription")[0].firstChild.nodeValue);
				else
				{
					var artifactId = xmlFile.getElementsByTagName("id")[0].firstChild.nodeValue;
					controller.loadDocument("UserDataSheet", userNameInput.value ,"&artifactId="+artifactId, "BaseData");
				}
			} else alert("internal error.");
		} else alert("Internal error.");
		self.closeModalWindow();
	}
	
	function _OnCancelClickHandler() {
		self.closeModalWindow();
	}

}