var editableHTMLDocumentId ="";
var editableHTMLDocumentName ="";
var editableHTMLDocumentsViewName = "";
var actionNameForSaveComment ="SaveHTMLText";
var textEditorRows = 15;

function clickOnEditorDivHandler(theEvent) {
	browserEvent = new BrowserEvent(theEvent);	
	if((browserEvent.getType() == "click")) {
		initEditableComment(browserEvent.getSourceElement());
	}			
}

function clickOnAnzsoEditorDivHandler(theEvent) {
	browserEvent = new BrowserEvent(theEvent);	
	if((browserEvent.getType() == "click")) {
		initAnzsoEditableComment(browserEvent.getSourceElement());
	}			
}

function initAnzsoEditableComment(theDiv) {

	var divElement = theDiv;
	var id = theDiv.id;
	if (id=="") id="newDivId";
	var taId = id+"_editor";
	
	var noObstacleSpan = document.createElement("span");
	noObstacleSpan.appendChild(document.createTextNode("Elharulas Datum: "));
	
	var noObstacleDateInput;
	
	noObstacleDateInput = document.createElement("input");
	noObstacleDateInput.type="text";
	noObstacleDateInput.id=id+"_noObstacleText";
	
	noObstacleSpan.appendChild(noObstacleDateInput);
	
	noObstacleSpan.appendChild(document.createTextNode(" (yyyy.mm.dd)"));
	
	divElement.parentNode.insertBefore(noObstacleSpan, divElement);
	
	divElement.parentNode.insertBefore(document.createElement("<br>"), divElement);
	
	var myTextArea = document.createElement("textarea");
	myTextArea.id=taId;
	myTextArea.style.width="100%";
	myTextArea.rows=textEditorRows;
	myTextArea.value=divElement.innerHTML;
	divElement.parentNode.insertBefore(myTextArea, divElement);
	divElement.style.display="none";

	var editor = new HTMLArea(taId);
	var buttonSave,buttonCancel;
	editor.generate();

	buttonSave = document.createElement("input");
	buttonSave.type="button";
	buttonSave.id=id+"_savebutton";
	buttonSave.value="Save";
	buttonSave.onclick= function () { 
		iHtml=editor.getInnerHTML();
		divElement.innerHTML=iHtml;
		ec = document.getElementById(taId+"_EditorContainer");
		buttonSave.parentNode.removeChild(ec);
		ec=null;
		divElement.parentNode.removeChild(buttonSave);
		divElement.parentNode.removeChild(buttonCancel);
		divElement.style.display="block";
		var reqLink="./CommandControllerServlet?action="+actionNameForSaveComment;
		reqLink+="&artifactId="+editableHTMLDocumentId;
		reqLink+="&artifactName="+editableHTMLDocumentName;
		reqLink+="&viewName="+editableHTMLDocumentsViewName;
		reqLink+="&"+id+"="+iHtml;
		reqLink+="&noObstacleDate="+noObstacleDateInput.value;
		var xmlFile;
		var xmlError=false;
		try { xmlFile = new XmlResource( reqLink ); }
		catch(e) {xmlError=true;}
		if (!xmlError) {
			var xmlDivIdTags = xmlFile.getElementsByTagName("divId");
			if (xmlDivIdTags!=null) {
				divElement.id=xmlDivIdTags[0].firstChild.nodeValue;
				document.getElementById("newEditableCommentButton").style.display="block";
			}
		}
	}
	divElement.parentNode.insertBefore(buttonSave, divElement);

	buttonCancel = document.createElement("input");
	buttonCancel.type="button";
	buttonCancel.id=id+"_savebutton";
	buttonCancel.value="Cancel";
	buttonCancel.onclick= function () { 
		ec = document.getElementById(taId+"_EditorContainer"); 
		buttonCancel.parentNode.removeChild(ec); 				
		ec=null;
		divElement.parentNode.removeChild(buttonSave);
		divElement.parentNode.removeChild(buttonCancel);
		divElement.style.display="block";
	}
	divElement.parentNode.insertBefore(buttonCancel, divElement);			
}

function initEditableComment(theDiv) {
	var divElement = theDiv;
	var id = theDiv.id;
	if (id=="") id="newDivId";
	var taId = id+"_editor";
	var myTextArea = document.createElement("textarea");
	myTextArea.id=taId;
	myTextArea.style.width="100%";
	myTextArea.rows=textEditorRows;
	myTextArea.value=divElement.innerHTML;
	divElement.parentNode.insertBefore(myTextArea, divElement);
	divElement.style.display="none";

	var editor = new HTMLArea(taId);
	var buttonSave,buttonCancel;
	editor.generate();

	buttonSave = document.createElement("input");
	buttonSave.type="button";
	buttonSave.id=id+"_savebutton";
	buttonSave.value="Save";
	buttonSave.onclick= function () { 
		iHtml=editor.getInnerHTML();
		divElement.innerHTML=iHtml;
		ec = document.getElementById(taId+"_EditorContainer");
		buttonSave.parentNode.removeChild(ec);
		ec=null;
		divElement.parentNode.removeChild(buttonSave);
		divElement.parentNode.removeChild(buttonCancel);
		divElement.style.display="block";
		var reqLink="./CommandControllerServlet?action="+actionNameForSaveComment;
		reqLink+="&artifactId="+editableHTMLDocumentId;
		reqLink+="&artifactName="+editableHTMLDocumentName;
		reqLink+="&viewName="+editableHTMLDocumentsViewName;
		reqLink+="&"+id+"="+iHtml;
		var xmlFile;
		var xmlError=false;
		try { xmlFile = new XmlResource( reqLink ); }
		catch(e) {xmlError=true;}
		if (!xmlError) {
			var xmlDivIdTags = xmlFile.getElementsByTagName("divId");
			if (xmlDivIdTags!=null) {
				divElement.id=xmlDivIdTags[0].firstChild.nodeValue;
				document.getElementById("newEditableCommentButton").style.display="block";
			}
		}
	}
	divElement.parentNode.insertBefore(buttonSave, divElement);

	buttonCancel = document.createElement("input");
	buttonCancel.type="button";
	buttonCancel.id=id+"_savebutton";
	buttonCancel.value="Cancel";
	buttonCancel.onclick= function () { 
		ec = document.getElementById(taId+"_EditorContainer"); 
		buttonCancel.parentNode.removeChild(ec); 				
		ec=null;
		divElement.parentNode.removeChild(buttonSave);
		divElement.parentNode.removeChild(buttonCancel);
		divElement.style.display="block";
	}
	divElement.parentNode.insertBefore(buttonCancel, divElement);			
}

function newEditableComment() {
	var newButton = document.getElementById("newEditableCommentButton");
	newButton.style.display="none";
	var newDiv = document.createElement("div");
	newDiv.id="newDivId";
	newDiv.style.width="100%";
	newDiv.style.border="1px dotted";
	newDiv.style.cursor="text";
	if (typeof newDiv.addEventListener != 'undefined') {		//DOM 2 event handling
		newDiv.addEventListener('click', clickOnEditorDivHandler, false);
	}else if (typeof newDiv.attachEvent != 'undefined') {		// IE5+ event handling
		newDiv.attachEvent('onclick', clickOnEditorDivHandler);
	}else {newDiv.onclick = clickOnEditorDivHandler;}	
	newDiv.innerHTML="";
	newButton.parentNode.insertBefore(newDiv, newButton);
	newDiv.parentNode.insertBefore(newButton, newDiv);
}

function newAnzsoEditableComment() {
	var newButton = document.getElementById("newEditableCommentButton");
	newButton.style.display="none";
	var newDiv = document.createElement("div");
	newDiv.id="newDivId";
	newDiv.style.width="100%";
	newDiv.style.border="1px dotted";
	newDiv.style.cursor="text";
	if (typeof newDiv.addEventListener != 'undefined') {		//DOM 2 event handling
		newDiv.addEventListener('click', clickOnAnzsoEditorDivHandler, false);
	}else if (typeof newDiv.attachEvent != 'undefined') {		// IE5+ event handling
		newDiv.attachEvent('onclick', clickOnAnzsoEditorDivHandler);
	}else {newDiv.onclick = clickOnAnzsoEditorDivHandler;}	
	newDiv.innerHTML="";
	newButton.parentNode.insertBefore(newDiv, newButton);
	newDiv.parentNode.insertBefore(newButton, newDiv);
}

function pasteNewEditableCommentButton() {
	document.writeln("<input type='button' id='newEditableCommentButton' onclick='javascript:newEditableComment();' value='Uj' class='buttonSmall' >");
}

function pasteNewAnzsoEditableCommentButton() {
	document.writeln("<input type='button' id='newEditableCommentButton' onclick='javascript:newAnzsoEditableComment();' value='...' class='buttonSmall' >");
}