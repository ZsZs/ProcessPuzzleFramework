<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<HTML>
<HEAD>
	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<META name="GENERATOR" content="IBM Software Development Platform">
	<link rel="stylesheet" href="../../styles/base.css" type="text/css">
	<link rel="stylesheet" href="../../styles/content.css" type="text/css">
	<link rel="stylesheet" href="../../styles/form_styles.css" type="text/css">
	<script language="javascript" src="./JavaScript/BasicValidation/BasicValidation.js" ></script>

<TITLE>Adatbázis Adminisztráció</TITLE>
	<script language="javascript">
		function setMethod(method)
		{
			document.getElementsByName("method")[0].value = method;
		}
		
		function setUpForRestore(version, date){
		
			<c:set property="key" value="ui.databaseAdministration.schemaDropConfirmation" target="${i18Helper}"></c:set>
			
			var confirmation = confirm('${i18Helper.value}');
			if (confirmation == true) {
				document.getElementById("version").value = version;
				document.getElementById("date").value = date;
				databaseAdminForm.submit();
				}
			}
			
		function noBackupWarning(){
		
			if (${helper.versionSize == 0 }){
				<c:set property="key" value="ui.databaseAdministration.noExistingBackupWarningPartOne" target="${i18Helper}"></c:set>
				alert('${i18Helper.value}');
				<c:set property="key" value="ui.databaseAdministration.noExistingBackupWarningPartTwo" target="${i18Helper}"></c:set>
				alert('${i18Helper.value}');
			}
			else{
				showOtherDatas('availableBackupsDiv');
			}
		}
		
		function backupConfirmation(){
			
			<c:set property="key" value="ui.databaseAdministration.backupConfirmation" target="${i18Helper}"></c:set>
			var confirmation = confirm('${i18Helper.value}');
			
			if (confirmation == true) {
			}
			else{
				<c:set property="key" value="ui.databaseAdministration.abortBackup" target="${i18Helper}"></c:set>
				alert('${i18Helper.value}');
			}
		}		
		
	</script>
	
</HEAD>
<BODY>

		<div class="readOnlyContainer">
		
			<form id="form" name="databaseAdminForm" id="databaseAdminForm" method="POST" action="/ADIBrowserInterface/CommandControllerServlet">
				<input type="hidden" name="method" value="">
				<input type="hidden" name="action" value="DatabaseAdmin">
				<input type="hidden" name="version" id="version" value="">
				<input type="hidden" name="date" id="date" value="">				
		
				<br>
				<center>
						<c:set property="key" value="ui.databaseAdministration.welcome" target="${i18Helper}"></c:set>
						<h4>${i18Helper.value}</h4>
						<c:set property="key" value="ui.databaseAdministration.adminInfo" target="${i18Helper}"></c:set>
						<span class="label" style="font-family: Verdana; font-size:10pt;">${i18Helper.value}</span>
				</center>

				<br><br>

				<center>
					<c:set property="key" value="ui.databaseAdministration.startBackup" target="${i18Helper}"></c:set>
					<span class="formw"><input type="submit" class="buttonLarge" name="backup" id="backupButton" value="${i18Helper.value}" onclick="setMethod('backUp');"></span>
					<c:set property="key" value="ui.databaseAdministration.startRestore" target="${i18Helper}"></c:set>
					<span class="formw"><input type="button" class="buttonLarge" name="restore" id="restoreButton" value="${i18Helper.value}" onclick="setMethod('restore'); noBackupWarning();"></span>
				</center>

				<br><br>
				
				<div class="readOnlyContainer" style="display:none" id="availableBackupsDiv">
					<center>
					
						<c:set property="key" value="ui.databaseAdministration.availableBackups" target="${i18Helper}"></c:set>
						<span class="label" style="font-family: Verdana; font-size:10pt;">${i18Helper.value}</span>
						
						<br><br>
					
						<table>
							<thead>
								<tr>
									<th>
										<c:set property="key" value="ui.databaseAdministration.adiVersion"  target="${i18Helper}"></c:set>
										${i18Helper.value}
									</th>
									<th>
										<c:set property="key" value="ui.databaseAdministration.backupDate"  target="${i18Helper}"></c:set>
										${i18Helper.value}
									</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="version" items="${helper.versions}" varStatus="index">
							<tr>
								<td>${version[0]}</td>
								<td>${version[1]}</td>
								<c:set property="key" value="ui.databaseAdministration.restoreButton"  target="${i18Helper}"></c:set>
								<td><input type="button" value="${i18Helper.value}" class="buttonSmall" onclick="setUpForRestore('${version[0]}', '${version[1]}');"/></td>
							</tr>
							</c:forEach>					
						</table>
					</center>

					<br><br>
					
					<center>
						<c:set property="key" value="ui.databaseAdministration.schemaDropWarning"  target="${i18Helper}"></c:set>
						<span class="label" style="font-family: Verdana; font-size:10pt;">${i18Helper.value}</span>
					</center>

				</div>

			</form>		
		
		</div>
	
</BODY>
</HTML>
