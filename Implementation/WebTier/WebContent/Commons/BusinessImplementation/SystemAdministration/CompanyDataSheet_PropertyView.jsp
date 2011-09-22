<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Company properties</title>
</head>
<body>
	<form name="form" id="companyPropertyForm" action="post" action="${artifactView.codeBase}">
		<br><br>	
		<div class="readOnlyContainer">
			<fieldset style="width: 400px; border-color: white;">
			<c:set property="key" value="ui.label.company" target="${i18Helper}"></c:set>
			<legend>${i18Helper.value}</legend>
				<div class="row">
					<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">${artifactView.companyName}</span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.companyds.basedata.tradeRegisterNumber" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">${artifactView.tradeRegisterNumber}</span>
				</div>		
				<div class="row">
					<c:set property="key" value="ui.companyds.basedata.taxNumber" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">${artifactView.taxNumber}</span>
				</div>	
				<div class="row">
					<c:set property="key" value="ui.pds.geodata.settlement" target="${i18Helper}"></c:set>
					<span class="label" id="cityText">${i18Helper.value}:</span>
					<span class="formw">${artifactView.settlement}</span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.pds.geodata.zip" target="${i18Helper}"></c:set>
					<span class="label" id="zipText">${i18Helper.value}:</span>
					<span class="formw">${artifactView.zipCode}</span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.pds.geodata.street" target="${i18Helper}"></c:set>
					<span class="label" id="streetText">${i18Helper.value}:</span>
					<span class="formw">${artifactView.street}</span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.pds.geodata.buildingNumber" target="${i18Helper}"></c:set>
					<span class="label" id="buildingNumberText">${i18Helper.value}:</span>
					<span class="formw">${artifactView.buildingNumber}</span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.companyds.basedata.centralPhone" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">${artifactView.telecomAddress}</span>
				</div>		
				<div class="row">
					<c:set property="key" value="ui.pds.webpagedata.webpage" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">${artifactView.webPageAddress}</span>
				</div>
			</fieldset>
		</div>
	</form>
</body>
</html>