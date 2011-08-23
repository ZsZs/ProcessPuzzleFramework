// MenuItem.js

function MenuItem( theName, theCaption, thePreferedIndex ) {
	// inherit from CSimpleObservable to make this object an Observable
  	inheritFrom( this, new CSimpleObservable() );
  	
	//private instance variables
  	var isVisible = false;
	var logger = null;
	var name = theName;
	var caption = theCaption;
	var preferedIndex = thePreferedIndex; //0,1,2,3,...

	//public accessors methods
	this.getCaption = function() {return caption;}
	this.isVisible = function() { return isVisible; }
	this.getName = function() {return name;}
	this.getPreferedIndex = function() {return preferedIndex;}

	//public mutators methods
	this.setCaption = function(newCaption) {caption=newCaption;}
	this.show = _Show;
	this.hide = _Hide;
	this.onClick = _OnClickEventHandler;	
	
	_Constructor();
	
	//Constructors
	function _Constructor() {
		logger = log4javascript.getLogger( ROOT_LOGGER_NAME + ".menuWidget.menuItem" );
		logger.debug( "'MenuItem' was instantiated. name:" + name + ", caption:" + caption + ", preferecIndes:" + thePreferedIndex );
	}
	
	//private methods
	function _Show( element ) { isVisible = true; } //nem itt

	function _Hide( element ) { isVisible = false; } //nem itt
	
	function _OnClickEventHandler( theEvent ) {
		alert("On menuItem click()!");
		if( logger.getLevel() == log4javascript.Level.TRACE )
			logger.trace( "Click event:" + theEvent + " on menu:" + name + " was captured." );
	}
}