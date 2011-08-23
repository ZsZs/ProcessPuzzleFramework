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

		var webUiController = getWebUIController();
		var dataRetreiver = null;
		
		function init() {
			calendar_init();
		}

		function getOwners() {
			var selectedValue = document.getElementsByName("ownerType")[0].options[document.getElementsByName("ownerType")[0].selectedIndex].value;
			dataRetriever = new DataRetriever(null, "artifactId",  ${artifactView.parentArtifact.id}, "ActionDataSheet_BaseData", null, webUiController.getResourceBundle());
			dataRetriever.setMethod("getPartiesByPartyTypeName");
			dataRetriever.setParameters(selectedValue);
			dataRetriever.makeHttpRequest("processDatas('owner', 'partyName');");
		}
		
		function getPerformers() {
			var selectedValue = document.getElementsByName("performerType")[0].options[document.getElementsByName("performerType")[0].selectedIndex].value;
			dataRetriever = new DataRetriever(null, "artifactId",  ${artifactView.parentArtifact.id}, "ActionDataSheet_BaseData", null, webUiController.getResourceBundle());
			dataRetriever.setMethod("getPartiesByPartyTypeName");
			dataRetriever.setParameters(selectedValue);
			dataRetriever.makeHttpRequest("processDatas('performer', 'partyName');");
		}
		
		function implementAction() {
			document.getElementsByName("method")[0].value = "implement";
		}
		
		function completeAction() {
			document.getElementsByName("method")[0].value = "complete";
		}

</script>

<title>ActionDataSheet_BaseData.jsp</title>
</head>
<body onload="init();">
	<form id="acdbdForm" name="acdbdForm" method="post" action="${artifactView.codeBase }">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name }">
		<input type="hidden" name="method" value="">

		<div class="readOnlyContainer">
		
			<div class="row">
				<c:set property="key" value="ui.label.subject" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" name="subject" value="${artifactView.subject}"></span>
			</div>
			
			<div class="row">
				<c:set property="key" value="ui.label.description" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><textarea name="description" rows="3" cols="50">${artifactView.description}</textarea></span>
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
							<c:set property="key" value="ui.plands.basedata.priorityType.${priority}" target="${i18Helper}"></c:set>							
							>${i18Helper.value}</option>
						</c:forEach>
				</select>
				</span>
			</div>
			
	<br><br>
			
			<div class="row">
				<c:set property="key" value="ui.label.projectedBegin" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" name="projectedBegin" id="projectedBeginEdit" readonly="readonly" value="${artifactView.projectedBegin }" >
				<a href="javascript: void(0);" id="deleteAnchor" onmouseover="if (timeoutId) clearTimeout(timeoutId);window.status='Show Calendar';return true;" onmouseout="if (timeoutDelay) calendarTimeout();window.status='';" onclick="g_Calendar.show(event,'acdbdForm.projectedBegin',true,'yyyy.mm.dd'); return false;">
				<img src="./Images/scalendar/calendar.gif" id="vToImg" name="imgCalendar" width="34" height="21" border="0" alt=""></a>
				<c:set property="key" value="ui.generic.button.delete" target="${i18Helper}"></c:set>				
				<input type="button" id="clearDateFieldButton" class="buttonSmall" onClick="clearTextFieldById('projectedBeginEdit');" value="${i18Helper.value}">
				</span>
			</div>
			
			<div class="row">
				<c:set property="key" value="ui.label.projectedEnd" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" name="projectedEnd" id="projectedEndEdit" readonly="readonly" value="${artifactView.projectedEnd }">
				<a href="javascript: void(0);" id="deleteAnchor" onmouseover="if (timeoutId) clearTimeout(timeoutId);window.status='Show Calendar';return true;" onmouseout="if (timeoutDelay) calendarTimeout();window.status='';" onclick="g_Calendar.show(event,'acdbdForm.projectedEnd',true,'yyyy.mm.dd'); return false;">
				<img src="./Images/scalendar/calendar.gif" id="vToImg" name="imgCalendar" width="34" height="21" border="0" alt=""></a>
				<c:set property="key" value="ui.generic.button.delete" target="${i18Helper}"></c:set>				
				<input type="button" id="clearDateFieldButton" class="buttonSmall" onClick="clearTextFieldById('projectedEndEdit');" value="${i18Helper.value}">
				</span>
			</div>
			
			<div class="row">
				<c:set property="key" value="ui.label.realBegin" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" name="realBegin" id="realBeginEdit" readonly="readonly" value="${artifactView.realBegin }" >
				<a href="javascript: void(0);" id="deleteAnchor" onmouseover="if (timeoutId) clearTimeout(timeoutId);window.status='Show Calendar';return true;" onmouseout="if (timeoutDelay) calendarTimeout();window.status='';" onclick="g_Calendar.show(event,'acdbdForm.realBegin',true,'yyyy.mm.dd'); return false;">
				<img src="./Images/scalendar/calendar.gif" id="vToImg" name="imgCalendar" width="34" height="21" border="0" alt=""></a>
				<c:set property="key" value="ui.generic.button.delete" target="${i18Helper}"></c:set>				
				<input type="button" id="clearDateFieldButton" class="buttonSmall" onClick="clearTextFieldById('realBeginEdit');" value="${i18Helper.value}">
				</span>
			</div>
			
			<div class="row">
				<c:set property="key" value="ui.label.realEnd" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" name="realEnd" id="realEndEdit" readonly="readonly" value="${artifactView.realEnd }">
				<a href="javascript: void(0);" id="deleteAnchor" onmouseover="if (timeoutId) clearTimeout(timeoutId);window.status='Show Calendar';return true;" onmouseout="if (timeoutDelay) calendarTimeout();window.status='';" onclick="g_Calendar.show(event,'acdbdForm.realEnd',true,'yyyy.mm.dd'); return false;">
				<img src="./Images/scalendar/calendar.gif" id="vToImg" name="imgCalendar" width="34" height="21" border="0" alt=""></a>
				<c:set property="key" value="ui.generic.button.delete" target="${i18Helper}"></c:set>				
				<input type="button" id="clearDateFieldButton" class="buttonSmall" onClick="clearTextFieldById('realEndEdit');" value="${i18Helper.value}">
				</span>
			</div>
		<br><br>
		</div>
		
	<br>
		
		<div class="readOnlyContainer" id="statusDiv" align="left" style="display: block;">
			<fieldset style="width: 400px; border-color: white;">
				<c:set property="key" value="ui.label.status" target="${i18Helper}"></c:set>
				<legend style="color: brown;">${i18Helper.value}</legend>

			<div class="row">
				<c:set property="key" value="ui.label.status" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.status}</span>
			</div>

			<div class="row">
				<c:set property="key" value="ui.generic.button.start" target="${i18Helper}"></c:set>
				<input type="submit" class="buttonSmall" style="margin-left: 150px;" value="${i18Helper.value}" onclick="implementAction();">
				<c:set property="key" value="ui.generic.button.finish" target="${i18Helper}"></c:set>
				<input type="submit" class="buttonSmall" value="${i18Helper.value}" disabled="disabled" onclick="completeAction();">
			</div>
			<div class="row">
				<c:set property="key" value="ui.generic.button.suspend" target="${i18Helper}"></c:set>
				<input type="button" class="buttonSmall" style="margin-left: 70px;" value="${i18Helper.value}">
				<c:set property="key" value="ui.generic.button.resume" target="${i18Helper}"></c:set>
				<input type="button" class="buttonSmall" value="${i18Helper.value}">
				<c:set property="key" value="ui.generic.button.discard" target="${i18Helper}"></c:set>
				<input type="button" class="buttonSmall" value="${i18Helper.value}">
			</div>
			<br>
			</fieldset>
		</div>
		
		<div class="readOnlyContainer" align="left" id="ownerDiv">
			<fieldset style="width: 400px; border-color: white;">
				<c:set property="key" value="ui.label.owner" target="${i18Helper}"></c:set>
				<legend style="color: brown;">${i18Helper.value}</legend>

				<div class="row">
					<c:set property="key" value="ui.label.type" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">
						<select id="ownerTypeSelect" name="ownerType" onchange="getOwners();">
							<option value="-1">---</option>
							<c:forEach var="partyType" items="${artifactView.partyTypes}" varStatus="index">
							<c:set property="key" value="com.processpuzzle.party.domain.${partyType.name}" target="${i18Helper}"></c:set>
								<option value="${partyType.id}">${i18Helper.value}</option>
							</c:forEach>	
						</select>
					</span>
				</div>
								
				<div class="row">
					<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">
						<select id="ownerNameSelect" name="owner">
							<option value="-1">---</option>
							<c:if test="${artifactView.owner != null}">
								<option value="${artifactView.owner.id}" selected="selected">${artifactView.owner.partyName.name}</option>
							</c:if>
						</select>
					</span>
				</div>

				<div class="row">
					<c:set property="key" value="ui.label.notifyStart" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"><input type="checkbox" id="notifyOnStartCheckbox" name="notifyOnStart"></span>
				</div>

				<div class="row">
					<c:set property="key" value="ui.label.notifyFinish" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"><input type="checkbox" id="notifyOnFinishCheckbox" name="notifyOnFinish"></span>
				</div>

				<div class="row">
					<c:set property="key" value="ui.label.enforceAcceptance" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"><input type="checkbox" id="enforceCheckbox" name="enforceAcceptance">
					<c:set property="key" value="ui.generic.button.accept" target="${i18Helper}"></c:set>
					<input type="button" class="buttonSmall" id="acceptButton" value="${i18Helper.value}" onclick="">
					</span>
				</div>
				<br><br>			
			</fieldset>
		</div>
		
		<div class="readOnlyContainer" align="left" id="performerDiv">
			<fieldset style="width: 400px; border-color: white;">
				<c:set property="key" value="ui.label.performer" target="${i18Helper}"></c:set>
				<legend style="color: brown;">${i18Helper.value}</legend>

				<div class="row">
					<c:set property="key" value="ui.label.type" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">
						<select id="performerTypeSelect" name="performerType" onchange="getPerformers();">
							<option value="-1">---</option>
							<c:forEach var="partyType" items="${artifactView.partyTypes}" varStatus="index">
							<c:set property="key" value="com.processpuzzle.party.domain.${partyType.name}" target="${i18Helper}"></c:set>
								<option value="${partyType.id}">${i18Helper.value}</option>
							</c:forEach>	
						</select>
					</span>
				</div>
								
				<div class="row">
					<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">
						<select id="performerNameSelect" name="performer">
							<option value="-1">---</option>
							<c:if test="${artifactView.performer != null}">
								<option value="${artifactView.performer.id}" selected="selected">${artifactView.performer.partyName.name}</option>
							</c:if>
						</select>
					</span>
				</div>

				<div class="row">
					<c:set property="key" value="ui.label.estimatedExpenditure" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"><input type="text" name="estimatedExpenditure" value=""></span>
					<select id="unit" name="unit">
						<option value="-1">---</option>
						<c:set property="key" value="ui.label.hour" target="${i18Helper}"></c:set>
						<option value="0">${i18Helper.value}</option>
						<c:set property="key" value="ui.label.day" target="${i18Helper}"></c:set>
						<option value="1">${i18Helper.value}</option>
						<c:set property="key" value="ui.label.week" target="${i18Helper}"></c:set>
						<option value="2">${i18Helper.value}</option>
						<c:set property="key" value="ui.label.month" target="${i18Helper}"></c:set>
						<option value="3">${i18Helper.value}</option>
					</select>
				</div>
				<br>		
			</fieldset>
		</div>

	<br><br>

		<div class="readOnlyContainer">
			<c:set property="key" value="ui.generic.button.save" target="${i18Helper}"></c:set>
			<input type="submit" class="buttonSmall" style="margin-left: 150px;" value="${i18Helper.value}">
			<c:set property="key" value="ui.generic.button.cancel" target="${i18Helper}"></c:set>
			<input type="button" class="buttonSmall" value="${i18Helper.value}">
		</div>
		
	<a name="Anchor"></a>
	</form>
</body>
</html>
