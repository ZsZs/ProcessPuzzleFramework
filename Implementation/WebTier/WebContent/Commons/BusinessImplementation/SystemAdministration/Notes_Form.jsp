<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Feljegyzés - Űrlap nézet</title>
</head>
<body>
<center><h4>Feljegyzés</h4></center>

<form name="form" action="">

	<div class="editableContainer">
	
		<div class="row">
			<span class="label">Tárgy:</span>
			<span class="formw"><input type="text" style="width: 300px" value="${artifactView.subject}"></span>
		</div>
		
		<div class="row">
			<span class="label">Szerző:</span>
			<span class="formw"><input type="text" value="${artifactView.responsible.partyName.formattedName}"></span>
		</div>
		
		<div class="row">
			<span class="label">Kapják:</span>
			<span class="formw">
				<c:forEach var='recipient' items='${artifactView.recipients}' varStatus='index'>${recipient.partyName.formattedName}</c:forEach>
			</span>
		</div>
	
		<div class="row">
			<span class="label"></span>
			<span class="formw"><textarea cols="50" rows="5">${artifactView.content}</textarea></span>
		</div>

	</div>
</form>
</body>
</html>