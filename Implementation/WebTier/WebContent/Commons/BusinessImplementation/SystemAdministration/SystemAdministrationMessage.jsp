<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="GENERATOR" content="IBM Software Development Platform">
	<link rel="stylesheet" href="../../styles/base.css" type="text/css">
	<link rel="stylesheet" href="../../styles/content.css" type="text/css">
	<link rel="stylesheet" href="../../styles/form_styles.css" type="text/css">

	<title>Message from System Administration</title>
</head>

<body>
	<c:set property="key" value="ui.databaseAdministration.${messageKey}" target="${i18Helper}"></c:set>
	${i18Helper.value}
</body>
</html>