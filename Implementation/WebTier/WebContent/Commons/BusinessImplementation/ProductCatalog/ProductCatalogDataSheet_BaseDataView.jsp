<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>
	<title>Product Catalog Data Sheet</title>
	
	<script type="text/javascript">
	
		function init() {
			<c:if test="${artifactView.catalogStatus}">
				document.getElementById("isActive").checked = "true";
			</c:if>
		}
	
	</script>
</head>
<body onload="init();">
	<form name="form" id="catalogEntryBaseDataForm" method="post" action="${artifactView.codeBase}">
		
		<br><br>
		
			<div class="readOnlyContainer">
				<fieldset style="width: 400px; border-color: white;">
				<c:set property="key" value="ui.label.productCatalog" target="${i18Helper}"></c:set>
				<legend>${i18Helper.value}</legend>
					<div class="row">
						<c:set property="key" value="ui.label.catalogName" target="${i18Helper}"></c:set>
						<span class="label">${i18Helper.value}:</span>
						<span class="formw"><input type="text" id="catalogNameEdit" value="${artifactView.catalogName}" onchange="setToDirty();"></span>
					</div>
					<div class="row">
						<c:set property="key" value="ui.label.isActive" target="${i18Helper}"></c:set>
						<span class="label">${i18Helper.value}:</span>
						<span class="formw"><input type="radio" name="isActive" id="isActive" value="${artifactView.catalogStatus}" onchange="setToDirty();"></span>
					</div>
					<div class="row">
						<c:set property="key" value="ui.label.catalogDescription" target="${i18Helper}"></c:set>
						<span class="label">${i18Helper.value}:</span>
						<span class="formw"><input type="text" id="catalogDescriptionEdit" value="${artifactView.catalogDescription}" onchange="setToDirty();"></span>
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