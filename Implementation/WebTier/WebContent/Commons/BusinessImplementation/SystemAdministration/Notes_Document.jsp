<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- load the main HTMLArea files -->
    <script type="text/javascript">
      _editor_url = './JavaScript/TextEditor/';
      _editor_lang = "en";
    </script>
   	<script type="text/javascript" src="./JavaScript/TextEditor/htmlarea.js"></script>
   	<script type="text/javascript" src="./JavaScript/CommonScripts/XmlResource.js"></script>
   	<script type="text/javascript" src="./JavaScript/CommonScripts/BrowserEvent.js"></script>
   	<script type="text/javascript" src="./JavaScript/TextEditor/EditorManager.js"></script>
   	<script language="javascript" src="./JavaScript/DataRetriever/DataRetriever.js" ></script>
		<script language="javascript" src="./JavaScript/DataRetriever/InvalidXmlResponseException.js" ></script>
   	
   	<script type="text/javascript">
			editableHTMLDocumentName="${artifactView.parentArtifact.name}";		
			editableHTMLDocumentsViewName = "${artifactView.name}";
			actionNameForSaveComment ="UpdateView";
			textEditorRows=25;			
    </script>
    
    <script type="text/javascript">
    
    	var dataRetriever;
    
    	function init() {  		
	    	dataRetriever = new DataRetriever(null, "artifactId", "${artifactView.artifactId}", "${artifactView.name}", null, parent.browserFrame.webUIController.getResourceBundle());
	    	dataRetriever.setMode("text");
				dataRetriever.makeHttpRequest("getXml();");
			}
			
			function getXml() {			
				var responseData = dataRetriever.getCurrentResponseValue();
				var msgText = document.getElementById("content");
				msgText.innerHTML = responseData;
			}
			
    </script>
<title>Feljegyzés - Dokumentum nézet</title>
</head>
<body onload="init();javascript:HTMLArea.init();">
<center><h4>Feljegyzés</h4></center>

<form name="form" method="post" action="${artifactView.codeBase}">
	<input name="action" type="hidden" value="UpdateView">
	<input name="artifactName" type="hidden" value="${artifactView.parentArtifact.name}">
	<input name="viewName" type="hidden" value="${artifactView.name}">
		
	<div class="messageText" id="content" style="border: 1px dotted;" onclick="javascript:initEditableComment(this);"></div>
</form>
</body>
</html>