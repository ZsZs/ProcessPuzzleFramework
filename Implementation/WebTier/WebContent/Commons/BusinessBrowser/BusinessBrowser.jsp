<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link type="text/css" rel="stylesheet" href="../../${skinDescriptor.stylesPath}/BusinessBrowserTree.css">
	<link type="text/css" rel="stylesheet" href="../../${skinDescriptor.stylesPath}/BusinessBrowser.css">
	<link type="text/css" rel="stylesheet" href="../../${skinDescriptor.stylesPath}/frameBrowser.css">

	<script type="text/javascript" src="../JavaScript/CommonScripts/AssertUtil.js"></script>
	<script type="text/javascript" src="../JavaScript/CommonScripts/Collection.js"></script>
	<script type="text/javascript" src="../JavaScript/CommonScripts/UserException.js"></script>
	<script type="text/javascript" src="../JavaScript/FundamentalTypes/XmlResource.js"></script>
	<script type="text/javascript" src="../JavaScript/CommonScripts/inheritFrom.js"></script>
	<script type="text/javascript" src="../JavaScript/CommonScripts/CList.js"></script>
	<script type="text/javascript" src="../JavaScript/TabWidget/Tab.js"></script>
	<script type="text/javascript" src="../JavaScript/TabWidget/TabWidget.js" ></script>
	<script type="text/javascript" src="../JavaScript/TabWidget/TabEvents.js"></script>
	<script type="text/javascript" src="../JavaScript/TreeWidget/TreeWidget.js" ></script>
	<script type="text/javascript" src="../JavaScript/TreeWidget/TreeNode.js" ></script>
	<script language="javascript" src="../JavaScript/WebUIController/WebUILogger.js"></script>
	<script language="javascript" src="../JavaScript/WebUIController/WebUIController.js" ></script>
	<script type="text/javascript" src="../JavaScript/Log4JavaScript/log4javascript.js"></script>

	<script type="text/javascript">
		function Processes() {
			this.activate = function() {
				leftTreeWidget.setResourceBundle(parent.webUIController);
				leftTreeWidget.setDefinitionFileName("Processes.xml");
				leftTreeWidget.loadWidget();
				leftTreeWidget.showWidget();
			}
			this.deActivate = function() {
				leftTreeWidget.removeWidgetTree();
			}
		}
		function Roles() {
			this.activate = function() {
				leftTreeWidget.setResourceBundle(parent.webUIController);
				leftTreeWidget.setDefinitionFileName("Roles.xml");
				leftTreeWidget.loadWidget();
				leftTreeWidget.showWidget();
			}
			this.deActivate = function() {
				leftTreeWidget.removeWidgetTree();
			}
		}
		function Artifacts() {
			this.activate = function() {
				leftTreeWidget.setResourceBundle(parent.webUIController);
				leftTreeWidget.setDefinitionFileName("Artifacts.xml");
				leftTreeWidget.loadWidget();
				leftTreeWidget.showWidget();
			}
			this.deActivate = function() {
				leftTreeWidget.removeWidgetTree();
			}
		}
		function Documents() {
			this.activate = function() {
				leftTreeWidget.setResourceBundle(parent.webUIController);
				leftTreeWidget.setDefinitionFileName("Documents.xml");
				leftTreeWidget.loadWidget();
				leftTreeWidget.showWidget();
			}
			this.deActivate = function() {
				leftTreeWidget.removeWidgetTree();
			}
		}
	</script>

	<script type="text/javascript">
		var leftTreeWidget;
		var leftTreesTabWidget;
		var webUIController;

		function init() {
			var treeDiv = document.getElementById("TreeView");
			leftTreeWidget = new TreeWidget(treeDiv);
		}

		function setUpTreeTabWidget() {
			webUIController = parent.webUIController;

			var tabDiv = document.getElementById("TabWidget");
			leftTreesTabWidget = new TabWidget(tabDiv);
			leftTreesTabWidget.show();
			leftTreesTabWidget.addNewTab("Processes", webUIController.getText("Processes"), new Processes(), webUIController.getApplicationConfiguration().getProperty("isProcesssesTabActive"));
			leftTreesTabWidget.addNewTab("Artifacts", webUIController.getText("Artifacts"), new Artifacts(), webUIController.getApplicationConfiguration().getProperty("isArtifactsTabActive"));
			//leftTreesTabWidget.addNewTab("Documents", webUIController.getText("Documents"), new Documents(), webUIController.getApplicationConfiguration().getProperty("isDocumentsTabActive"));
		}
	</script>

</HEAD>
<body onload="javascript:init();" oncontextmenu="return false;">
	<div id="TabWidget"></div>
	<div id="TreeView"></div>
	<!-- to resolve IE6 cache bug -->
	<div style="display:none;">
		<img src="../../${skinDescriptor.imagesPath}/BusinessBrowser/folder_closed.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/BusinessBrowser/folder_open.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/BusinessBrowser/help_16x16.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/BusinessBrowser/page16x16.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/BusinessBrowser/user_16x16.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/BusinessBrowser/white.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/BusinessBrowser/line.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/BusinessBrowser/minus.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/plus.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/minus_last.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/plus_last.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/lastnode.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/t.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/minus_nolines.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/minus_no_root.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/minus_last_no_root.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/plus_nolines.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/plus_no_root.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/plus_last_no_root.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/t_no_root.gif"/>

		<img src="../../${skinDescriptor.imagesPath}/activities.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/activity.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/ar_doc.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/ar_list.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/ar_uc.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/ar_ucpkg.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/artifact.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/artifacts.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/book.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/buc.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/discipline.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/lifecycle.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/milestone.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/procmod.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/procomp.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/role.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/usecase.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/wfdtree.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/workflow.gif"/>
		<img src="../../${skinDescriptor.imagesPath}/workflowdetail.gif"/>
	</div>
</body>
</html>