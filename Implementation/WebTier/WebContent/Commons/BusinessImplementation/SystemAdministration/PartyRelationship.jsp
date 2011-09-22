<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Party Kapcsolatok</title>

	<script language="javascript" src="./JavaScript/DataRetriever/DataRetriever.js" ></script>
	<script language="javascript" src="./JavaScript/DataRetriever/InvalidXmlResponseException.js"></script>
	<script language="javascript" src="./JavaScript/WebUIController/LoadUtility.js" ></script>

	<script language="javascript">
	
		var webUiController = getWebUIController();
		var validateMethod = validate();
		
		function getValidRelations() {
				var selectedValue = document.getElementsByName("partyDataSheet")[0].options[document.getElementsByName("partyDataSheet")[0].selectedIndex].value;
				var dataRetriever = new DataRetriever(null, "artifactId", ${artifactView.parentArtifact.id}, "Party_RelationshipView", null, webUiController.getResourceBundle());
				dataRetriever.setMethod("getValidRelationships");
				dataRetriever.setParameters(selectedValue);
				dataRetriever.makeHttpRequest("processDatas('vRelation', 'validRelationship');");	
		} 
		
		function getValidRoles() {
				var selectedValue = document.getElementsByName("vRelation")[0].options[document.getElementsByName("vRelation")[0].selectedIndex].value;
				var dataRetriever = new DataRetriever(null, "artifactId", /* ??? */ ${artifactView.parentArtifact.id}, "Party_RelationshipView", null, webUiController.getResourceBundle());
				dataRetriever.setMethod("getValidRoles");
				dataRetriever.setParameters(selectedValue, document.getElementsByName("partyDataSheet")[0].options[document.getElementsByName("partyDataSheet")[0].selectedIndex].value);
				dataRetriever.makeHttpRequest("processDatas('ownRole', 'ownValidRole');");
				dataRetriever.makeHttpRequest("processDatas('partyRole', 'partyValidRole');");		
		} 
		
		function init() {
			<c:if test="${artifactView.errorMsg != null}">
				alert(${errorMsg});
			</c:if>
		}
		
		function validate() {
			return false;
		}
		
		function fillparameters(id) {
			document.getElementsByName("partyRoleId")[0].value= id;
		}

		function newRelationship() {
			document.getElementsByName("method")[0].value = "newRelationship";
			var isOk = true;
			var selectNames = new Array("PartyDataSheet", "vRelation", "ownRole", "partyRole");
			for(var i = 0; i<selectNames.length; i++) {
				var sElement = document.getElementsByName(selectNames[i])[0];
				var selectedValue = sElement.options[sElement.selectedIndex].value;
				if(selectedValue == "-1") 
					isOk = false;
			}	
			if(isOk == false)
				alert("Adja meg a hiányzó adatokat!");				
			return isOk;
		}
		
		function editRelationship() {
		
		}
		
		function delRelationship() {
			document.getElementsByName("method")[0].value = "delRelationship";
			var selectedId = document.getElementsByName("partyRoleId")[0].value;
			if(selectedId == "") {
				alert("Jelölje ki a kapcsolatot!");
				return false;
			}
			document.getElementsByName("partyRoleId")[0].value = selectedId;
			return true;
		}
		

	</script>
</head>
<body onload="init();">

<br><br>
	<form name="relationshipForm" method="post" action="${artifactView.codeBase}" onsubmit="return validateMethod;">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name }">
		<input type="hidden" name="method" value="">
		<input type="hidden" name="partyRoleId" value="">
	<center>
	
		<div class="readOnlyContainer">
		
			<table border="1" width="700px;">
				<thead>
					<tr>
						<th></th>
						<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.partyRelationship.contactType" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.partyRelationship.ownRole" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.partyRelationship.contactRole" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="partyRole" items="${artifactView.partyRoles}" varStatus="index">						
					<tr>
						<td style="width: 20px;"><input type="radio" name="radio" value="${partyRole.id }" onclick="fillparameters('${partyRole.id }');"></td>
						<c:set property="currentPartyRole" value="${partyRole.id}" target="${artifactView}"></c:set>					
						<td>${artifactView.currentRelationParty}</td>
						<c:set property="key" value="ui.partyRelationship.relationshipType.${partyRole.partyRelationship.name}" target="${i18Helper}"></c:set>
						<td>${i18Helper.value}</td>
						<c:set property="key" value="ui.partyRelationship.validRoleType.${partyRole.roleType.name}" target="${i18Helper}"></c:set>
						<td>${i18Helper.value}</td>
						<c:set property="key" value="ui.partyRelationship.validRoleType.${artifactView.otherRole.roleType.name}" target="${i18Helper}"></c:set>
						<td>${i18Helper.value}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>

		</div>

		<div class="row">
			<select name="partyDataSheet" onchange="getValidRelations();">
			<option value="-1">---</option>
				<c:forEach var="partyDataSheet" items="${artifactView.partyDataSheets}" varStatus="index">						
					<option value="${partyDataSheet.id}" 
					>${partyDataSheet.party.partyName.name}
					</option>
				</c:forEach>
			</select>
			<select name="vRelation" onchange="getValidRoles();">
				<option value="-1">---</option>				
			</select>
			<select name="ownRole">
				<option value="-1">---</option>
			</select>
			<select name="partyRole">
				<option value="-1">---</option>
			</select>			
		</div>		
		
		<div class="row">
			<c:set property="key" value="ui.generic.button.add" target="${i18Helper}"></c:set>
			<input type="submit" id="newRelationshipButton" class="buttonSmall" value="${i18Helper.value}" onclick="validateMethod = newRelationship();">
			<c:set property="key" value="ui.generic.button.modify" target="${i18Helper}"></c:set>
			<input type="button" disabled="disabled" id="editRelationshipButton" class="buttonSmall" value="${i18Helper.value}" onclick="editRelationship();">
			<c:set property="key" value="ui.generic.button.delete" target="${i18Helper}"></c:set>
			<input type="submit" id="delRelationshipButton" class="buttonSmall" value="${i18Helper.value}" onclick="validateMethod = delRelationship();">
		</div>

	</center>

</form>
</body>
</html>