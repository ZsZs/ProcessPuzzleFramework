<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>Product Type Data Sheet</title>

	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>
	<link rel="stylesheet" href="./JavaScript/Calendar/simplecalendar.css" type="text/css">
	<script type="text/javascript" src="./JavaScript/Calendar/simplecalendar.js"></script>
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

	<form name="form" id="productTypeBaseDataForm" method="post" action="${artifactView.codeBase}">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name}">

	<br><br>
	
		<div class="editableContainer">
			<fieldset style="width: 400px; border-color: white;">
			<c:set property="key" value="ui.label.productType" target="${i18Helper}"></c:set>
			<legend>${i18Helper.value}</legend>
			<div class="row">
				<c:set property="key" value="ui.label.identifier" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.identifier}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" id="nameEdit" value="${artifactView.name}" onchange="setToDirty();"></span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.description" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><textarea rows="3" cols="50" name="description" onchange="setToDirty();">${artifactView.description}</textarea></span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.taxationType" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" id="taxTypeEdit" value="${artifactView.taxationType}" onchange="setToDirty();"></span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.EAN" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" id="eanCodeEdit" value="${artifactView.EANCode}" onchange="setToDirty();"></span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.manufacturerCode" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" id="producerCodeEdit" value="${artifactView.producerCode}" onchange="setToDirty();"></span>
			</div>
			</fieldset>
		</div>

	<br><br>

		<div class="readOnlyContainer">
			<c:set property="key" value="ui.generic.button.save" target="${i18Helper}"></c:set>
			<input type="submit" class="buttonSmall" style="margin-left: 150px;" value="${i18Helper.value}" onclick="setToClean();">
			<c:set property="key" value="ui.generic.button.cancel" target="${i18Helper}"></c:set>
			<input type="button" class="buttonSmall" value="${i18Helper.value}" onclick="closeIt();">
		</div>
		
	<br><br>

		<div class="readOnlyContainer">
		<table style="width: 700px;" class="sortable" id="priceListTable">
			<thead>
				<tr>
					<c:set property="key" value="ui.label.priceType" target="${i18Helper}"></c:set>
					<th>${i18Helper.value}</th>
					<c:set property="key" value="ui.label.price" target="${i18Helper}"></c:set>
					<th>${i18Helper.value}</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="validPriceHelper" items="${artifactView.currentValidPrices}" varStatus="index">
					<c:set var="classNameForRow" value="odd"/>
						<c:if test="${index.count % 2 == 0}">
							<c:set var="classNameForRow" value="even"/>
						</c:if>
						<tr class="${classNameForRow}">
							<td>${validPriceHelper.priceTypeName}</td>
							<td>${validPriceHelper.priceAmountAsText}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>		
	
	<br><br>

		<div class="readOnlyContainer">
		<table style="width: 700px;" class="sortable" id="featureListTable">
			<thead>
				<tr>
					<c:set property="key" value="ui.label.featureName" target="${i18Helper}"></c:set>
					<th>${i18Helper.value}</th>
					<c:set property="key" value="ui.label.feature" target="${i18Helper}"></c:set>
					<th>${i18Helper.value}</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="featureHelper" items="${artifactView.featureValues}" varStatus="index">
					<c:set var="classNameForRow" value="odd"/>
						<c:if test="${index.count % 2 == 0}">
							<c:set var="classNameForRow" value="even"/>
						</c:if>
						<tr class="${classNameForRow}">
						<td>${featureHelper.featureName}</td>
						<td>${featureHelper.featureValue}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	
	</form>

</body>
</html>