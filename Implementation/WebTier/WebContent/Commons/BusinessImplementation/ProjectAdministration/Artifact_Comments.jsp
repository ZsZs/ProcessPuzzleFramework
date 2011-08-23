<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK rel="stylesheet" href="./styles/base.css" type="text/css">
	<LINK rel="stylesheet" href="./styles/content.css" type="text/css">
	<LINK rel="stylesheet" href="./styles/messageStyle.css" type="text/css">
	<title>Document comments</title>
	
	<!-- load the main HTMLArea files -->
    <script type="text/javascript">
      _editor_url = './JavaScript/TextEditor/';
      _editor_lang = "en";
    </script>
   	<script type="text/javascript" src="./JavaScript/TextEditor/htmlarea.js"></script>
   	<script type="text/javascript" src="./JavaScript/CommonScripts/XmlResource.js"></script>
   	<script type="text/javascript" src="./JavaScript/CommonScripts/BrowserEvent.js"></script>
   	<script type="text/javascript" src="./JavaScript/TextEditor/EditorManager.js"></script>
	<script type="text/javascript">
		editableHTMLDocumentId="${commentListId}";
		editableHTMLDocumentName="${commentListName}";		
		actionNameForSaveComment ="SaveComment";
    </script>
</HEAD>

<BODY onload="javascript:HTMLArea.init();">
	<form name="form" method="post" action="./CommandControllerServlet">	
	<input type="hidden" name="action" id="action" value="ShowDocumentNotes">
	<input type="hidden" name="artifactId" id="artifactId" value="${commentListId}">
	<input type="hidden" name="artifactName" id="artifactName" value="${commentListName}">

	<div class="editableContainer">
		<div class="spacer">&nbsp;</div>

	    <script type="text/JavaScript">pasteNewEditableCommentButton();</script>

		<c:forEach var="comment" items="${comments}">

				<div class="messageHeader">
					<span class="label">${comment.creationDate.asFormattedString}</span>
					<span class="formw">
						<c:choose>
							<c:when test="${comment.author!=null && comment.author.partyName!=null}">
								${comment.author.partyName.givenName} ${comment.author.partyName.familyName}
							</c:when>
							<c:otherwise> - </c:otherwise>
						</c:choose>
					</span>
			   	</div>			   	
				<div class="row">
				   	<div class="messageText" id="${comment.divId}">${comment.text}</div>
			   	</div>
		</c:forEach>

		<div class="spacer">&nbsp;</div>		
	</div>
	
</form>
</BODY>
</HTML>