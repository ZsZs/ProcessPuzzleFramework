// CompositMenuItem.js

function CompositMenuItem(theName, theCaption, thePreferedIndex) {
  	inheritFrom(this, new MenuItem(theName, theCaption, thePreferedIndex));

	//private contants
	var DT = "dt";
	var DTCLASSNAME = "dtcn";
	var DTSPANCLASSNAME = "dtscn";
	var SPAN = "span";

	//private instance variables
	var definitionTermElement = null; //DOM.Element
	var DLElement = null; //DOM.Element
	var isSubmenusExpanded = false; //Boolean
	var subMenus = new Collection();
	var self = this;
	var spanForCaption;
	var captionTextNode;

	//public accessor methods
	this.getDefinitionTermElement = function() {return definitionTermElement;}
	this.getCountOfItems = function() {return subMenus.getCountOfObjects();}
	this.findMenuByName = function(menuName) {return subMenus.item(menuName);}

	//public mutator methods
	this.addSubMenu = _AddSubMenu;
	this.addDualStateMenu = _AddDualStateMenu;
	this.removeMenuItem = _RemoveMenuItem;
	this.expandSubMenus = _ExpandSubMenus;
	this.callopseSubMenus = _CallopseSubMenus;
	this.show = _Show;
	this.hide = _Hide;
	this.changeCaption = _ChangeCaption;	
	//this.observe = ...;

	//private methods
	function _AddSubMenu(menuName,caption,command,preferedIndex) {
		if(!subMenus.exists(menuName)) {
			var subMenu = new SubMenuItem(menuName,caption,command,preferedIndex);
			//subMenu.addObserver(self);
			subMenus.add(menuName, subMenu);
			if(isSubmenusExpanded && definitionTermElement) subMenu.show(definitionTermElement);
		}
	}

	function _AddDualStateMenu(menuName,caption,command,isOn,preferedIndex) {
		if(!subMenus.exists(menuName)) {
			var dualStateMenu = new DualStateMenuItem(menuName,caption,command,isOn,preferedIndex);
			//subMenu.addObserver(self);
			subMenus.add(menuName, dualStateMenu);
			if(isSubmenusExpanded && definitionTermElement) dualStateMenu.show(definitionTermElement);
		}
	}

	function _RemoveMenuItem(menuName) {
		if(subMenus.exists(menuName)) {
			var subMenu = subMenus.item(menuName);
			if (isSubmenusExpanded) subMenu.hide();
			subMenus.remove(menuName);
		}
	}

	function _ExpandSubMenus() {
		if(!isSubmenusExpanded) {
			subMenus.moveFirst();
			var aMenu;
			while (aMenu = subMenus.getNext()) {
				aMenu.show(definitionTermElement);
			}
			isSubmenusExpanded = true;
		}
	}

	function _CollapseSubMenus() {
		if(isSubmenusExpanded) {
			subMenus.moveFirst();
			var aMenu;
			while (aMenu = subMenus.getNext()) {
				aMenu.hide();
			}
			isSubmenusExpanded = false;
		}
	}

	function _OnClickEventHandler(theEvent) {
		browserEvent = new BrowserEvent(theEvent);
		if(browserEvent.getType() == "click") {
			self.notify(self);
		}
	}

	function _Show(theDLElement) {
		DLElement = theDLElement;
		if(DLElement && !definitionTermElement)
			_InsertDefinitionTermElement();
	}

	function _Hide() {
		if(DLElement && definitionTermElement) {
			subMenus.moveFirst();
			var aMenu;
			while(aMenu = subMenus.getNext()) {
				aMenu.hide();
			}
			_RemoveDefinitionTermElement();
		}
	}

	function _InsertDefinitionTermElement() {
		if(DLElement != null) {
			definitionTermElement = document.createElement(DT);
			definitionTermElement.setAttribute("className", DTCLASSNAME);
			spanForCaption=document.createElement(SPAN);
			spanForCaption.setAttribute("className", DTSPANCLASSNAME);

			//Add click event listener
			if(typeof spanForCaption.addEventListener != 'undefined') {		//DOM 2 event handling
				spanForCaption.addEventListener('click', _OnClickEventHandler, false);
			}else if(typeof spanForCaption.attachEvent != 'undefined') {	// IE5+ event handling
				spanForCaption.attachEvent('onclick', _OnClickEventHandler);
			}else {spanForCaption.onclick = _OnClickEventHandler;}			// IE4 event handling

			var nb_caption = self.getCaption().replace(/ /g,String.fromCharCode(160));
			captionTextNode=document.createTextNode(nb_caption);
			spanForCaption.appendChild(captionTextNode);
			definitionTermElement.appendChild(spanForCaption);

			if(self.getPreferedIndex()!=null && self.getPreferedIndex()>=0 &&
				DLElement.childNodes.length>self.getPreferedIndex())
				DLElement.insertBefore(definitionTermElement,DLElement.childNodes[self.getPreferedIndex()]);
			else
				DLElement.appendChild(definitionTermElement);
		}
		else {
			throw new UserException("Division not defined", "CompositMenuItem._InsertDefinitionListElement()");
		}
	}

	function _RemoveDefinitionTermElement() {
		if(DLElement != null) {
			if(definitionTermElement != null) {
				DLElement.removeChild(definitionTermElement);
				definitionTermElement = null;
				captionTextNode = null;
				spanForCaption = null;
			}
		}
		else {
			throw new UserException("Division not defined", "CompositMenuItem._RemoveDefinitionListElement()");
		}
	}

	function _ChangeCaption(controller) {
		self.setCaption(controller.getText(self.getName()));
		if(spanForCaption != null && captionTextNode != null) {
			spanForCaption.removeChild(captionTextNode);
			var nb_caption = self.getCaption().replace(/ /g,String.fromCharCode(160));
			captionTextNode=document.createTextNode(nb_caption);
			spanForCaption.appendChild(captionTextNode);

			subMenus.moveFirst();
			var aMenu;
			while(aMenu = subMenus.getNext()) {
				aMenu.changeCaption(controller);
			}
		}
	}
}