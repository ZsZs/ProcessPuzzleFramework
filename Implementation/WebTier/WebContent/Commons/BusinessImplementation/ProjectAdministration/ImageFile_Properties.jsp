<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<HTML>
<HEAD>

	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">

<TITLE></TITLE>
</HEAD>
<BODY>
	<div class="readOnlyContainer">
		<div class="row">
			<span id="creationDate_Label" class="label">
			<c:set target="${i18Helper}" property="key" value="ui.processpuzzle.general.creationDate"/>${i18Helper.value}:
			</span>
			<span id="creationDate_Value" class="formw">${artifactView.creationDate}</span>
		</div>

		<div class="row">
			<span id="lastModDate_Label" class="label">
			<c:set target="${i18Helper}" property="key" value="ui.processpuzzle.general.lastModDate"/>${i18Helper.value}:
			</span>
			<span id="lastModDate_Value" class="formw">${artifactView.lastModificationDate}</span>
		</div>

		<div class="row">
			<span id="lastModifier_Label" class="label">
			<c:set target="${i18Helper}" property="key" value="ui.processpuzzle.general.lastModifier"/>${i18Helper.value}:
			</span>
			<span id="lastModifier_Value" class="formw">${artifactView.lastModifierName}</span>
		</div>

		<div class="row">
			<span id="responsible_Label" class="label">
			<c:set target="${i18Helper}" property="key" value="ui.processpuzzle.general.responsible"/>${i18Helper.value}:
			</span>
			<span id="responsible_Value" class="formw">${artifactView.responsibleName}</span>
		</div>

		<div class="row">
			<span id="versionControlled_Label" class="label">
			<c:set target="${i18Helper}" property="key" value="ui.generic.artifact.isVersionControlled"/>${i18Helper.value}:
			</span>
			<span id="versionControlled_Value" class="formw">${artifactView.isVersionControlled}</span>
		</div>

		<div class="row">
			<span id="originalFileNameLabel" class="label">
			<c:set target="${i18Helper}" property="key" value="ui.FileStorage_Properties.originalFileName"/>${i18Helper.value}
			</span>
			<span id="originalFileNameValue" class="formw"><a href="DownloadFile?fileId=${artifactView.artifactId}">${artifactView.originalFileName}</a></span>
		</div>

		<div class="row">
			<span id="contentTypeLabel" class="label">
			<c:set target="${i18Helper}" property="key" value="ui.label.type"/>${i18Helper.value}				
			</span>
			<span id="contentTypeValue" class="formw">${artifactView.contentType}</span>
		</div>

		<div class="row">
			<span id="sizeLabel" class="label">
			<c:set target="${i18Helper}" property="key" value="ui.FileStorage_Properties.size"/>${i18Helper.value}				
			</span>
			<span id="sizeValue" class="formw">${artifactView.size} bájt</span>
		</div>

		<div class="row">
			<span id="uploadDateLabel" class="label">
			<c:set target="${i18Helper}" property="key" value="ui.FileStorage_Properties.uploadDate"/>${i18Helper.value}				
			</span>
			<span id="uploadDateValue" class="formw">${artifactView.uploadDate}</span>
		</div>

		<div class="row">
			<span id="thumbnailLabel" class="label">
			<c:set target="${i18Helper}" property="key" value="ui.ImageFile_Properties.thumbnail"/>${i18Helper.value}				
			</span>
			<span id="thumbnailValue" class="formw"><a href="DownloadFile?fileId=${artifactView.artifactId}"><img src="${artifactView.thumbnailSource}"></a></span>
		</div>

	</div>
</BODY>
</HTML>
