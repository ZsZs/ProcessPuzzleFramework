// WebUiCommand.js

// WebUiCommand
function WebUiCommand( theController ) {
	//check parameter assertions
	//	if(theController == null || theController == "") throw new InvalidParameterException(theController);	//theController can't be "" or null

	//private instance variables
	var logger = null;
	var controller = null;
	if (theController == undefined) {
		try { controller = webUIController; } 
		catch (e) {}
	}else controller = theController;
	
	//public accessors methods
	this.getController = function () {return controller;}
	
	//public mutators methods
	this.execute = _Execute;

	_Constructor();
	
	//Constructors
	function _Constructor() {
		logger = log4javascript.getLogger( ROOT_LOGGER_NAME + ".webUiCommand" );
	}
	
	//private methods
	function _Execute () {}
}