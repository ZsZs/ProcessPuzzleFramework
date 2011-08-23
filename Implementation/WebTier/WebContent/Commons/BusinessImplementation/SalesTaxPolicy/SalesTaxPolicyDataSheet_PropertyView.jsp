<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>Sales Tax Properties</title>

	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>

</head>
<body>

	<form name="form" id="salesTaxPolicyBaseDataForm" method="post" action="${artifactView.codeBase}">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name}">

	<br><br>
	
		<div class="editableContainer">
			<fieldset style="width: 400px; border-color: white;">
			<c:set property="key" value="ui.label.salesTaxPolicy" target="${i18Helper}"></c:set>
			<legend>${i18Helper.value}</legend>
			<div class="row">
				<c:set property="key" value="ui.label.taxationType" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.taxationType}</span>
			</div>
			<div class="row">
				<c:set property="key" value="ui.label.taxationRate" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}:</span>
				<span class="formw">${artifactView.taxationRate}</span>
			</div>
			</fieldset>
		</div>
	</form>

</body>
</html>