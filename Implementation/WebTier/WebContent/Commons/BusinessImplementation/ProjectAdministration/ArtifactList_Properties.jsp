<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<HTML>
<HEAD>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">

	<TITLE>ArtifactList_Properties</TITLE>
</HEAD>
<BODY>
<center></center>
	<form name="ArtifactList_PropertiesForm">
		<div class="readOnlyContainer">
			<div class="row">
				<span id="Name_Label" class="label">
				<c:set target="${i18Helper}" property="key" value="ui.label.name"/>${i18Helper.value}:
				</span>
				<span id="Name_Value" class="formw">${artifactView.artifactName}</span>
			</div>

			<div class="row">
				<span id="FullName_Label" class="label">
				<c:set target="${i18Helper}" property="key" value="ui.label.fullName"/>${i18Helper.value}:				
				</span>
				<span id="FullName_Value" class="formw">${artifactView.fullName}</span>
			</div>

			<div class="row">
				<span id="Type_Label" class="label">
				<c:set target="${i18Helper}" property="key" value="ui.label.type"/>${i18Helper.value}:				
				</span>
				<span id="Type_Value" class="formw">${artifactView.type.name}</span>
			</div>
		</div>
	</form>
</BODY>
</HTML>
