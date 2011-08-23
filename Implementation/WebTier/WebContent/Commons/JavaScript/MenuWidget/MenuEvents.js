// MenuEvents.js

function MenuEvent(theMenuItem) {
//private contants
//private instance variables
	var menuItem = theMenuItem;
//public accessor methods
	this.getMenuItem = function() {return menuItem;}
//public mutator methods
	this.setMenuItem = function(mi) { menuItem = mi; }
//private methods
}



function SubMenuExpandEvent(theMenuItem, theExpandedMenu){
  	inheritFrom(this, new MenuEvent(theMenuItem));
//private instance variables
	var expandedMenu = theExpandedMenu; //CompositMenuItem
//public accessor methods
	this.getExpandedMenu = function() {return expandedMenu;}
}



function ActionInvocationEvent(theMenuItem, theActionUrl){
  	inheritFrom(this, new MenuEvent(theMenuItem));
//private instance variables
	var actionUrl = theActionUrl; //String
//public accessor methods
	this.getActionUrl = function() {return actionUrl;}
}



function MenuStateChangedEvent(theMenuItem, theIsOn){
  	inheritFrom(this, new MenuEvent(theMenuItem));
//private instance variables
	var isOn = theIsOn; //Boolean
//public accessor methods
	this.isIsOn = function() {return isOn;}
}