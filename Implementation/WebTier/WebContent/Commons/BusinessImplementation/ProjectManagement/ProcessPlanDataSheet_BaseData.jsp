<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<script language="javascript" src="./JavaScript/WebUIController/LoadUtility.js" ></script>
	<script language="javascript" src="./JavaScript/BasicValidation/BasicValidation.js" ></script>
	<script language="javascript" src="./JavaScript/DataRetriever/DataRetriever.js" ></script>
	<script language="javascript" src="./JavaScript/DataRetriever/InvalidXmlResponseException.js"></script>

	<link rel="stylesheet" href="./JavaScript/Calendar/simplecalendar.css" type="text/css">
	<script type="text/javascript" src="./JavaScript/Calendar/simplecalendar.js"></script>
	<script language="javascript" src="./JavaScript/WebUIController/WebUIController.js"></script>
	<script type="text/javascript">
		imgUp.src="./Images/scalendar/up.gif";
		imgDown.src="./Images/scalendar/down.gif";
	</script>
	
	<script type="text/javascript">
			function init() {
				calendar_init();
			}
	</script>

<title>Folyamattervadatlap alapadatok</title>
</head>
<body onload="init();">
	<form name="form" id="pdsbdForm" method="post" action="${artifactView.codeBase }">	
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name }">

		<div class="editableContainer">
		
			<div class="row">
				<c:set property="key" value="ui.label.subject" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}: </span>
				<span class="row"><input type="text" id="subjectEdit" name="subject" value=""></span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.description" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><textarea id="description" name="description" cols="50" rows="3">${artifactView.description }</textarea></span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.owner" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">
					<select id="owner" name="owner">
						<option value="-1">---</option>
							<c:forEach var="owner" items="${artifactView.owners}" varStatus="index">
								<option value="${owner.id}"
								<c:if test="${artifactView.owner.id == owner.id}">
									selected
								</c:if>
								>${owner.partyName.name}</option>
							</c:forEach>
					</select>
				</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.priority" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">
					<select id="priority" name="priority">
						<option value="-1">---</option>
					<c:forEach var="priority" items="${artifactView.priorities}" varStatus="index">
							<option value="${priority}"
							<c:if test="${priority == artifactView.priority}">
								selected
							</c:if>
							<c:set property="key" value="ui.plands.basedata.priorityType.${priority }" target="${i18Helper}"></c:set>							
							>${i18Helper.value}</option>
						</c:forEach>
				</select>
				</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.projectedBegin" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" name="projectedBegin" id="projectedBeginEdit" readonly="readonly" value="${artifactView.projectedBegin }">
				<a href="javascript: void(0);" id="deleteAnchor" onmouseover="if (timeoutId) clearTimeout(timeoutId);window.status='Show Calendar';return true;" onmouseout="if (timeoutDelay) calendarTimeout();window.status='';" onclick="g_Calendar.show(event,'form.projectedBegin',true,'yyyy.mm.dd'); return false;">
				<img src="./Images/scalendar/calendar.gif" id="vToImg" name="imgCalendar" width="34" height="21" border="0" alt=""></a>
				<c:set property="key" value="ui.generic.button.delete" target="${i18Helper}"></c:set>				
				<input type="button" class="buttonSmall" onClick="clearTextFieldById('projectedBeginEdit');" value="${i18Helper.value}">
				</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.projectedEnd" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" name="projectedEnd" id="projectedEndEdit" readonly="readonly"  value="${artifactView.projectedEnd }">
				<a href="javascript: void(0);" id="deleteAnchor" onmouseover="if (timeoutId) clearTimeout(timeoutId);window.status='Show Calendar';return true;" onmouseout="if (timeoutDelay) calendarTimeout();window.status='';" onclick="g_Calendar.show(event,'form.projectedEnd',true,'yyyy.mm.dd'); return false;">
				<img src="./Images/scalendar/calendar.gif" id="vToImg" name="imgCalendar" width="34" height="21" border="0" alt=""></a>
				<c:set property="key" value="ui.generic.button.delete" target="${i18Helper}"></c:set>				
				<input type="button" class="buttonSmall" onClick="clearTextFieldById('projectedendEdit');" value="${i18Helper.value}">
				</span>
			</div>
		</div>
		
	<br>
	
		<div class="readOnlyContainer">
			<c:set property="key" value="ui.generic.button.save" target="${i18Helper}"></c:set>
			<input type="submit" class="buttonSmall" style="margin-left: 150px;" value="${i18Helper.value}">
			<c:set property="key" value="ui.generic.button.cancel" target="${i18Helper}"></c:set>
			<input type="button" class="buttonSmall" value="${i18Helper.value}">
		</div>
	</form>
</body>
</html>