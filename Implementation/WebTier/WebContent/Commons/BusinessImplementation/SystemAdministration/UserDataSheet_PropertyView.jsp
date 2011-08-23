<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<link rel="stylesheet" href="./styles/base.css" type="text/css">
<link rel="stylesheet" href="./styles/content.css" type="text/css">
<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">

<title>Felhasználó tulajdonságai</title>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" src="./JavaScript/WebUIController/LoadUtility.js"></script>
<script language="javascript" src="./JavaScript/BasicValidation/BasicValidation.js"></script>
<script language="javascript" src="./JavaScript/DataRetriever/DataRetriever.js"></script>
<script language="javascript" src="./JavaScript/DataRetriever/InvalidXmlResponseException.js"></script>
<script language="javascript" src="./JavaScript/WebUIController/WebUIController.js"></script>
	
	
</head>
<body>

	<form name="form" id="userBaseDataForm" method="post" action="${artifactView.codeBase}">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name}">

	<br><br>
	
		<div class="editableContainer">
			<div class="row">
				<c:set property="key" value="ui.label.userName" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.userName}</span>
			</div>

			<div class="row">
				<c:set property="key" value="ui.label.designation" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.designation}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.description" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.description}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.status" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.status}</span>
			</div>
		</div>

	</form>
</body>
</html>
