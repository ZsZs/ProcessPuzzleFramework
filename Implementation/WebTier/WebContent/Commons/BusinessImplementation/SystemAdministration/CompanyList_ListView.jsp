<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="../../PageIncludes/ArtifactListPageHeader.jsp" %>

	<title>Settlement list</title>

	<script language="javascript" src="./JavaScript/WebUiCommands/CreateNewCompanyCommand.js"></script>

	<script language="javascript">
		var COMMAND_POSTFIX = "Company";
		var ARTIFACT_TYPE = "CompanyList";
	</script>
	
</head>

<body onload="init();">

	<form name="companyListForm" id="companyListForm" method="post" action="${artifactView.codeBase}">
	
		<center>
		
		<br><br>
		
			<div id="pageingWidget"></div>
		
		<br><br>

			<div class="readOnlyContainer">
				<table class="sortable" style="width: 700px;" id="companyTable">
					<thead>
						<tr>
							<th style="width: 25px;"></th>
							<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
							<th>${i18Helper.value}</th>
							<c:set property="key" value="ui.label.address" target="${i18Helper}"></c:set>
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
								<td><input type="radio" name="company" onclick="refreshPropertyInfo('${artifactProperty.id}', 'CompanyDataSheet_PropertyView'); setSelectedArtifact('${artifactProperty.companyName}','${artifactProperty.id}'); enableButtons();"></td>				
								<td><a href="#" onclick="goToDataSheetByLink('${artifactProperty.companyName}','${artifactProperty.id}');">${artifactProperty.companyName}</a></td>
								<td>${artifactProperty.geographicAddress}</td>
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