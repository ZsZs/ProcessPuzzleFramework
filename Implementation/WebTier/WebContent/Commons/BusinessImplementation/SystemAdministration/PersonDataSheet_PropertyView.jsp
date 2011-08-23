<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META name="GENERATOR" content="IBM Software Development Platform">
<TITLE>PersonDataSheet_Properties</TITLE>
<script language="javascript" src="../../JavaScript/BasicValidation/BasicValidation.js" ></script>
<script type="text/javascript">

</script>


</HEAD>
<BODY>
		
	<div class="editableContainer" id="addDiv">
		
		<div class="row">
			<span class="label" id="prefixNameText">
				<c:set property="key" value="ui.pds.basedata.prefix" target="${i18Helper}"></c:set>
				${i18Helper.value}:
			</span>
			<span id="formw">${artifactView.prefix}</span>
		</div>

		<div class="row">
			<span class="label" id="familyNameText">
				<c:set property="key" value="ui.pds.basedata.familyName" target="${i18Helper}"></c:set>
				${i18Helper.value}:
			</span>
			<span id="formw">${artifactView.familyName}</span>
		</div>

		<div class="row">
			<span class="label" id="givenNameText">
				<c:set property="key" value="ui.pds.basedata.givenName" target="${i18Helper}"></c:set>
				${i18Helper.value}:
			</span>
			<span id="formw">${artifactView.givenName}</span>
		</div>
				
		<div class="row">
			<span class="label" id="birthDateText">
				<c:set property="key" value="ui.pds.basedata.birthDate" target="${i18Helper}"></c:set>
				${i18Helper.value}:
			</span>
			<span id="formw">${artifactView.birthDate}</span>
		</div>

		<div class="row">
			<span class="label" id="geographicAddressText">
				<c:set property="key" value="ui.pds.geodata.default" target="${i18Helper}"></c:set>
				${i18Helper.value}:
			</span>
			<span id="formw">${artifactView.geographicAddress}</span>
		</div>
	
		<div class="row">
			<span class="label" id="telecomAddressText">
				<c:set property="key" value="ui.label.phone" target="${i18Helper}"></c:set>
				${i18Helper.value}:
			</span>
			<span id="formw">${artifactView.telecomAddress}</span>
		</div>

		<div class="row">
			<span class="label" id="emailAddressText">
				<c:set property="key" value="ui.pds.emaildata.email" target="${i18Helper}"></c:set>
				${i18Helper.value}:
			</span>
			<span id="formw">${artifactView.emailAddress}</span>
		</div>

	</div>

</BODY>
</HTML>
