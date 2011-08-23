<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
	<link rel="stylesheet" href="styles/base.css" type="text/css">
	<link rel="stylesheet" href="styles/content.css" type="text/css">
	<link rel="stylesheet" href="styles/form_styles.css" type="text/css">

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<script language="javascript" src="./JavaScript/WebUIController/LoadUtility.js" ></script>	
	<script language="javascript" src="./JavaScript/BasicValidation/BasicValidation.js" ></script>
	<script language="javascript" src="./JavaScript/DataRetriever/DataRetriever.js" ></script>
	<script language="javascript" src="./JavaScript/DataRetriever/InvalidXmlResponseException.js"></script>

	<title>ActionDataSheet_GeneralResourceAllocations</title>
	
	<script language="javascript">
	
	var dataRetreiver = null;
var validateMethod = validate();

function validate() {
	return false;
}
	
function getGeneralResourceAllocationData(rId) {
	dataRetriever = new DataRetriever(null, "artifactId", ${artifactView.parentArtifact.id}, "ActionDataSheet_GeneralResourceAllocations", null,parent.browserFrame.webUIController.getResourceBundle());
	dataRetriever.setMethod("getGeneralResourceAllocation");
	dataRetriever.setParameters(rId);
	dataRetriever.makeHttpRequest("fillForm();");
	dataRetriever.processDatas('performerRole', 'partyRoleType');		
	dataRetriever.processDatas('unit', 'unit');	
}

function fillForm() {
	var responseXml = dataRetriever.getCurrentResponseValue();
	document.getElementsByName("quantity")[0].value = responseXml.getElementsByTagName("quantity")[0].text;
	document.getElementsByName("method")[0].value = "saveGra";
	document.getElementById("add").value = "Mentés";
}
		
function fillParameters(val) {
	document.getElementsByName("graId")[0].value = val;		
}
		
function deSelectTableRows() {
	var rbGas = document.getElementsByName("rbGas");
	for (i = 0; i < rbGas.length; i++) {
		if(rbGas[i].checked)  {
			rbGas[i].checked = false;
		}
	document.getElementsByName("graId")[0].value = "";
	}
	
	document.getElementsByName("performerRole")[0].selectedIndex = 0;
	document.getElementsByName("quantity")[0].value = "";
	document.getElementsByName("unit")[0].selectedIndex = 0;
	document.getElementById("add").value = "Hozzáad";			
}

function createSaveGra() {
	if(document.getElementsByName("method")[0].value != "saveGra")
		document.getElementsByName("method")[0].value = "createGra";
	return true;
}

function removeGra() {
	document.getElementsByName("method")[0].value = "removeGra";
	var selectedId = document.getElementsByName("graId")[0].value;
	if(selectedId == "") {
		alert("Jelölje ki az er?forrást!");
		return false;
	}
	return true;
}

	</script>
	
</head>
<body>

	<form onsubmit="return validateMethod;" name="adsgraForm" id="adsgraForm" method="post" action="${artifactView.codeBase }">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name }">
		<input type="hidden" name="method" value="">
		<input type="hidden" name="graId" value="">

		<center>
			<div class="readOnlyContainer">
				<table border="1" class="sortable">
					<thead>
						<tr>
							<th><a href="#" onclick="deSelectTableRows();">x</a> </th>
							<c:set property="key" value="ui.label.designation" target="${i18Helper}"></c:set>
							<th>${i18Helper.value}</th>
							<c:set property="key" value="ui.label.quantity" target="${i18Helper}"></c:set>
							<th>${i18Helper.value}</th>
							<th>Mértékegység</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="resourceAllocation" items="${artifactView.generalResourceAllocations}" varStatus="index">
						<tr>
							<td><input type="radio" name="rbGas" onclick="fillParameters('${resourceAllocation.id}');getGeneralResourceAllocationData('${resourceAllocation.id}');"></td>
							<c:set property="key" value="ui.pantokrator.partyRoleType.${resourceAllocation.partyRoleType.name}" target="${i18Helper}"></c:set>														
							<td>${i18Helper.value}</td>
							<td>${resourceAllocation.quantity.amount}</td>
							<c:set property="key" value="ui.pantokrator.unitType.${resourceAllocation.quantity.unit.name}" target="${i18Helper}"></c:set>															
							<td>${i18Helper.value}</td>
						</tr>	
					</c:forEach>
					</tbody>
				</table>
			</div>
			<br><br>
			<div class="editableContainer">
				<fieldset style="width: 400px; border-color: white;">
				<c:set property="key" value="ui.label.intakeNewGeneralResource" target="${i18Helper}"></c:set>
				<legend style="color: brown;">${i18Helper.value}</legend>
				<br>
				 	<div class="row">
				 		<c:set property="key" value="ui.label.resourceType" target="${i18Helper}"></c:set>
						<span class="label">${i18Helper.value}:</span>
						<span class="formw">		
						<select id="performerRole" name="performerRole">
							<c:forEach var="performerRole" items="${artifactView.performerRoles}" varStatus="index">
								<c:set property="key" value="ui.pantokrator.partyRoleType.${performerRole.name}" target="${i18Helper}"></c:set>										
								<option value="-1">---</option>
								<option value="${performerRole.id}"
								>${i18Helper.value}</option>
							</c:forEach>
						</select>
						</span>
					</div>
					<div class="row">
						<c:set property="key" value="ui.label.quantity" target="${i18Helper}"></c:set>
						<span class="label">${i18Helper.value}:</span>
						<span class="formw"><input type="text" name="quantity" size="1" maxlength="1"></span>
					</div>
				 	<div class="row">
				 		<c:set property="key" value="ui.label.measure" target="${i18Helper}"></c:set>
						<span class="label">${i18Helper.value}:</span>
						<span class="formw">		
							<c:set property="key" value="ui.pantokrator.unitType.unit" target="${i18Helper}"></c:set>															
							<select name="unit">
								<option value="-1">---</option>
								<option value="unit">${i18Helper.value}</option>
							</select>
						</span>
					</div>
					<br>
					<div class="row">
						<c:set property="key" value="ui.generic.button.add" target="${i18Helper}"></c:set>
						<input type="submit" class="buttonSmall" name="add" value="${i18Helper.value}"  onclick="validateMethod = createSaveGra();">
						<c:set property="key" value="ui.generic.button.delete" target="${i18Helper}"></c:set>
						<input type="submit" class="buttonSmall" name="del" value="${i18Helper.value}" onclick="validateMethod = removeGra();">
					</div>
					<br><br>
				</fieldset>
			</div>
		</center>	
	</form>
</body>
</html>
