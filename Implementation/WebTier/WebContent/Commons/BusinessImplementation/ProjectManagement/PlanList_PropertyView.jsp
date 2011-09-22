<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<%@ include file="../../PageIncludes/PropertyViewPageHeader.jsp" %>

	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tervlista tulajdonságok</title>
</head>
<body>

	<form action="" name="form">
	
		<div class="readOnlyContainer">
		
			<div class="row">
				<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">PlanList</span>
			</div>
			
			<div class="row">
				<c:set property="key" value="ui.label.fullName" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">PlanList_ListView</span>
			</div>
			
			<div class="row">
				<c:set property="key" value="ui.label.type" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">ArtifactList_Properties</span>
			</div>
			
		</div>
	
	</form>

</body>
</html>