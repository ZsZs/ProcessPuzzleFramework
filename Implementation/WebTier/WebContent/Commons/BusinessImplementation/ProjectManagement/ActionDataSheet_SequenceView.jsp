<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ActionDataSheet_SequenceView.jsp</title>
</head>
<body>
	<form name="form" id="adssqForm" method="POST" action="${artifactView.codeBase}">	
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name }">
		
		<center>
		<div class="readOnlyContainer">
		
			<div class="row">
				<span class="left">
					<table>
						<thead>
							<tr>
								<th width="30px;"></th>
								<th width="270px;">Megelőző tevékenységek</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="preAction" items="${artifactView.previousActions}" varStatus="index">
							<c:set var="classNameForRow" value="odd" />
							<c:if test="${index.count % 2 == 0}">
								<c:set var="classNameForRow" value="even" />
							</c:if>
							<tr class="${classNameForRow}">
								<td><input type="radio" name="plan" onclick="refreshPropertyInfo('${preAction.id}'); fillParameters('${preAction.actionName}','${preAction.id}'); enableButtons();"></td>
								<td><a href="#" onclick="goToDataSheetByLink('${preAction.actionName}', '${preAction.id}');">${preAction.actionName}</a></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</span>
				<span class="right">
					<table>
						<thead>
							<tr>
								<th width="30px;"></th>
								<th width="270px;">Következő tevékenységek</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="nextAction" items="${artifactView.nextActions}" varStatus="index">
							<c:set var="classNameForRow" value="odd" />
							<c:if test="${index.count % 2 == 0}">
								<c:set var="classNameForRow" value="even" />
							</c:if>
							<tr class="${classNameForRow}">
								<td><input type="radio" name="plan" onclick="refreshPropertyInfo('${nextAction.id}'); fillParameters('${nextAction.actionName}','${nextAction.id}'); enableButtons();"></td>
								<td><a href="#" onclick="goToDataSheetByLink('${nextAction.actionName}', '${nextAction.id}');">${nextAction.actionName}</a></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</span>
			</div>

		</div>
		</center>

		<center>
		<div class="readOnlyContainer">
			<div class="row">
				<span class="left">
					<input type="button" class="buttonSmall" id="addPreviousAction" value="">
					<input type="button" class="buttonSmall" id="delPreviousAction" value="">
					<select name="previousAction"><option>---</option></select>
				</span>
				<span class="right">
					<input type="button" class="buttonSmall" id="addNextAction" value="">
					<input type="button" class="buttonSmall" id="delNextAction" value="">
					<select name="previousAction"><option>---</option></select>
				</span>
			</div>
		</div>
		</center>
		
	</form>
</body>
</html>