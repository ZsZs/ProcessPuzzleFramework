<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">
	<script language="javascript" src="./JavaScript/WebUIController/LoadUtility.js" ></script>	
	<script language="javascript" src="./JavaScript/BasicValidation/BasicValidation.js" ></script>
	<script language="javascript" src="./JavaScript/DataRetriever/DataRetriever.js" ></script>
	<script language="javascript" src="./JavaScript/DataRetriever/InvalidXmlResponseException.js"></script>
	<script language="javascript">
	
		var dataRetreiver = null;
		var validateMethod = validate();
		
		function validate() {
			return false;
		}
		
		function newEmailAddress() {
			if (document.getElementById("emailAddress").value == "") {
				alert("Töltse ki a hiányzó mezőt!");
				return false;
			}
			document.getElementsByName("method")[0].value = "newEmailAddress";
			
			return true;
		}
		
		function modifyEmailAddress() {
		
		}
		
		function newWebPageAddress() {
			if (document.getElementById("webPageAddress").value == "") {
				alert("Töltse ki a hiányzó mezőt!");
				return false;
			}
			document.getElementsByName("method")[0].value = "newWebPageAddress";
			
			return true;
		}
		
		function newGeographicAddress() {
			if(document.getElementsByName("settlementDataSheet")[0].options[document.getElementsByName("settlementDataSheet")[0].selectedIndex].value == "-1") {
				alert("Kérjük töltse ki a hiányzó mezőket!")
				return false;
			}
			document.getElementsByName("method")[0].value = "newGeographicAddress"
			return true;			
		}

		function delAddress(rbName) {
			var isChecked = false;	
			var selectedValue = null;		
			var rbWebPages = document.getElementsByName(rbName);
			for (i = 0; i < rbWebPages.length; i++) {
				if(rbWebPages[i].checked)  {
					selectedValue = rbWebPages[i];
					isChecked = true;
				}
			}
			
			if(!isChecked) {
				alert("Nincs kijelölve semmi!");
				return false;
			}
			else {
				document.getElementsByName("method")[0].value = "deAddress";
				document.getElementsByName("selectedValue")[0].value = selectedValue.value;
				return true;			
			}
		}
		
		function getGeographicAddressData(geoId) {
			dataRetriever = new DataRetriever(null, "artifactId", ${artifactView.parentArtifact.id}, "PartyDataSheet_AddressesView", null,parent.browserFrame.webUIController.getResourceBundle());
			dataRetriever.setMethod("getGeographicAddress");
			dataRetriever.setParameters(geoId);
			dataRetriever.makeHttpRequest("fillForm();");
			dataRetriever.processDatas('settlementDataSheet', 'settlement');		
			dataRetriever.processDatas('zipCode', 'zipCode');
		}
		
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
		
		function fillForm() {
			var responseXml = dataRetriever.getCurrentResponseValue();
			document.getElementsByName("street")[0].value = responseXml.getElementsByTagName("street")[0].text;
			document.getElementsByName("buildingNumber")[0].value = responseXml.getElementsByTagName("buildingNumber")[0].text;
			document.getElementById("geoAddButton").value = "Mentés";
		}
		
		function checkEmailAddress(value) {
			if( value != "" && value.indexOf(".") != -1 && value.indexOf("@") != -1 ) return true;
			else return false;
		}
		
		function fillParameters(val) {
			document.getElementsByName("id")[0].value = val;
			
		}
		
		function deSelect() {
			var rbGeoAddress = document.getElementsByName("rbGeoAddress");
			for (i = 0; i < rbGeoAddress.length; i++) {
				if(rbGeoAddress[i].checked)  {
					rbGeoAddress[i].checked = false;
				}
			}
			
			document.getElementsByName("settlementDataSheet")[0].selectedIndex = 0;
			document.getElementsByName("street")[0].value = "";
			document.getElementsByName("buildingNumber")[0].value = "";
			getSettlementData();
			document.getElementById("geoAddButton").value = "Hozzáad";
			
		}

				
	</script>
	<title>PartyDataSheet_Addresses</title>
</head>
<body onload="getSettlementData();">
	<form name="pdsAddressesForm" action="${artifactView.codeBase}" method="post" onsubmit="return validateMethod">
	
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name }">
		<input type="hidden" name="method" value="">
		<input type="hidden" name="selectedValue" value="">
		<input type="hidden" name="id" value="">

	<br>
		
		<fieldset style="width: 350px; border-color: white;">
			<c:set property="key" value="ui.pds.geodata.table.header" target="${i18Helper}"></c:set>
			<legend style="color: brown;">${i18Helper.value}</legend>
		
			<div class="readOnlyContainer">			
				<center>
				<table>
					<thead>
						<tr>
							<th><a href="#" onclick="deSelect();">x</a></th>
							<th>Cím</th>
							<th>Központi</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="geo" items="${artifactView.geographicAddresses}" varStatus="index">
						<c:set property="currentGeoAddress" value="${geo.id}" target="${artifactView}"></c:set>					
						<tr>
							<td><input type="radio" name="rbGeoAddress" value="${geo.id}" onclick="fillParameters('${geo.id }');getGeographicAddressData('${geo.id}');"></td>
							<td>${artifactView.currentGeoAddressName}</td>
							<td>${geo.isDefault }</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>	
				</center>		
			</div>
			
			<br><br>
		
			<div class="editableContainer" id="addressDiv">
	
				<div class="row">
					<span class="label" id="cityText">
						<c:set property="key" value="ui.pds.geodata.settlement" target="${i18Helper}"></c:set>
						${i18Helper.value}:
					</span>
					<span class="formw">						
					<select id="settlementDataSheet" name="settlementDataSheet" onChange="getSettlementData();setToDirty();">
						<option value="-1">---</option>
							<c:if test="${artifactView.settlementDataSheet != null}">
								<option value="${artifactView.settlementDataSheet.id}" selected="selected">${artifactView.settlementDataSheet.settlement.name}</option>
							</c:if>
					</select>
					 *</span>
				</div>
				
				<div class="row">
					<span class="label" id="zipText">
						<c:set property="key" value="ui.pds.geodata.zip" target="${i18Helper}"></c:set>
						${i18Helper.value}:
					</span>
					<span class="formw">
						<select id="zipCode" name="zipCode" onChange="setToDirty();">
							<option value="-1">---</option>
						</select>
					</span>
				</div>
				
				<div class="row">
					<span class="label" id="streetText">
						<c:set property="key" value="ui.pds.geodata.street" target="${i18Helper}"></c:set>
						${i18Helper.value}:
					</span>
					<span class="formw"><input type="text" id="street" name="street" value="" onChange="setToDirty();"> *</span>
				</div>
	
				<div class="row">
					<span class="label" id="buildingNumberText">
						<c:set property="key" value="ui.pds.geodata.buildingNumber" target="${i18Helper}"></c:set>
						${i18Helper.value}:
					</span>
					<span class="formw"><input type="text" id="buildingNumber" name="buildingNumber" value="" onChange="setToDirty();"> *</span>
				</div>
	
			</div>
			
	<br>
	
		<div class="readOnlyContainer">		
			<c:set property="key" value="ui.generic.button.add" target="${i18Helper}"></c:set>
			<input type="submit" style="margin-left: 150px;" class="buttonSmall" id="geoAddButton" value="${i18Helper.value}" onclick="validateMethod = newGeographicAddress();">
			<c:set property="key" value="ui.generic.button.delete" target="${i18Helper}"></c:set>
			<input type="button" class="buttonSmall" id="deleteButton" value="${i18Helper.value}" onclick="">			
		</div>
	
	<br><br>

	</fieldset>
		
	<fieldset style="width: 350px; border-color: white;">
	
		<c:set property="key" value="ui.pds.webpagedata.table.header" target="${i18Helper}"></c:set>
		<legend style="color: brown;">${i18Helper.value}</legend>
		
		<br>
		
		<div class="readOnlyContainer" id="webSitesDiv">
			<table style="margin-left: 150px;">
				<tbody>
					<c:forEach var="webPage" items="${artifactView.webPages}" varStatus="index">
						<tr>
							<td style="width: 10px;"><input type="radio" name="rbWebPageAddress" value="${webPage.id}" onclick=""></td>
							<td style="width: 280px;" id="webPage_${index.index}"><a href="#">${webPage.url}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>		
		</div>
			
		<div class="readOnlyContainer">		
			<c:set property="key" value="ui.generic.button.add" target="${i18Helper}"></c:set>
			<input type="submit" style="margin-left: 150px;" class="buttonSmall" id="addButton" value="${i18Helper.value}" onclick="validateMethod = newWebPageAddress();">
			<c:set property="key" value="ui.generic.button.delete" target="${i18Helper}"></c:set>
			<input type="submit" class="buttonSmall" id="deleteButton" value="${i18Helper.value}" onclick="validateMethod = deleteWebPageAddress();">		
		</div>
		
		<br><br>
		
		<div class="editableContainer">
			<div class="row">
				<span class="label" id="webPageText">
					<c:set property="key" value="ui.pds.webpagedata.webpage" target="${i18Helper}"></c:set>
					${i18Helper.value}:
				</span>
				<span id="formw"><input type="text" id="webPageEdit" name="webPageAddress" value="" style="width: 300px;"></span>
			</div>
		</div>
		
	<br>
		
	</fieldset>
	
	<br><br>
		
	<fieldset style="width: 350px; border-color: white;">
	
		<c:set property="key" value="ui.pds.emaildata.table.header" target="${i18Helper}"></c:set>
		<legend style="color: brown;">${i18Helper.value}</legend>
		
	<br>

		<div class="readOnlyContainer">
			<table style="margin-left: 150px;">
				<tbody>
					<c:forEach var="emailAddress" items="${artifactView.emailAddresses}" varStatus="index">
						<tr>					
							<td style="width: 10px"><input type="radio" name="rbEmailAddress" value="${emailAddress.id}" onclick=""></td>
							<td style="width: 280px" id="email_${index.index}"><a href="mailto:${emailAddress.emailAddress}">${emailAddress.emailAddress}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		
		</div>
		
		<div class="readOnlyContainer">		
			<c:set property="key" value="ui.generic.button.add" target="${i18Helper}"></c:set>
			<input type="submit" style="margin-left: 150px;" class="buttonSmall" id="addButton" value="${i18Helper.value}" onclick="validateMethod = newEmailAddress();">
			<c:set property="key" value="ui.generic.button.delete" target="${i18Helper}"></c:set>
			<input type="submit" class="buttonSmall" id="deleteButton" value="${i18Helper.value}" onclick="validateMethod = deleteEmailAddress();">		
		</div>

		<br>
	
		<div class="editableContainer" id="emailsDiv">
			<div class="row">
				<span class="label" id="emailText">
					<c:set property="key" value="ui.pds.emaildata.email" target="${i18Helper}"></c:set>
					${i18Helper.value}:
				</span>
				<span id="formw"><input type="text" id="emailEdit" name="emailAddress" value="user@domain.ext"></span>
			</div>
		</div>

	<br>

	</fieldset>		

	<br><br>

	</form>

</body>
</html>