<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<HTML>
<HEAD>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="../../PageIncludes/ArtifactListPageHeader.jsp" %>
	<TITLE>List of action in plan</TITLE>
</HEAD>

<BODY onload="init();">
	<form name="PlanDataSheet_ActionListForm" action="${artifactView.codeBase }">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name }">
		<input type="hidden" name="id">
		<input type="hidden" name="name">
	
		<CENTER>
		<div id="pageingWidget"></div>
		
		<div class="readOnlyContainer">
			<table style="width: 900px;" class="sortable" id="actionListTable">
				<thead>
					<tr>
						<th></th>
						<c:set property="key" value="ui.label.fullName" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
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
					<c:forEach var="activityDataSheet" items="${artifactView.listedArtifactsPropertyViews}" varStatus="index">
						<c:set var="classNameForRow" value="odd"/>
							<c:if test="${index.count % 2 == 0}">
								<c:set var="classNameForRow" value="even"/>
							</c:if>
						<tr class="${classNameForRow}">
							<td><input type="radio" name="action" onclick="refreshPropertyInfo('${activityDataSheet.id}'); fillParameters('${activityDataSheet.protocolName}','${activityDataSheet.id}'); enableButtons();"></td>
							<td><a href="#" onclick="goToDataSheetByLink('${activityDataSheet.protocolName}', '${activityDataSheet.id}');">${activityDataSheet.protocolName}</a></td>
							<td>${activityDataSheet.status}</td>
							<td>${activityDataSheet.priority}</td>
							<td>${activityDataSheet.owner}</td>
							<td>${activityDataSheet.projectedBegin}</td>
							<td>${activityDataSheet.projectedEnd}</td>
							<td>${activityDataSheet.realBegin}</td>
							<td>${activityDataSheet.realEnd}</td>						
						</tr>
					</c:forEach>
				</tbody>			
			</table>
		</div>
		
		<%@ include file="../../PageIncludes/ArtifactListPageFooter.jsp" %>
		</CENTER>
	</form>
</BODY>
</HTML>
