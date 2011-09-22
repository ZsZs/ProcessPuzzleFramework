// ModalWindowBase.js

// ModalWindowBase
function ModalWindowBase( theStylesPath ) {
	//check parameter assertions

  	//private constants
  	var CONTAINER_DIV_ID = "container";

	//private instance variables
  	var stylesPath = (theStylesPath == null ? "./Skins/Default/Styles" : theStylesPath );
	var width = "450";
	var height = "150";
	var left = "325";
	var top = "300";
	var toolbar = "0";
	var location = "0";
	var status = "0"
	var menubar = "0";
	var scrollbars = "0";
	var resizable = "1";
	var modalWindow = null;
	var containerElementInWindow;

	//public accessors methods

	//public mutators methods
	this.setWidth = function ( newValue ) { width = new String( newValue ); }
	this.setHeight = function ( newValue ) { height = new String( newValue ); }
	this.setLeft = function ( newValue ) { left = new String( newValue ); }
	this.setTop = function ( newValue ) { top = new String( newValue ); }
	this.setToolbar = function ( newValue ) { toolbar = new String( newValue ); }
	this.setLocation = function ( newValue ) { location = new String( newValue ); }
	this.setStatus = function ( newValue ) { status = new String( newValue ); }
	this.setMenubar = function ( newValue ) { menubar = new String( newValue ); }
	this.setScrollbars = function ( newValue ) { scrollbars = new String( newValue ); }
	this.setResizable = function ( newValue ) { resizable = new String( newValue ); }

	//private methods
	this.createModalWindow = _CreateModalWindow;
	this.setUpInputTextsAndSelects = _SetUpInputTextsAndSelects;
	this.getElementInWindowById = _GetElementInWindowById;
	this.constructButton = _ConstructButton;
	this.appendNewOption = _AppendNewOption;
	this.createTextNode = _CreateTextNode;
	this.createElement = _CreateElement;
	this.addElement = _AddElement;
	this.createFormRow = _CreateFormRow;
	this.closeModalWindow = _CloseModalWindow;
	this.initCalendar = _InitCalendar;


	// constructive methods
	function _CreateModalWindow(title) {
//		var args='width=400,height=150,left=325,top=300,toolbar=0,';
//		args+='location=0,status=1,menubar=0,scrollbars=1,resizable=1';
		var args = "width=" + width;
		args += ", height=" + height;
		args += ", left=" + left;
		args += ", top=" + top;
		args += ", toolbar=" + toolbar;
		args += ", location=" + location;
		args += ", status=" + status;
		args += ", menubar=" + menubar;
		args += ", scrollbars=" + scrollbars;
		args += ", resizable=" + resizable;

		modalWindow=window.open("","mwWindow",args);
		modalWindow.document.open();
		modalWindow.document.write( _ConstructWindowContent(title) );
		modalWindow.document.close();
		modalWindow.focus();

		containerElementInWindow = modalWindow.document.getElementById(CONTAINER_DIV_ID);
	}

	function _SetUpInputTextsAndSelects() {
		modalWindow.setUpInputTexts();		
		modalWindow.setUpSelects();		
	}
	
	function _GetElementInWindowById(id) {
		return modalWindow.document.getElementById(id);
	}

	function _ConstructButton(labelText) {
		var button = modalWindow.document.createElement("input");
		button.type = "button";
		button.className = "buttonSmall";
		button.value = labelText;
		return button;
	}
	
	function _CreateTextNode(text) {
		return modalWindow.document.createTextNode(text);
	}

	function _CreateElement(elementName) {
		return modalWindow.document.createElement(elementName);
	}

	function _CreateFormRow(labelText, inputElementType, inputElementId, inputElementStyle ) {
		var rowElement = _CreateElement("div");
		rowElement.className = "row";
		var labelSpanElement = _CreateElement("span");
		labelSpanElement.className = "label";
		_AddElement(_CreateTextNode(labelText), labelSpanElement);
		_AddElement(labelSpanElement, rowElement);

		var inputSpanElement = _CreateElement("span");
		inputSpanElement.className = "formw";
		var inputElement = _CreateElement(inputElementType);
		inputElement.setAttribute("id", inputElementId);
		inputElement.setAttribute("name", inputElementId);
		if( inputElementStyle ) _SetElementStyle( inputElement, inputElementStyle );
		_AddElement(inputElement, inputSpanElement);
		_AddElement(inputSpanElement, rowElement);

		return rowElement;
	}

	function _AddElement(element, containingElement) {
		var contextElement = ((containingElement == undefined) ? containerElementInWindow : containingElement)
		contextElement.appendChild(element);
	}

	function _AppendNewOption( optionText, optionValue, selectElement  ) {
		var option = modalWindow.document.createElement("option");
		option.value = optionValue;
		option.text = optionText;
		selectElement.options.add( option );
		return option;
	}

	function _SetElementStyle ( element, styleExpression ) {
		AssertUtil.assertParamNotNull( element, "ModalWindowBase.SetElementStyle(param: element)");
		AssertUtil.assertParamNotNull( styleExpression, "ModalWindowBase.SetElementStyle(param: styleExpression)");

		var width = _RetrieveStyleValue( "width", styleExpression );
		if( width != null ) element.width = width.substr(0, width.match(/\d/));

		var height = _RetrieveStyleValue( "height", styleExpression );
		if( height != null ) element.height = height.substr(0, height.match(/\d/));
	}
	
	function _RetrieveStyleValue ( style, styleExpression ) {
		var value = null;
		var styleAttributes = new Map();
		var styleTokens = styleExpression.split(";");
		for( i = 0; i < styleTokens.length; i++ ) {
			var styleName = styleTokens[i].substr( 0, styleTokens[i].indexOf(":") );
			var styleValue = styleTokens[i].substr( styleTokens[i].indexOf(":") +1 );
			styleAttributes.put( styleName, styleValue );
		}
		return styleAttributes.get( style );
	}	

	function _CloseModalWindow() {
		modalWindow.close();
	}

	function _InitCalendar() {
		modalWindow.calendar_init();
	}

	//window creator method
	function _ConstructWindowContent(title) {
		htmlContent ='<html>\n';
		htmlContent+='<head>\n';
		htmlContent+='<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">';
		htmlContent+='<title>'+title+'</title>\n';
		htmlContent+='<link href="'+ stylesPath + '/base.css" rel="stylesheet">\n';
		htmlContent+='<link href="'+ stylesPath + '/form_styles.css" rel="stylesheet">\n';
		htmlContent+='<link href="'+ stylesPath + '/ModalWindow.css" rel="stylesheet">\n';
		htmlContent+='<script type="text/javascript">\n';
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
		htmlContent+='function setUpSelects() {\n';
		htmlContent+='        var selects = document.getElementsByTagName("select");\n';
		htmlContent+='        for (i=0; i<selects.length; i++)\n';
		htmlContent+='                if(selects[i].modalCompatible==null) {\n';
		htmlContent+='                        _AddEventListener(selects[i], "focus", _SkipcycleToTrue);\n';
		htmlContent+='                        _AddEventListener(selects[i], "blur", _SkipcycleToFalse);\n';
		htmlContent+='                        selects[i].modalCompatible="";\n';
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
		htmlContent+='<div id="'+CONTAINER_DIV_ID+'" class="editableContainer"></div>\n';
		htmlContent+='</body>\n';
		htmlContent+='</html>';
		return htmlContent;
	}
}