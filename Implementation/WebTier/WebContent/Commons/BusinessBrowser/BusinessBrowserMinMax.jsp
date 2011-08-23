<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href=../../${skinDescriptor.stylesPath}/frameBrowserMinMax.css type="text/css">

	<script type="text/javascript">
		var frameState;
		function init() {

			frameState=0;
		}

		function manageFrame(anElement) {
			if (frameState==0) {
				parent.browserFrameWidth=22;
				parent.manageFrameset("mainFrameset",null,"22,21,*,"+parent.menuFrameWidth);
				parent.manageFrameset("browserFrameset",null,"10,12");
				anElement.childNodes[0].src="../../${skinDescriptor.imagesPath}/minmaxframes/jobbra.gif";
				frameState=1;
			} else
			{
				parent.browserFrameWidth=290;
				parent.manageFrameset("mainFrameset",null,"290,21,*,"+parent.menuFrameWidth);
				parent.manageFrameset("browserFrameset",null,"220,12");
				anElement.childNodes[0].src="../../${skinDescriptor.imagesPath}/minmaxframes/balra.gif";
				frameState=0;
			}
		}
	</script>

</HEAD>
<body onload="init();">
	<div class="FrameMinMaxButtonDiv" onclick="javascript:manageFrame(this);"><img src="../../${skinDescriptor.imagesPath}/minmaxframes/balra.gif" class="FrameMinMaxButtonImg"></img></div>
</body>
</html>