<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
	<%@ include file="../../../PageIncludes/FrameSetPageHeader.jsp" %>
	<title>ProcessPuzzle</title>
</head>

<frameset name="mainFrameset" id="mainFrameset" rows="*" cols="300,21,*,212" onload="init();" frameborder="0" border="0" framespacing="0">
	<frameset name="browserFrameset" id="browserFrameset" cols="220,12" frameborder="0" border="0" framespacing="0">
		<frame name="browserFrame" src="../../../BusinessBrowser/BusinessBrowser.jsp" frameborder="0" marginheight="0" marginwidth="0" scrolling="on" noresize="noresize">
		<frame name="browserMinMaxFrame" src="../../../BusinessBrowser/BusinessBrowserMinMax.jsp" frameborder="0" marginheight="0" marginwidth="0" scrolling="no" noresize="noresize">
	</frameset>
	<frame name="leftBorderFrame" src="LeftBorder.htm"  frameborder="0" marginheight="0" marginwidth="0" scrolling="no" noresize="noresize">
	<frameset name="middleFrameset" id="middleFrameset" rows="21,25,*,25,12,25,25%,21" frameborder="0" border="0" framespacing="0">
		<frame name="topBorderFrame" src="TopBorder.htm"  frameborder="0" marginheight="0" marginwidth="0" scrolling="no" noresize="noresize">
		<frameset name="docuNavigatorFrameset" id="docuNavigatorFrameset" cols="20,*" frameborder="0" border="0" framespacing="0">
			<frame name="DocuNavigatorScrollFrame" src="../../../DocuNavigator/DocuNavigatorScroll.jsp" frameborder="0" marginheight="0" marginwidth="0" scrolling="no" noresize="noresize">
			<frame name="DocuNavigatorFrame" src="../../../DocuNavigator/DocuNavigator.jsp" frameborder="0" marginheight="0" marginwidth="0" scrolling="no" noresize="noresize">
		</frameset>
		<frame name="ContentFrame" src="Content.jsp" frameborder="0" marginheight="0" marginwidth="0" scrolling="on" noresize="noresize">
		<frameset name="docuViewSelectorFrameset" id="docuViewSelectorFrameset" cols="*,20" frameborder="0" border="0" framespacing="0">
			<frame name="DocuViewSelectorFrame" src="../../../DocuNavigator/DocuViewSelector.jsp" frameborder="0" marginheight="0" marginwidth="0" scrolling="no" noresize="noresize">
			<frame name="DocuViewSelectorScrollFrame" src="../../../DocuNavigator/DocuViewSelectorScroll.jsp" frameborder="0" marginheight="0" marginwidth="0" scrolling="no" noresize="noresize">
		</frameset>
		<frame name="InfoMinMaxFrame" src="../../../InfoNavigator/InfosMinMax.jsp" frameborder="0" marginheight="0" marginwidth="0" scrolling="no" noresize="noresize">			
		<frame name="InfoNavigatorFrame" src="../../../InfoNavigator/InfoNavigator.jsp" frameborder="0" marginheight="0" marginwidth="0" scrolling="no" noresize="noresize">
		<frame name="InfoFrame" src="../../../InfoNavigator/Info.jsp" frameborder="0" marginheight="0" marginwidth="0" scrolling="on" noresize="noresize">
		<frame name="BottomBorder" src="BottomBorder.htm" frameborder="0" marginheight="0" marginwidth="0" scrolling="no" noresize="noresize">
	</frameset>
	<frameset name="menuFrameset" id="menuFrameset" cols="12,200" frameborder="0" border="0" framespacing="0">
		<frame name="menuMinMaxFrame" src="../../../MenuNavigator/MenuNavigatorsMinMax.jsp" frameborder="0" marginheight="0" marginwidth="0" scrolling="no" noresize="noresize">			
		<frame name="menuFrame" src="../../../MenuNavigator/MenuNavigator.jsp" frameborder="0" marginheight="0" marginwidth="0" scrolling="no" noresize="noresize">			
	</frameset>

	<noframes>
		<body>
			<p>Sorry, ProcessPuzzle requires support for frames.</p>
		</body>
	</noframes>	
</frameset>

</html>