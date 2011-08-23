<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Product Group Base Data</title>

</head>
<body>
	<form name="form" id="productGroupBaseDataForm" method="post" action="${artifactView.codeBase}">
	
	<br><br>
	
		<div class="editableContainer">
			<fieldset style="width: 400px; border-color: white;">
			<c:set property="key" value="ui.label.productGroup" target="${i18Helper}"></c:set>
			<legend>${i18Helper.value}</legend>
				<div class="row">
					<c:set property="key" value="ui.label.groupName" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"><input type="text" id="nameEdit" value="${artifactView.groupName}" onchange="setToDirty();"></span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.shortDescription" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"><textarea rows="5" cols="50" id="shortDescriptionArea" onchange="setToDirty();">${artifactView.shortDescription}</textarea></span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.longDescription" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw"><textarea rows="5" cols="50" id="longDescriptionArea" onchange="setToDirty();">${artifactView.longDescription}</textarea></span>
				</div>
			</fieldset>
		</div>
		
	<br><br>
	
			<div class="readOnlyContainer">
			<table style="width: 700px;" class="sortable" id="catlogEntryTable">
				<thead>
					<tr>
						<c:set property="key" value="ui.label.entryIdentifier" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.manufacturerCode" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.EAN" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.size" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.packagedUnit" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.price" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="artifactProperty" items="${artifactView.catalogEntryPropertyViews}" varStatus="index">
						<c:set var="classNameForRow" value="odd"/>
							<c:if test="${index.count % 2 == 0}">
								<c:set var="classNameForRow" value="even"/>
							</c:if>
							<tr class="${classNameForRow}">
							<td>${artifactProperty.entryIdentifier}</td>
							<td>${artifactProperty.producerCode}</td>
							<td>${artifactProperty.eanCode}</td>
							<td></td>
							<td></td>
							<td>${artifactProperty.price}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>


		<div class="readOnlyContainer">
			<c:set property="key" value="ui.generic.button.save" target="${i18Helper}"></c:set>
			<input type="submit" class="buttonSmall" style="margin-left: 150px;" value="${i18Helper.value}" onclick="setToClean();">
			<c:set property="key" value="ui.generic.button.cancel" target="${i18Helper}"></c:set>
			<input type="button" class="buttonSmall" value="${i18Helper.value}" onclick="closeIt();">
		</div>
	</form>
		

</body>
</html>