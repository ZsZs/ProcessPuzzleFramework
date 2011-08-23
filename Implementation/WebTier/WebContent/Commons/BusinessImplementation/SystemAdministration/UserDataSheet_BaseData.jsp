<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<link rel="stylesheet" href="./styles/base.css" type="text/css">
<link rel="stylesheet" href="./styles/content.css" type="text/css">
<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">

<title>Felhasználó alapadatok</title>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" src="./JavaScript/WebUIController/LoadUtility.js"></script>
<script language="javascript" src="./JavaScript/BasicValidation/BasicValidation.js"></script>
<script language="javascript" src="./JavaScript/DataRetriever/DataRetriever.js"></script>
<script language="javascript" src="./JavaScript/DataRetriever/InvalidXmlResponseException.js"></script>
<script language="javascript" src="./JavaScript/WebUIController/WebUIController.js"></script>
	
	<script type="text/javascript">
	
		var pass = document.getElementById("passwordEdit").value;
		var newpass = document.getElementById("newPasswordEdit").value;
		var retypednewpass = document.getElementById("retypeNewPasswordEdit").value;
	
		function init() {
			if (pass == "") {
				document.getElementById("passwordDiv").style.display = "none";
			}
			else {
				document.getElementById("passwordDiv").style.display = "block";
			}
		}
	
		function openPassChangerDiv() {
			if (document.getElementById("fieldSetDiv").style.display = "none") {
				document.getElementById("newPasswordEdit").value = "";
				document.getElementById("retypeNewPasswordEdit").value = "";
				document.getElementById("fieldSetDiv").style.display = "block";
			}
			else {
				document.getElementById("fieldSetDiv").style.display = "none";
			}
		}
		
		function compareNewPasswords() {
			if (newpass != retypednewpass) {
				alert("Password mismatch error!" + \n\n + "The new passwords are different!");
				return false;
			}
			
			if (newpass == retypednewpass) {
				return true;
				document.getElementById("confirmNewPasswordButton").disabled = "true";
				openPassChangerDiv();
			}
		}
		
		function enableSubmit() {
			if (newpass != "" && retypednewpass != "") {
				document.getElementById("confirmNewPasswordButton").disabled = "false";
			}
			else {
				document.getElementById("confirmNewPasswordButton").disabled = "true";
			}
		}

	</script>
	
</head>
<body onload="init();">

	<form name="form" id="userBaseDataForm" method="post" action="${artifactView.codeBase}">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name}">

	<br><br>
	
		<div class="editableContainer">
			<div class="row">
				<c:set property="key" value="ui.label.userName" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" name="userName" id="userNameEdit" value="${artifactView.userName}" readonly="readonly"></span>
			</div>

			<div class="row" id="passwordDiv" style="display: block;">
				<c:set property="key" value="ui.label.password" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="password" name="password" id="passwordEdit" value="${artifactView.password}"></span>
				<c:set property="key" value="ui.label.retypePassword" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="password" id="retypePasswordEdit" value=""></span>
			</div>

			<div class="row">
				<c:set property="key" value="ui.label.changePassword" target="${i18Helper}"></c:set>
				<input type="button" class="buttonLarge" id="changePasswordButton" value="${i18Helper.value}" onclick="openPassChangerDiv();">
			</div>
		</div>		
		
		<hr>
		
		<div class="editableContainer" id="fieldSetDiv" style="display: none;">
			<fieldset style="width: 400px;">
				<legend>${i18Helper.value}</legend>
				
			<div class="row">
				<c:set property="key" value="ui.label.newPassword" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="password" id="newPasswordEdit" value="" onchange="enableSubmit();"></span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.retypePassword" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="password" id="retypeNewPasswordEdit" value="" onchange="enableSubmit();"></span>
			</div>
		<br><br>
			</fieldset>
		</div>
		
		<div class="editableContainer">
			<div class="row">
				<c:set property="key" value="ui.label.designation" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><input type="text" id="designationEdit" value="${artifactView.designation}" maxlength="40"></span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.description" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw"><textarea rows="3" cols="40" id="descriptionArea">${artifactView.description}</textarea></span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.status" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.status}</span>
			</div>
		</div>
		
		<div class="readOnlyContainer">
			<div class="row">
				<c:set property="key" value="ui.label.activate" target="${i18Helper}"></c:set>
				<input class="buttonSmall" type="submit" id="activateButton" value="${i18Helper.value}">
				<c:set property="key" value="ui.label.suspend" target="${i18Helper}"></c:set>
				<input class="buttonSmall" type="button" id="suspendButton" value="${i18Helper.value}">
				<c:set property="key" value="ui.label.delete" target="${i18Helper}"></c:set>
				<input class="buttonSmall" type="button" id="deleteButton" value="${i18Helper.value}">
			</div>
		</div>

		<br><br>

		<div class="readOnlyContainer">
			<div class="row">
				<c:set property="key" value="ui.label.save" target="${i18Helper}"></c:set>
				<input class="buttonSmall" type="submit" id="confirmNewPasswordButton" disabled="disabled" value="${i18Helper.value}" onclick="compareNewPasswords();">
				<c:set property="key" value="ui.label.cancel" target="${i18Helper}"></c:set>
				<input class="buttonSmall" type="button" id="cancelButton" value="${i18Helper.value}" onclick="javascript: document.forms[0].reset(); closeIt();">
			</div>
		</div>

	</form>
</body>
</html>
