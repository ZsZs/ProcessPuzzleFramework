<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<HTML>
<HEAD>
	<%@ include file="../PageIncludes/SimpleContentPageHeader.jsp" %>
	<TITLE>Authorization Error</TITLE>
</HEAD>
<BODY>
	<br>
	<c:set property="key" value="fc.autherror.authorizationError" target="${i18Helper}"></c:set>
	<h4>${i18Helper.value}</h4>
</BODY>
</HTML>