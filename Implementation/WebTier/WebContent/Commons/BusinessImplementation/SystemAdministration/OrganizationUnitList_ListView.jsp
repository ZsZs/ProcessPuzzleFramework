<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
	<link rel="stylesheet" href="styles/base.css" type="text/css">
	<link rel="stylesheet" href="styles/content.css" type="text/css">
	<link rel="stylesheet" href="styles/form_styles.css" type="text/css">

	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<META name="GENERATOR" content="IBM Software Development Platform">

	<title>Szervezeti egység lista</title>
	
	<script language="javascript" src="./JavaScript/WebUiCommands/CreateNewOrganizationUnitCommand.js"></script>

	<script language="javascript" src="./JavaScript/WebUiCommands/CreateNewPersonCommand.js"></script>
	<script language="javascript" src="./JavaScript/WebUiCommands/CreateNewSettlementCommand.js"></script>
	<script language="JavaScript" src="JavaScript/CommonScripts/AssertUtil.js"></script>
	<script language="JavaScript" src="JavaScript/CommonScripts/XmlResource.js"></script>
	<script language="JavaScript" src="JavaScript/Internalization/Locale.js"></script>
	<script language="JavaScript" src="JavaScript/Internalization/LocaleUtil.js"></script>
	<script language="JavaScript" src="JavaScript/Internalization/ResourceKey.js"></script>
	<script language="JavaScript" src="JavaScript/Internalization/ResourceCache.js"></script>
	<script language="JavaScript" src="JavaScript/Internalization/XMLBundleParser.js"></script>
	<script language="JavaScript" src="JavaScript/Internalization/XMLResourceBundle.js"></script>
	<script language="JavaScript" src="JavaScript/XMLforScript/xmlsax.js"></script>
	<script language="JavaScript" src="JavaScript/XMLforScript/preMadeSaxEventHandler.js"></script>
	<script language="JavaScript" src="JavaScript/CommonScripts/inheritFrom.js"></script>
	<script language="JavaScript" src="JavaScript/CommonScripts/HashMap.js"></script>
	<script language="JavaScript" src="JavaScript/CommonScripts/ArrayList.js"></script>
	<script language="JavaScript" src="JavaScript/CommonScripts/StringBuffer.js"></script>
	<script language="JavaScript" src="JavaScript/CommonScripts/StringUtil.js"></script>
	<script language="JavaScript" src="JavaScript/CommonScripts/Configuration.js"></script>
	<script language="JavaScript" src="JavaScript/CommonScripts/Collection.js"></script>
	<script language="JavaScript" src="JavaScript/CommonScripts/UserException.js"></script>
	<script language="JavaScript" src="JavaScript/CommonScripts/GenericBrowser.js"></script>
	<script language="JavaScript" src="JavaScript/DocumentReservationWidget/DocumentReservationWidget.js"></script>
	<script language="javascript" src="JavaScript/ProgressBar/progressBar.js"></script>
	<script language="javascript" src="JavaScript/DataRetriever/DataRetriever.js"></script>
	<script language="javascript" src="JavaScript/WebUiCommands/WebUiCommand.js"></script>
	<script language="javascript" src="JavaScript/ModalWindow/ModalWindowBase.js"></script>
	<script language="javascript" src="JavaScript/WebUiCommands/CreateNewRealEstateCommand.js"></script>
	<script language="javascript" src="JavaScript/WebUIController/WebUIController.js"></script>
	<script language="javascript" src="JavaScript/PageingWidget/PageingWidget.js"></script>
	<script language="JavaScript" src="./JavaScript/SortTable/SortTable_Server.js"></script>
	<script language="JavaScript" src="./JavaScript/BrowserWidget/BrowserWidget.js"></script>
	
	<script type="text/javascript">
		var webUiController;
		var dataRetreiver = null;
		var pageingWidget;
		var sortTable;

		function init(){
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
		}
		
		function createNewOrganizationUnit() {
			var command = new CreateNewOrganizationUnitCommand();
			command.execute();
		}
		
		function del() {
			document.getElementsByName("method")[0].value= "del";
		} 
		
		function goToDataSheetByLink(artifactName, artifactId) {
			webUiController.loadDocument('OrganizationUnitDataSheet', artifactName, '&artifactId='+artifactId);
		}
		
		function goToDataSheetByButton() {
			var artifactId = document.getElementsByName("id")[0].value;
			var artifactName = document.getElementsByName("name")[0].value;
			webUiController.loadDocument('OrganizationUnitDataSheet', artifactName, '&artifactId='+artifactId);			
		}	
		
		function refreshPropertyInfo( artifactId ) {
			var documentPropertiesInfoPageName = webUiController.getText("DocumentPropertiesInfoPageName");
			var menuWidget = webUiController.getRightMenu();
			var menuItem = menuWidget.findMenuByName("InfoPagesMenu").findMenuByName("DocumentPropertiesInfoPageName");
			if( !menuItem.isIsOn() ) menuItem.onClick();
	
			webUiController.loadInfoPanelDocument(null,	documentPropertiesInfoPageName, 
				"./CommandControllerServlet?action=ShowView&artifactId=" + artifactId + "&viewName=OrganizationUnitDataSheet_PropertyView");
		}
		
		function fillparameters(aName, aId) {
			document.getElementsByName("id")[0].value= aId;
			document.getElementsByName("name")[0].value= aName;
		}

		function enableButtons() {
			document.getElementById("openActionButton").disabled=false;
			document.getElementById("deleteActionButton").disabled=false;
		}	
		
	</script>

</HEAD>

<BODY onload="init();">

	<form name="form" method="POST" action="${artifactView.codeBase}">
		<input name="action" type="hidden" value="ShowListView">
		<input name="artifactName" type="hidden" value="${artifactView.parentArtifact.name}">
		<input name="viewName" type="hidden" value="${artifactView.name}">	
		<input name="query" type="hidden" value="">
		<input type="hidden" name="method">
		<input type="hidden" name="id">
		<input type="hidden" name="name">
		
		<center>
		
		<br><br>
		
		<div id="pageingWidget"></div>
		
		<br><br>
		
		<div class="readOnlyContainer">
			<table id="companyTable" border="1" width="550px;">
				<thead>
					<tr>
						<th></th>
						<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
						<th onclick="sortTable.sortBy('party.partyName.name')">${i18Helper.value}</th>
						<c:set property="key" value="ui.companyds.basedata.address" target="${i18Helper}"></c:set>
						<th onclick="sortTable.sortBy('party.partyName.name')">${i18Helper.value}</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="artifactProperty" items="${artifactView.listedArtifactsPropertyViews}" varStatus="index">
						<c:set var="classNameForRow" value="odd"/>
						<c:if test="${index.count % 2 == 0}">
							<c:set var="classNameForRow" value="even"/>
						</c:if>
						<tr class="${classNameForRow}">
							<td><input type="radio" name="subjectCompany" onclick="refreshPropertyInfo('${artifactProperty.id}', 'OrganizationUnitDataSheet_PropertyView'); setSelectedArtifact('${artifactProperty.name}','${artifactProperty.id}'); enableButtons();"></td>				
							<td width="300px;"><a id="${artifactProperty.id}" href="#" onclick="goToDataSheetByLink('${artifactProperty.organizationUnitName}','${artifactProperty.id}');">${artifactProperty.organizationUnitName}</a></td>
							<td>${artifactProperty.geographicAddress}</td>
							
						</tr>
					</c:forEach>
					
					
				</tbody>
			</table>
		</div>
		
		<br><br>
		<c:set property="key" value="ui.generic.button.new" target="${i18Helper}"></c:set>
		<input type="button" class="buttonSmall" id="newActionButton" value="${i18Helper.value}" onclick="createNewOrganizationUnit()">
		<c:set property="key" value="ui.generic.button.open" target="${i18Helper}"></c:set>
		<input type="button" class="buttonSmall" id="openActionButton" value="${i18Helper.value}" disabled="disabled" onclick="goToDataSheetByButton();">
		<c:set property="key" value="ui.generic.button.delete" target="${i18Helper}"></c:set>
		<input type="button" class="buttonSmall" id="deleteActionButton" value="${i18Helper.value}" disabled="disabled" onclick="del();">
		</center>							
	</form>		
</BODY>
</HTML>