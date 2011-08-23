<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<HTML>
<HEAD>

	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">

<TITLE>Artifact_Properties</TITLE>
</HEAD>
<BODY>
	<form name="Artifact_PropertiesForm">
		<div class="readOnlyContainer">
			<div class="row">
				<span id="creationDate_Label" class="label">
				<c:set target="${i18Helper}" property="key" value="ui.processpuzzle.general.creationDate"/>${i18Helper.value}:
				</span>
				<span id="creationDate_Value" class="formw">${propertyView.creationDate}</span>
			</div>

			<div class="row">
				<span id="lastModDate_Label" class="label">
				<c:set target="${i18Helper}" property="key" value="ui.processpuzzle.general.lastModDate"/>${i18Helper.value}:			
				</span>
				<span id="lastModDate_Value" class="formw">${propertyView.lastModDate}</span>
			</div>

			<div class="row">
				<span id="lastModifier_Label" class="label">
				<c:set target="${i18Helper}" property="key" value="ui.processpuzzle.general.lastModifier"/>${i18Helper.value}:
				</span>
				<span id="lastModifier_Value" class="formw">${propertyView.lastModifier}</span>
			</div>

			<div class="row">
				<span id="responsible_Label" class="label">
				<c:set target="${i18Helper}" property="key" value="ui.processpuzzle.general.responsible"/>${i18Helper.value}:
				</span>
				<span id="responsible_Value" class="formw">${propertyView.responsible}</span>
			</div>

			<div class="row">
				<span id="versionControlled_Label" class="label">
				<c:set target="${i18Helper}" property="key" value="ui.generic.artifact.isVersionControlled"/>${i18Helper.value}				
				</span>
				<span id="versionControlled_Value" class="formw">${propertyView.isVersionControlled}</span>
			</div>

		</div>
	</form>


</BODY>
</HTML>
