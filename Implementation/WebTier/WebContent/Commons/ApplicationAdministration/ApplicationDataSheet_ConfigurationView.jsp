<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>Application Configuration</title>

	<%@ include file="../PageIncludes/DataFormPageHeader.jsp" %>

</head>
<body>

	<form name="form" id="applicationDataForm" method="post" action="${artifactView.codeBase}">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name}">

	<br><br>
	
		<div class="editableContainer">
			<fieldset style="width: 550px; border-color: white;">
			<c:set property="key" value="ui.label.application" target="${i18Helper}"></c:set>
			<legend>${i18Helper.value}</legend>
				<div class="row">
					<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"><input type="text" id="appname" value="${artifactView.name}" onchange="setToDirty();"></span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.version" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"><input type="text" id="appver" value="${artifactView.version}" onchange="setToDirty();"></span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.workingFolder" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"><input type="text"></span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"><input type="text"></span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.password" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"><input type="text"></span>
				</div>
			</fieldset>
		</div>		

		<div class="editableContainer">
			<fieldset style="width: 550px; border-color: white;">
			<c:set property="key" value="ui.label.presentation" target="${i18Helper}"></c:set>
			<legend>${i18Helper.value}</legend>
				<div class="row">
					<c:set property="key" value="ui.label.businessBrowser" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">
						<select id="bbSelect" onchange="setToDirty();">
							<c:set property="key" value="ui.label.choose" target="${i18Helper}"></c:set>
							<option>${i18Helper.value}</option>
							<c:set property="key" value="ui.label.on" target="${i18Helper}"></c:set>
							<option>${i18Helper.value}</option>
							<c:set property="key" value="ui.label.off" target="${i18Helper}"></c:set>
							<option>${i18Helper.value}</option>
						</select>
					</span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.contextMenu" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">
						<select id="cmSelect" onchange="setToDirty();">
							<c:set property="key" value="ui.label.choose" target="${i18Helper}"></c:set>
							<option>${i18Helper.value}</option>
							<c:set property="key" value="ui.label.on" target="${i18Helper}"></c:set>
							<option>${i18Helper.value}</option>
							<c:set property="key" value="ui.label.off" target="${i18Helper}"></c:set>
							<option>${i18Helper.value}</option>
						</select>
					</span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.infoPane" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">
						<select id="ipSelect" onchange="setToDirty();">
							<c:set property="key" value="ui.label.choose" target="${i18Helper}"></c:set>
							<option>${i18Helper.value}</option>
							<c:set property="key" value="ui.label.on" target="${i18Helper}"></c:set>
							<option>${i18Helper.value}</option>
							<c:set property="key" value="ui.label.off" target="${i18Helper}"></c:set>
							<option>${i18Helper.value}</option>
						</select>
					</span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.skin" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"></span>
				</div>
			</fieldset>
		</div>		

		<div class="editableContainer">
			<fieldset style="width: 550px; border-color: white;">
			<c:set property="key" value="ui.label.internalization" target="${i18Helper}"></c:set>
			<legend>${i18Helper.value}</legend>
				<div class="row">
					<c:set property="key" value="ui.label.language" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"></span>
				</div>
			</fieldset>
		</div>		

	<br><br>

		<div class="readOnlyContainer">
			<c:set property="key" value="ui.generic.button.undeploy" target="${i18Helper}"></c:set>
			<input type="button" class="buttonSmall" style="margin-left: 150px;" value="${i18Helper.value}">
			<c:set property="key" value="ui.generic.button.start" target="${i18Helper}"></c:set>
			<input type="button" class="buttonSmall" value="${i18Helper.value}">
			<c:set property="key" value="ui.generic.button.stop" target="${i18Helper}"></c:set>
			<input type="button" class="buttonSmall" value="${i18Helper.value}">
		</div>
	</form>


</body>
</html>