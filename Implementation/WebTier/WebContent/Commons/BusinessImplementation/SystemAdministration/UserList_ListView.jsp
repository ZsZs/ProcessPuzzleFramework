<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="../../PageIncludes/ArtifactListPageHeader.jsp" %>
	
	<title>User List</title>

	<script language="javascript" src="../../JavaScript/WebUiCommands/CreateNewUserCommand.js"></script>
		
	<script language="javascript">
		var COMMAND_POSTFIX = "User";
		var ARTIFACT_TYPE = "UserList";
	</script>

</head>

<body onload="init();">

	<form name="form" id="userListForm" method="post" action="${artifactView.codeBase}">
		<center>
		<br><br>
		<div id="pageingWidget"></div>
		<br><br>
		<div class="readOnlyContainer">
			<table style="width: 700px;" class="sortable" id="userListTable">
				<thead>
					<tr>
						<th style="width: 25px;"></th>
						<c:set property="key" value="ui.label.userName" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.pds.basedata.prefferedLocale" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.pds.geodata.country" target="${i18Helper}"></c:set>
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
							<td><input type="radio" name="user" onclick="refreshPropertyInfo('${artifactProperty.id}', 'UserDataSheet_PropertyView'); setSelectedArtifact('${artifactProperty.userName}','${artifactProperty.id}'); enableButtons();"></td>
							<td><a href="#" onclick="goToDataSheetByLink('${artifactProperty.userName}', '${artifactProperty.id}');">${artifactProperty.userName}</a></td>
							<td>${artifactProperty.preferredLocale}</td>
							<td>${artifactProperty.country}</td>
						</tr>
						
					</c:forEach>
				</tbody>
			</table>
		</div>
		<br><br>
		<%@ include file="../../PageIncludes/ArtifactListPageFooter.jsp" %>
		<br><br>
		</center>	
	</form>
</body>
</html>