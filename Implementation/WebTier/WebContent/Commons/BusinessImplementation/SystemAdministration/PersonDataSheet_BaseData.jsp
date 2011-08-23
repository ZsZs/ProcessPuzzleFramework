<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<HTML>
<HEAD>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>
	<script type="text/javascript" src="./JavaScript/Calendar/simplecalendar.js"></script>
	<script language="javascript" src="./JavaScript/BasicValidation/BasicValidation.js" ></script>
	<script language="javascript" src="./JavaScript/WebUIController/LoadUtility.js" ></script>
	<script type="text/javascript">
		imgUp.src="./Images/scalendar/up.gif";
		imgDown.src="./Images/scalendar/down.gif";
	</script>
	
	<script type="text/javascript">
	
		var webUiController = getWebUIController();
		var validateMethod = validate();
	
		function init() {
			calendar_init();
		}
		
		function goToList() {
			parent.webUIController.loadDocument('PersonList','PersonList');		
		}	
		
		function validate() {
			return false;
		}
		
		function savePerson() {
			if(document.getElementsByName("familyName")[0].value == "" || document.getElementsByName("givenName")[0].value == "" || document.getElementsByName("userName")[0].value == "") {
				alert("Toltse ki a hiányzó adatokat!");
				return false;
			}
			return true;
		}
	
	</script>

</HEAD>
<BODY onload="init();">

	<form name="personDataSheetBaseDatas" method="post" action="${artifactView.codeBase}" onsubmit="return validateMethod">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="PersonDataSheet_BaseDataView">
		
		<br><br>
	
		<div class="editableContainer" id="addDiv">
		
		<div class="row">
				<span class="label" id="prefixText">
					<c:set property="key" value="ui.pds.basedata.prefix" target="${i18Helper}"></c:set>
					${i18Helper.value}:
				</span>
				<span class="formw">
					<select id="prefixSelect" name="prefix">
						<option value="-1">---</option>
											
						<c:set property="key" value="ui.pds.basedata.prefix.dr" target="${i18Helper}"></c:set>
						<option value="dr"
						<c:if test="${artifactView.prefix == 'dr'}">
							selected="selected"
						</c:if>
						>${i18Helper.value}</option>
						<c:set property="key" value="ui.pds.basedata.prefix.prof" target="${i18Helper}"></c:set>
						<option value="prof"
						<c:if test="${artifactView.prefix == 'prof'}">
							selected="selected"
						</c:if>						
						>${i18Helper.value}</option>
						<c:set property="key" value="ui.pds.basedata.prefix.jr" target="${i18Helper}"></c:set>
						<option value="jr"
						<c:if test="${artifactView.prefix == 'jr'}">
							selected="selected"
						</c:if>						
						>${i18Helper.value}</option>
						<c:set property="key" value="ui.pds.basedata.prefix.old" target="${i18Helper}"></c:set>
						<option value="old"
						<c:if test="${artifactView.prefix == 'old'}">
							selected="selected"
						</c:if>						
						>${i18Helper.value}</option>
					</select>
				</span>					
			</div>
		

			<div class="row">
				<span class="label" id="familyNameText">
					<c:set property="key" value="ui.pds.basedata.familyName" target="${i18Helper}"></c:set>
					${i18Helper.value}:
				</span>
				<span id="formw"><input type="text" id="familyName" name="familyName" value="${artifactView.familyName}"  onChange="setToDirty();"> *</span>
			</div>

			<div class="row">
				<span class="label" id="givenNameText">
					<c:set property="key" value="ui.pds.basedata.givenName" target="${i18Helper}"></c:set>
					${i18Helper.value}:
				</span>
				<span id="formw"><input type="text" id="givenName" name="givenName" value="${artifactView.givenName}" onChange="setToDirty();"> *</span>
			</div>
		</div>
		
		<br>
		
		<div class="editableContainer">
		
		<fieldset style="width: 400px; border-color: white;">
			<c:set property="key" value="ui.pds.basedata.userData" target="${i18Helper}"></c:set>
			<legend style="color: brown;">${i18Helper.value}</legend>
						
		<div class="row">
			<span class="label" id="userNameText">
				<c:set property="key" value="ui.pds.basedata.userName" target="${i18Helper}"></c:set>
				${i18Helper.value}
			</span>
	 		<span class="formw"><input type="text" name="userName" id="userName" value="${artifactView.userName }"> *</span> 
   		</div>

		<div class="row">
			<span class="label" id="passwordText">
				<c:set property="key" value="ui.pds.basedata.password" target="${i18Helper}"></c:set>
				${i18Helper.value}
			</span>
			<span class="formw"><input type="password" name="password" id="password" value="${artifactView.password }"> *</span>
   		</div>
   		
   		<div class="row">
			<span class="label">
				<c:set property="key" value="ui.pds.basedata.prefferedLocale" target="${i18Helper}"></c:set>
				${i18Helper.value}
			</span>
			<span class="formw">
				<select name="selectLocale" id="selectLocale">
					<option value="-1">---</option>
					<c:forEach var="locale" items="${artifactView.locales}" varStatus="index">
						<option value="${locale}" 
						<c:if test="${artifactView.selectLocale == locale}">
							selected="selected"
						</c:if>
						>
							<c:set property="key" value="ui.pds.basedata.prefferedLocale.${locale}" target="${i18Helper}"></c:set>
							${i18Helper.value}
						</option>
					</c:forEach>
				</select>
			 *</span>
	   	</div>
			
			</fieldset>

		</div>
		
		<br>
	
			<c:set property="key" value="ui.generic.button.backToList" target="${i18Helper}"></c:set>
			<input type="button" style="margin-left: 150px;" class="buttonLarge" id="backButton" value="${i18Helper.value}" onclick="goToList();">
	
	<br><br>
		
		<div class="readOnlyContainer">

			<c:set property="key" value="ui.generic.button.save" target="${i18Helper}"></c:set>
			<input type="submit" style="margin-left: 150px;" class="buttonSmall" id="saveButton" value="${i18Helper.value}" onclick="validateMethod = savePerson();">
			<c:set property="key" value="ui.generic.button.cancel" target="${i18Helper}"></c:set>
			<input type="button" class="buttonSmall" id="cancelButton" value="${i18Helper.value}" onclick="closeIt();">
		
		</div>
	
	</form>

</BODY>
</HTML>
