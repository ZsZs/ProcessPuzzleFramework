<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>Purchase Order Base Data</title>

	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>
	<link rel="stylesheet" href="JavaScript/Calendar/simplecalendar.css" type="text/css">
	<script type="text/javascript" src="JavaScript/Calendar/simplecalendar.js"></script>
	<script type="text/javascript">
		imgUp.src="./Images/scalendar/up.gif";
		imgDown.src="./Images/scalendar/down.gif";
	</script>
	
	<script type="text/javascript">
		function init() {
			calendar_init();
		}
	</script>

</head>
<body onload="init();">

	<form name="form" id="orderBaseDataForm" method="post" action="${artifactView.codeBase}">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name}">

	<br><br>
	
		<div class="editableContainer">
			<fieldset style="width: 550px; border-color: white;">
			<c:set property="key" value="ui.label.order" target="${i18Helper}"></c:set>
			<legend>${i18Helper.value}</legend>
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
				<span class="formw"><input type="text" id="deliveryAddressEdit" value="${artifactView.deliveryInstructions}" onchange="setToDirty();"></span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.orderDate" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" id="orderDateEdit" name="orderDate" readonly="readonly" value="${artifactView.dateCreated}" onchange="setToDirty();">
				<a href="javascript: void(0);" id="deleteAnchor" onmouseover="if (timeoutId) clearTimeout(timeoutId);window.status='Show Calendar';return true;" onmouseout="if (timeoutDelay) calendarTimeout();window.status='';" onclick="g_Calendar.show(event,'form.orderDate',true,'yyyy.mm.dd'); return false;">
				<img src="./JavaScript/Calendar/Images/calendar.gif" name="imgCalendar" width= 34 height=21 border="0" alt=""></a>
				<c:set property="key" value="ui.generic.button.delete" target="${i18Helper}"></c:set>				
				<img src="./JavaScript/Calendar/Images/del.gif" border="0" onmouseover="this.style.cursor='hand'" alt="Törlés..." onClick="clearTextFieldById('orderDateEdit');">
				</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.contactPerson" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" id="contactPersonEdit" value="${artifactView.contactPerson}" onchange="setToDirty();"></span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.phone" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" id="telephoneEdit" value="${artifactView.telephoneNumber}" onchange="setToDirty();"></span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.fax" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" id="faxEdit" value="${artifactView.faxNumber}" onchange="setToDirty();"></span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.email" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" id="emailEdit" value="${artifactView.emailAddress}" onchange="setToDirty();"></span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.comment" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><textarea rows="3" cols="50" name="comment" onchange="setToDirty();">${artifactView.termsAndConditions}</textarea></span>
			</div>
			</fieldset>
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
	
	<br><br>

		<div class="readOnlyContainer">
			<c:set property="key" value="ui.generic.button.updateAndSave" target="${i18Helper}"></c:set>
			<input type="submit" class="buttonSmall" style="margin-left: 150px;" value="${i18Helper.value}" onclick="setToClean();">
			<c:set property="key" value="ui.generic.button.placeOrder" target="${i18Helper}"></c:set>
			<input type="submit" class="buttonSmall" value="${i18Helper.value}" onclick="setToClean();">
			<c:set property="key" value="ui.generic.button.cancel" target="${i18Helper}"></c:set>
			<input type="submit" class="buttonSmall" value="${i18Helper.value}" onclick="closeIt();">
		</div>
	</form>

</body>
</html>