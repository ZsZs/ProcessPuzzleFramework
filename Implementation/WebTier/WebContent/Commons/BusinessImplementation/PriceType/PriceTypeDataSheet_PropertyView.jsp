<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>Price Type Property View</title>

	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>

</head>
<body>

	<form name="form" id="priceTypeBaseDataForm" method="post" action="${artifactView.codeBase}">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name}">

		<br><br>
	
		<div class="editableContainer">
			<fieldset style="width: 400px; border-color: white;">
				<c:set property="key" value="ui.label.priceType" target="${i18Helper}"></c:set>
				<legend>${i18Helper.value}</legend>
				<div class="row">
					<c:set property="key" value="ui.label.priceType" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">${artifactView.priceType}</span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.description" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">${artifactView.description}</span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.valid" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">${artifactView.valid}</span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.default" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">${artifactView.default}</span>
				</div>
			</fieldset>
		</div>
	</form>

</body>
</html>