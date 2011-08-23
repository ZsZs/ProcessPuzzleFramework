<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<HTML>
<HEAD>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="../../PageIncludes/ArtifactListPageHeader.jsp" %>

	<script language="JavaScript" src="./JavaScript/DocumentReservationWidget/DocumentReservationWidget.js"></script>
	<script language="javascript" src="./JavaScript/ProgressBar/progressBar.js"></script>
	
	<script type="text/javascript">
		var webUiController;
		var documentReservationWidget;
	
		function initializations(){
			webUiController = parent.browserFrame.webUIController;
			documentReservationWidget = new DocumentReservationWidget("reservationWidget", webUiController.getResourceBundle());
			documentReservationWidget.show();
		}

		function goToDataSheetByLink(artifactName){
			webUiController.loadDocument('ArtifactDataSheet', ''+artifactName, '&artifactName='+artifactName);
		}
	
	</script>
	
	<TITLE>ArtifactList_ListedArtifacts</TITLE>
</HEAD>
<BODY onload="initializations();">
<br><br>
<form name="ArtifactList_ListedArtifactsForm">
<center>
<div class="readOnlyContainer">
			<table id="artifactList" border="1">
				<thead>
					<tr>
						<td></td>
						<td align="center" id="artifactName" style="cursor:pointer" onClick="" width="100px"><b>
						<c:set target="${i18Helper}" property="key" value="ui.label.name"/>${i18Helper.value}</b>
						</td>
						<td align="center" id="owner" style="cursor:pointer" onClick="" width="150"><b>
						<c:set target="${i18Helper}" property="key" value="ui.label.owner"/>${i18Helper.value}</b>
						</td>
						<td align="center" id="creationDate" style="cursor:pointer" onClick="" width="200"><b>
						<c:set target="${i18Helper}" property="key" value="ui.label.creationDate"/>${i18Helper.value}</b>
						</td>
						<td align="center" id="lastModDate" style="cursor:pointer" onClick="" width="200"><b>
						<c:set target="${i18Helper}" property="key" value="ui.label.lastModDate"/>${i18Helper.value}</b>
						</td>
						<td align="center" id="lastModifier" style="cursor:pointer" onClick="" width="200"><b>
						<c:set target="${i18Helper}" property="key" value="ui.label.lastModifier"/>${i18Helper.value}</b>
						</td>
						<td align="center" id="reserveStatus" style="cursor:pointer" onClick="" width="100"><b>
						<c:set target="${i18Helper}" property="key" value="ui.ArtifactList_ListedArtifacts.reserveStatus"/>${i18Helper.value}</b>
						</td>
						<td align="center" id="isVersionControlled" style="cursor:pointer" onClick="" width="150"><b>
						<c:set target="${i18Helper}" property="key" value="ui.label.versionControlled"/>${i18Helper.value}</b>
						</td>
						<td align="center" id="versionNumber" style="cursor:pointer" onClick="" width="50"><b>
						<c:set target="${i18Helper}" property="key" value="ui.label.version"/>${i18Helper.value}</b>
						</td>
						
					</tr>
				</thead>
				<c:forEach var="artifactProperty" items="${listView.listedArtifactsPropertyViews}" varStatus="index">
					<tr>
						<td><input type="checkbox" name="selectCheckbox" value="${artifactProperty.artifactName}" id="" onclick="documentReservationWidget.checkSelection();"></td>
						<td id="status${artifactProperty.artifactName}">
							<input type="hidden" id="msg${artifactProperty.artifactName}" name="msg${artifactProperty.artifactName}"/>
							<img src="" id="img${artifactProperty.artifactName}" name="img${artifactProperty.artifactName}" onclick="showMsg('${artifactProperty.artifactName}');" style="display:none;">
						</td>
						<td align="center" width="100"><a class="listedDocumentName" href="#" onclick="goToDataSheetByLink('${artifactProperty.artifactName}');">${artifactProperty.artifactName}</a></td>
						<td align="center" width="100">${artifactProperty.responsibleName}</td>
						<td align="center" width="200"><fmt:formatDate value="${artifactProperty.creationDate}" pattern="yyyy.MM.dd"/></td>				
						<td align="center" width="100"><fmt:formatDate value="${artifactProperty.lastModificationDate}" pattern="yyyy.MM.dd"/></td>	
						<td align="center" width="100">${artifactProperty.lastModifierName}</td>						
						<td align="center" width="100" id="reserveStatus_${index.count}" value=
						<c:choose>
							<c:when test="${artifactProperty.reserveStatus}">
								"true"
								<c:set target="${i18Helper}" property="key" value="ui.generic.artifact.reservestatus.reserve"/>
							</c:when>		
							<c:otherwise>
								"false"
								<c:set target="${i18Helper}" property="key" value="ui.generic.artifact.reservestatus.release"/>
							</c:otherwise>
						</c:choose>
						>${i18Helper.value}</td>
						<c:set target="${i18Helper}" property="key" value="ui.generic.artifact.versionControlled.${artifactProperty.isVersionControlled}"/>
						<td align="center" width="100">${i18Helper.value}</td>
						<td align="center" width="100">${artifactProperty.versionNumber}</td>
					</tr>
				</c:forEach>
			</table>
</div>
</center>
</form>
<center>
<div id="reservationWidget"></div>
</center>
</BODY>
</HTML>
