// MessageWallManageCommand.js

// MessageWallManageCommand
function MessageWallManageCommand( theContextRootPrefix ) {
	//check parameter assertions

	// inherit from WebUiCommand to make this object a specialized command
  	inheritFrom(this, new WebUiCommand()); 

	//private instance variables
  	MESSAGE_WALL_TYPE = "MessageWall";
	MESSAGE_WALL_NAME = "MessageWall";
	var logger = null;
	var contextRootPrefix = theContextRootPrefix;
	var self = this;

	//public accessors methods
	this.execute = _Execute;

	//public mutators methods

	_Constructor();
	
	//Constructors
	function _Constructor() {
		logger = log4javascript.getLogger( ROOT_LOGGER_NAME + ".webUiCommand.MessageWallManageCommand" );
		logger.debug( "'MessageWallManageCommand' was instantiated with 'contextRootPrefix:" + contextRootPrefix + "'." );
	}
	
	//private methods
	function _Execute( isOn ) {
		var controller = self.getController();
		if(controller != null) {

			if(isOn != null && isOn == true) {
				controller.loadInfoPanelDocumentByName( MESSAGE_WALL_TYPE, MESSAGE_WALL_NAME );
			} else 
			{
				var documentSelector = controller.getInfoPanelManager().getDocumentSelector();
				var activeDocument = controller.getInfoPanelManager().getActiveDocument();
				if(activeDocument != null && documentSelector != null) {
					if(activeDocument.getDocumentName() == MESSAGE_WALL_NAME) documentSelector.onCloseClick();
					else
					{
						documentSelector.removeTab(MESSAGE_WALL_NAME);
						controller.getInfoPanelManager().unLoadDocument(MESSAGE_WALL_NAME);
					}
				}
			}
		}
	}
}