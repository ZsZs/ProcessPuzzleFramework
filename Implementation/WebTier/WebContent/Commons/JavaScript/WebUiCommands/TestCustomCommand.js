// TestCustomCommand.js

// newFunction
function TestCustomCommand() {
	// inherit from WebUiCommand to make this object a specialized command
  	inheritFrom(this, new WebUiCommand( )); 

	//private instance variables
	
	//public accessors methods
	this.execute = _Execute;
	
	//public mutators methods

	//private methods
	function _Execute () {
		alert("Hello word!");
	}
}