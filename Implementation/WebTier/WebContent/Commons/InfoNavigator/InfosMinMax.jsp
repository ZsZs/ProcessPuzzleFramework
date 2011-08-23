<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK rel="stylesheet" href="../../${skinDescriptor.stylesPath}/frameInfoMinMax.css" type="text/css">

	<script type="text/javascript">
		var frameState;

		function init() {
			frameState=0;
		}

		function manageFrame(anElement) {
			if (frameState==0) {
				parent.manageFrameset("middleFrameset","21,25,*,25,12,0,0,21",null);
				anElement.childNodes[0].src="../../${skinDescriptor.imagesPath}/minmaxframes/felfele.gif";
				frameState=1;
			} else
			{
				parent.manageFrameset("middleFrameset","21,25,*,25,12,25,25%,21",null);
				anElement.childNodes[0].src="../../${skinDescriptor.imagesPath}/minmaxframes/lefele.gif";
				frameState=0;
			}
		}
	</script>

</HEAD>
<body onload="init();">
	<div class="FrameMinMaxButtonDiv" onclick="javascript:manageFrame(this);"><img src="../../${skinDescriptor.imagesPath}/minmaxframes/lefele.gif" class="FrameMinMaxButtonImg"></img></div>
</body>
</html>