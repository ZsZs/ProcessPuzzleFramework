//BrowserEvent.js JavaScript file

//This class provides a unified - browser agnostic - interface for handling events.
//

//constructor function
function BrowserEvent(theEvent) {
	var anEvent = (!theEvent ? window.event : theEvent);				//set window event if browser doesn't sents
	var sourceElement;

	//public accessors
	this.getType = function() {return anEvent.type;}
	this.getSourceElement = function() {return sourceElement;}
//Ez minek?	this.getEvent = function() {return anEvent;}

	//Identify the source element of event
	if (anEvent.target) sourceElement = anEvent.target;
	else if (anEvent.srcElement) sourceElement = anEvent.srcElement;
	if (sourceElement.nodeType == 3) sourceElement = sourceElement.parentNode;	//defeat Safary bug
}