// DocumentRelationshipWidget.js

function DocumentRelationshipWidget(widgetId, artifactId, resourceBundle) {
	AssertUtil.assertParamNotNull(widgetId, "widgetId");
	AssertUtil.assertParamNotNull(resourceBundle, "resourceBundle");

	var widgetDiv = document.getElementById(widgetId);
	var i18Resource = resourceBundle;

//constants
	var BASE_COMMANDNAME = "/CommandControllerServlet?action=";
	var ADDRELATEDARTIFACT_COMMANDNAME = "AddRelatedArtifact";
	var REMOVERELATEDARTIFACT_COMMANDNAME = "RemoveRelatedArtifact";
	var SOURCEARTIFACTNAME_INCOMMANDLINE = "artifactName";
	var TARGETARTIFACTNAME_INCOMMANDLINE = "targetArtifactName";
	var TAGNAME_INRESPONSEXML = "actionOutcomeStatus";
	var FOLDER_TREE_DEF_FILE_NAME = "./CommandControllerServlet?action=RetrieveArtifactFolderStructure"
	var TRANSFORMATION_DEF_FILE_NAME_FOR_ARTIFACTS = "JavaScript/TreeWidget/AFSConverterToTWT_artifacts.xsl";
	var TRANSFORMATION_DEF_FILE_NAME_FOR_FOLDERS = "JavaScript/TreeWidget/AFSConverterToTWT_artifactFolders.xsl";

//events
	var isNewRelationship = false;
	var isRemoveRelationship = false;
	var isAddNewRelatedFile = false;

//local variables
	var newRelationshipDivision = null;
	var removeRelationshipDivision = null;
	var addNewRelatedFileDivision = null;
	var addNewRelatedFileDivision1 = null;
	
	var displayedForm = null;

	var newRelationButton = null;
	var delRelationButton = null;
	var newFileButton = null;
	var cancelButton = null;

	var documentSelectLabel = null;
 	var documentConfirmRemoveLabel = null;
 	var selectRelatedFileLabel = null;

 	var folderSeparator = ".";
 	var selectedSeparatorForServerSide = "/";
 	var pictureFolder = "JavaScript/TreeWidget/";

	var documentSelection = null;
	var subjectArtifactIdentifier = (artifactId == null ? "" : artifactId);
	var fmWidgetDiv = null;
	var fileUploadedForm;
	var targetArtifactNameElement = document.getElementsByName("targetArtifactName")[0];
	var targetFolderElement = document.getElementsByName("targetFolder")[0];
	var centerElement;

	//public accessors methods
	this.retrieveDocumentList = function() {return retrieveDocumentList;}

	//public mutator methods
	this.show = _Show;
	this.hide = _Hide;
	this.selectionChanged = _SelectionChanged;

//	this.documentSelectionEvent = _DocumentSelectionEvent;

//-BO-function Implementation-------

	//-Buttonstatus functions---
	function _defaultButtonStatus(){
		newRelationButton.disabled = false;	//enabled
		newRelationButton.value = newRelationButtonCaption;
		delRelationButton.disabled = true; //disabled
		delRelationButton.value = delRelationButtonCaption;
		newFileButton.disabled = false; //enabled
		newFileButton.value = newFileButtonCaption;
		cancelButton.disabled = true; //disabled
	}

	function _newRelationshipButtonStatus(){
		newRelationButton.disabled = false;
		delRelationButton.disabled = true;
		newFileButton.disabled = true;
		cancelButton.disabled = false;
	}

	function _deleteRelationshipButtonStatus(){
		newRelationButton.disabled = true;
		delRelationButton.disabled = false;
		newFileButton.disabled = true;
		cancelButton.disabled = false;
	}

	function _addNewRelatedFileButtonStatus(){
		newRelationButton.disabled = true;
		delRelationButton.disabled = true;
		newFileButton.disabled = true;
		cancelButton.disabled = false;
	}
	//-End Buttonstatus functions---

//-start-----------------------------------------------------------------
	function _showNewRelationshipDivision(){
		if(isNewRelationship == false){	
			_ConstructNewRelationshipDivision();
			_newRelationshipButtonStatus();
			newRelationButton.value=newRelationButtonClickCaption;
			isNewRelationship = true;
		}	
		else{
			if( targetArtifactNameElement.value != "" ) 
			{
				document.getElementsByName("action")[0].value = ADDRELATEDARTIFACT_COMMANDNAME;
				form.submit();
			}
			else
			{
				widgetDiv.removeChild(newRelationshipDivision);
				widgetDiv.removeChild(fmWidgetDiv);
				_defaultButtonStatus();
				newRelationButton.value=newRelationButtonCaption;
				isNewRelationship = false;
			}
		}
	}

	function _showRemoveRelationshipDivision(){
		if(isRemoveRelationship == false){
			_ConstructRemoveRelationshipDivision();
			_deleteRelationshipButtonStatus();
			delRelationButton.value = delRelationButtonClickCaption;
			isRemoveRelationship = true;
		}
		else{		
			document.getElementsByName("action")[0].value = REMOVERELATEDARTIFACT_COMMANDNAME;
			form.submit();
		}
	}

	function _showAddNewRelatedFileDivision(){
		if(isAddNewRelatedFile == false){
			_ConstructAddNewRelatedFileDivision();
			_addNewRelatedFileButtonStatus();
			isAddNewRelatedFile = true;
		}
		else{
			targetArtifactNameElement.value = "";
			alert(targetFolderElement);
			widgetDiv.removeChild(addNewRelatedFileDivision);
			widgetDiv.removeChild(addNewRelatedFileDivision1);
			_defaultButtonStatus();
			isAddNewRelatedFile = false;
		}
	}

	function _Cancel(){
		if  (isNewRelationship == true){
			widgetDiv.removeChild(newRelationshipDivision);
			_defaultButtonStatus();
			isNewRelationship = false;
		}

		if (isRemoveRelationship == true){
			widgetDiv.removeChild(removeRelationshipDivision);
			_defaultButtonStatus();
			isRemoveRelationship = false;
		}

		if (isAddNewRelatedFile == true){
			widgetDiv.removeChild(addNewRelatedFileDivision);
			widgetDiv.removeChild(addNewRelatedFileDivision1);
			_defaultButtonStatus();
			isAddNewRelatedFile = false;
		}
	}

	function _addRelatedFile(){
		alert("Under construction...");
	}
//-end------

//-EO-function Implementation------

//------------------------------------------------------------

//-Construct functions---
	function _ConstructForm(){
		
		centerElement = document.createElement("center");
		widgetDiv.appendChild(centerElement);
		displayedForm = document.createElement("form");
		displayedForm.setAttribute("name", "documentRelationship");
		displayedForm.setAttribute("id", "documentRelationshipForm");
		displayedForm.setAttribute("method", "post");
		 
		_ConstructNewRelationButton();
		_ConstructDelRelationButton();
		_ConstructNewFileButton();
		_ConstructCancelButton();
	}

//-Buttons-------------------------------
	function _ConstructNewRelationButton(){
		newRelationButtonCaption = i18Resource.getText("NewRelationButtonCaption") ?  i18Resource.getText("NewRelationButtonCaption") : "New relation";
		newRelationButtonClickCaption = i18Resource.getText("NewRelationButtonClickCaption") ?  i18Resource.getText("NewRelationButtonClickCaption") : "Attach";
		newRelationButton = document.createElement("input");
		newRelationButton.setAttribute("type", "button");
		newRelationButton.className="buttonSmall";
		newRelationButton.setAttribute("value", newRelationButtonCaption);
		newRelationButton.onclick = new function () {return _showNewRelationshipDivision;}
		centerElement.appendChild(newRelationButton);
	}

	function _ConstructDelRelationButton(){
		delRelationButtonCaption = i18Resource.getText("DelRelationButtonCaption") ?  i18Resource.getText("DelRelationButtonCaption") : "Delete relation";
		delRelationButtonClickCaption = i18Resource.getText("DelRelationButtonClickCaption") ?  i18Resource.getText("DelRelationButtonClickCaption") : "Yes";
		delRelationButton = document.createElement("input");
		delRelationButton.setAttribute("type", "button");
		delRelationButton.setAttribute("disabled", "disabled");
		delRelationButton.className="buttonSmall";
		delRelationButton.setAttribute("value", delRelationButtonCaption);
		delRelationButton.onclick = new function () {return _showRemoveRelationshipDivision;}
		centerElement.appendChild(delRelationButton);
	}

	function _ConstructNewFileButton(){
		newFileButtonCaption = i18Resource.getText("NewFileButtonCaption") ?  i18Resource.getText("NewFileButtonCaption") : "New file";
		newFileButton = document.createElement("input");
		newFileButton.setAttribute("type", "button");
		newFileButton.className="buttonSmall";
		newFileButton.setAttribute("value", newFileButtonCaption);
		newFileButton.onclick = new function () {return _showAddNewRelatedFileDivision;}
		centerElement.appendChild(newFileButton);
	}

	function _ConstructCancelButton(){
		cancelButtonCaption = i18Resource.getText("CancelButtonCaption") ?  i18Resource.getText("CancelButtonCaption") : "Cancel";
		cancelButton = document.createElement("input");
		cancelButton.setAttribute("type", "button");
		cancelButton.setAttribute("disabled", "disabled");		
		cancelButton.className="buttonSmall";
		cancelButton.setAttribute("value", cancelButtonCaption);
		cancelButton.onclick = new function () {return _Cancel;}
		centerElement.appendChild(cancelButton);
	}

//-Divisions-------------------------------
	function _AppendDivWithTreeWidget(transformDefFileName, parentDivision, actionElement) {
        var area = document.createElement("div");
        area.id="TreeWidget";
        area.style.width="100%";
        area.style.height="100px";
        area.style.border="1px dotted";
		parentDivision.appendChild(area);
		
        var treeWidget = new TreeWidget(area, document);
   		var documentSelectLabelText = i18Resource.getText("SelectedArtifact") ?  i18Resource.getText("SelectedArtifact") : "Selected ";
		parentDivision.appendChild(document.createTextNode("Selected "));
		var selectedSpan = document.createElement("span");
		parentDivision.appendChild(selectedSpan);
		var selectedText = document.createTextNode("");
		brElement = document.createElement("br");
		selectedSpan.appendChild(brElement);
		selectedSpan.insertBefore(selectedText, brElement);
		
		treeWidget.setSourceXmlFileName(FOLDER_TREE_DEF_FILE_NAME);
		treeWidget.setTransformationXslFileName(transformDefFileName);
		treeWidget.setPictureFolder(pictureFolder);
//		treeWidget.loadWidget();
		treeWidget.showWidget();
		treeWidget.functionInSelect = function() {
			var list = treeWidget.getSelectedNameList();
			var index = treeWidget.getSelectedNameListSize();
			var selectedArtifact = "";
			while(index>1) {
				selectedArtifact+=list[index-1]+folderSeparator;
				index--;
			}
			selectedArtifact+=list[0];
			selectedSpan.removeChild(selectedText);
			selectedText = document.createTextNode(selectedArtifact);
			actionElement.value = selectedArtifact;
			selectedSpan.appendChild(selectedText);
		}
	}

	function _ConstructNewRelationshipDivision(){

		newRelationshipDivision = document.createElement("div");
		newRelationshipDivision.setAttribute("name", "newRelationshipDivision");
		newRelationshipDivision.setAttribute("id", "newRelationshipDivision");

		documentSelectLabel = document.createElement("p");
		var documentSelectLabelText = i18Resource.getText("DocumentSelectLabel") ?  i18Resource.getText("DocumentSelectLabel") : "Select document to relate:";
		var textElementDocumentSelect = document.createTextNode(documentSelectLabelText);

		newRelationshipDivision.appendChild(textElementDocumentSelect);

		newRelationshipDivision.appendChild(documentSelectLabel);

		_AppendDivWithTreeWidget(TRANSFORMATION_DEF_FILE_NAME_FOR_ARTIFACTS, newRelationshipDivision, targetArtifactNameElement);

		widgetDiv.insertBefore(newRelationshipDivision, centerElement);
	}

	function _ConstructRemoveRelationshipDivision(){
		removeRelationshipDivision = document.createElement("div");
		removeRelationshipDivision.setAttribute("name", "removeRelationshipDivision");		
		removeRelationshipDivision.setAttribute("id", "removeRelationshipDivision");

		documentConfirmRemoveLabel = document.createElement("p");
		var documentConfirmRemoveLabelText = i18Resource.getText("DocumentConfirmRemoveLabel") ?  i18Resource.getText("DocumentConfirmRemoveLabel") : "Are you sure you want to completely remove the selected relationship?";
		var textElementDocumentConfirmRemove = document.createTextNode(documentConfirmRemoveLabelText);

		removeRelationshipDivision.appendChild(textElementDocumentConfirmRemove);
		removeRelationshipDivision.appendChild(documentConfirmRemoveLabel);

		widgetDiv.insertBefore(removeRelationshipDivision, centerElement);
	}

	function _ConstructAddNewRelatedFileDivision(){
		addNewRelatedFileDivision = document.createElement("div");
		addNewRelatedFileDivision.setAttribute("name", "addNewRelatedFileDivision");		
		addNewRelatedFileDivision.setAttribute("id", "addNewRelatedFileDivision");
		addNewRelatedFileDivision.className = "row";
		widgetDiv.insertBefore(addNewRelatedFileDivision, centerElement);

		_AppendDivWithTreeWidget(TRANSFORMATION_DEF_FILE_NAME_FOR_FOLDERS, addNewRelatedFileDivision, targetFolderElement);

		var selectRelatedFileLabelText = i18Resource.getText("SelectRelatedFileLabel") ?  i18Resource.getText("SelectRelatedFileLabel") : "Select related file:";
		var textElementSelectRelatedFile = document.createTextNode(selectRelatedFileLabelText);
		brElement = document.createElement("br");
		addNewRelatedFileDivision.appendChild(brElement);
		brElement = document.createElement("br");
		addNewRelatedFileDivision.appendChild(brElement);
		addNewRelatedFileDivision.appendChild(textElementSelectRelatedFile);
		
		
		
		//
		
		var formatDiv = document.createElement("div");
		formatDiv.className = "row";

		var labelText1 = "Tipus: ";
		var relatedFile1 = document.createTextNode(labelText1);
		formatDiv.appendChild(relatedFile1);
		
		var select1 = document.createElement("select");
		select1.setAttribute("id", "docType");
		select1.setAttribute("name", "docType");
			
		var operatorOption = document.createElement("option");
		operatorOption.setAttribute("value", "-1");
		operatorOption.appendChild(document.createTextNode("---"));
		select1.appendChild(operatorOption);
					
		var opt1 = new Option(i18Resource.getText("fmw_PICTURE_DOCTYPE"), "Picture");
		var opt2 = new Option(i18Resource.getText("fmw_DOCUMENT_DOCTYPE"), "Document");
		select1.add(opt1);
		select1.add(opt2);
			
		select1.onchange = new function () { return _populateSelect2;}
		
		function _populateSelect2() {
			for( i=select2.length-1;i>=1; i--) 
	   		select2.remove(i);
	   		
			var docType = select1.options[select1.selectedIndex].value;
			if(docType == "Picture") {
				var opt1 = new Option(i18Resource.getText("fmw_INNERPIC_DOCSUBTYPE"), "InnerPic");
				var opt2 = new Option(i18Resource.getText("fmw_OUTERPIC_DOCSUBTYPE"), "OuterPic");
				select2.add(opt1);
				select2.add(opt2);
			}
		}
		
		formatDiv.appendChild(select1);

		var labelText2 = " Al Tipus: ";
		var relatedFile2 = document.createTextNode(labelText2);
		formatDiv.appendChild(relatedFile2);
		
		var select2 = document.createElement("select");
		select2.setAttribute("id", "docSubType");
		select2.setAttribute("name", "docSubType");
		
		var operatorOption = document.createElement("option");
		operatorOption.setAttribute("value", "-1");
		operatorOption.appendChild(document.createTextNode("---"));
		select2.appendChild(operatorOption);
		
		formatDiv.appendChild(select2);

		addNewRelatedFileDivision.appendChild(formatDiv);

		labelSpan = document.createElement("span");
		labelSpan.className = "label";

		var fileLabelText = i18Resource.getText("BrowseFileLabelText") ?  i18Resource.getText("BrowseFileLabelText") : "File:";
		var fileRelatedFile = document.createTextNode(fileLabelText);
		labelSpan.appendChild(fileRelatedFile);

		addNewRelatedFileDivision.appendChild(labelSpan);

		addNewRelatedFileDivision1 = document.createElement("div");
		addNewRelatedFileDivision1.className = "row";
		widgetDiv.insertBefore(addNewRelatedFileDivision1, centerElement);
		
		labelSpan1 = document.createElement("span");
		labelSpan1.className = "label";
		addNewRelatedFileDivision1.appendChild(labelSpan1);

		formwSpan = document.createElement("span");
		formwSpan.className = "formw";

		addNewRelatedFileDivision1.appendChild(formwSpan);

		//-Upload-
		var fileElement = document.createElement("input");
		fileElement.className ="upload";
		fileElement.setAttribute("type", "file");
		fileElement.setAttribute("name", "fileToUpload");
		fileElement.setAttribute("id", "fileToUpload");
		formwSpan.appendChild(fileElement);

		var textElement = document.createElement("input");
		textElement.setAttribute("type", "text");
		textElement.className = "fakeText";
		textElement.setAttribute("name", "fakeFileName");
		textElement.setAttribute("id", "fakeFileName");
		formwSpan.appendChild(textElement);

		var browseButtonValue = i18Resource.getText("BrowseButtonText") ?  i18Resource.getText("BrowseButtonText") : "Browse";

		fileElement.onmouseout = fillFakeText;

		var fakeBrowseButton = document.createElement("input");
		fakeBrowseButton.className = "fakeBrowse";
		fakeBrowseButton.setAttribute("type", "button");		
		fakeBrowseButton.setAttribute("name", "fakeBrowseButton");
		fakeBrowseButton.setAttribute("value", browseButtonValue);
		formwSpan.appendChild(fakeBrowseButton);

		var addButton = document.createElement("input");
		addButton.className = "buttonUpload";
		addButton.setAttribute("name", "addFile");
		addButton.setAttribute("type", "submit");
		var addButtonValue = i18Resource.getText("UploadFileButtonText") ?  i18Resource.getText("UploadFileButtonText") : "Upload";
		addButton.setAttribute("value", addButtonValue);
		addButton.onclick = new function () {return _ValidateUploadButton;}
		formwSpan.appendChild(addButton);

	}
	
	function _ValidateUploadButton() {
		
	}
	
//-End Construct functions---

//---
	function _Show(){
		_ConstructForm();
	}

	function _Hide(){
	}

//---
	function _widgetDivEvent (documentName) {
		delRelationButton.disabled = false;
	}

	function _SelectionChanged() {
		if( countSelectedItemsByName("targetArtifactId") > 0 ) {
			delRelationButton.disabled = false;	
		}
		else delRelationButton.disabled = true;
	}
}