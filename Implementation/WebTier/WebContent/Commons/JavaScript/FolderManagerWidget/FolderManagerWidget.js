// FolderManagerWidget.js

/*****************************************
* modes(theMode parameter):
*       0: modal window
*       1: in div
******************************************/

function FolderManagerWidget(theName,theMode,theController,theDivElement) {
//check parameter assertions
	if(theName == null || theName == "") throw new InvalidParameterException();		//theName can't be "" or null
	if(theMode != 0 && theMode != 1) throw new InvalidParameterException();			//theMode can be only 0 or 1
	if(theMode == 1 && theDivElement == null ) throw new InvalidParameterException();
	if(theMode == 1 && (!theDivElement.tagName || theDivElement.tagName.toUpperCase() != "DIV")) throw new InvalidParameterException();

//private instance variables
	var CREATE_WINDOW_TITLE = "Create new node";
	var RENAME_WINDOW_TITLE = "Rename node";
	var MOVE_WINDOW_TITLE = "Move node";
	var DELETE_WINDOW_TITLE = "Delete node";
	
	var CANCEL_BUTTON_TEXT = "Cancel";
	var CREATE_BUTTON_TEXT = "Create";
	var RENAME_BUTTON_TEXT = "Rename";
	var MOVE_BUTTON_TEXT = "Move";
	var DELETE_BUTTON_TEXT = "Delete";
	
	var NEW_NAME_LABEL = "New name: ";
	var SELECTED_LABEL = "Selected: ";
	
	var TREE_TITLE_CREATE_NODE_LABEL = "Select parent node where to place:";
	var TREE_TITLE_RENAME_NODE_LABEL = "Select node to rename:";
	var TREE_TITLE_MOVE_FROM_NODE_LABEL = "Select node to move:";
	var TREE_TITLE_MOVE_TO_NODE_LABEL = "Select parent node where to move:";
	var TREE_TITLE_DELETE_NODE_LABEL = "Select node to delete:";
	
	var WARNING_EMPTY_NEW_NAME = "The new name field is empty!";
	var WARNING_NOT_SELECTED = "Choose a node!";
	var WARNING_NOT_SELECTED2 = "Choose a node and it new parent too!";
	var WARNING_SEPARATOR_IN_NAME = "The new name contains the sperator: ";


	var mode = theMode;
	var self = this;
	var folderTreeDefFileName = ""; // name of XML file
	var transformationDefFileName = ""; // name of XML file
	var transformationDefFileName2 = ""; // name of XML file for moving nodes
	var controller = theController;
	var pictureFolder = "";
	var cssFolder = "";
	var htmlContent="";
	var name = theName;
	var modalWindow = null;
	var eventHandler = null;
	
	var usedWindow = null;
	var htmlDOMDocument = null;
	var containerElement = theDivElement; // in mode0 it will be modified

	var selected = "";
	var selected2 = "";// to move window
	var inputElement = null;
	
	var selectedSeparator = ".";

//public accessor methods
	this.setFolderTreeDefFileName = function(fileName) {folderTreeDefFileName = fileName;}
	this.setTransformationDefFileName = function(fileName) {transformationDefFileName = fileName;}
	this.setTransformationDefFileName2 = function(fileName) {transformationDefFileName = fileName;}
	this.setPictureFolder = function(newFolderName) {pictureFolder = newFolderName;}
	this.setCssFolder = function(newFolderName) {cssFolder = newFolderName;}
	this.setEventHandler = function (newEventHandler) {eventHandler = newEventHandler;}
	this.setSeparator = function (newSeparator) {selectedSeparator = newSeparator;}
	this.getSelected = function() {return selected;}
	this.getSelected2 = function() {return selected2;}
	this.getNewName = function() {return inputElement!=null?inputElement.value:"";}

//public mutator methods
	this.createNewNode = _CreateNewNode;
	this.renameNode = _RenameNode;
	this.moveNode = _MoveNode;
	this.deleteNode = _DeleteNode;
	this.clearContainerElement = _ClearContainerElement;

//private methods
	
	//main methods
	function _CreateNewNode(captionList) {
		_Init(CREATE_WINDOW_TITLE);
		_AddElement(_CreateTextNode(NEW_NAME_LABEL));
		inputElement = _CreateElement("input");
		inputElement.type = "text";
		_AddElement(inputElement);
		_AddElement(_CreateElement("br"));
		if(captionList!=null && caltionList.length)
		for(var i=0; i<captionList.length; i++) {
			_AddElement(_CreateTextNode(NEW_NAME_LABEL));
			var anElement = _CreateElement("input");
			_AddElement(anElement);
			eval("self.getValue"+i+"() = function() {return anElement.value;}");
			_AddElement(_CreateElement("br"));
		}
		_AddElement(_CreateTextNode(TREE_TITLE_CREATE_NODE_LABEL));
		_AddElement(_CreateElement("br"));
		_AppendDivWithTreeWidget();
		_AddElement(_CreateElement("br"));
		_AddElement(_CreateElement("br"));
		var createButton = _ConstructButton(CREATE_BUTTON_TEXT);
		createButton.onclick = _OnCreateClickHandler;
		_AddElement(createButton);
		if(mode == 0) {
			var cancelButton = _ConstructButton(CANCEL_BUTTON_TEXT);
			cancelButton.onclick = _OnCancelClickHandler;
			_AddElement(cancelButton);
			modalWindow.setUpInputTexts();
		}
	}

	function _RenameNode() {
		_Init(RENAME_WINDOW_TITLE);
		_AddElement(_CreateTextNode(TREE_TITLE_RENAME_NODE_LABEL));
		_AddElement(_CreateElement("br"));
		_AppendDivWithTreeWidget();
		_AddElement(_CreateElement("br"));
		_AddElement(_CreateTextNode(NEW_NAME_LABEL));
		inputElement = _CreateElement("input");
		inputElement.type = "text";
		_AddElement(inputElement);
		_AddElement(_CreateElement("br"));
		_AddElement(_CreateElement("br"));
		var renameButton = _ConstructButton(RENAME_BUTTON_TEXT);
		renameButton.onclick = _OnRenameClickHandler;
		_AddElement(renameButton);
		if(mode == 0) {
			var cancelButton = _ConstructButton(CANCEL_BUTTON_TEXT);
			cancelButton.onclick = _OnCancelClickHandler;
			_AddElement(cancelButton);
			modalWindow.setUpInputTexts();
		}
	}

	function _MoveNode() {
		_Init(MOVE_WINDOW_TITLE,true);
		_AddElement(_CreateTextNode(TREE_TITLE_MOVE_FROM_NODE_LABEL));
		_AddElement(_CreateElement("br"));
		_AppendDivWithTreeWidget();
		_AddElement(_CreateElement("br"));
		_AddElement(_CreateElement("br"));
		_AddElement(_CreateTextNode(TREE_TITLE_MOVE_TO_NODE_LABEL));
		_AddElement(_CreateElement("br"));
		_AppendDivWithTreeWidget(true);
		_AddElement(_CreateElement("br"));
		_AddElement(_CreateElement("br"));
		var moveButton = _ConstructButton(MOVE_BUTTON_TEXT);
		moveButton.onclick = _OnMoveClickHandler;
		_AddElement(moveButton);
		if(mode == 0) {
			var cancelButton = _ConstructButton(CANCEL_BUTTON_TEXT);
			cancelButton.onclick = _OnCancelClickHandler;
			_AddElement(cancelButton);
			modalWindow.setUpInputTexts();
		}
	}

	function _DeleteNode() {
		_Init(DELETE_WINDOW_TITLE);
		_AddElement(_CreateTextNode(TREE_TITLE_DELETE_NODE_LABEL));
		_AddElement(_CreateElement("br"));
		_AppendDivWithTreeWidget();
		_AddElement(_CreateElement("br"));
		_AddElement(_CreateElement("br"));
		var deleteButton = _ConstructButton(DELETE_BUTTON_TEXT);
		deleteButton.onclick = _OnDeleteClickHandler;
		_AddElement(deleteButton);
		if(mode == 0) {
			var cancelButton = _ConstructButton(CANCEL_BUTTON_TEXT);
			cancelButton.onclick = _OnCancelClickHandler;
			_AddElement(cancelButton);
			modalWindow.setUpInputTexts();
		}
	}

	function _Init(title,longerWindow) {
		if(mode == 1) {
			usedWindow = window;
			htmlDOMDocument = document;
		} else {
			_MakeModalWindow(title,longerWindow);
			usedWindow = modalWindow;
			htmlDOMDocument = modalWindow.document;
			containerElement = htmlDOMDocument.getElementById("content");
			//_AddElement(_CreateTextNode(title));
		}
	}

	// constructive methods
	function _MakeModalWindow(title,longerWindow) {
		_InitializeWindow(title)

		var height="225";
		if (longerWindow) height="350";
		var args='width=350,height='+height+',left=325,top=300,toolbar=0,';
		args+='location=0,status=1,menubar=0,scrollbars=1,resizable=0';

		modalWindow=window.open("","fmwWindow",args);
		modalWindow.document.open();
		modalWindow.document.write(htmlContent);
		modalWindow.document.close();
		modalWindow.focus();
	}
	
	function _AppendDivWithTreeWidget(second) {
	var isSecond=second; //for move a Node
        var area = _CreateElement("div");
        area.id="TreeWidget";
        area.style.width="100%";
        area.style.height="100px";
        area.style.border="1px dotted";
        _AddElement(area);
        var treeWidget = new TreeWidget(area, htmlDOMDocument);
	if(controller!=null && controller!="")
		treeWidget.setResourceBundle(controller);
        _AddElement(_CreateTextNode(SELECTED_LABEL));
		var selectedSpan = _CreateElement("span");
		_AddElement(selectedSpan);
		var selectedText = _CreateTextNode("");
		selectedSpan.appendChild(selectedText);

		treeWidget.setSourceXmlFileName(folderTreeDefFileName);
		treeWidget.setTransformationXslFileName(transformationDefFileName);
		if(isSecond && transformationDefFileName2!=null && transformationDefFileName2!="")
			treeWidget.setTransformationXslFileName(transformationDefFileName2);
		treeWidget.setPictureFolder(pictureFolder);
		treeWidget.loadWidget();
		treeWidget.showWidget();

		treeWidget.functionInSelect = function() {
			var list = treeWidget.getSelectedNameList();
			var index = treeWidget.getSelectedNameListSize();
			var s="";
			while(index>1) {s+=list[index-1]+selectedSeparator;index--;}
			s+=list[0];
			selectedSpan.removeChild(selectedText);
			selectedText = _CreateTextNode(s);
			selectedSpan.appendChild(selectedText);
			if (isSecond) selected2 = s;
			else selected = s;
		}
	}

	function _ConstructButton(labelText) {
		var button = htmlDOMDocument.createElement("input");
		button.type = "button";
		button.className = "buttonSmall";
		button.value = labelText;
		return button;
	}

	function _CreateTextNode(text) {
		return htmlDOMDocument.createTextNode(text);
	}

	function _CreateElement(elementName) {
		return htmlDOMDocument.createElement(elementName);
	}

	function _AddElement(element) {
		containerElement.appendChild(element);
	}

	function _RemoveElement(element) {
		containerElement.removeChild(element);
	}

	function _ClearContainerElement() {
		while (containerElement.hasChildNodes())
			containerElement.removeChild(containerElement.firstChild);
	}

	//event handlers
	function _OnCreateClickHandler() {
		if(inputElement.value == "")
			usedWindow.alert(WARNING_EMPTY_NEW_NAME);
		else if(inputElement.value.indexOf(selectedSeparator) > -1)
			usedWindow.alert(WARNING_SEPARATOR_IN_NAME+selectedSeparator);
		else if(selected == "")
			usedWindow.alert(WARNING_NOT_SELECTED);
		else {
			if(mode == 0) modalWindow.close();
			if(eventHandler != null) eventHandler(selected,inputElement.value,"");
		}
	}
	function _OnRenameClickHandler() {
		if(selected == "")
			usedWindow.alert(WARNING_NOT_SELECTED);
		else if(inputElement.value == "")
			usedWindow.alert(WARNING_EMPTY_NEW_NAME);
		else if(inputElement.value.indexOf(selectedSeparator) > -1)
			usedWindow.alert(WARNING_SEPARATOR_IN_NAME+selectedSeparator);
		else {
			if(mode == 0) modalWindow.close();
			if(eventHandler != null) eventHandler(selected,inputElement.value,"");
		}
	}
	function _OnMoveClickHandler() {
		if (selected=="" || selected2=="")
			usedWindow.alert(WARNING_NOT_SELECTED2);
		else {
			if(mode == 0) modalWindow.close();
			if(eventHandler != null) eventHandler(selected,"",selected2);
		}
	}
	function _OnDeleteClickHandler() {
		if (selected=="")
			usedWindow.alert(WARNING_NOT_SELECTED);
		else {
			if(mode == 0) modalWindow.close();
			if(eventHandler != null) eventHandler(selected,"","");
		}
	}
	function _OnCancelClickHandler() {
		modalWindow.close();
	}

	//window creator method
	function _InitializeWindow(title) {
        htmlContent ='<html>\n';
        htmlContent+='<head>\n'; 
        htmlContent+='<title>' + title + '</title>\n';
        htmlContent+='<link href="' + cssFolder + 'TreeWidget.css" rel="stylesheet">\n';
        htmlContent+='<link href="' + cssFolder + 'ModalWindow.css" rel="stylesheet">\n';
        htmlContent+='<script' + ' language=JavaScript>\n';
        htmlContent+='var skipcycle=false;\n';
        htmlContent+='function _SkipcycleToTrue() {skipcycle=true;}\n';
        htmlContent+='function _SkipcycleToFalse() {skipcycle=false;}\n';
        htmlContent+='function _AddEventListener(element,eventName,theFunction) {\n';
        htmlContent+='        if (element.addEventListener != null) {                //DOM 2 event handling\n';
        htmlContent+='                element.addEventListener(eventName, theFunction, false);\n';
        htmlContent+='        }else if (element.attachEvent != null) {                // IE5+ event handling\n';
        htmlContent+='                element.attachEvent("on"+eventName, theFunction);\n';
        htmlContent+='        }else { eval("element.on"+eventName+" = "+theFunction+";");}        // IE4 event handling\n';
        htmlContent+='}\n';
        htmlContent+='function focusOnMe() {\n';
        htmlContent+='        if (!skipcycle) {window.focus();}\n';
        htmlContent+='        setTimeout("focusOnMe()", 10);\n';
        htmlContent+='}\n';
        htmlContent+='function setUpInputTexts() {\n';
        htmlContent+='        var inputs = document.getElementsByTagName("input");\n';
        htmlContent+='        for (i=0; i<inputs.length; i++)\n';
        htmlContent+='                if(inputs[i].type=="text" && inputs[i].modalCompatible==null) {\n';
        htmlContent+='                        _AddEventListener(inputs[i], "focus", _SkipcycleToTrue);\n';
        htmlContent+='                        _AddEventListener(inputs[i], "blur", _SkipcycleToFalse);\n';
        htmlContent+='                        inputs[i].modalCompatible="";\n';        
        htmlContent+='                }\n';        
        htmlContent+='}\n';        
        htmlContent+='function startModal() {\n';        
        htmlContent+='        setUpInputTexts();\n';        
        htmlContent+='        setTimeout("focusOnMe()", 10);\n';        
        htmlContent+='}\n';        
        htmlContent+='</script>\n';        
        htmlContent+='</head>\n';   
        htmlContent+='<body onload = \'javascript:startModal();\'>\n';
        htmlContent+='<script>window.opener.'+name+' = this;</script>\n';
        htmlContent+='<div id="content"></div>\n';
        htmlContent+='</body>\n';
        htmlContent+='</html>';
    }
    
    //getting texts form resourceBundle
    function _GetTextsFromResourceBundle() {
		if (controller!=null) {
			var createwt = _GetText("fmw_CREATE_WINDOW_TITLE",CREATE_WINDOW_TITLE);
			if (createwt!="fmw_CREATE_WINDOW_TITLE") CREATE_WINDOW_TITLE = createwt;
			var renamewt = _GetText("fmw_RENAME_WINDOW_TITLE");
			if (renamewt!="fmw_RENAME_WINDOW_TITLE") RENAME_WINDOW_TITLE = renamewt;
			var movewt = _GetText("fmw_MOVE_WINDOW_TITLE");
			if (movewt!="fmw_MOVE_WINDOW_TITLE") MOVE_WINDOW_TITLE = movewt;
			var deletewt = _GetText("fmw_DELETE_WINDOW_TITLE");
			if (deletewt!="fmw_DELETE_WINDOW_TITLE") DELETE_WINDOW_TITLE = deletewt;

			var cancelbt = _GetText("fmw_CANCEL_BUTTON_TEXT");
			if (cancelbt!="fmw_CANCEL_BUTTON_TEXT") CANCEL_BUTTON_TEXT = cancelbt;
			var cratebt = _GetText("fmw_CREATE_BUTTON_TEXT");
			if (cratebt!="fmw_CREATE_BUTTON_TEXT") CREATE_BUTTON_TEXT = cratebt;
			var renamebt = _GetText("fmw_RENAME_BUTTON_TEXT");
			if (renamebt!="fmw_RENAME_BUTTON_TEXT") RENAME_BUTTON_TEXT = renamebt;
			var movebt = _GetText("fmw_MOVE_BUTTON_TEXT");
			if (movebt!="fmw_MOVE_BUTTON_TEXT") MOVE_BUTTON_TEXT = movebt;
			var deletebt = _GetText("fmw_DELETE_BUTTON_TEXT");
			if (deletebt!="fmw_DELETE_BUTTON_TEXT") DELETE_BUTTON_TEXT = deletebt;
			
			var newnamelabel = _GetText("fmw_NEW_NAME_LABEL");
			if (newnamelabel!="fmw_NEW_NAME_LABEL") NEW_NAME_LABEL = newnamelabel;
			var selectedlabel = _GetText("fmw_SELECTED_LABEL");
			if (selectedlabel!="fmw_SELECTED_LABEL") SELECTED_LABEL = selectedlabel;

			var ttcnl = _GetText("fmw_TREE_TITLE_CREATE_NODE_LABEL");
			if (ttcnl!="fmw_TREE_TITLE_CREATE_NODE_LABEL") TREE_TITLE_CREATE_NODE_LABEL = ttcnl;
			var ttrnl = _GetText("fmw_TREE_TITLE_RENAME_NODE_LABEL");
			if (ttrnl!="fmw_TREE_TITLE_RENAME_NODE_LABEL") TREE_TITLE_RENAME_NODE_LABEL = ttrnl;
			var ttmfnl = _GetText("fmw_TREE_TITLE_MOVE_FROM_NODE_LABEL");
			if (ttmfnl!="fmw_TREE_TITLE_MOVE_FROM_NODE_LABEL") TREE_TITLE_MOVE_FROM_NODE_LABEL = ttmfnl;
			var ttmtnl = _GetText("fmw_TREE_TITLE_MOVE_TO_NODE_LABEL");
			if (ttmtnl!="fmw_TREE_TITLE_MOVE_TO_NODE_LABEL") TREE_TITLE_MOVE_TO_NODE_LABEL = ttmtnl;
			var ttdnl = _GetText("fmw_TREE_TITLE_DELETE_NODE_LABEL");
			if (ttdnl!="fmw_TREE_TITLE_DELETE_NODE_LABEL") TREE_TITLE_DELETE_NODE_LABEL = ttdnl;

			var warningenn = _GetText("fmw_WARNING_EMPTY_NEW_NAME");
			if (warningenn!="fmw_WARNING_EMPTY_NEW_NAME") WARNING_EMPTY_NEW_NAME = warningenn;
			var warningns = _GetText("fmw_WARNING_NOT_SELECTED");
			if (warningns!="fmw_WARNING_NOT_SELECTED") WARNING_NOT_SELECTED = warningns;
			var warningns2 = _GetText("fmw_WARNING_NOT_SELECTED2");
			if (warningns2!="fmw_WARNING_NOT_SELECTED2") WARNING_NOT_SELECTED2 = warningns2;
			var warningsin = _GetText("fmw_WARNING_SEPARATOR_IN_NAME");
			if (warningsin!="fmw_WARNING_SEPARATOR_IN_NAME") WARNING_SEPARATOR_IN_NAME = warningsin;
		}
	}

	function _GetText(key,defaultValue) {
		try{
			return controller.getText(key,defaultValue);
		} catch (e) { if(defaultValue!=null) return defaultValue; else return key;}
	}

	_GetTextsFromResourceBundle();
}