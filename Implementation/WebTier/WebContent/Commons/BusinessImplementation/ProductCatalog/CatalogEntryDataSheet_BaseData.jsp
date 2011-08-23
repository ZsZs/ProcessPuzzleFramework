<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Catalog Entry Base Data</title>

</head>
<body>
	<form name="form" id="catalogEntryBaseDataForm" method="post" action="${artifactView.codeBase}">
	
	<br><br>
	
		<div class="editableContainer">
			<fieldset style="width: 400px; border-color: white;">
			<c:set property="key" value="ui.label.catalogEntry" target="${i18Helper}"></c:set>
			<legend>${i18Helper.value}</legend>
				<div class="row">
					<c:set property="key" value="ui.label.productTypeIdentifier" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">${artifactView.productTypeIdentifier}</span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.manufacturerCode" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"><input type="text" id="producerCodeEdit" value="${artifactView.producerCode}" onchange="setToDirty();"></span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.EAN" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"><input type="text" id="eanEdit" value="${artifactView.EAN}" onchange="setToDirty();"></span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.properties" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"><textarea rows="5" cols="50" id="propertiesArea" onchange="setToDirty();">${artifactView.productFeatures}</textarea></span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.price" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"><input type="text" id="priceEdit" value="${artifactView.price}" onchange="setToDirty();"></span>
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