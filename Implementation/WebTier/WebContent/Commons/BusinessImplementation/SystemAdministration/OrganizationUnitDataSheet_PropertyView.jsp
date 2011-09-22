<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Szervezeti egység tulajdonságok</title>
</head>
<body>

	<div class="row">
		<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
		<span class="label">${i18Helper.value}:</span>
		<span class="formw">${artifactView.organizationUnitName}</span>
	</div>

	<div class="row">
		<c:set property="key" value="ui.pds.geodata.settlement" target="${i18Helper}"></c:set>
		<span class="label" id="cityText">${i18Helper.value}:</span>
		<span class="formw">${artifactView.settlement}</span>
	</div>

	<div class="row">
		<c:set property="key" value="ui.pds.geodata.zip" target="${i18Helper}"></c:set>
		<span class="label" id="zipText">${i18Helper.value}:</span>
		<span class="formw">${artifactView.zipCode}</span>
	</div>

	<div class="row">
		<c:set property="key" value="ui.pds.geodata.street" target="${i18Helper}"></c:set>
		<span class="label" id="streetText">${i18Helper.value}:</span>
		<span class="formw">${artifactView.street}</span>
	</div>

	<div class="row">
		<c:set property="key" value="ui.pds.geodata.buildingNumber" target="${i18Helper}"></c:set>
		<span class="label" id="buildingNumberText">${i18Helper.value}:</span>
		<span class="formw">${artifactView.buildingNumber}</span>
	</div>

</body>
</html>