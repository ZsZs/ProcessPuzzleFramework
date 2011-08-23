<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<HTML>
<HEAD>
	<%@ include file="../PageIncludes/SimpleContentPageHeader.jsp" %>
</HEAD>
<BODY>
	<h1>Programming Error</h1> 
	<H6>Error: <c:out value="${error.name}" /></H6>
	<p><b>Description:</b> <c:out value="${error.description}" /></p>
	<p><b>Stack trace:</b> <c:out value="${error.stackTrace}" /></p>
</BODY>
</HTML>
