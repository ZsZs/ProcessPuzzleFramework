<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="expires" content="0">
	<meta name="resource-type" content="document">
	<meta name="distribution" content="global">
	<meta name="robots" content="index, follow">
	<meta name="revisit-after" content="1 days">
	<meta name="rating" content="general">
	<meta name="author" content="IT Kódex Kft.">
	<meta name="description" content="ProcessPuzzle - Workflow and Content Management Framework">
	<meta name="keywords" content="processpuzzle, workflow, content, management, framework, soa, ajax, web">

	<title>ProcessPuzzle</title>
</head>

<body bgcolor="#FFFFFF" >
	<table border="0" cellpadding="4" cellspacing="0" width="100%" align="center" style="vertical-align: middle">
		<tr>
			<td width="100%" align="center">
				<img src="Commons/Skins/Default/Images/intro.jpg" alt="ProcessPuzzle" width="770" height="770" border="0" usemap="#Map">
			</td>
		</tr>
	</table>
	
	<map name="Map">
	  <area shape="rect" coords="30,250,655,350" href=${skinDescriptor.layoutPath}/FrameSet.jsp>
	  <area shape="rect" coords="30,430,655,525" href="Commons/ApplicationAdministration/DefineNewApplication.jsp">
	  <area shape="rect" coords="30,585,655,670" href="Commons/ApplicationAdministration/ManageApplications.jsp">
	</map>
</body>
</html>
