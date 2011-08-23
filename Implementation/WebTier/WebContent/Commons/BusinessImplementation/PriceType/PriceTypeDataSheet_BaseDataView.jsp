<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>
	<title>PriceType Data Sheet</title>
</head>
<body>
	<form name="form" id="catalogEntryBaseDataForm" method="post" action="${artifactView.codeBase}">
		
		<br><br>
		
			<div class="readOnlyContainer">
				<fieldset style="width: 400px; border-color: white;">
				<c:set property="key" value="ui.label.priceType" target="${i18Helper}"></c:set>
				<legend>${i18Helper.value}</legend>
					<div class="row">
						<c:set property="key" value="ui.label.priceType" target="${i18Helper}"></c:set>
						<span class="label">${i18Helper.value}:</span>
						<span class="formw"><input type="text" id="priceTypeEdit" value="${artifactView.priceTypeName}" onchange="setToDirty();"></span>
					</div>
					<div class="row">
						<c:set property="key" value="ui.label.description" target="${i18Helper}"></c:set>
						<span class="label">${i18Helper.value}:</span>
						<span class="formw"><input type="text" id="priceTypeDescriptionEdit" value="${artifactView.description}" onchange="setToDirty();"></span>
					</div>
					<div class="row">
						<c:set property="key" value="ui.label.valid" target="${i18Helper}"></c:set>
						<span class="label">${i18Helper.value}:</span>
						<span class="formw"><input type="text" id="priceTypeValidEdit" value="${artifactView.valid}" onchange="setToDirty();"></span>
					</div>
					<div class="row">
						<c:set property="key" value="ui.label.default" target="${i18Helper}"></c:set>
						<span class="label">${i18Helper.value}:</span>
						<span class="formw"><input type="text" id="priceTypeDefaultEdit" value="${artifactView.default}" onchange="setToDirty();"></span>
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