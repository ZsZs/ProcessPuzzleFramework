<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="../../${skinDescriptor.stylesPath}/frameMenuMinMax.css" type="text/css">

	<script type="text/javascript">
		var frameState;

		function init() {
			frameState=0;
		}

		function manageFrame(anElement) {
			if (frameState==0) {
				parent.menuFrameWidth=34;
				parent.manageFrameset("mainFrameset",null,parent.browserFrameWidth+",21,*,34");
				parent.manageFrameset("menuFrameset",null,"12,22");
				anElement.childNodes[0].src="../../${skinDescriptor.imagesPath}/minmaxframes/balra.gif";
				frameState=1;
			} else
			{
				parent.menuFrameWidth=212;
				parent.manageFrameset("mainFrameset",null,parent.browserFrameWidth+",21,*,212");
				parent.manageFrameset("menuFrameset",null,"12,200");
				anElement.childNodes[0].src="../../${skinDescriptor.imagesPath}/minmaxframes/jobbra.gif";
				frameState=0;
			}
		}
	</script>

</HEAD>
<body onload="init();">
	<div class="Top"></div>
	<div class="Bottom"></div>
	<div class="FrameMinMaxButtonDiv" onclick="javascript:manageFrame(this);"><img src="../../${skinDescriptor.imagesPath}/minmaxframes/jobbra.gif" class="FrameMinMaxButtonImg"></img></div>
</body>
</html>