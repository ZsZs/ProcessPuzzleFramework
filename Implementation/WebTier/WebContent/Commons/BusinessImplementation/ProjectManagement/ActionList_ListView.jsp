<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<HTML>
<HEAD>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Action List</title>
	<%@ include file="../../PageIncludes/ArtifactListPageHeader.jsp" %>

	<script language="javascript">
		var COMMAND_POSTFIX = "Action";
		var ARTIFACT_TYPE = "ActionList";
	</script>

</HEAD>
<body onload="init();">

	<form name="form" id="actionList_ListViewForm" method="post" action="${artifactView.codeBase }">
		<input name="action" type="hidden" value="ShowListView">
		<input name="artifactName" type="hidden" value="${artifactView.parentArtifact.name}">
		<input name="viewName" type="hidden" value="${artifactView.name}">	
		<input name="query" type="hidden" value="">
		<input type="hidden" name="method">
		<input type="hidden" name="id">
		<input type="hidden" name="name">

		<center>
		<br>
		<div id="pageingWidget"></div>
		<br>
		<div class="readOnlyContainer">
			<table style="width: 800px;" class="sortable" id="actionListTable">
				<thead>
					<tr>
						<th></th>
						<c:set property="key" value="ui.label.fileNumber" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.subject" target="${i18Helper}"></c:set>
						<th style="width: 250px;">${i18Helper.value}</th>
						<c:set property="key" value="ui.label.status" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.priority" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.responsible" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.projectedBegin" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.projectedEnd" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.realBegin" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.realEnd" target="${i18Helper}"></c:set>
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
						<td><input type="radio" name="action" onclick="refreshPropertyInfo('${artifactProperty.id}', 'ActionDataSheet_PropertyView'); fillParameters('${artifactProperty.actionName}','${artifactProperty.id}'); enableButtons();"></td>
						<td><a href="#" onclick="goToDataSheetByLink('${artifactProperty.actionName}-${artifactProperty.protocolName }', '${artifactProperty.id}');">${artifactProperty.actionName}</a></td>
						<td>${artifactProperty.subject}</td>
						<td>${artifactProperty.status}</td>
						<td>${artifactProperty.priority}</td>
						<td>${artifactProperty.owner}</td>
						<td>${artifactProperty.projectedBegin}</td>
						<td>${artifactProperty.projectedEnd}</td>
						<td>${artifactProperty.realBegin}</td>
						<td>${artifactProperty.realEnd}</td>						
					</tr>
				</c:forEach>
				</tbody>			
			</table>		
		</div>
		
		</center>
	</form>	
</body>
</HTML>
