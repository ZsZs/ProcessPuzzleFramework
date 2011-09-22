<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Composite Category BaseData</title>

</head>
<body>
	<form name="form" id="compositeCategoryBaseDataForm" method="post" action="${artifactView.codeBase}">
	
	<br><br>
	
		<div class="editableContainer">
			<fieldset style="width: 400px; border-color: white;">
			<c:set property="key" value="ui.label.compositeCategory" target="${i18Helper}"></c:set>
			<legend>${i18Helper.value}</legend>
				<div class="row">
					<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"><input type="text" id="nameEdit" value="${artifactView.name}" onchange="setToDirty();"></span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.description" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"><textarea rows="5" cols="50" id="descriptionArea" onchange="setToDirty();">${artifactView.description}</textarea></span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.picture" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"></span>
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