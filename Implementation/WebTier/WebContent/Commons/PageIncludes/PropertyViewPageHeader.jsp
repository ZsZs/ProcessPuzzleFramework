<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
	var linkToCSS = document.createElement("link");
	linkToCSS.type = "text/css";
	linkToCSS.rel = "stylesheet";
	
	if( window.name == "InfoFrame" )	linkToCSS.href = "./${skinDescriptor.stylesPath}/frameInfo.css";
	else linkToCSS.href = "./${skinDescriptor.stylesPath}/content.css";

	document.getElementsByTagName('head')[0].appendChild( linkToCSS );
</script>
