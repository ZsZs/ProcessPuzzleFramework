<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>Purchase Order Properties</title>

	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>

</head>
<body>

	<form name="form" id="orderPropertiesForm" method="post" action="${artifactView.codeBase}">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name}">

	<br><br>
	
		<div class="editableContainer">
			<div class="row">
				<c:set property="key" value="ui.label.orderId" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.orderId}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.purchaser" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.purchaser}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.deliveryAddress" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.deliveryInstructions}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.orderDate" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.dateCreated}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.contactPerson" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.contactPerson}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.phone" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.telephoneNumber}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.fax" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.faxNumber}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.email" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.emailAddress}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.comment" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.termsAndConditions}</span>
			</div>
		</div>

	<br><br>
	
			<div class="readOnlyContainer">
			<table style="width: 700px;" class="sortable" id="orderListTable">
				<thead>
					<tr>
						<th></th>
						<c:set property="key" value="ui.label.orderLineId" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.itemIdentifier" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.designation" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.manufacturerCode" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.customsTariffNumber" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.quantity" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.unitPrice" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.price" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="artifactProperty" items="${artifactView.orderLinePropertyViews}" varStatus="index">
						<c:set var="classNameForRow" value="odd"/>
							<c:if test="${index.count % 2 == 0}">
								<c:set var="classNameForRow" value="even"/>
							</c:if>
							<tr class="${classNameForRow}">
							<td><input type="checkbox" name="items" onclick=""></td>
							<td><a href="#" onclick="goToDataSheetByLink('${artifactProperty.orderLineIdentifier}', '${artifactProperty.id}');">${artifactProperty.orderLineIdentifier}</a></td>
							<td>${artifactProperty.productTypeIdentifier}</td>
							<td>${artifactProperty.name}</td>
							<td>${artifactProperty.producerCode}</td>
							<td>${artifactProperty.customsTariffNumber}</td>
							<td>${artifactProperty.numberOrdered}</td>
							<td>${artifactProperty.unitPrice}</td>
							<td>${artifactProperty.price}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="row">
				<c:set property="key" value="ui.label.total" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.total} </span>
			</div>
		</div>
	</form>

</body>
</html>