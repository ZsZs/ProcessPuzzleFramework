// CreateNewProductTypeCommand.js

// CreateNewProductTypeCommand
function CreateNewProductTypeCommand( theController, theStylesPath ) {
	//check parameter assertions

	// inherit from WebUiCommand to make this object a specialized command
  	inheritFrom(this, new WebUiCommand( theController ));
  	inheritFrom(this, new ModalWindowBase( theStylesPath ));

  	//private constants
  	var WINDOW_TITLE = "CreateNewProductType";
  	var PRODUCT_TYPE_IDENTIFIER_LABEL = "ProductTypeIdentifierLabel";
  	var PRODUCT_TYPE_IDENTIFIER_INPUT_ID = "ProductTypeIdentifierInputID";
  	var PRODUCT_TYPE_NAME_LABEL = "ProductTypeNameLabel";
  	var PRODUCT_TYPE_NAME_INPUT_ID = "ProductTypeNameInputID";

	//private instance variables
	var self = this;
	var ProductTypeNameInput;
	var ProductTypeIdentifierInput;
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
		var formRow = self.createFormRow(controller.getText(PRODUCT_TYPE_IDENTIFIER_LABEL), "input", PRODUCT_TYPE_IDENTIFIER_INPUT_ID);
		var formRow2 = self.createFormRow(controller.getText(PRODUCT_TYPE_NAME_LABEL), "input", PRODUCT_TYPE_NAME_INPUT_ID);
		
		self.addElement(formRow);
		self.addElement(formRow2);
			
		ProductTypeIdentifierInput = self.getElementInWindowById(PRODUCT_TYPE_IDENTIFIER_INPUT_ID);
		ProductTypeNameInput = self.getElementInWindowById(PRODUCT_TYPE_NAME_INPUT_ID);

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
		commandLine += "&artifactTypeName=ProductTypeDataSheet";
		commandLine += "&artifactName="+ProductTypeIdentifierInput.value;
		commandLine += "&catalogName="+ProductTypeNameInput.value;
		commandLine += "&artifactFolderName=Trading";

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
					controller.loadDocument("ProductTypeDataSheet", ProductTypeIdentifierInput.value+"_"+ProductTypeNameInput.value ,"&artifactId="+artifactId, "BaseData");
				}
			} else alert("internal error.");
		} else alert("Internal error.");
		self.closeModalWindow();
	}
	
	function _OnCancelClickHandler() {
		self.closeModalWindow();
	}

}