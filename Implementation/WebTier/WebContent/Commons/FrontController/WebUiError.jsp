<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="../PageIncludes/SimpleContentPageHeader.jsp" %>
	<title>Browser Interface Error</title>

	<script type="text/javascript">
		function buildExceptionText() {
			var exception = top.BrowserInterfaceException;
			
			var errorDiv = document.getElementById( "Error" );
			var errorText = exception != null ? exception.name : "Unknown";
			var errorTextNode = document.createTextNode( errorText );
			errorDiv.appendChild( errorTextNode );

			var descriptionDiv = document.getElementById( "Description" );
			var descriptionText = exception != null ? exception.description : "Unknown";
			var descriptionTextNode = document.createTextNode( descriptionText );
			descriptionDiv.appendChild( descriptionTextNode );
		}
	</script>
</head>

<body onload = buildExceptionText(); >
	<h1>Browser Interface Error</h1>
	<br><br><br>
	<p><b>Error:</b> <div id=Error></div>
	<p><b>Description:</b> <div id=Description></div>
</body>
</html>