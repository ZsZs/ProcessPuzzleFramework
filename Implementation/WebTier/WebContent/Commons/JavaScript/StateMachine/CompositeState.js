var STATE_IDENTIFIER_DELIMITER = "/";

var CompositeState = new Class({
	Extends: State,
	initialize: function( name, description ){
		this.parent( name, description );
		this.possibleSubStates = new Collection();
		this.currentState = null;
	},
	
	addSubState: function( subState ) {
		this.possibleSubStates.add( subState.name, subState );
		subState.parentState = this;
	},
	
	removeSubState: function( subStateName ) {
		this.possibleSubStates.remove( subStateName );
	},
	
	reset: function() {
		this.validate();
		
		var subState = null;
		for( var index = 0; index < this.possibleSubStates.getCountOfObjects(); index++ ) {
			subState = this.possibleSubStates.getItemByIndex( index );
			if( subState instanceof StartState ) {
				this.currentState = subState;
				break;
			}
		}
	},
	
	findSubStateByKind: function( kindOfSubState ) {
		var subState = null;
		for( var index = 0; index < this.possibleSubStates.getCountOfObjects(); index++ ) {
			subState = this.possibleSubStates.getItemByIndex( index );
			if( instanceOf( subState, kindOfSubState ) ) {
				return subState;
			}
		}
		return null;
	},
	
	validate: function() {
		if( this.findSubStateByKind( StartState ) == null ) 
			throw new InvalidStateMachineException( this, "Validation error: missing start state.", "CompositeState.js" ); 
		
		if( this.findSubStateByKind( EndState ) == null ) 
			throw new InvalidStateMachineException( this, "Validation error: missing end state.", "CompositeState.js" ); 
		
		return true;
	},
	
	uniqueStateIdentifier: function() {
		return "hello";
	}
});

function InvalidStateMachineException( stateMachine, message, source ){
	this.base = CustomError;
	this.base("( InvalidStateMachineException ) " + message );
	this.stateMachine = stateMachine;
}

InvalidStateMachineException.prototype = CustomError;

