<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	function initFooter() {
	}
	
	function refresh() {
		location.reload(true);
	}
</script>

<div class="readOnlyContainer">
	<br>
	<c:set property="key" value="ui.generic.button.new" target="${i18Helper}"></c:set>
	<input type="button" class="buttonSmall" id="newActionButton" value="${i18Helper.value}" onclick="createNewArtifact('${skinDescriptor.stylesPath}');">
	<c:set property="key" value="ui.generic.button.open" target="${i18Helper}"></c:set>
	<input type="button" class="buttonSmall" id="openActionButton" value="${i18Helper.value}" disabled="disabled" onclick="goToDataSheetByButton();">
	<c:set property="key" value="ui.generic.button.delete" target="${i18Helper}"></c:set>
	<input type="button" class="buttonSmall" id="deleteActionButton" value="${i18Helper.value}" disabled="disabled" onclick="deleteArtifact(); refresh();">
</div>