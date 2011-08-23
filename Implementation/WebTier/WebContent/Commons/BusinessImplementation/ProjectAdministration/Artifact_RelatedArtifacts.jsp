<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<HTML>

<HEAD>

	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">
	<link href="./ProjectBrowser/TreeWidget.css" rel=stylesheet >
	
  <script language="javascript" src="./JavaScript/WebUIController/LoadUtility.js"></script>
	<script language="javascript" src="./JavaScript/CommonScripts/AssertUtil.js"></script>
	<script language="javascript" src="./JavaScript/CommonScripts/XmlResource.js"></script>
	<script language="javascript" src="./JavaScript/WebUIController/WebUIController.js"></script>
	<script language="javascript" src="./JavaScript/Internalization/Locale.js"></script>
	<script language="javascript" src="./JavaScript/Internalization/LocaleUtil.js"></script>
	<script language="javascript" src="./JavaScript/Internalization/ResourceKey.js"></script>
	<script language="javascript" src="./JavaScript/Internalization/ResourceCache.js"></script>
	<script language="javascript" src="./JavaScript/Internalization/XMLBundleParser.js"></script>
	<script language="javascript" src="./JavaScript/Internalization/XMLResourceBundle.js"></script>
	<script language="javascript" src="./JavaScript/XMLforScript/xmlsax.js"></script>
	<script language="javascript" src="./JavaScript/XMLforScript/preMadeSaxEventHandler.js"></script>
	<script language="javascript" src="./JavaScript/CommonScripts/inheritFrom.js"></script>
	<script language="javascript" src="./JavaScript/CommonScripts/HashMap.js"></script>
	<script language="javascript" src="./JavaScript/CommonScripts/ArrayList.js"></script>
	<script language="javascript" src="./JavaScript/CommonScripts/StringBuffer.js"></script>
	<script language="javascript" src="./JavaScript/CommonScripts/StringUtil.js"></script>
	<script language="javascript" src="./JavaScript/CommonScripts/Configuration.js"></script>
	<script language="javascript" src="./JavaScript/CommonScripts/Collection.js"></script>
	<script language="javascript" src="./JavaScript/CommonScripts/UserException.js"></script>
	<script language="javascript" src="./JavaScript/CommonScripts/GenericBrowser.js"></script>
	<script language="javascript" src="./JavaScript/DocumentRelationshipWidget/DocumentRelationshipWidget.js"></script>
	<script language="javascript" src="./JavaScript/DataRetriever/DataRetriever.js" ></script>
	<script language="javascript" src="./JavaScript/BasicValidation/BasicValidation.js" ></script>
	<script language="javascript" src="./JavaScript/CommonScripts/XmlResource.js"></script>
	<script language="javascript" src="./JavaScript/TreeWidget/TreeNode.js"></script>
	<script language="javascript" src="./JavaScript/TreeWidget/TreeWidget.js"></script>

	<script type="text/javascript">
		var webUiController;
		var dataRetriever = null;
		var documentRelationshipWidget = null;
		
		function initializations(){
			webUiController = parent.browserFrame.webUIController;
			documentRelationshipWidget = new DocumentRelationshipWidget("relationshipWidget", "${artifactView.artifactId}", webUiController.getResourceBundle());
			documentRelationshipWidget.show();
		}

		function goToDataSheetByLink(artifactType, artifactName, artifactId){
			webUiController.loadDocument(artifactType, artifactName, '&artifactId='+artifactId);
		}

	</script>

<TITLE>Artifact_RelatedArtifacts</TITLE>
</HEAD>
<BODY onload="initializations();">
	<form name="form" id="form" method="post" accept-charset="UTF-8" enctype="multipart/form-data" action="${artifactView.codeBase}">
	
		<input type="hidden" name="action" value="AddRelatedArtifact">
		<input type="hidden" name="viewName" value="RelatedArtifactsListView">
		<input type="hidden" name="targetFolder" value="">
		<input type="hidden" name="subjectArtifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="targetArtifactName" value="">
		
		<br>
			<div id="relationshipWidget" class="readOnlyContainer"></div>
		<br>
		<center>

		<div class="editableContainer">
			<c:if test="${artifactView.relatedArtifactsPropertyViews != null}">
				<table id="artifact_relatedArtifactsTable" width="700px" border="1">
					<thead>
						<tr>
							<th>
							</th>
							<th align="center" id="artifactName" style="cursor:pointer" onClick="" width="100px"><b>
							<c:set target="${i18Helper}" property="key" value="ui.label.name"/>${i18Helper.value}:</b>
							</th>
							<th align="center" id="creationDate" style="cursor:pointer" onClick="" width="120px"><b>
							<c:set target="${i18Helper}" property="key" value="ui.label.creationDate"/>${i18Helper.value}:</b>
							</th>
							<th align="center" id="lastModDate" style="cursor:pointer" onClick="" width="160px"><b>
							<c:set target="${i18Helper}" property="key" value="ui.label.lastModDate"/>${i18Helper.value}:</b>
							</th>
							<th align="center" id="lastModifier" style="cursor:pointer" onClick=""><b>
							<c:set target="${i18Helper}" property="key" value="ui.label.lastModifier"/>${i18Helper.value}:</b>
							</th>
							<th align="center" id="responsible" style="cursor:pointer" onClick=""><b>
							<c:set target="${i18Helper}" property="key" value="ui.label.responsible"/>${i18Helper.value}:</b>
							</th>
							<th align="center" id="versionControlled" style="cursor:pointer" onClick=""><b>
							<c:set target="${i18Helper}" property="key" value="ui.label.versionControlled"/>${i18Helper.value}:</b>
							</th>
							<th align="center" id="useBy" style="cursor:pointer" onClick=""><b>
							<c:set target="${i18Helper}" property="key" value="ui.ArtifactList_ListedArtifacts.useBy"/>${i18Helper.value}:</b>
							</th>
							<th align="center" id="useBy" style="cursor:pointer" onClick=""><b>
							<c:set target="${i18Helper}" property="key" value="ui.label.type"/>${i18Helper.value}:</b>
							</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="propertyView" items="${artifactView.relatedArtifactsPropertyViews}" varStatus="index">
						<c:set var="classNameForRow" value="odd"/>
						<c:if test="${index.count % 2 == 0}">
							<c:set var="classNameForRow" value="even"/>
						</c:if>
						<tr class="${classNameForRow}">
								<td align="center"><input type="checkbox" name="targetArtifactId" value="${propertyView.artifactId}" onclick="documentRelationshipWidget.selectionChanged();"></td>
								<td align="center" width="100"><a href="#" onclick="goToDataSheetByLink('${propertyView.artifactTypeName}', '${propertyView.artifactName}', '${propertyView.artifactId}');">
								<c:if test="${propertyView.isFile}">
									${propertyView.originalFileName}
								</c:if>
								<c:if test="${!propertyView.isFile}">							
									${propertyView.artifactName}
								</c:if>
								</a></td>
								<td align="center" width="120">${propertyView.creationDate}</td>
								<td align="center" width="160">${propertyView.lastModificationDate}</td>
								<td align="center" width="100">${propertyView.lastModifierName}</td>
								<td align="center" width="100">${propertyView.responsibleName}</td>
								<td align="center" width="100">${propertyView.isVersionControlled}</td>
								<td align="center" width="100">${propertyView.useBy}</td>
								<td align="center" width="100">${propertyView.artifactTypeName}</td>					
						</tr>
					</c:forEach>	
					</tbody>
				</table>
			</c:if>
		</div>
		</center>
	</form>
</BODY>
</HTML>
