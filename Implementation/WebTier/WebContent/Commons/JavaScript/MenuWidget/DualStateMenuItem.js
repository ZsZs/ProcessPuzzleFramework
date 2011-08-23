// DualStateMenuItem.js

function DualStateMenuItem(theName, theCaption, theCommand, theIsOn, thePreferedIndex) {
 	var ancestor = new MenuItem(theName, theCaption, thePreferedIndex );
	inheritFrom( this, ancestor );

	//private contants
	var DD = "dd";
	var DDCLASSNAME = "ddcn";

	//private instance variables
	var logger = null;
	var definitionDescriptionElement; //DOM.Element
	var DTElement = null; //DOM.Element
	var isOn = _DetermineStatus( theIsOn );
	var command = theCommand;
	var checkboxElement = null;
	var self = this;
	var spanForCheckboxAndCaption;
	var captionTextNode;

	//public accessor methods
	this.isIsOn = function() { return isOn; }

	//public mutator methods
	this.changeCaption = _ChangeCaption;
	this.hide = _Hide;
	this.show = _Show;
	this.turnOff = _TurnOff;
	this.turnOn = _TurnOn;
//	this.onClick = _OnClickEventHandler;	

	_Constructor();
	
	//Constructors
	function _Constructor() {
		logger = log4javascript.getLogger( ROOT_LOGGER_NAME + ".menuWidget.dualStateMenuItem" );
	}
	
	//private methods
	function _ChangeCaption(controller) {
		self.setCaption(controller.getText(self.getName()));
		if(spanForCheckboxAndCaption != null && captionTextNode != null) {
			spanForCheckboxAndCaption.removeChild(captionTextNode);
			var nb_caption = self.getCaption().replace(/ /g,String.fromCharCode(160));
			captionTextNode=document.createTextNode(nb_caption);
			spanForCheckboxAndCaption.appendChild(captionTextNode);
		}
	}
	
	function _DetermineStatus( theIsOn ) {
		return (theIsOn==true || theIsOn=="true" ? true : false); //Boolean
	}
		
	function _Hide() {
		if(DTElement && definitionDescriptionElement)
			_RemoveDefinitionDescriptionElement();
		ancestor.hide();
	}

	function _InsertDefinitionDescriptionElement() {
		if(DTElement != null) {
			definitionDescriptionElement = document.createElement(DD);
			definitionDescriptionElement.setAttribute("className", DDCLASSNAME);
			spanForCheckboxAndCaption=document.createElement("span");

			var checkd=document.createElement("input");
			checkd.setAttribute("type","checkbox");
			if (isOn) checkd.setAttribute("defaultChecked",true);
			else checkd.setAttribute("defaultChecked",false);
			spanForCheckboxAndCaption.appendChild(checkd);
			checkboxElement=checkd;

			var nb_caption = self.getCaption().replace(/ /g,String.fromCharCode(160));
			captionTextNode=document.createTextNode(nb_caption);
			spanForCheckboxAndCaption.appendChild(captionTextNode);

			//Add click event listener
			if(typeof definitionDescriptionElement.addEventListener != 'undefined') {		//DOM 2 event handling
				definitionDescriptionElement.addEventListener('click', _OnClickEventHandler, false);
			}else if(typeof definitionDescriptionElement.attachEvent != 'undefined') {		// IE5+ event handling
				definitionDescriptionElement.attachEvent('onclick', _OnClickEventHandler);
			}else {definitionDescriptionElement.onclick = _OnClickEventHandler;}			// IE4 event handling

			definitionDescriptionElement.appendChild(spanForCheckboxAndCaption);

			if(self.getPreferedIndex()!=null && self.getPreferedIndex()>=0 &&
				DTElement.childNodes.length>1 &&
				DTElement.childNodes.length-1>self.getPreferedIndex())
				DTElement.insertBefore(definitionDescriptionElement,DTElement.childNodes[self.getPreferedIndex()+1]);
			else
				DTElement.appendChild(definitionDescriptionElement);
		}
		else {
			throw new UserException("Division not defined", "DualStateMenuItem._InsertDefinitionDescriptionElement()");
		}
	}

	function _OnClickEventHandler( theEvent ) {
		logger.trace( "Handling event:" + theEvent );
		
		browserEvent = new BrowserEvent( theEvent );
		if( browserEvent.getType() == "click" ){
			if(isOn) {
				if( checkboxElement ) checkboxElement.checked = false;
				isOn=false;
			} else
			{
				if(checkboxElement) checkboxElement.checked=true;
				isOn=true;
			}
			self.notify(self);

			if (command && command.execute) command.execute(isOn);
		}
	}

	function _Show( theDTElement ) {
		DTElement = theDTElement;
		if(DTElement && !definitionDescriptionElement)
			_InsertDefinitionDescriptionElement();
		
		ancestor.show();
	}
	
	function _TurnOff() {
		if( isOn ) { 
			isOn = false;
			if( this.isVisible() ) {
				if( checkboxElement ) checkboxElement.checked = false;				
			}
		}
	}
	
	function _TurnOn( theNewStatus ) {
		if( !isOn ) { 
			isOn = true;
			if( this.isVisible() ) {
				if( checkboxElement ) checkboxElement.checked = true;				
			}
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
			throw new UserException("Division not defined", "DualStateMenuItem._RemoveDefinitionDescriptionElement()");
		}
	}
}