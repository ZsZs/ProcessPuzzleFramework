// CustomCommand.js

// newFunction
function CustomCommand(statement) {
	//check parameter assertions
	AssertUtil.assertParamNotNull(statement, "statement");

	// inherit from WebUiCommand to make this object a specialized command
  	inheritFrom(this, new WebUiCommand( )); 

	//private instance variables
	var actionStatement = statement;
	var self = this;
	
	//public accessors methods
	this.execute = _Execute;
	
	//public mutators methods

	//private methods
	function _Execute () {
		eval(actionStatement);
	}
}