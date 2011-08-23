<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<%@ include file="../../PageIncludes/ArtifactListPageHeader.jsp" %>
	<title>Product Catalog List</title>
	
	<script language="javascript" src="./JavaScript/WebUiCommands/CreateNewProductCatalogCommand.js"></script>
	
	<script language="javascript">
		var COMMAND_POSTFIX = "ProductCatalog";
		var ARTIFACT_TYPE = "ProductCatalogList";
	</script>
	
</head>
<body onload="init();">
	<form name="form" id="productCatalogListForm" method="post" action="${artifactView.codeBase}">
	<center><br><br>
	<div id="pageingWidget"></div>
	<br><br>
		<div class="readOnlyContainer">
			<table style="width: 700px;" class="sortable" id="productCatalogListTable">
				<thead>
					<tr>
						<th style="width: 25px;"></th>
						<c:set property="key" value="ui.label.catalogName" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.isActive" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="artifactProperty" items="${artifactView.listedArtifactsPropertyViews}" varStatus="index">
						<c:set var="classNameForRow" value="odd"/>
						<c:if test="${index.count % 2 == 0}">
							<c:set var="classNameForRow" value="even"/>
						</c:if>
						<tr class="${classNameForRow}">
							<td><input type="radio" name="productCatalog" onclick="refreshPropertyInfo('${artifactProperty.id}', 'ProductCatalogDataSheet_PropertyView'); setSelectedArtifact('${artifactProperty.catalogName}','${artifactProperty.id}'); enableButtons();"></td>
							<td><a href="#" onclick="goToDataSheetByLink('${artifactProperty.catalogName}', '${artifactProperty.id}');">${artifactProperty.catalogName}</a></td>
							<td>${artifactProperty.catalogStatus}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	<br><br>
	
		<%@ include file="../../PageIncludes/ArtifactListPageFooter.jsp" %>
	
	</center>	
	
	</form>

</body>
</html>