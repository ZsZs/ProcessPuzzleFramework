<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" href="styles/base.css" type="text/css">
	<link rel="stylesheet" href="styles/content.css" type="text/css">
	<link rel="stylesheet" href="styles/form_styles.css" type="text/css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<script language="JavaScript" src="./JavaScript/SortTable/SortTable.js"></script>
<title>PlanDataSheet_GeneralResourcesAllocations</title>
</head>
<body>

	<form name="plandsgrForm" action="${artifactView.codeBase }">
	<input type="hidden" name="action" value="UpdateView">
	<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
	<input type="hidden" name="viewName" value="${artifactView.name }">

		<center>

			<div class="readOnlyContainer">
				<table style="width: 700px;" class="sortable" id="planDataSheetGeneralResourceAllocationTable">
					<thead>
						<tr>
							<c:set property="key" value="ui.label.resource" target="${i18Helper}"></c:set>
							<th>${i18Helper.value}</th>
							<c:set property="key" value="ui.label.quantity" target="${i18Helper}"></c:set>
							<th>${i18Helper.value}</th>
							<th>Mértékegység</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="resourceAllocation" items="${artifactView.generalResourceAllocations}" varStatus="index">
						<c:set property="key" value="ui.pantokrator.partyRoleType.${resourceAllocation.partyRoleType.name}" target="${i18Helper}"></c:set>					
						<tr>
							<td>${i18Helper.value}</td>
							<td>${resourceAllocation.quantity.amount}</td>
							<td>${resourceAllocation.quantity.unit.name}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>

		</center>
	</form>	
</body>
</html>