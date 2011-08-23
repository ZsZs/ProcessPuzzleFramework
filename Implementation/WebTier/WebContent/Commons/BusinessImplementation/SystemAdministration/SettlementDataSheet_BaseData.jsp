<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">
	
	<script language="javascript" src="./JavaScript/WebUIController/LoadUtility.js" ></script>
	

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Település alapadatok</title>

<script language="javascript">
	
		var webUiController = getWebUIController();
		var validateMethod = validate();
		
		function validate() {
			return false;
		}
		
		function delZipcode() {
			document.getElementsByName("method")[0].value = "delZipcode";
			return true;
		}
		
		function newZipcode() {
			if(document.getElementsByName("zipCode")[0].value == "") {
				alert("Adjon meg értéket!");
				return false
			}			
			document.getElementsByName("method")[0].value = "newZipcode";
			return true;
		}
		
		function delCircle() {
			document.getElementsByName("method")[0].value = "delCircle";
			return true;
		}
		
</script>

</head>
<body>

	<form name="settlementForm" method="post" action="${artifactView.codeBase}" onsubmit="return validateMethod;">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="SettlementBaseDataView">
		<input type="hidden" name="method" value="">		
	
		<div class="editableContainer">
		
			<div class="row">
				<span class="label">
				<c:set property="key" value="ui.settlementds.settlementproperties.settlementName" target="${i18Helper}"></c:set>
				${i18Helper.value}:
				</span>
				<span class="formw">${artifactView.settlementName}</span>
			</div>
			
			<div class="row">
				<span class="label">
				<c:set property="key" value="ui.settlementds.settlementproperties.zipCodes" target="${i18Helper}"></c:set>
				${i18Helper.value}:
				</span>
				<span class="formw">
					<c:forEach var="artifactProperty" items="${artifactView.zipCodes}" varStatus="index">
						<c:set property="zipCodeId" value="${artifactProperty.id}" target="${artifactView}"></c:set>
						 ${artifactProperty.zipCode}
						 <c:if test="${artifactView.unusedZipCode}">
							<input type="checkbox" value="${artifactProperty.id}" name="zipCodes"/>
						</c:if>
						<c:if test="${!artifactView.unusedZipCode}">
						használt
						</c:if>
						<br>
					</c:forEach>
					<br>
					<c:set property="key" value="ui.generic.button.delete" target="${i18Helper}"></c:set>
					<input type="submit" value="${i18Helper.value}" class="buttonSmall" onclick="validateMethod = delZipcode();"><br>
					<c:set property="key" value="ui.generic.button.add" target="${i18Helper}"></c:set>
					<input type="text" name="zipCode" value=""/><input type="submit" value="${i18Helper.value}" onclick="validateMethod = newZipcode();" class="buttonSmall"/>
				</span>
			</div>
		
		</div>
	
	</form>

</body>
</html>