<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<LINK rel="stylesheet" href="../../${skinDescriptor.stylesPath}/base.css" type="text/css">
	<link rel="stylesheet" href="../../${skinDescriptor.stylesPath}/frameMenu.css" type="text/css">
	<link rel="stylesheet" href="../../${skinDescriptor.stylesPath}/MenuWidget.css" type="text/css">
	<LINK rel="stylesheet" href="../../${skinDescriptor.stylesPath}/AuthenticationWidget.css" type="text/css">

	<script language="JavaScript" src="../JavaScript/CommonScripts/AssertUtil.js"></script>
	<script language="JavaScript" src="../JavaScript/FundamentalTypes/XmlResource.js"></script>
	<script language="JavaScript" src="../JavaScript/CommonScripts/Collection.js"></script>
	<script language="JavaScript" src="../JavaScript/CommonScripts/UserException.js"></script>
	<script language="JavaScript" src="../JavaScript/CommonScripts/BrowserEvent.js"></script>
	<script language="javascript" src="../JavaScript/CommonScripts/CList.js"></script>
	<script language="javascript" src="../JavaScript/CommonScripts/inheritFrom.js"></script>
	<script language="javascript" src="../JavaScript/CommonScripts/CSimpleObservable.js"></script>
	<script language="JavaScript" src="../JavaScript/AuthenticationWidget/AuthenticationWidget.js"></script>
	<script language="javascript" src="../JavaScript/MenuWidget/MenuItem.js" ></script>
	<script language="javascript" src="../JavaScript/MenuWidget/SubMenuItem.js" ></script>
	<script language="javascript" src="../JavaScript/MenuWidget/DualStateMenuItem.js" ></script>
	<script language="javascript" src="../JavaScript/MenuWidget/CompositMenuItem.js" ></script>
	<script language="javascript" src="../JavaScript/MenuWidget/MenuWidget.js" ></script>
	<script type="text/javascript" src="../JavaScript/WebUIController/WebUILogger.js"></script>
	<script type="text/javascript" src="../JavaScript/WebUIController/WebUIController.js"></script>
	<script type="text/javascript" src="../JavaScript/Log4JavaScript/log4javascript.js"></script>

	<script language="javascript">
		var menuWidget;
		var webUIController;

		function init(){
			configureRootLogger();
			var menuDiv = document.getElementById("MenuWidget");
			menuWidget = new MenuWidget( menuDiv );
		}

		function setUpAuthentication() {
			webUIController = parent.webUIController;
			var userMenuDiv = document.getElementById("usersMenuWidget");
			var userMenuWidget = new MenuWidget( userMenuDiv );
			userMenuWidget.show();
			var authenticationWidget = new AuthenticationWidget("loginWidget", webUIController, userMenuWidget, "../../../../CommandControllerServlet?action=Login");
			menuWidget.show();
		}	
	</script>
</head>

<body onload="init();" oncontextmenu="return false;">
	<div class="center">
		<div id="loginWidget"></div>
		<div id="usersMenuWidget" class="MenuWidget"></div>
		<div class="spacer">&nbsp;</div>
		<div id="MenuWidget" class="MenuWidget"></div>
	</div>
	<div class="centerRight"></div>
	<div class="top"></div>
	<div class="bottom"></div>
	<div class="topCorner"></div>
	<div class="bottomCorner"></div>
</body>
</html>