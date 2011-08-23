<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Info Navigator</title>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="../../${skinDescriptor.stylesPath}/base.css">

	<!-- *************************************************** -->
	<link rel="stylesheet" href="../../${skinDescriptor.stylesPath}/InforNavigator.css">
	<!-- *************************************************** -->

	<script language="JavaScript" src="../JavaScript/CommonScripts/AssertUtil.js"></script>
	<script language="JavaScript" src="../JavaScript/CommonScripts/Collection.js"></script>
	<script language="JavaScript" src="../JavaScript/CommonScripts/UserException.js"></script>
	<script language="javascript" src="../JavaScript/CommonScripts/CList.js"></script>
	<script language="javascript" src="../JavaScript/CommonScripts/inheritFrom.js"></script>
	<script language="JavaScript" src="../JavaScript/TabWidget/Tab.js"></script>
	<script language="JavaScript" src="../JavaScript/TabWidget/TabEvents.js"></script>
	<script language="javascript" src="../JavaScript/TabWidget/TabWidget.js" ></script>
	<script type="text/javascript" src="../JavaScript/WebUIController/WebUILogger.js"></script>
	<script language="javascript" src="../JavaScript/WebUIController/WebUIController.js" ></script>
	<script type="text/javascript" src="../JavaScript/Log4JavaScript/log4javascript.js"></script>

	<script type="text/javascript">
		var infoPanelSelector;
		
		function setUp(){
			infoPanelSelector = new TabWidget(document.getElementById("TabWidget"));
			infoPanelSelector.show();
		}	
	</script>
</head>

<body onLoad="setUp();">
	<div id="TabWidget"></div>
</body>
</html>
