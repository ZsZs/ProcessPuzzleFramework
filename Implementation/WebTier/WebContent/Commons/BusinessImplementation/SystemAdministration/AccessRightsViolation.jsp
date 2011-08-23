<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">

<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META name="GENERATOR" content="IBM Software Development Platform">
<TITLE>Hiányzó hozzáférési jogok</TITLE>
</HEAD>
<BODY>

	<form name="accessRightsViolationForm">
	
		<div class="readOnlyContainer">
		
			<c:set property="key" value="ui.arv.header" target="${i18Helper}"></c:set>
			<center><span class="label" style="font-family: Verdana;"><b>${i18Helper.value}</b></span></center>

				<br>
				<br>
			
			<c:set property="key" value="ui.arv.info" target="${i18Helper}"></c:set>
			<center><span class="label" style="font-family: Verdana;">${i18Helper.value}</span></center>
			
			<br>
			
				<center>
	
					<c:set property="key" value="ui.Artifact_AccessRights.read" target="${i18Helper}"></c:set>
					<center>${i18Helper.value}</center>

					<br>
					
					<c:set property="key" value="ui.Artifact_AccessRights.create" target="${i18Helper}"></c:set>
					<center>${i18Helper.value}</center>

					<br>
					<c:set property="key" value="ui.Artifact_AccessRights.write" target="${i18Helper}"></c:set>
					<center>${i18Helper.value}</center>

					<br>
					<c:set property="key" value="ui.Artifact_AccessRights.delete" target="${i18Helper}"></c:set>
					<center>${i18Helper.value}</center>

					<br>
									
				</center>
			
			
		</div>
	
	</form>

</BODY>
</HTML>