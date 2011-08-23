// TextEditor.js

// TextEditor
function TextEditor(commitEventHandler, cancelEventHandler) {
	//private instance variables
	var onCommit = commitEventHandler;
	var onCancel = cancelEventHandler;
	var visible = false;
	var commitButton = null;
	var cancelButton = null;

	createButtons();
	
	//public accessor methods
	this.isVisible = function() {return visible;}
		
	//public mutator methods
	this.show = function() {return true;}

	//private methods
	function createButtons () {
		//create buttons for edit mode
		commitButton = document.createElement('BUTTON');
		var caption = document.createTextNode('Commit');
		commitButton.appendChild(caption);
		commitButton.onclick = onCommit;
		commitButton.id = 'CommitEditButton';

		cancelButton = document.createElement('BUTTON');
		caption = document.createTextNode('Cancel');
		cancelButton.appendChild(caption);
		cancelButton.onclick = onCancel;
		cancelButton.id = 'CancelEditButton';
	}
}