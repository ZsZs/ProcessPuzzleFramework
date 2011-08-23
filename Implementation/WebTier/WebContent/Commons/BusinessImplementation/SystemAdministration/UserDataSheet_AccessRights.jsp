<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<link rel="stylesheet" href="./styles/base.css" type="text/css">
<link rel="stylesheet" href="./styles/content.css" type="text/css">
<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">

<title>Felhasználói jogosultságok</title>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" src="./JavaScript/WebUIController/LoadUtility.js"></script>
<script language="javascript" src="./JavaScript/BasicValidation/BasicValidation.js"></script>
<script language="javascript" src="./JavaScript/DataRetriever/DataRetriever.js"></script>
<script language="javascript" src="./JavaScript/DataRetriever/InvalidXmlResponseException.js"></script>
<script language="javascript" src="./JavaScript/WebUIController/WebUIController.js"></script>

</head>
<body>

	<form name="form" id="userAccessRightsForm" method="post" action="${artifactView.codeBase}">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name}">

	<br><br>
	
		<div class="row">
			<select name="artifactType" id="artifactTypeSelect" onchange="">
				<c:set property="key" value="ui.label.choose" target="${i18Helper}"></c:set>
				<option value="-1">${i18Helper.value}</option>
					<c:forEach var="artifactType" items="${artifactView.artifactTypes}" varStatus="index">
						<option value="${artifactType.id}"
						<c:if test="${artifactView.artifactType.id == artifactType.id}">
							selected
						</c:if>
						>${artifactType.typeName}</option>
					</c:forEach>
			</select>
		</div>
	
	<br><br>

		<div class="row">
			<span class="label">
			<select name="object" id="objectSelect" size="10">
			</select>
			</span>
	
			<table id="operationListTable" class="sortable">
				<thead>
					<tr>
						<th></th>
						<c:set property="key" value="ui.label.operations" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="artifactProperty" items="${artifactView.operations}" varStatus="index">
					<c:set var="classNameForRow" value="odd"/>
						<c:if test="${index.count % 2 == 0}">
						<c:set var="classNameForRow" value="even"/>
						</c:if>
					<tr class="${classNameForRow}">
						<td><input type="checkbox" name="operation"></td>
						<td>${artifactProperty.operation}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>		
		</div>
	
		<div class="readOnlyContainer">
			<div class="row">
				<c:set property="key" value="ui.label.save" target="${i18Helper}"></c:set>
				<input class="buttonSmall" type="submit" id="saveButton" value="${i18Helper.value}">
				<c:set property="key" value="ui.label.cancel" target="${i18Helper}"></c:set>
				<input class="buttonSmall" type="button" id="cancelButton" value="${i18Helper.value}" onclick="javascript: document.forms[0].reset(); closeIt();">
			</div>
		</div>
	
	</form>
</body>
</html>