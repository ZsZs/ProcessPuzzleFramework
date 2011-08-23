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
<title>PlanDataSheet_RoleAssignments</title>
</head>
<script type="text/javascript">
	
	var validateMethod = false;

	function createRoleAssignment() {
		if(document.getElementById("roleSelect").selectedIndex < 0 || document.getElementById("personSelect").selectedIndex < 0 ){
			alert("Jelölje ki a kapcsolatot!");
			return false;
		}
		else{
			document.getElementsByName("method")[0].value = "newRoleAssignment";
			return true;
		}
	}
	
	function removeRoleAssignment(partyRoleId) {	
		document.getElementsByName("method")[0].value = "removeRoleAssignment";
		document.getElementsByName("roleAssignmentId")[0].value = partyRoleId;
		return true;
	}

</script>

<body>

	<form onsubmit="return validateMethod" name="plandsraForm" method="post" action="${artifactView.codeBase }">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name }">
		<input type="hidden" name="method" value="">
		<input type="hidden" name="roleAssignmentId" value="">

		<div class="readOnlyContainer">
			<table>
				<thead>
					<tr>
						<c:set property="key" value="ui.plands.roleassignments.role" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.person" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="roleAssignment" items="${artifactView.roleAssignments}" varStatus="index">
						<tr>
							<c:set property="key" value="ui.pantokrator.partyRoleType.${roleAssignment.name}" target="${i18Helper}"></c:set>
							<td>${i18Helper.value }</td>
							<td>${roleAssignment.party.partyName.name }</td>
							
							<td>
								<c:set property="key" value="ui.generic.button.remove" target="${i18Helper}"></c:set>
								<input type="submit" class="buttonSmall" id="associateButton" value="${i18Helper.value}" onclick="validateMethod = removeRoleAssignment('${roleAssignment.id }');">
							</td>
						</tr>
						
					</c:forEach>
					
				</tbody>			
			</table>
		</div>

			<br>

		<div class="editableContainer">

			<div class="row">

				<span style="margin-left: 10px;">
					<c:set property="key" value="ui.plands.roleassignments.roles" target="${i18Helper}"></c:set>
					${i18Helper.value}
				</span>
				<span class="formw">
					<select multiple="multiple" size="10" id="roleSelect" name="partyRoleType">
						<c:forEach var="partyRoleType" items="${artifactView.partyRoleTypes}" varStatus="index">
							<c:set property="key" value="ui.pantokrator.partyRoleType.${partyRoleType.name}" target="${i18Helper}"></c:set>
							<option value="${partyRoleType.id }">${i18Helper.value}</option>
						</c:forEach>
					</select>	
				</span>
		
				<span style="margin-left: 115px;">
					<c:set property="key" value="ui.label.persons" target="${i18Helper}"></c:set>
					${i18Helper.value}
				</span>
				<span class="formw" style="margin-left: 50px;">
					<select multiple="multiple" size="10" id="personSelect" name="person">
						<c:forEach var="person" items="${artifactView.persons}" varStatus="index">
							<option value="${person.id }">${person.partyName.name}</option>
						</c:forEach>
					</select>	
				</span>

			</div>

		</div>
		
		<br><br>
		
		<div>
			<c:set property="key" value="ui.plands.roleassignments.associate" target="${i18Helper}"></c:set>
			<input type="submit" class="buttonSmall" id="associateButton" value="${i18Helper.value}" onclick="validateMethod = createRoleAssignment();">
		</div>
		
	</form>	

</body>
</html>