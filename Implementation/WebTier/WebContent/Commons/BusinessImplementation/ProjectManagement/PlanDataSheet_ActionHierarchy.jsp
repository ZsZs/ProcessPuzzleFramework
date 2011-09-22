<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">
	<link rel="stylesheet" href="./JavaScript/TreeWidget/TreeWidget.css" type="text/css">

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Workflow activities</title>

	<script language="JavaScript" src="./JavaScript/CommonScripts/AssertUtil.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/Collection.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/UserException.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/XmlResource.js"></script>
	<script language="JavaScript" src="./JavaScript/TreeWidget/TreeNode.js"></script>
	<script language="JavaScript" src="./JavaScript/TreeWidget/TreeWidget.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/inheritFrom.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/HashMap.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/ArrayList.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/StringBuffer.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/StringUtil.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/Configuration.js"></script>
	<script language="JavaScript" src="./JavaScript/CommonScripts/GenericBrowser.js"></script>
	<script language="JavaScript" src="./JavaScript/Internalization/Locale.js"></script>
	<script language="JavaScript" src="./JavaScript/Internalization/LocaleUtil.js"></script>
	<script language="JavaScript" src="./JavaScript/Internalization/ResourceCache.js"></script>
	<script language="JavaScript" src="./JavaScript/Internalization/ResourceKey.js"></script>
	<script language="JavaScript" src="./JavaScript/Internalization/XMLBundleParser.js"></script>
	<script language="JavaScript" src="./JavaScript/Internalization/XMLResourceBundle.js"></script>
	<script language="JavaScript" src="./JavaScript/XMLforScript/xmlsax.js"></script>
	<script language="JavaScript" src="./JavaScript/XMLforScript/preMadeSaxEventHandler.js"></script>
	<script language="JavaScript" src="./JavaScript/BrowserWidget/BrowserWidget.js"></script>
	<script language="JavaScript" src="./JavaScript/WebUIController/WebUIController.js"></script>
	<script language="javascript" src="./JavaScript/WebUiCommands/WebUiCommand.js"></script>
	<script language="javaScript" src="./JavaScript/PlanManagerWidget/PlanManagerWidget.js" ></script>

	<script type="text/javascript">
		var widgetName = "PlanManagerWidget";
		var webUiController = null;
		var planManagerWidget = null;
		var divElementForPlanManager = null;
		var treeDiv = null;
		var treeWidget = null;
		
		function init( planId ) {
			webUiController = parent.browserFrame.webUIController;
			planManagerWidget = new PlanManagerWidget( webUiController, 1, "./CommandControllerServlet?action=RetrieveArtifactData&artifactId=" + planId, "PlanManagerWidget" );
			planManagerWidget.setPictureFolder("../../JavaScript/PlanManagerWidget/images");
			planManagerWidget.show();
		}
		
//		function goToDataSheetByLink(protocolName){
//			top.browserFrame.webUIController.loadDocument('ActionDataSheet','ActionDataSheet','&amp;query=from ActionDataSheet a where a.action.protocol.name=\''+protocolName+'\'');
//		}
	
	</script>

</head>
<body onload="javascript:init(${artifactView.subjectPlanDataSheetId});" >
	<div id="PlanManagerWidget"></div>
</body>
</html>