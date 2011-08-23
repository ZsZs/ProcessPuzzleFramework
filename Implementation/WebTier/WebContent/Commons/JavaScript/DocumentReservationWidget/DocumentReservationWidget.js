//Document reservation class

function DocumentReservationWidget(widgetId, resourceBundle) {
	AssertUtil.assertParamNotNull(widgetId, "widgetId");
	AssertUtil.assertParamNotNull(resourceBundle, "resourceBundle");
	
	//private fields
	var widgetDiv = document.getElementById(widgetId);
	AssertUtil.assertParamNotNull(widgetDiv, "widgetDiv");	
	var i18Resource = resourceBundle;
	var displayedForm = null;
	var commentVisibility = false;
	var commentDiv = null;
	//var artifactsLabel = null;
	var reserveButton = null;
	var releaseButton = null;
	var cancelButton = null;
	//var targetDocuments = new Array();
	//var targetDocumentListDiv = null;
	var wprogressBar = null;
	var progressBarDiv = null;
	
	//public accessors methods
	this.commentVisibility = function(){return commentVisibility;}
	this.getTargetDocuments = function() {return targetDocuments;}

	//public mutator methods
	this.show = _Show;
	this.hide = _Hide;
	this.reserve = _Reserve;
	this.release = _Release;
	this.cancel = _Cancel;
	this.checkSelection = _CheckSelection;

	function _CheckSelection()
	{
		reserveButton.disabled = true;
		releaseButton.disabled = true;
		if( _CanReserve() )
		{
			reserveButton.disabled = false;
		}
		else if( _CanRelease() )
		{
			releaseButton.disabled = false;
		}
	}

	function _ConstructElements() {
		_ConstructCommentDiv();
	//	_ConstructTargetDocumentList();
		_ConstructCancelButton();
		displayedForm.removeChild(reserveButton);			
		displayedForm.removeChild(releaseButton);			
	}

	function _Reserve(){
		if (_CanReserve() == false){
			alert(i18Resource.getText("NotSelectedItems") ?  i18Resource.getText("NotSelectedItems") : "Warning! Not selected items or wrong selection!");
			return;
		}
		_ConstructElements();
	//	_ConstructAppletHelpers();
		_ConstructReserveApplet();
	}

	function _Release(){
		if (_CanRelease() == false){
			alert(i18Resource.getText("NotSelectedItems") ?  i18Resource.getText("NotSelectedItems") : "Warning! Not selected items or wrong selection!");
			return;
		}		
		_ConstructElements();
		_ConstructReleaseApplet();
	}
	
	function _Cancel(){
	//	displayedForm.removeChild(targetDocumentListDiv);			
		displayedForm.removeChild(commentDiv);
	//	displayedForm.removeChild(artifactsLabel);		
		displayedForm.removeChild(cancelButton);			
		displayedForm.removeChild(progressBarDiv);			
		_ConstructReserveButton();
		_ConstructReleaseButton();
	}
	
	function _ConstructForm(){
		displayedForm = document.createElement("form");
		displayedForm.setAttribute("name", "documentReservation");
		displayedForm.setAttribute("id", "documentReservationForm");
		displayedForm.setAttribute("method", "post");
	}

	function _setAttributes(element, attributesArray) {
        for( i = 0; i < attributesArray.length; i++)
        {
    	    element.setAttribute(attributesArray[i][0], attributesArray[i][1]);
    	}
	}

	function _addAttribute(attributesArray, attributeName, attributeValue) {
		attributesArray[attributesArray.length] = new Array(attributeName, attributeValue);
	}
	
	function _createInputAttributesArray(name, id, value, type, disabled) {
		var attributesArray = new Array();		
		_addAttribute(attributesArray, "name", name);
		_addAttribute(attributesArray, "id", id);
		_addAttribute(attributesArray, "value", value);
		_addAttribute(attributesArray, "type", type);
		_addAttribute(attributesArray, "disabled", disabled);
		return attributesArray;
	}
	
	function _ConstructInputElement(buttonCaption, name, id, className, value, type, disabled) {
		var button = document.createElement("input");
		var attribs = _createInputAttributesArray(name, id, value, type, disabled);
		_setAttributes(button, attribs);
		button.className = className;
		return button;
	}

	function _ConstructReserveButton(){
		reserveButtonCaption = i18Resource.getText("ReserveButtonCaption") ?  i18Resource.getText("ReserveButtonCaption") : "Reserve";
		reserveButton = _ConstructInputElement(reserveButtonCaption, "reserve", "reserveButton", "buttonSmall", reserveButtonCaption, "button", false);
		reserveButton.onclick = new function () {return _Reserve;}
		displayedForm.appendChild(reserveButton);
	}

	function _ConstructReleaseButton(){
		var releaseButtonCaption = i18Resource.getText("ReleaseButtonCaption") ?  i18Resource.getText("ReleaseButtonCaption") : "Release";	
		releaseButton = _ConstructInputElement(releaseButtonCaption, "release", "releaseButton", "buttonSmall", releaseButtonCaption, "button", true);
		releaseButton.onclick = new function () {return _Release;}
		displayedForm.appendChild(releaseButton);
	}

	function _ConstructCancelButton(){
		var cancelButtonCaption = i18Resource.getText("CancelButtonCaption") ?  i18Resource.getText("CancelButtonCaption") : "Cancel";
		cancelButton = _ConstructInputElement(cancelButtonCaption, "cancel", "cancelButton", "buttonSmall", cancelButtonCaption, "button", false);
		cancelButton.onclick = new function () {return _Cancel;}
		displayedForm.appendChild(cancelButton);
	}

	function _ConstructCommentDiv(){
		commentDiv = document.createElement("div");

	//	artifactsLabel = document.createElement("p");
	//	var artifactsLabelText = i18Resource.getText("ReservationArtifactsLabel") ?  i18Resource.getText("ReservationArtifactsLabel") : "Reserved artifacts";
	//	var textElementArtifacts = document.createTextNode(artifactsLabelText);

		var commentLabel = document.createElement("p");
		var commentLabelText = i18Resource.getText("ReservationCommentLabel") ?  i18Resource.getText("ReservationCommentLabel") : "Comments";
		var textElement = document.createTextNode(commentLabelText);
		var commentTextArea = document.createElement("textarea");
		
//		_DiscoverDocuments();

		commentDiv.setAttribute("name", "commentDiv");
	//	artifactsLabel.setAttribute("name", "artifactsLabel")
		commentLabel.setAttribute("name", "commentLabel")
		commentTextArea.setAttribute("name", "commentTextArea");
		commentTextArea.setAttribute("cols", "60");
		commentTextArea.setAttribute("rows", "5");

	//	artifactsLabel.appendChild(textElementArtifacts);
	//	commentDiv.appendChild(artifactsLabel);

		commentLabel.appendChild(textElement);
		commentDiv.appendChild(commentLabel);
		commentDiv.appendChild(commentTextArea);
		
		progressBarDiv = document.createElement("div");
		progressBarDiv.setAttribute("id", "progressBarDiv")
		
		displayedForm.appendChild(commentDiv, reserveButton)
		//displayedForm.insertBefore(artifactsLabel, commentDiv);
		displayedForm.appendChild(progressBarDiv);
		wprogressBar = new progressBar("progressBarDiv", "300px", "15px", "white", "1px", "black", "white");
	}
	
	function _ConstructAppletHelpers() {
		var inVisibleDiv = document.createElement("div");
		inVisibleDiv.style.display = "none";
		var imageSelectorHidden = _ConstructInputElement("imageSelector", "imageSelector", "imageSelector", "", "", "hidden", true);
		inVisibleDiv.appendChild(imageSelectorHidden);

		var imageSelectorButton = _ConstructInputElement("imageSelectorButton", "imageSelectorButton", "imageSelectorButton", "", "imageSelectorButton", "button", true);
		imageSelectorButton.onclick = new function () {return _ShowImage();}
		inVisibleDiv.appendChild(imageSelectorButton);

		var resetPageButton = _ConstructInputElement("resetPageButton", "resetPageButton", "resetPageButton", "", "resetPageButton", "button", true);
		resetPageButton.onclick = new function () {return _ResetPage();}
		inVisibleDiv.appendChild(resetPageButton);

		displayedForm.appendChild(inVisibleDiv);
	}
	
	function progressAction()
	{
		wprogressBar.execute(document.getElementsByName("stepInterval")[0].value);
	}

	function progressReset()
	{
		wprogressBar.reset();
	}

	function _showMsg(docId)
	{
		var msg = document.getElementById("msg"+docId).value;
		alert(msg);
	}

	function _ShowImage()
	{
		var id = document.getElementById("imageSelector").value;
		showOtherDatas("img"+id);
	}
	
	function _ResetPage()
	{
		var images = document.getElementsByTagName("img");
		for(i = 0; i < images.length; i++)
		{
			images[i].style.display = "none";
			images[i].src = "";
			var msg = document.getElementById("msg"+images[i].name.substring(3,images[i].name.length));
			msg.value = "";
		}
	}
	
	/*function _ConstructTargetDocumentList(){
		targetDocumentListDiv = document.createElement("div");
		var ulist = document.createElement("ul");
		
		targetDocumentListDiv.setAttribute("name", "targetDocumentListDiv");
		targetDocumentListDiv.setAttribute("id", "targetDocumentListDiv");		
		targetDocumentListDiv.setAttribute("class", "readOnlyContainer");

		ulist.setAttribute("name", "unordlist");
		ulist.setAttribute("id", "unordList");		
		targetDocumentListDiv.appendChild(ulist);
		
		for (var i=0; i<targetDocuments.length; i++){
			var list = document.createElement("li");
			var textNode = document.createTextNode(targetDocuments[i]);
			targetDocumentListDiv.appendChild(list);
			list.appendChild(textNode);
		}
		displayedForm.insertBefore(targetDocumentListDiv, commentDiv);
	} */

	/*function _DiscoverDocuments(){
		targetDocuments = new Array();
		var anchors = document.getElementsByTagName("a");
		for (var i=0; i<anchors.length; i++){
			var anchor = anchors[i];
			if (anchor.getAttribute("className") == "listedDocumentName"){
				var checkbox = anchor.parentElement.previousSibling.firstChild;
				if (checkbox.checked){
					var documentName = anchor.outerText;
					targetDocuments.push(documentName);
				}
			}
		}
	}*/
	
	function _ConstructApplet( name, archive, code, width, height, params ) {
		var reserveAppletElement = document.createElement("applet");
		reserveAppletElement.setAttribute("id", name+"Applet");
        reserveAppletElement.setAttribute("code", code);

		var loc = window.location;
        reserveAppletElement.setAttribute("codebase", loc.protocol+"//"+loc.host+"//"+"AnzsoBrowserInterface//applets");
        reserveAppletElement.setAttribute("archive", archive);
        reserveAppletElement.setAttribute("width", width);
        reserveAppletElement.setAttribute("height", height);
        for( i = 0; i < params.length; i++)
        {
	        var paramElement = document.createElement("param");
    	    paramElement.setAttribute("name", params[i][0]);
	        paramElement.setAttribute("value",params[i][1]);
    	    reserveAppletElement.appendChild(paramElement);
    	}
    	commentDiv.appendChild(document.createElement("br"));
        commentDiv.appendChild(reserveAppletElement);
	}

	function _ConstructReserveApplet() {
		//reserve applet
		var appletParams = new Array();
		_addAttribute(appletParams, "buttonText", i18Resource.getText("ReserveButtonCaption") ? i18Resource.getText("ReserveButtonCaption") : "Reserve");
		_addAttribute(appletParams, "acceptedText", i18Resource.getText("ReserveAcceptedMessage") ?  i18Resource.getText("ReserveAcceptedMessage") : "Reserved.");
		_addAttribute(appletParams, "ioErrorMessage", i18Resource.getText("IOError") ? i18Resource.getText("IOError") : "IO error.");
		_addAttribute(appletParams, "modifierName", webUiController.getUserName());
		_addAttribute(appletParams, "location", webUiController.getUserLocation());
		_ConstructApplet("reserve", "ReserveReleaseApplets.jar", "com.processpuzzle.framework.applet.control.ReserveApplet.class", 50, 20, appletParams);
	}

	function _ConstructReleaseApplet() {
		//release applet
		var appletParams = new Array();
		_addAttribute(appletParams, "buttonText", i18Resource.getText("ReleaseButtonCaption") ? i18Resource.getText("ReleaseButtonCaption") : "Release");
		_addAttribute(appletParams, "acceptedText", i18Resource.getText("ReleaseAcceptedMessage") ?  i18Resource.getText("ReleaseAcceptedMessage") : "Released.");
		_addAttribute(appletParams, "ioErrorMessage", i18Resource.getText("IOError") ? i18Resource.getText("IOError") : "IO error.");
		_addAttribute(appletParams, "missingFileMessage", i18Resource.getText("MissingFileFromLocal") ? i18Resource.getText("MissingFileFromLocal") : "Missing the local file.");
		_addAttribute(appletParams, "location", webUiController.getUserLocation());
		_ConstructApplet("release", "ReserveReleaseApplets.jar", "com.processpuzzle.framework.applet.control.ReleaseApplet.class", 50, 20, appletParams);
	}

	function _checkSelectedItems(wrongValue, goodValue) {
		var can = false;
		var cBoxes = document.getElementsByName("selectCheckbox");
		if( cBoxes )
		{
			for( i = 0; i < cBoxes.length; i++ )
			{
				if( cBoxes[i].checked )
				{
					j = i+1;
					if( document.getElementById("reserveStatus_"+j).value == wrongValue )
					{
						return false;
					}
					else if( document.getElementById("reserveStatus_"+j).value == goodValue )
					{
						can = true;	
					}		
				}
			}			
		}
		return can;
	}

	function _CanReserve() {
		return true;
	}

	function _CanRelease() {
		return _checkSelectedItems("false", "true")
	}

	function _Show(){
		_ConstructForm();
		_ConstructReserveButton();
		_ConstructReleaseButton();
		widgetDiv.appendChild(displayedForm);
	}

	function _Hide(){
		widgetDiv.removeChild(displayedForm);
	}
}