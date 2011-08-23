<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<style>
	body{
		width:100%;
		height:100%;
		margin:0px;
		padding:0px;
		background-color: #D49063;
    		background-image: url("../../${skinDescriptor.imagesPath}/DocuNavigator/inverted_bg.jpg");
    		background-repeat: repeat-x;
		padding-top:6px;
	}
	</style>

	<script type="text/javascript">
		var tabWidget=null;

		function setUp(){
			// the tabWidget must set up in index.htm!
		}

		function button_right() {
			if (tabWidget!=null) tabWidget.moveFirstToLast();
		}

		function button_left() {
			if (tabWidget!=null) tabWidget.moveLastToFirst();
		}
	</script>
</head>

<body onLoad="setUp()">
<img src="../../${skinDescriptor.imagesPath}/tabscroll/left.bmp" style="cursor:pointer;" onclick="javascript:button_left();"> <img src="../../${skinDescriptor.imagesPath}/tabscroll/right.bmp" style="cursor:pointer;" onclick="javascript:button_right();">
</body>
</html>