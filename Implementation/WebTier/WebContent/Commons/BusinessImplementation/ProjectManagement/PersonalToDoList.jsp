<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
	<%@ include file="../../PageIncludes/ArtifactListPageHeader.jsp" %>
	<%@ include file="../../PageIncludes/InfoPageHeader.jsp" %>
	<title>Personal Todo List</title>
</head>
<body>
	<form name="peronalTaskList">
	<center>
	<h4>${artifactView.signedInUserName} tasks</h4>
	<div class="readOnlyContainer">
	<table width="500" border="1">
		<thead>
			<tr>
				<th></th>
				<th>Name</th>
				<th>Subject</th>
				<th>Priority</th>
				<th>Status</th>
				<th>Start</th>
				<th>Finish</th>
			</tr>
		</thead>
		<tbody>
				<c:forEach var="artifactProperty" items="${artifactView.listedArtifactsPropertyViews}" varStatus="index">
					<c:set var="classNameForRow" value="odd"/>
						<c:if test="${index.count % 2 == 0}">
							<c:set var="classNameForRow" value="even"/>
						</c:if>
						<tr class="${classNameForRow}">
						<td><input type="radio" name="action" onclick="refreshPropertyInfo('${artifactProperty.id}', 'ActionDataSheet_PropertyView'); setSelectedArtifact('${artifactProperty.actionName}','${artifactProperty.id}'); enableButtons();"></td>
						<td><a href="#" onclick="goToDataSheetByLink('${artifactProperty.actionName}-${artifactProperty.protocolName }', '${artifactProperty.id}');">${artifactProperty.actionName}</a></td>
						<td>${artifactProperty.subject}</td>
						<td>${artifactProperty.status}</td>
						<td>${artifactProperty.priority}</td>
						<td>${artifactProperty.projectedBegin}</td>
						<td>${artifactProperty.projectedEnd}</td>
					</tr>
				</c:forEach>
		</tbody>
	</table>
	</div>
	</center>
	</form>
</body>
</html>
