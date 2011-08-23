<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>
	<title>ActionDataSheet_SpecificResourceAllocations</title>

	<script language="javascript" src="ResourceAllocationFunctions.js" ></script>	

	<script language="javascript">
	</script>	
</head>

<body>
	<form action="" name="form" id="adssraForm">
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
					<c:forEach var="resourceAllocation" items="${artifactView.specificResourceAllocations}" varStatus="index">
						<tr>
							<td><input type="radio" name="rbGas" onclick="fillParameters('${resourceAllocation.id}'); getGeneralResourceAllocationData('${resourceAllocation.id}');"></td>
							<td>${i18Helper.value}</td>
							<td>${resourceAllocation.quantity.amount}</td>
							<c:set property="key" value="ui.pantokrator.unitType.${resourceAllocation.quantity.unit.name}" target="${i18Helper}"></c:set>															
							<td>${i18Helper.value}</td>
						</tr>	
					</c:forEach>
				</tbody>
			</table>
		</div><br>
		
		<div class="readOnlyContainer">
			<c:set property="key" value="ui.generic.button.add" target="${i18Helper}"></c:set>
			<input type="button" class="buttonSmall" name="add" value="${i18Helper.value}">
			<c:set property="key" value="ui.generic.button.delete" target="${i18Helper}"></c:set>
			<input type="button" class="buttonSmall" name="delete" value="${i18Helper.value}">
		</div><br>
		
		<div class="editableContainer">
			<fieldset style="width: 400px; border-color: white;">
			<c:set property="key" value="ui.ads.specificresources.intakeNewSpecificResource" target="${i18Helper}"></c:set>
			<legend style="color: brown;">${i18Helper.value}</legend><br>
			 	<div class="row">
			 		<c:set property="key" value="ui.label.resourceType" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">		
						<select id="resourceType" name="resourceType">
							<c:forEach var="resourceType" items="${artifactView.possibleResourceTypes}" varStatus="index">
								<c:set property="key" value="ui.pantokrator.partyRoleType.${resourceType.name}" target="${i18Helper}"></c:set>										
								<option value="-1">---</option>
								<option value="${resourceType.id}">${i18Helper.value}</option>
							</c:forEach>
						</select>

					</span>
				</div>
			 	<div class="row">
			 		<c:set property="key" value="ui.label.resource" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">		
						<select name="resource">
							<option value="resource">---</option>
						</select>
					</span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.quantity" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">
						<input type="text" name="quantity">
					</span>
				</div>
			 	<div class="row">
			 		<c:set property="key" value="ui.label.measure" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">		
						<c:set property="key" value="ui.pantokrator.unitType.unit" target="${i18Helper}"></c:set>															
						<select name="mUnit">
							<option value="-1">---</option>
							<option value="unit">${i18Helper.value}</option>
						</select>
					</span>
				</div>
	<br><br><br>
			</fieldset>
		</div>
		
		</center>
	</form>
</body>
</html>