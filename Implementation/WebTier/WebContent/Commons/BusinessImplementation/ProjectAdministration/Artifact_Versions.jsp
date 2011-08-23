<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<HTML>

<HEAD>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">
<TITLE>Artifact_Versions</TITLE>

	<script language="JavaScript" src="./JavaScript/CommonScripts/AssertUtil.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/XmlResource.js"></script>
	<script language="javascript" src="./JavaScript/WebUIController/WebUIController.js"></script>
	<script language="JavaScript" src="./JavaScript/Internalization/Locale.js"></script>
	<script language="JavaScript" src="./JavaScript/Internalization/LocaleUtil.js"></script>
	<script language="JavaScript" src="./JavaScript/Internalization/ResourceKey.js"></script>
	<script language="JavaScript" src="./JavaScript/Internalization/ResourceCache.js"></script>
	<script language="JavaScript" src="./JavaScript/Internalization/XMLBundleParser.js"></script>
	<script language="JavaScript" src="./JavaScript/Internalization/XMLResourceBundle.js"></script>
	<script language="JavaScript" src="./JavaScript/XMLforScript/xmlsax.js"></script>
	<script language="JavaScript" src="./JavaScript/XMLforScript/preMadeSaxEventHandler.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/inheritFrom.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/HashMap.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/ArrayList.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/StringBuffer.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/StringUtil.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/Configuration.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/Collection.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/UserException.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/GenericBrowser.js"></script>
	<script language="JavaScript" src="./JavaScript/DocumentReservationWidget/DocumentReservationWidget.js"></script>
	<script language="javascript" src="./JavaScript/ProgressBar/progressBar.js"></script>

	<script type="text/javascript">
		var webUiController;
		var documentReservationWidget;
	
		function init(){
			webUiController = parent.browserFrame.webUIController;
			documentReservationWidget = new DocumentReservationWidget("reservationWidget", webUiController.getResourceBundle());
			documentReservationWidget.show();
		}
		
		</script>
</HEAD>
<BODY onload="init();">
<form name="Artifact_VersionsForm">
<input type="hidden" id="subjectElement" name="grgr" value="fsfs"/>
<div class="readOnlyContainer">
			<table id="artifact_versionsTable" border="1">
				<thead>
					<tr>
						<td align="center" id="version" style="cursor:pointer" onClick="" width="100px"><b>
						<c:set target="${i18Helper}" property="key" value="ui.Artifact_Versions.version"/>${i18Helper.value}</b>
						</td>
						<td align="center" id="beginOfMod" style="cursor:pointer" onClick=""><b>
						<c:set target="${i18Helper}" property="key" value="ui.Artifact_Versions.beginOfMod"/>${i18Helper.value}</b>
						</td>
						<td align="center" id="endOfMod" style="cursor:pointer" onClick=""><b>
						<c:set target="${i18Helper}" property="key" value="ui.Artifact_Versions.endOfMod"/>${i18Helper.value}</b>
						</td>
						<td align="center" id="modifier" style="cursor:pointer" onClick=""><b>
						<c:set target="${i18Helper}" property="key" value="ui.Artifact_Versions.modifier"/>${i18Helper.value}</b>
						</td>
						<td align="center" id="comment" style="cursor:pointer" onClick=""><b>
						<c:set target="${i18Helper}" property="key" value="ui.Artifact_Versions.comment"/>${i18Helper.value}</b>
						</td>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="modification" items="${artifactView.modifications}">
					<tr>
						<td align="center" width="100">${modification.id }</td>
						<td align="center" width="100"><fmt:formatDate value="${modification.modificationPeriod.startDate}" pattern="yyyy.MM.dd"></fmt:formatDate></td>
						<td align="center" width="100"><fmt:formatDate value="${modification.modificationPeriod.endDate}" pattern="yyyy.MM.dd"></fmt:formatDate></td>
						<td align="center" width="100">${modification.modifier.partyName.familyName } ${modification.modifier.partyName.givenName }</td>
						<td align="center" width="100">${modification.comment}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
</div>

<div id="reservationWidget"></div>
</form>
</BODY>
</HTML>
