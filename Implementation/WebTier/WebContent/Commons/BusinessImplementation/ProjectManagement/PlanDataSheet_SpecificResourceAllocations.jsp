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
<title>PlanDataSheet_SpecificResourceAllocation</title>
</head>
<body>

	<form name="plandssrForm" method="post" action="${artifactView.codeBase }">
	<input type="hidden" name="action" value="UpdateView">
	<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
	<input type="hidden" name="viewName" value="${artifactView.name }">

		<center>

			<div class="readOnlyContainer">
				<table style="width: 700px;" class="sortable" id="planDataSheetSpecificResourceAllocationTable">
					<thead>
						<tr>
							<c:set property="key" value="ui.label.resource" target="${i18Helper}"></c:set>
							<th>${i18Helper.value}</th>
							<c:set property="key" value="ui.label.resourceType" target="${i18Helper}"></c:set>
							<th>${i18Helper.value}</th>
							<c:set property="key" value="ui.label.quantity" target="${i18Helper}"></c:set>
							<th>${i18Helper.value}</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="artifactProperty" items="${artifactView.specificResourceAllocations}" varStatus="index">
						<tr>
							<td>${resourceAllocation.resource}</td>
							<td>${resourceAllocation.resourceType}</td>
							<td>${resourceAllocation.quantity}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			
		</center>
	</form>	
</body>
</html>