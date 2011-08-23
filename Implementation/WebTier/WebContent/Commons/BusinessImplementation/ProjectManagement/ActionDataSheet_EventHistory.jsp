<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<link rel="stylesheet" href="./styles/base.css" type="text/css">
<link rel="stylesheet" href="./styles/content.css" type="text/css">
<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>ActionDataSheet_EventHistory</title>
</head>
<body>

<form name="adsehForm" id="adsehForm" method="post" action="${artifactView.codeBase }">
<center>

<div class="readOnlyContainer">
<table border="1" class="sortable">
	<thead>
		<tr>
			<c:set property="key" value="ui.label.time" target="${i18Helper}"></c:set>
			<th>${i18Helper.value}</th>
			<c:set property="key" value="ui.label.event" target="${i18Helper}"></c:set>
			<th>${i18Helper.value}</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="event" items="${artifactView.events}" varStatus="index">
			<c:set property="key" value="ui.generic.action.event.${event.name}" target="${i18Helper}"></c:set>		
			<tr>
				<td>${event.occuredOn.asFormattedString}</td>
				<td>${i18Helper.value}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>
</center>
</form>

</body>
</html>
