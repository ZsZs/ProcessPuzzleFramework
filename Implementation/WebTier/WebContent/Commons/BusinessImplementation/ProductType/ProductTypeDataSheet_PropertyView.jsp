<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>ProductCatalogDataSheet_PropertyView</title>

</head>

<body>

	<form name="form" id="productTypePropertiesForm" method="post" action="${artifactView.codeBase}">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name}">
		<br><br>
		<div class="editableContainer">
			<div class="row">
				<c:set property="key" value="ui.label.identifier" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.identifier}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.productTypeName}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.description" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.description}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.taxationType" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.taxationType}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.EAN" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.EANCode}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.manufacturerCode" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.producerCode}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.price" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.currentValidPricesAsText}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.feature" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.featureTypesAsText}</span>
			</div>
		</div>
	</form>
</body>
</html>