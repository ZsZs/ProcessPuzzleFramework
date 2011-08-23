<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<HTML>
<HEAD>
	<%@ include file="../PageIncludes/SimpleContentPageHeader.jsp" %>
	<TITLE>Unknown FrontCommand Error</TITLE>
</HEAD>
<BODY>
	<c:set property="key" value="fc.unknownerror.error" target="${i18Helper}"></c:set>
	<H1>${i18Helper.value}</H1>
	<c:set property="key" value="fc.unknownerror.description" target="${i18Helper}"></c:set>
	<P>${i18Helper.value} <%= request.getAttribute("error") %></P>
</BODY>
</HTML>
