// ShowDocumentPropertiesCommand.js

// ShowDocumentPropertiesCommand
function ShowDocumentPropertiesCommand( theContextRootPrefix ) {
	//check parameter assertions

	// inherit from WebUiCommand to make this object a specialized command
  	inheritFrom( this, new WebUiCommand() ); 

	//private instance variables
	var logger = null;
	var contextRootPrefix = theContextRootPrefix;
	var documentPropertiesInfoPageName = null;
	var self = this;

	//public accessors methods
	this.execute = _Execute;

	//public mutators methods

	_Constructor();
	
	//Constructors
	function _Constructor() {
		logger = log4javascript.getLogger( ROOT_LOGGER_NAME + ".webUiCommand.showDocumentPropertiesCommand" );
		logger.debug( "'ShowDocumentPropertiesCommand' was instantiated with 'contextRootPrefix:" + contextRootPrefix + "'." );
	}
	
	//private methods
	function _Execute( isOn ) {
		logger.trace( "'Executing' isOn:" + isOn );
		
		var controller = self.getController();
		if(controller != null) {
			documentPropertiesInfoPageName = controller.getText("DocumentPropertiesInfoPageName");

			if(isOn != null && isOn == true) {
				controller.loadInfoPanelDocumentByUri( null, documentPropertiesInfoPageName, contextRootPrefix + "InfoNavigator/Info.jsp" );
				_ShowActiveDocumentProperties( controller );
			} else {
				var documentSelector = controller.getInfoPanelManager().getDocumentSelector();
				var activeDocument = controller.getInfoPanelManager().getActiveDocument();
				if(activeDocument != null && documentSelector != null) {
					if(activeDocument.getDocumentName() == documentPropertiesInfoPageName) documentSelector.onCloseClick();
					else {
						documentSelector.removeTab(documentPropertiesInfoPageName);
						controller.getInfoPanelManager().unLoadDocument(documentPropertiesInfoPageName);
					}
				}
			}
		}
	}

	function _ShowActiveDocumentProperties( controller ) {
		var documentManager = controller.getDocumentManager();
		var infoPanelManager = controller.getInfoPanelManager();
		var activeInfoPanel = infoPanelManager.getActiveDocument();

		if(activeInfoPanel == null || activeInfoPanel.getDocumentName() != documentPropertiesInfoPageName) {
			return;
		}

		if(documentManager.getDocumentCount() == 0){
			controller.loadInfoPanelDocumentByUri( null, documentPropertiesInfoPageName, contextRootPrefix + "InfoNavigator/ShowDocumentPropertiesError.jsp" );
			return;
		}

		var activeDocument = documentManager.getActiveDocument();
		var propertyView = activeDocument.getViewByClass("PropertyView");
		if(propertyView != null) {
			controller.loadInfoPanelDocumentByUri( null, documentPropertiesInfoPageName, propertyView.getContentUrl() );
		}
	}
}