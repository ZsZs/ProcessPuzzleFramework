<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<HTML>
<HEAD>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK rel="stylesheet" href="./${skinDescriptor.stylesPath}/base.css" type="text/css">
	<LINK rel="stylesheet" href="./${skinDescriptor.stylesPath}/frameInfo.css" type="text/css">
	<LINK rel="stylesheet" href="./${skinDescriptor.stylesPath}/messageStyle.css" type="text/css">
	<title>Message Wall</title>
	
	<!-- load the main HTMLArea files -->
    <script type="text/javascript">
      _editor_url = './JavaScript/TextEditor/';
      _editor_lang = "en";
    </script>
   	<script type="text/javascript" src="./Commons/JavaScript/TextEditor/htmlarea.js"></script>
   	<script type="text/javascript" src="./Commons/JavaScript/CommonScripts/XmlResource.js"></script>
   	<script type="text/javascript" src="./Commons/JavaScript/CommonScripts/BrowserEvent.js"></script>
   	<script type="text/javascript" src="./Commons/JavaScript/TextEditor/EditorManager.js"></script>
	<script type="text/javascript" src="./Commons/JavaScript/Log4JavaScript/log4javascript.js"></script>
	
	<script type="text/javascript">
		editableHTMLDocumentId = "${artifactView.artifactId}";
		actionNameForSaveComment ="SaveComment";
    </script>
</HEAD>

<BODY onload="javascript:HTMLArea.init();">
	<form name="form" method="post">	

	<div class="editableContainer">
		<div class="spacer">&nbsp;</div>

	    <script type="text/JavaScript">pasteNewEditableCommentButton();</script>

		<c:forEach var="message" items="${artifactView.comments}">
				<div class="messageHeader">
					<span class="label">${message.creationTimeStamp}</span>
					<span class="formw">
						<c:choose>
							<c:when test="${message.author!=null && message.author.partyName!=null}">
								${message.author.partyName.givenName} ${message.author.partyName.familyName}
							</c:when>
							<c:otherwise> - </c:otherwise>
						</c:choose>
					</span>
			   	</div>
				<div class="row">
				   	<div class="messageText" id="${message.divId}">${message.text}</div>
			   	</div>
		</c:forEach>

		<div class="spacer">&nbsp;</div>		
	</div>
</form>
</BODY>
</HTML>