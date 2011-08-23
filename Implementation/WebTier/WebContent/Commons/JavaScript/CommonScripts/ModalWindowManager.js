var skipcycle=false;

function _SkipcycleToTrue() {skipcycle=true;}
function _SkipcycleToFalse() {skipcycle=false;}	

function _AddEventListener(element,eventName,theFunction) {
	if (typeof element.addEventListener != 'undefined') {		//DOM 2 event handling
		element.addEventListener(eventName, theFunction, false);
	}else if (typeof element.attachEvent != 'undefined') {		// IE5+ event handling
		element.attachEvent("on"+eventName, theFunction);
	}else { eval("element.on"+eventName+" = "+theFunction+";");}	// IE4 event handling
}

function focusOnMe() {
	if (!skipcycle) {window.focus();}
	setTimeout("focusOnMe()", 10);
}

function setUpInputTexts() {
	var inputs = document.getElementsByTagName("input");
	for (i=0; i<inputs.length; i++)
		if(inputs[i].type=="text" && inputs[i].modalCompatible==null) {
			_AddEventListener(inputs[i], "focus", _SkipcycleToTrue);
			_AddEventListener(inputs[i], "blur", _SkipcycleToFalse);
			inputs[i].modalCompatible="";
		}
}

function startModal() { // call in body 'onload' event
	setUpInputTexts();
	setTimeout("focusOnMe()", 10);
}