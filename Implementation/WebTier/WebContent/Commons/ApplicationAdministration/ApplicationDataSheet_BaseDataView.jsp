<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>Application BaseData</title>

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
					<c:set property="key" value="ui.label.status" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">${artifactView.status}</span>
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
	</form>


</body>
</html>