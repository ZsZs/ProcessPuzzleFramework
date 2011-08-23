<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<HTML>
<HEAD>
	<%@ include file="../PageIncludes/SimpleContentPageHeader.jsp" %>
	<TITLE>Authentication Error</TITLE>
</HEAD>
<BODY>
	<br>
	<c:set property="key" value="fc.autherror.authenticationError" target="${i18Helper}"></c:set>
	<center>
	<br><br><br>
		<span><b>${i18Helper.value}</b></span>
	</center>
	<br><br>
</BODY>
</HTML>