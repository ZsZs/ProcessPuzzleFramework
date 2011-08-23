<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="./${skinDescriptor.stylesPath}/base.css" type="text/css">
<link rel="stylesheet" href="./${skinDescriptor.stylesPath}/content.css" type="text/css">
<link rel="stylesheet" href="./${skinDescriptor.stylesPath}/form_styles.css" type="text/css">
<link rel="stylesheet" href="./${skinDescriptor.stylesPath}/PageingWidget.css" type="text/css">

<script language="JavaScript" src="./Commons/JavaScript/BrowserWidget/BrowserWidget.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/CommonScripts/ArrayList.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/CommonScripts/AssertUtil.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/CommonScripts/Configuration.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/CommonScripts/Collection.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/CommonScripts/GenericBrowser.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/CommonScripts/inheritFrom.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/CommonScripts/Map.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/CommonScripts/HashMap.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/CommonScripts/StringBuffer.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/CommonScripts/StringUtil.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/CommonScripts/UserException.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/CommonScripts/XmlResource.js"></script>
<script language="javascript" src="./Commons/JavaScript/DataRetriever/DataRetriever.js"></script>
<script language="javascript" src="./Commons/JavaScript/DataRetriever/InvalidXmlResponseException.js"></script>
<script language="javascript" src="./Commons/JavaScript/DocumentManager/DocumentList.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/Internalization/Locale.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/Internalization/LocaleUtil.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/Internalization/ResourceKey.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/Internalization/ResourceCache.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/Internalization/XMLBundleParser.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/Internalization/XMLResourceBundle.js"></script>
<script language="javascript" src="./Commons/JavaScript/Log4JavaScript/log4javascript.js"></script>
<script language="javascript" src="./Commons/JavaScript/ModalWindow/ModalWindowBase.js"></script>
<script language="javascript" src="./Commons/JavaScript/PageingWidget/PageingWidget.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/SortTable/SortTable.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/SortTable/SortTable_Server.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/XMLforScript/xmlsax.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/XMLforScript/preMadeSaxEventHandler.js"></script>
<script language="JavaScript" src="./Commons/JavaScript/WebUIController/ConfigureLogging.js" ></script>	
<script language="javascript" src="./Commons/JavaScript/WebUiCommands/WebUiCommand.js"></script>
<script language="javascript" src="./Commons/JavaScript/WebUiCommands/DeleteArtifactCommand.js"></script>
<script language="javascript" src="./Commons/JavaScript/WebUIController/WebUIController.js"></script>

<script type="text/javascript">
	var PROPERTY_VIEW_POSTFIX = "_PropertyView";
	var webUiController;
	var dataRetreiver = null;
	var pageingWidget;
	var sortTable;
	var listedArtifactName = null;
	var listedArtifactType = null;
	var selectedItemId = null;
	var selectedItemName = null;
	var contextRootAccessPath;
	
	function init( theContextRootAccessPath ){
		contextRootAccessPath = (theContextRootAccessPath == null ? "../../../" : theContextRootAccessPath );
		webUiController = parent.browserFrame.webUIController;
		pageingWidget = new PageingWidget("pageingWidget", webUiController.getResourceBundle());
		pageingWidget.setCurrentStart(${artifactView.startQueryFrom});
		pageingWidget.setMaxReturnSize(${artifactView.maxReturnSize});
		pageingWidget.setRowCount(${artifactView.rowCount});
		pageingWidget.show();
		sortTable = new SortTable_Server();
		sortTable.setOrderBy("${artifactView.orderBy}");
		sortTable.setOrder("${artifactView.order}");
		sortTable.init();	

		listedArtifactType = determinelistedArtifactType();
		
		try {
			initFooter();
		} catch(e) {
			//alert("initFooter is undefined.");
		}		
	}
	
	function setSelectedArtifact( aName, aId ) {
		selectedItemId = aId;
		selectedItemName = aName;
//		alert("Name: " + selectedItemName + ", Id: " + selectedItemId );
	}

	function goToDataSheetByLink( artifactName, artifactId ){
//		var url = contextRootAccessPath + "CommandControllerServlet?action=ShowView&artifactId=" + artifactId + listedArtifactType + PROPERTY_VIEW_POSTFIX;
		var commandText = contextRootAccessPath + "CommandControllerServlet?action=ShowView&artifactId=" + artifactId + "&viewName=" + listedArtifactType + PROPERTY_VIEW_POSTFIX;
		webUiController.loadDocumentById( listedArtifactType, artifactName, artifactId );
	}

	function goToCompanyByLink( artifactName, artifactId ){
		webUiController.loadDocument( listedArtifactType, selectedItemName, "&artifactId=" + artifactId );
	}
	
	function goToDataSheetByButton() {
//		alert("Name: " + selectedItemName + ", Id: " + selectedItemId );
		webUiController.loadDocumentById( listedArtifactType, selectedItemName, selectedItemId );
	}	
	
	function createNewArtifact( theStylesPath ) {
		var commandText = "new CreateNew" + COMMAND_POSTFIX + "Command( webUiController, './" + theStylesPath + "' )";
		var command = eval( commandText );
		command.execute();
	}
		
	function deleteArtifact() {
		var command = new DeleteArtifactCommand( true, selectedItemId );
		command.execute();
	} 
	
	function refreshPropertyInfo( artifactId ) {
		var documentPropertiesInfoPageName = webUiController.getText("DocumentPropertiesInfoPageName");
		var menuWidget = webUiController.getRightMenu();
		var menuItem = menuWidget.findMenuByName("InfoPagesMenu").findMenuByName("DocumentPropertiesInfoPageName");
		menuItem.turnOn();
		/*
		if( !menuItem.isIsOn() ) {
			var menuContainerElement = menuWidget.getContainerElement();
			var menuElement = menuContainerElement.document.getElementById( menuItem.getName() );
			menuElement.click();
		}
		*/

		var viewName = listedArtifactType + PROPERTY_VIEW_POSTFIX;
		webUiController.loadInfoPanelDocumentById( listedArtifactType, documentPropertiesInfoPageName, artifactId, viewName );
	}

	function enableButtons() {
		document.getElementById("openActionButton").disabled=false;
		document.getElementById("deleteActionButton").disabled=false;
	}
	
	function determinelistedArtifactType() {
		var listedArtifactTypeClassName = "${artifactView.type.listedArtifactType}";
		var lastDot = listedArtifactTypeClassName.lastIndexOf(".");
		return listedArtifactTypeClassName.substr( lastDot + 1 );
	}	

/*
	function buildForm() {
		var form = document.forms[0];
		form.setAttribute("name", "theForm");
		form.setAttribute("method", "post");
		form.setAttribute("action", "${artifactView.codeBase}");
		
		var hAction = document.createElement("hidden");
		hAction.setAttribute("name", "action");
		hAction.setAttribute("value", "ShowListView");
		
		form.appendChild(hAction);
		
		var hArtifactName = document.createElement("hidden");
		hArtifactName.setAttribute("name", "artifactName");
		hArtifactName.setAttribute("value", "${artifactView.parentArtifact.name}");
		
		form.appendChild(hArtifactName);
		
		var hViewName = document.createElement("hidden");
		hViewName.setAttribute("name", "viewName");
		hViewName.setAttribute("value", "${artifactView.name}");
		
		form.appendChild(hViewName);
		
		var hQuery = document.createElement("hidden");
		hQuery.setAttribute("name", "query");
		hQuery.setAttribute("value", "");
		
		form.appendChild(hQuery);
		
		var hMethod = document.createElement("hidden");
		hMethod.setAttribute("name", "method");
		hMethod.setAttribute("value", "");
		
		form.appendChild(hMethod);
		
		var hId = document.createElement("hidden");
		hId.setAttribute("name", "id");
		hId.setAttribute("value", "");
		
		form.appendChild(hId);
		
		var hName = document.createElement("hidden");
		hName.setAttribute("name", "name");
		hName.setAttribute("value", "");
		
		form.appendChild(hName);		
	}
*/
</script>
