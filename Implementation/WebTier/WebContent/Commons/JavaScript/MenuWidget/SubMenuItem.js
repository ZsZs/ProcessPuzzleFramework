// SubMenuItem.js

function SubMenuItem(theName, theCaption, theCommand, thePreferedIndex) {
  	inheritFrom(this, new MenuItem(theName, theCaption, thePreferedIndex));

	//private contants
	var DD = "dd";
	var DDCLASSNAME = "ddcn";

	//private instance variables
	var definitionDescriptionElement; //DOM.Element
	var DTElement = null; //DOM.Element
	var command = theCommand;
	var self = this;
	var spanForCaption;
	var captionTextNode;

	//public accessor methods
	this.getCommand = function () {return command;}

	//public mutator methods
	this.show = _Show;
	this.hide = _Hide;
	this.changeCaption = _ChangeCaption;

	//private methods
	function _ChangeCaption(controller) {
		self.setCaption(controller.getText(self.getName()));
		if(spanForCaption != null && captionTextNode != null) {
			spanForCaption.removeChild(captionTextNode);
			var nb_caption = self.getCaption().replace(/ /g,String.fromCharCode(160));
			captionTextNode=document.createTextNode(nb_caption);
			spanForCaption.appendChild(captionTextNode);
		}
	}

	function _Hide() {
		if(DTElement && definitionDescriptionElement)
			_RemoveDefinitionDescriptionElement();
	}

	function _InsertDefinitionDescriptionElement() {
		if(DTElement != null) {
			definitionDescriptionElement = document.createElement(DD);
			definitionDescriptionElement.setAttribute("className", DDCLASSNAME);
			definitionDescriptionElement.setAttribute("id", self.getName());
			spanForCaption=document.createElement("span");

			var nb_caption = self.getCaption().replace(/ /g,String.fromCharCode(160));
			captionTextNode=document.createTextNode(nb_caption);
			spanForCaption.appendChild(captionTextNode);

			//Add click event listener
			if(typeof definitionDescriptionElement.addEventListener != 'undefined') {		//DOM 2 event handling
				definitionDescriptionElement.addEventListener('click', _OnClickEventHandler, false);
			}else if(typeof definitionDescriptionElement.attachEvent != 'undefined') {		// IE5+ event handling
				definitionDescriptionElement.attachEvent('onclick', _OnClickEventHandler);
			}else {definitionDescriptionElement.onclick = _OnClickEventHandler;}			// IE4 event handling

			definitionDescriptionElement.appendChild(spanForCaption);

			if(self.getPreferedIndex()!=null && self.getPreferedIndex()>=0 &&
				DTElement.childNodes.length>1 &&
				DTElement.childNodes.length-1>self.getPreferedIndex())
				DTElement.insertBefore(definitionDescriptionElement,DTElement.childNodes[self.getPreferedIndex()+1]);
			else
				DTElement.appendChild(definitionDescriptionElement);
		}
		else {
			throw new UserException("Division not defined", "SubMenuItem._InsertDefinitionDescriptionElement()");
		}
	}

	function _OnClickEventHandler(theEvent) {
		browserEvent = new BrowserEvent(theEvent);
		if(browserEvent.getType() == "click"){
			self.notify(self);
			if (command  && command.execute) command.execute();
		}
	}

	function _RemoveDefinitionDescriptionElement() {
		if(DTElement != null) {
			if(definitionDescriptionElement != null) {
				DTElement.removeChild(definitionDescriptionElement);
				definitionDescriptionElement = null;
				captionTextNode = null;
				spanForCaption = null;
			}
		}
		else {
			throw new UserException("Division not defined", "SubMenuItem._RemoveDefinitionDescriptionElement()");
		}
	}

	function _Show(theDTElement) {
		DTElement = theDTElement;
		if(DTElement && !definitionDescriptionElement)
			_InsertDefinitionDescriptionElement();
	}
}