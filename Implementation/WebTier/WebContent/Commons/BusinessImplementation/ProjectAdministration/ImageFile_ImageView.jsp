<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<HTML>
<HEAD>

	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">

<TITLE>ImageView</TITLE>
</HEAD>
<BODY>
	<br>
	<div class="readOnlyContainer">
			<span id="imageValue" class="formw"><a href="DownloadFile?fileId=${artifactView.artifactId}"><img src="${artifactView.asJpegSource}"></a></span>
	</div>
</BODY>
</HTML>
