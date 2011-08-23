<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>Application Properties</title>

	<%@ include file="../PageIncludes/DataFormPageHeader.jsp" %>

</head>
<body>

	<form name="form" id="applicationPropertiesForm" method="post" action="${artifactView.codeBase}">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name}">

	<br><br>
	
		<div class="editableContainer">
			<div class="row">
				<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.name}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.version" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.version}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.status" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.status}</span>
			</div>
		</div>

	</form>
</body>
</html>