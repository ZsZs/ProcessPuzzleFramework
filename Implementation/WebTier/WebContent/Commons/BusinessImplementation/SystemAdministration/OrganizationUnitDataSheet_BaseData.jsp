<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>

	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">

<title>Szervezeti egység adatlap alapadatok</title>

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script language="javascript" src="./JavaScript/WebUIController/LoadUtility.js" ></script>
	<script language="javascript" src="./JavaScript/BasicValidation/BasicValidation.js" ></script>
	<script language="javascript" src="./JavaScript/DataRetriever/DataRetriever.js" ></script>
	<script language="javascript" src="./JavaScript/DataRetriever/InvalidXmlResponseException.js"></script>
	<script language="javascript" src="./JavaScript/WebUIController/WebUIController.js"></script>
<script type="text/javascript">
	
	var webUiController = getWebUIController();
	var dataRetreiver = null;
	
		function getSettlements() {
			var typedValue = document.getElementById("settlementStart").value;
			if(typedValue != "") {
				dataRetriever = new DataRetriever(null, "artifactName", "SettlementList", "SettlementList_ListView", null, parent.browserFrame.webUIController.getResourceBundle());
				dataRetriever.setMethod("getSettlementsByStartName");			
				dataRetriever.setParameters(typedValue);	
				dataRetriever.makeHttpRequest("processDatas('settlementDataSheet', 'settlement');");
			}
		}
	
		function getSettlementData() {
			var selectedValue = document.getElementsByName("settlementDataSheet")[0].options[document.getElementsByName("settlementDataSheet")[0].selectedIndex].value;
			dataRetriever = new DataRetriever(null, "artifactId", selectedValue, "SettlementProperties", null,parent.browserFrame.webUIController.getResourceBundle());
			dataRetriever.setMethod("getSettlementData");
			dataRetriever.setParameters(selectedValue);
			dataRetriever.makeHttpRequest("processDatas('zipCode', 'zipCode');");
		}
	
</script>
</head>
<body>

<br>

	<form name="form" method="post" action="${artifactView.codeBase }">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name }">
	
		<div class="readOnlyContainer">

			<div class="row">
				<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" name="organizationUnitName" value="${artifactView.organizationUnitName }"></span>
			</div>		
					
		</div>

	<br>
		
		<div class="readOnlyContainer">

			<fieldset style="width: 400px; border-color: white;" >
				<c:set property="key" value="ui.companyds.basedata.centralAccessibility" target="${i18Helper}"></c:set>
				<legend style="color: brown;">${i18Helper.value}</legend>
					
			<div class="row">
				<span class="label" id="cityText">
					<c:set property="key" value="ui.pds.geodata.settlement" target="${i18Helper}"></c:set>
					${i18Helper.value}:
				</span>
				<span class="formw">
					<input type="text" id="settlementStart" name="settlementStart" style="width: 2em;" maxlength="2" onkeyup="getSettlements();">
					<select id=settlementDataSheet name="settlementDataSheet" onChange="getSettlementData();setToDirty();">
						<option value="-1">---</option>
						<c:if test="${artifactView.settlementDataSheet != null}">
								<option value="${artifactView.settlementDataSheet.id}" selected="selected">${artifactView.settlementDataSheet.settlement.name}</option>
						</c:if>
					</select> *
				 </span>
			</div>
				
				
				<div class="row">
				<span class="label" id="zipText">
					<c:set property="key" value="ui.pds.geodata.zip" target="${i18Helper}"></c:set>
					${i18Helper.value}:
				</span>
				<span class="formw">
					<select id="zipSelect" name="zipCode" onChange="setToDirty();">
						<option value="-1">---</option>
						<c:forEach var="zipCode" items="${artifactView.zipCodes}" varStatus="index">						
							<option value="${zipCode.id}"
							<c:if test="${artifactView.zipCode.id == zipCode.id}">
								selected
							</c:if>
							>${zipCode.zipCode}</option>
						</c:forEach>
					</select>
				 *</span>
			</div>

			<div class="row">
				<span class="label" id="streetText">
					<c:set property="key" value="ui.pds.geodata.street" target="${i18Helper}"></c:set>
					${i18Helper.value}:
				</span>
				<span class="formw"><input type="text" id="streetEdit" name="street" value="${artifactView.street}" onChange="setToDirty();"></span>
			</div>

			<div class="row">
				<span class="label" id="buildingNumberText">
					<c:set property="key" value="ui.pds.geodata.buildingNumber" target="${i18Helper}"></c:set>
					${i18Helper.value}:
				</span>
				<span class="formw"><input type="text" id="buildingNumberEdit" name="buildingNumber" value="${artifactView.buildingNumber}" onChange="setToDirty();"></span>
			</div>

			<div class="spacer">&nbsp;</div>		
			</fieldset>

		</div>
		
	<br>
		
		<div class="readOnlyContainer">
			<c:set property="key" value="ui.generic.button.save" target="${i18Helper}"></c:set>
			<input type="submit" class="buttonSmall" id="saveButton" value="${i18Helper.value}" onclick="setToClean();">
			<c:set property="key" value="ui.generic.button.cancel" target="${i18Helper}"></c:set>
			<input type="button" class="buttonSmall" id="closeButton" value="${i18Helper.value}" onclick="closeIt();">
		</div>
	
	</form>

</body>
</html>