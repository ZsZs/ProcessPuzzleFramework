<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="../../PageIncludes/ArtifactListPageHeader.jsp" %>

	<title>Settlement list</title>

	<script language="javascript" src="./JavaScript/WebUiCommands/CreateNewSettlementCommand.js"></script>

	<script language="javascript">
		var COMMAND_POSTFIX = "Settlement";
		var ARTIFACT_TYPE = "SettlementList";
	</script>
	
</head>

<body onload="init();">

	<form name="settlementListForm" id="settlementListForm" method="post" action="${artifactView.codeBase}">
	
		<center>
		
		<br><br>
		
			<div id="pageingWidget"></div>
		
		<br><br>

			<div class="readOnlyContainer">
				<table class="sortable" style="width: 550px;" id="settlementTable">
					<thead>
						<tr>
							<th style="width: 25px;"></th>
							<c:set property="key" value="ui.settlementds.settlementlist.settlement" target="${i18Helper}"></c:set>
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
								<td><input type="radio" name="settlement" onclick="refreshPropertyInfo('${artifactProperty.id}', 'SettlementDataSheet_PropertyView'); setSelectedArtifact('${artifactProperty.settlementName}','${artifactProperty.id}'); enableButtons();"></td>				
								<td><a href="#" onclick="goToDataSheetByLink('${artifactProperty.settlementName}','${artifactProperty.id}');">${artifactProperty.settlementName}</a></td>
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