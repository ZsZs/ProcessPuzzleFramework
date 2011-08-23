<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>
	<title>Product Catalog Properties</title>
</head>
<body>
	<form name="form" id="productGroupPropertyForm" method="post" action="${artifactView.codeBase}">
	
	<br><br>
	
		<div class="readOnlyContainer">
			<fieldset style="width: 400px; border-color: white;">
			<c:set property="key" value="ui.label.productGroup" target="${i18Helper}"></c:set>
			<legend>${i18Helper.value}</legend>
				<div class="row">
					<c:set property="key" value="ui.label.groupName" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">${artifactView.groupName}</span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.shortDescription" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">${artifactView.shortDescription}</span>
				</div>
				<div class="row">
					<c:set property="key" value="ui.label.longDescription" target="${i18Helper}"></c:set>
					<span class="label">${i18Helper.value}:</span>
					<span class="formw">${artifactView.longDescription}</span>
				</div>
			</fieldset>
		</div>
		
	</form>
		

</body>
</html>