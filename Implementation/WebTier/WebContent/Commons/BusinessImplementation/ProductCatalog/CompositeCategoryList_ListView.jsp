<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="../../PageIncludes/ArtifactListPageHeader.jsp" %>
	<title>Composite Category List</title>

	<script language="javascript" src="../../JavaScript/WebUiCommands/CreateNewCompositeCategoryCommand.js"></script>
	
	<script language="javascript">
		var COMMAND_POSTFIX = "CompositeCategory";
		var ARTIFACT_TYPE = "CompositeCategoryList";
	</script>

</head>
<body onload="init();">

	<form name="form" id="compositeCategoryListForm" method="post" action="${artifactView.codeBase}">
	
	<center>
	
	<br><br>
	
		<div id="pageingWidget"></div>

	<br><br>

		<div class="readOnlyContainer">
			<table style="width: 700px;" class="sortable" id="compositeCategoryListTable">
				<thead>
					<tr>
						<th></th>
						<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.description" target="${i18Helper}"></c:set>
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
							<td><input type="radio" name="compositeCategory" onclick="refreshPropertyInfo('${artifactProperty.id}', 'CompositeCategoryDataSheet_PropertyView'); enableButtons();"></td>
							<td><a href="#" onclick="goToDataSheetByLink('${artifactProperty.name}', '${artifactProperty.id}');">${artifactProperty.name}</a></td>
							<td>${artifactProperty.description}</td>
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