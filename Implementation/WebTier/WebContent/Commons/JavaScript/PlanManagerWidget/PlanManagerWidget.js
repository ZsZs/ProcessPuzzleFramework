// FolderManagerWidget.js

	//public constants
	PLAN_ID_PREFIX = "Plan:";
	ACTION_ID_PREFIX = "Action:";
	PLAN_URI_PROPERTY_NAME = "PlanHierarchy";
	PLAN_TRANSFORMATION_URI_PROPERTY_NAME = "PlanToTreeTransformationSchema";
	PLAN_MANAGER_FORM_NAME = "PlanManagerForm";
	DELETE_ACTION_BUTTON_CAPTION = "DeleteActionButtonCaption";
	INSERT_ACTION_BUTTON_CAPTION = "InsertActionButtonCaption";
	
/*****************************************
* modes(theMode parameter):
*       0: modal window
*       1: in div
******************************************/

function PlanManagerWidget( theController, theWindowMode, thePlanDataUri, theName ) {
	//check parameter assertions
	AssertUtil.assertParamNotNull( theController, "theController" );
	if(theWindowMode != 0 && theWindowMode != 1) throw new InvalidParameterException("PlanManagerWidget: theWindowMode can be only 0 or 1");
	AssertUtil.assertParamNotNull( thePlanDataUri, "thePlanDataUri");

//	if(theWindowMode == 1 && theContainerElement == null ) throw new InvalidParameterException();
//	if(theWindowMode == 1 && (!theContainerElement.tagName || theContainerElement.tagName.toUpperCase() != "DIV")) throw new InvalidParameterException();

	//private instance variables
	var containerElement = null;
	var self = this;
	var name = ( theName != null && theName != "" ) ? theName : "PlanManagerWidget";
	var windowMode = theWindowMode;
	var uiController = theController;
	var i18Resource = uiController.getResourceBundle();
	var configuration = uiController.getApplicationConfiguration();
	var planDataUri = thePlanDataUri;
	var pictureFolder = "";
	var planManagerFormElement = null;	
	var selectedActionSpanElement = null;
	var selectedActionAnchorElement = null;
	var selectedActionTextElement = null;
	var selectedActionId = null;
	var selectedActionName = null;
	var deleteActionButton = null;
	var insertActionButton = null;
	var newActionNameElement = null;
	var planTreeWidget = null;
	var isVisible = false;
	
	// initialization
	if( theName != null && theName != "" ) containerElement = document.getElementById( theName );
	
	// inherit from BrowserWidget
	var parent = new BrowserWidget(document, containerElement, theController.getResourceBundle() );
  	inheritFrom(this, parent);

	//private constants
	var SELECTED_SEPARATOR = ".";
	
	//public accessor methods
	this.isVisible = function () { return isVisible; }
	this.getPlanTreeWidget = function () {return planTreeWidget;}
	this.getSelectedActionTextElement = function () { return selectedActionTextElement; }
	
	//public mutator methods
	this.setPictureFolder = function( folder ) { pictureFolder = folder; }
	this.show = _Show;
	this.hide = _Hide;
	
	//private methods
	function _Show () {
		_ConstructPlanManagerForm();
		isVisible = true;
	}
	
	function _Hide () {
		_RemoveTreeWidget();
		isVisible = false;
	}
	
	//HTML construction methods
	function _ConstructPlanManagerForm () {
		var planManagerFormElement = self.appendFormElement ( PLAN_MANAGER_FORM_NAME, "POST" );
		var readOnlyContainer = self.insertReadOnlyContainer( planManagerFormElement );

		_ConstructPlanTreeRow( readOnlyContainer );
		_ConstructSelectedActionRow( readOnlyContainer );
		_ConstructInsertActionRow( readOnlyContainer );
		
		if( uiController != null && uiController != "" ) planTreeWidget.setResourceBundle(uiController.getResourceBundle());
		planTreeWidget.setSourceXmlFileName( planDataUri );
		planTreeWidget.setTransformationXslFileName( configuration.getProperty( PLAN_TRANSFORMATION_URI_PROPERTY_NAME ));
//		planTreeWidget.setPictureFolder( pictureFolder );
		planTreeWidget.loadWidget();
		planTreeWidget.showWidget();

		planTreeWidget.functionInSelect = _OnPlanClick;
		
		return readOnlyContainer;
	}
	
	function _ConstructPlanTreeRow( readOnlyContainer ) {
		var label = self.createRowLabelElement("Workflow actitities:");
		var value = self.createRowValueElement();
		
        var area = self.createElement("div");
        area.id="PlanTreeWidget";
        area.style.border="1px dotted";
        value.appendChild(area);
		self.insertRow( readOnlyContainer, label, value );
        planTreeWidget = new TreeWidget(area, self.getHtmlDOMDocument());
	}

	function _ConstructSelectedActionRow( readOnlyContainer ) {
		var label = self.createRowLabelElement( "Selected activity:" );
		selectedActionSpanElement = self.createRowValueElement();
		selectedActionAnchorElement = self.createAnchor( 0, "", _OnSelectedActionClick );
		selectedActionTextElement = selectedActionAnchorElement.firstChild;
		selectedActionSpanElement.appendChild( selectedActionAnchorElement );
		selectedActionSpanElement.appendChild( self.createElement( "BR" ));
		deleteActionButton = self.createButton( DELETE_ACTION_BUTTON_CAPTION,  _OnDeleteActionClick );
		selectedActionSpanElement.appendChild( deleteActionButton );
		self.insertRow( readOnlyContainer, label, selectedActionSpanElement );
	}
	
	function _ConstructInsertActionRow( readOnlyContainer ) {
		var label = self.createRowLabelElement( "Insert activity:" );
		var value = self.createRowValueElement();
		
		var protocolList = self.createElement( "SELECT" );
		value.appendChild( protocolList );
		
		newActionNameElement = self.createElement( "INPUT" );
		newActionNameElement.type = "text";
		value.appendChild( newActionNameElement );
		value.appendChild( self.createElement( "BR" ));
		
		insertActionButton = self.createButton( INSERT_ACTION_BUTTON_CAPTION,  _OnInsertActionClick );
		value.appendChild( insertActionButton );
		self.insertRow( readOnlyContainer, label, value );		
	}
	
	function _RemoveTreeWidget () {
		planTreeWidget.hideWidget();
		self.removeWidget();
	}
	
	function _OnPlanClick () {
		selectedActionName = planTreeWidget.getSelectedNodeFullCaption();
		var newTextElement = self.createTextNode( selectedActionName );
		selectedActionAnchorElement.replaceChild( newTextElement, selectedActionTextElement );
		selectedActionTextElement = newTextElement;
		deleteActionButton.disabled = false;
		insertActionButton.disabled = false;
		
		selectedActionId = planTreeWidget.getSelectedNode().getID();
		selectedActionName = planTreeWidget.getSelectedNode().getName();
	}
	
	function _OnSelectedActionClick () {
		var actionId = "";
		if( selectedActionId.indexOf( PLAN_ID_PREFIX ) >= 0 ) {
			actionId = selectedActionId.substring( selectedActionId.indexOf( PLAN_ID_PREFIX ) + PLAN_ID_PREFIX.length );
			uiController.loadDocument('PlanDataSheet', selectedActionName, '&artifactName=' + selectedActionName );
		}
		else if( selectedActionId.indexOf( ACTION_ID_PREFIX ) >= 0 ) {
			id = selectedActionId.substring( selectedActionId.indexOf( ACTION_ID_PREFIX  + ACTION_ID_PREFIX.length ));
			uiController.loadDocument('ActionDataSheet', selectedActionName, '&artifactName=' + selectedActionName  );
		}else throw new UndefinedActionPrefixException( PLAN_ID_PREFIX + ", nor " + ACTION_ID_PREFIX );
	}
	
	function _OnDeleteActionClick () {
		alert("Don't worry. We are on the way to implement this functionality.");
	}
	
	function _OnInsertActionClick () {
		alert("Don't worry. We are on the way to implement this functionality.");
	}
}

	function UndefinedActionPrefixException( prefix ){
		this.base = CustomError;
		this.base("( None of the following id prefixes found int plan tree definition: ) " + prefix );
	}
	
	UndefinedActionPrefixException.prototype = CustomError;
