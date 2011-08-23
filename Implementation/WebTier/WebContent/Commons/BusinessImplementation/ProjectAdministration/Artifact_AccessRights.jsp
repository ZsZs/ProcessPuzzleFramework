<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<HTML>
<HEAD>

	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">

<TITLE>Artifact_AccessRights</TITLE>
<script type="text/javascript">
		
		function init() {}
		
		function setParameters() {
			var f = document.getElementById("Artifact_AccessRightsForm");
			var cbNames = new Array("cbRead", "cbCreate", "cbModify", "cbDelete");			
			var hiddenNames = new Array("canRead", "canCreate", "canModify", "canDelete");
					
			for(var i = 0; i < cbNames.length; i++) {
				var cbOperation = document.getElementsByName(cbNames[i]);
				for(var j = 0; j < cbOperation.length; j++) {
					var canOperation = document.createElement("input");
					canOperation.setAttribute("type", "hidden");
					canOperation.setAttribute("name", hiddenNames[i]);
					canOperation.setAttribute("value", cbOperation[j].checked+"_"+cbOperation[j].value);
					f.appendChild(canOperation);
				}
			}			
		}
		
</script>
</HEAD>
<BODY onload="init();">
<form id="Artifact_AccessRightsForm" name="Artifact_AccessRightsForm" method="post" action="${artifactView.codeBase}">
	<input name="action" type="hidden" value="UpdateView">
	<input name="artifactName" type="hidden" value="${artifactView.parentArtifact.name}">
	<input name="viewName" type="hidden" value="${artifactView.name}">
	
	<div class="readOnlyContainer">
			<table id="artifact_accessRightsTable" border="1">
				<thead>
					<tr>
						<c:if test="${userSession.partyRoleType == 'Administrator'}">
							<td align="center" id="userName" style="cursor:pointer" onClick="" width="100px"><b>
							<c:set target="${i18Helper}" property="key" value="ui.label.userName"/>${i18Helper.value}</b>
							</td>
						</c:if>
						<td align="center" id="read" style="cursor:pointer" onClick=""><b>
						<c:set target="${i18Helper}" property="key" value="ui.Artifact_AccessRights.read"/>${i18Helper.value}</b>
						</td>
						<td align="center" id="read" style="cursor:pointer" onClick=""><b>
						<c:set target="${i18Helper}" property="key" value="ui.Artifact_AccessRights.create"/>${i18Helper.value}</b>
						</td>
						<td align="center" id="write" style="cursor:pointer" onClick=""><b>
						<c:set target="${i18Helper}" property="key" value="ui.Artifact_AccessRights.write"/>${i18Helper.value}</b>
						</td>
						<td align="center" id="delete" style="cursor:pointer" onClick=""><b>
						<c:set target="${i18Helper}" property="key" value="ui.Artifact_AccessRights.delete"/>${i18Helper.value}</b>
						</td>
					</tr>
				</thead>
				<tbody id="tableBody">
				<c:forEach var="right" items="${artifactView.rightsForArtifact}" varStatus="index">				
					<tr>
						<c:set target="${artifactView}" property="userId" value="${right.key}"></c:set>						
						<c:if test="${userSession.partyRoleType == 'Administrator'}"> <td align="center" width="100">${artifactView.userName }</td> </c:if>
						<td align="center" width="100"><input type="checkbox" <c:if test="${userSession.partyRoleType != 'Administrator'}"> disabled="disabled" </c:if> <c:if test="${right.value.canRead}"> checked="checked" </c:if> name="cbRead" value="${right.key}_${right.value.id}"  id="readCB"></td>
						<td align="center" width="100"><input type="checkbox" <c:if test="${userSession.partyRoleType != 'Administrator'}"> disabled="disabled" </c:if> <c:if test="${right.value.canCreate}"> checked="checked" </c:if> name="cbCreate" value="${right.key}_${right.value.id}" id="createCB"></td>						
						<td align="center" width="100"><input type="checkbox" <c:if test="${userSession.partyRoleType != 'Administrator'}"> disabled="disabled" </c:if> <c:if test="${right.value.canModify}"> checked="checked" </c:if> name="cbModify" value="${right.key}_${right.value.id}" id="writeCB"></td>
						<td align="center" width="100"><input type="checkbox" <c:if test="${userSession.partyRoleType != 'Administrator'}"> disabled="disabled" </c:if> <c:if test="${right.value.canDelete}"> checked="checked" </c:if> name="cbDelete" value="${right.key}_${right.value.id}" id="delCB"></td>						
					</tr>
				</c:forEach>

					
				</tbody>
			</table>
</div>

	<div class="row">
			<c:if test="${userSession.partyRoleType == 'Administrator'}">
				<input type="submit" value="Mentés" name="addUser" id="addUserButton" class="buttonSmall" onclick="setParameters();">
			</c:if>
	</div>	

</form>
</BODY>
</HTML>
