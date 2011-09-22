<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ include file="../../PageIncludes/PropertyViewPageHeader.jsp" %>

	<link rel="stylesheet" href="./styles/base.css" type="text/css" />
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css" />
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>ProcessPuzzle - PlanDataSheet PropertyView</title>
</head>
<body>

	<div class="readOnlyContainer">
		<div class="row">
			<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>	
			<span class="label">${i18Helper.value}:</span>
			<span class="formw">${artifactView.actionName}</span>
		</div>
		<div class="row">
			<c:set property="key" value="ui.label.subject" target="${i18Helper}"></c:set>	
			<span class="label">${i18Helper.value}:</span>
			<span class="formw">${artifactView.subject}</span>
		</div>
		<div class="row">
			<c:set property="key" value="ui.label.description" target="${i18Helper}"></c:set>	
			<span class="label">${i18Helper.value}</span>
			<span class="formw">${artifactView.description}</span>
		</div>
		
		<div class="row">
			<c:set property="key" value="ui.label.protocol" target="${i18Helper}"></c:set>
			<span class="label">${i18Helper.value}:</span>
			<span class="formw">${artifactView.protocol}</span>
		</div>
		<div class="row">
			<c:set property="key" value="ui.label.status" target="${i18Helper}"></c:set>
			<span class="label">${i18Helper.value}:</span>
			<span class="formw">${artifactView.status}</span>
		</div>
		
		<div class="row">
			<c:set property="key" value="ui.label.owner" target="${i18Helper}"></c:set>
			<span class="label">${i18Helper.value}:</span>
			<span class="formw">${artifactView.owner}</span>
		</div>
		<div class="row">
			<c:set property="key" value="ui.label.priority" target="${i18Helper}"></c:set>
			<span class="label">${i18Helper.value}:</span>
			<span class="formw">${artifactView.priority}</span>
		</div>
		
		<div class="row">
			<c:set property="key" value="ui.label.projectedBegin" target="${i18Helper}"></c:set>
			<span class="label">${i18Helper.value}:</span>
			<span class="formw">${artifactView.projectedBegin}</span>
		</div>
		<div class="row">
			<c:set property="key" value="ui.label.projectedEnd" target="${i18Helper}"></c:set>
			<span class="label">${i18Helper.value}:</span>
			<span class="formw">${artifactView.projectedEnd}</span>
		</div>
		<div class="row">
			<c:set property="key" value="ui.label.realBegin" target="${i18Helper}"></c:set>
			<span class="label">${i18Helper.value}:</span>
			<span class="formw">${artifactView.realBegin}</span>
		</div>
		<div class="row">
			<c:set property="key" value="ui.label.realEnd" target="${i18Helper}"></c:set>
			<span class="label">${i18Helper.value}:</span>
			<span class="formw">${artifactView.realEnd}</span>
		</div>
	
	</div>
</body>
</html>
