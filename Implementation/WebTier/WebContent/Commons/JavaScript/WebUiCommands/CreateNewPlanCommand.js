// CreateNewPlanCommand.js

// CreateNewPlanCommand
function CreateNewPlanCommand( theController, theStylesPath ) {
	//check parameter assertions

	// inherit from WebUiCommand to make this object a specialized command
 	inheritFrom(this, new WebUiCommand(theController));
 	inheritFrom(this, new ModalWindowBase( theStylesPath ));

 	//private constants
 	var WINDOW_TITLE = "CreateNewPlan";
 	
 	var PROTOCOL_SELECTOR_LABEL = "LifecycleName";
 	var PROTOCOL_SELECTOR_ID = "LifecycleChooseInputID";
 	
 	var PLAN_SUBJECT_LABEL = "Subject";
 	var PLAN_SUBJECT_INPUT_ID = "PlanSubjectInputID";
 	
 	var PLAN_DESCRIPTION_LABEL = "Description";
 	var PLAN_DESCRIPTION_INPUT_ID = "PlanDescriptionInputID";

	//private instance variables
	var self = this;
	var lifecycleChooseInput;
	var planName;
	var planSubjectInput;
	var planDescriptionInput;
	var protocolName;
	var controller = self.getController();

	//public accessors methods
	this.execute = _Execute;

	//public mutators methods

	//private methods
	function _Execute () {
		if(controller == null) controller = parent.browserFrame.webUIController;
		self.setWidth( 500 );
		self.createModalWindow(controller.getText(WINDOW_TITLE));

		_SetUpFormElements();
	}

	// creating window content
	function _SetUpFormElements () {
		var formRow1 = self.createFormRow( controller.getText( PROTOCOL_SELECTOR_LABEL ), "select", PROTOCOL_SELECTOR_ID );
		var formRow2 = self.createFormRow( controller.getText( PLAN_SUBJECT_LABEL), "input", PLAN_SUBJECT_INPUT_ID, "width:300em" );
	
		self.addElement(formRow1);	
		self.addElement(formRow2);	
		
		lifecycleChooseInput = self.getElementInWindowById( PROTOCOL_SELECTOR_ID );
		planSubjectInput = self.getElementInWindowById( PLAN_SUBJECT_INPUT_ID );
		
		var option = self.appendNewOption( "---", "-1", lifecycleChooseInput );
		
		lifecycleChooseInput.onchange = new function () { return _getIdentifier; }
		
		dataRetriever = new DataRetriever(null, "artifactName", "PlanList", "PlanList_ListView", null, controller);
		dataRetriever.setMethod("getLifecycles");			
		dataRetriever.makeHttpRequest(_getLifecycles);

		self.addElement(self.createElement("br"));
		self.addElement(self.createElement("br"));

		var buttonContainer = self.createElement("div");
		buttonContainer.className = "modalWindowContainer";
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
	
	function _getIdentifier() {
		dataRetriever = new DataRetriever(null, "artifactName", "PlanList", "PlanList_ListView", null, controller);
		dataRetriever.setMethod("getIdentifier");		
		dataRetriever.setParameters(lifecycleChooseInput.options[lifecycleChooseInput.selectedIndex].value);
		dataRetriever.makeHttpRequest(_populateName);
	}
	
	function _populateName() {
		var result = dataRetriever.getCurrentResponseValue().getElementsByTagName("identifier")[0].text;
		planName = result;
	}
	
	function _getLifecycles() {
   	var results = dataRetriever.getCurrentResponseValue().getElementsByTagName("lifecycleName");	
		for(i = 0; i<results.length; i++) {
			var option = self.appendNewOption( results[i].text, results[i].attributes[0].text, lifecycleChooseInput );
		}
	}
	
	function _getProtocolName() {
   	var result = dataRetriever.getCurrentResponseValue().getElementsByTagName("protocolName")[0];	
		protocolName = result.text;
	}
	
	function _updateProtocolName() {}
	
	//event handlers
	function _OnCreateClickHandler() {
	
		dataRetriever = new DataRetriever(null, "artifactName", "PlanList", "PlanList_ListView", null, controller);
		dataRetriever.setMethod("getProtocolName");			
		dataRetriever.setParameters(lifecycleChooseInput.options[lifecycleChooseInput.selectedIndex].value);
		dataRetriever.makeHttpRequest(_getProtocolName);
		
		dataRetriever = new DataRetriever(null, "artifactName", "PlanList", "PlanList_ListView", null, controller);
		dataRetriever.setMethod("updateIdentifier");			
		dataRetriever.makeHttpRequest(_updateProtocolName);
		
		var commandLine = "./CommandControllerServlet?action=CreateArtifact";
		commandLine += "&artifactTypeName=ProcessPlanDataSheet";
		commandLine += "&artifactName="+planName;
		commandLine += "&pplanName="+planName;
		commandLine += "&pplanSubject="+planSubjectInput.value;
		commandLine += "&lifecycleName="+protocolName;
		commandLine += "&artifactFolderName=SystemAdministration";

	//	alert("processing: "+commandLine);
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
					controller.loadDocument("ProcessPlanDataSheet",planName,"&artifactId="+artifactId, "BaseData");
				}
			} else alert("internal error.");
		} else alert("Internal error.");
		self.closeModalWindow();
	}
	
	function _OnCancelClickHandler() {
		self.closeModalWindow();
	}
}