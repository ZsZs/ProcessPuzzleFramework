<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<%@ include file="../../PageIncludes/ArtifactListPageHeader.jsp" %>
	<title>Price Type List</title>
	
	<script language="javascript" src="./JavaScript/WebUiCommands/CreateNewPriceTypeCommand.js"></script>
	
	<script language="javascript">
		var COMMAND_POSTFIX = "PriceType";
		var ARTIFACT_TYPE = "PriceTypeList";
	</script>
	
</head>
<body onload="init();">

	<form name="form" id="priceTypeListForm" method="post" action="${artifactView.codeBase}">
	
	<center>
	
	<br><br>
	
		<div id="pageingWidget"></div>
	
	<br><br>
	
		<div class="readOnlyContainer">
			<table style="width: 700px;" class="sortable" id="priceTypeListTable">
				<thead>
					<tr>
						<th style="width: 25px;"></th>
						<c:set property="key" value="ui.label.priceType" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.description" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.valid" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
						<c:set property="key" value="ui.label.default" target="${i18Helper}"></c:set>
						<th>${i18Helper.value}</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="artifactProperty" items="${artifactView.listedArtifactsPropertyViews}" varStatus="index">
						<c:set var="classNameForRow" value="odd"/>
						<c:if test="${index.count % 2 == 0}"><c:set var="classNameForRow" value="even"/></c:if>
						<tr class="${classNameForRow}">
							<td><input type="radio" name="priceType" onclick="refreshPropertyInfo('${artifactProperty.id}', 'PriceTypeDataSheet_PropertyView'); setSelectedArtifact('${artifactProperty.priceType}','${artifactProperty.id}'); enableButtons();"></td>
							<td><a href="#" onclick="goToDataSheetByLink('${artifactProperty.priceType}', '${artifactProperty.id}');">${artifactProperty.priceType}</a></td>
							<td>${artifactProperty.description}</td>
							<td>${artifactProperty.valid}</td>
							<td>${artifactProperty.default}</td>
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