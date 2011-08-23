<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<%@ include file="../../PageIncludes/ArtifactListPageHeader.jsp" %>
	<title>Product Type List</title>

	<script language="javascript" src="./JavaScript/WebUiCommands/CreateNewProductTypeCommand.js"></script>
	
	<script language="javascript">
		var COMMAND_POSTFIX = "ProductType";
		var ARTIFACT_TYPE = "ProductTypeList";
	</script>

</head>
<body onload="init();">
	<form name="form" id="productTypeListForm" method="post" action="${artifactView.codeBase}">
	
	<center>
	
	<br><br>
	
		<div id="pageingWidget"></div>
		
	<br><br>
	
		<div class="readOnlyContainer">
			<table style="width: 700px;" class="sortable" id="productTypeListTable">
				<thead>
					<tr>
						<th style="width: 25px;"></th>
						<c:set property="key" value="ui.label.identifier" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.manufacturerCode" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.EAN" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.price" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.feature" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="artifactProperty" items="${artifactView.listedArtifactsPropertyViews}" varStatus="index">
						<c:set var="classNameForRow" value="odd"/>
							<c:if test="${index.count % 2 == 0}">
								<c:set var="classNameForRow" value="even"/>
							</c:if>
						<tr class="${classNameForRow}">
							<td><input type="radio" name="productType"  onclick="refreshPropertyInfo('${artifactProperty.id}', 'ProductTypeDataSheet_PropertyView'); setSelectedArtifact('${artifactProperty.identifier}','${artifactProperty.id}'); enableButtons();"></td>
							<td><a href="#" onclick="goToDataSheetByLink('${artifactProperty.identifier}', '${artifactProperty.id}');">${artifactProperty.identifier}</a></td>
							<td>${artifactProperty.productTypeName}</td>
							<td>${artifactProperty.producerCode}</td>
							<td>${artifactProperty.EANCode}</td>
							<td>${artifactProperty.currentValidPricesAsText}</td>
							<td>${artifactProperty.featureTypesAsText}</td>
						</tr>	
					</c:forEach>
				</tbody>
			</table>
		</div>
		
	<br><br>
	
		<%@ include file="../../PageIncludes/ArtifactListPageFooter.jsp" %>
			
	</center>
			
	</form>
</body>
</html>