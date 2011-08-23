<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="../../PageIncludes/ArtifactListPageHeader.jsp" %>
	<title>Catalog Entry List</title>

</head>
<body onload="init();">

	<form name="form" id="catalogEntryListForm" method="post" action="${artifactView.codeBase}">
	
	<center>
	
	<br><br>
	
		<div id="pageingWidget"></div>

	<br><br>

		<div class="readOnlyContainer">
			<table style="width: 700px;" class="sortable" id="catalogEntryListTable">
				<thead>
					<tr>
						<th></th>
						<c:set property="key" value="ui.label.productTypeIdentifier" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.manufacturerCode" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.EAN" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.price" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<!-- 
						<c:set property="key" value="ui.label.stock" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th> -->
						<c:set property="key" value="ui.label.properties" target="${i18Helper}"></c:set>
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
							<td><a href="#" onclick="goToDataSheetByLink('${artifactProperty.productTypeIdentifier}', '${artifactProperty.id}');">${artifactProperty.productTypeIdentifier}</a></td>
							<td>${artifactProperty.manufacturerCode}</td>
							<td>${artifactProperty.EAN}</td>
							<td>${artifactProperty.price}</td>
							<!-- <td>${artifactProperty.stock}</td> -->
							<td>${artifactView.properties}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</center>	
	</form>

</body>
</html>