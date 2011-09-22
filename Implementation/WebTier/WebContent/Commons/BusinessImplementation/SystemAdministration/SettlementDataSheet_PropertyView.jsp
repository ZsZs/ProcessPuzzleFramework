<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SettlementDataSheet_Properties</title>
</head>
	<body>
		<div class="readOnlyContainer">
		
			<div class="row">
				<span class="label">
				<c:set property="key" value="ui.settlementds.settlementproperties.settlementName" target="${i18Helper}"></c:set>
				${i18Helper.value}:
				</span>
				<span class="formw">${artifactView.settlementName}</span>
			</div>
			
			<c:if test="${empty artifactView.districts}">
			<div class="row">
				<span class="label">
				<c:set property="key" value="ui.settlementds.settlementproperties.zipCodes" target="${i18Helper}"></c:set>
				${i18Helper.value}:
				</span>
				<span class="formw">
					<c:forEach var="zipCode" items="${artifactView.zipCodes}" varStatus="index">
						${zipCode}&nbsp;
					</c:forEach>
				</span>
			</div>
			</c:if>
			
			<c:if test="${not empty artifactView.districts}">
			<div class="row">
				<span class="label">
				<c:set property="key" value="ui.settlementds.settlementproperties.districts" target="${i18Helper}"></c:set>
				${i18Helper.value}:
				</span>
				<span class="formw">
					<c:forEach var="districtPresentationHelper" items="${artifactView.districts}" varStatus="index">
						${districtPresentationHelper.distrinctName} : ${districtPresentationHelper.zipCodesSeparetedBySpaces} 
						<br>
					</c:forEach>
				</span>
			</div>
			</c:if>
			
		</div>
	
	</body>
</html>