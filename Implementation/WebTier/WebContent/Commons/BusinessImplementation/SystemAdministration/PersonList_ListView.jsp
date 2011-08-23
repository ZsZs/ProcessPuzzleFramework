<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
	<%@ include file="../../PageIncludes/ArtifactListPageHeader.jsp" %>
	
	<TITLE>Person List</TITLE>

	<script language="javascript" src="./JavaScript/WebUiCommands/CreateNewPersonCommand.js"></script>
	
	<script language="javascript">
		var COMMAND_POSTFIX = "Person";
		var ARTIFACT_TYPE = "PersonList";
	</script>
	
</HEAD>
<body onload="init();">
	<form name="personListForm" method="post" action="${artifactView.codeBase}">
	<center><br><br>
		<div id="pageingWidget"></div><br><br>
		<div class="readOnlyContainer">
			<table id="personTable" class="sortable" style="width: 700px;">
				<thead>
					<tr>
						<th style="width: 25px;"></th>
						<c:set property="key" value="ui.label.name" target="${i18Helper}" /><th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.address" target="${i18Helper}" /><th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.phone" target="${i18Helper}" /><th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.email" target="${i18Helper}" /><th>${i18Helper.value}</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="artifactProperty" items="${artifactView.listedArtifactsPropertyViews}" varStatus="index">
						<c:set var="classNameForRow" value="odd"/>
						<c:if test="${index.count % 2 == 0}">
							<c:set var="classNameForRow" value="even"/>
						</c:if>
						<tr class="${classNameForRow}">
							<td><input type="radio" name="person" onclick="refreshPropertyInfo('${artifactProperty.id}', 'PersonDataSheet_PropertyView'); setSelectedArtifact('${artifactProperty.personName}','${artifactProperty.id}'); enableButtons();"></td>
							<td><a href="#" onclick="goToDataSheetByLink('${artifactProperty.personName}', '${artifactProperty.id}');">${artifactProperty.personName}</a></td>
							<td>${artifactProperty.geographicAddress}</td>
							<td>${artifactProperty.telecomAddress}</td>
							<td>${artifactProperty.emailAddress}</td>
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
</HTML>
