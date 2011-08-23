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
<form name="form" id="catalogEntryPropertyForm" method="post" action="${artifactView.codeBase}">
	
	<br><br>
	
		<div class="readOnlyContainer">
			<fieldset style="width: 400px; border-color: white;">
			<c:set property="key" value="ui.label.productCatalog" target="${i18Helper}"></c:set>
			<legend>${i18Helper.value}</legend>
				<div class="row">
					<c:set property="key" value="ui.label.catalogName" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">${artifactView.catalogName}</span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.isActive" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">${artifactView.catalogStatus}</span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.catalogDescription" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">${artifactView.catalogDescription}</span>
				</div>
			</fieldset>
		</div>
		
	</form>
</body>
</html>
