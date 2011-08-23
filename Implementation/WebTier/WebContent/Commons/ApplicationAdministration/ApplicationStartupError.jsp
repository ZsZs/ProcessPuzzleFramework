<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<HTML>

<HEAD>
	<%@ include file="../PageIncludes/SimpleContentPageHeader.jsp" %>
</HEAD>

<BODY>
	<H1>Application start up error.</H1>
	<H6>Name: <c:out value="${ApplicationException.name}" /></H6>
	<p><b>Description:</b> <c:out value="${ApplicationException.description}" /></p>
	<p><b>Stack trace:</b> <c:out value="${ApplicationException.stackTrace}" /></p>
</BODY>
</HTML>