// DataRetriever.js

function DataRetriever(theCommandName, theArtifactIdentifierName, theArtifactIdentifierValue, theViewName, theQuerySuffix, resourceBundle) {
//private contants
	var SERVLET = "./CommandControllerServlet";
	var COMMAND = "RetrieveDataView";

//private instance variables
	var self = this;
	var commandName = theCommandName == null ? COMMAND : theCommandName;
	var artifactIdentifierName = (theArtifactIdentifierName == null ? "" : theArtifactIdentifierName);
	var artifactIdentifierValue = (theArtifactIdentifierValue == null ? "" : theArtifactIdentifierValue);
	var viewName = (theViewName == null ? "" : theViewName);
	var querySuffix = (theQuerySuffix == null ? "" : theQuerySuffix);
    var http_request = false; 
    var currentResponseValue;
	var i18nResource = resourceBundle;
	var mode = "xml"
	var method = "";
	var parameters = "";

//public accessor methods
	this.getCurrentResponseValue = function() { return currentResponseValue; }
	this.url = _url;

//public mutator methods
	this.makeHttpRequest = _makeHttpRequest;
	this.setMode = function(theMode) { mode = theMode; }
	this.setMethod = function(theMethod) { method = theMethod; }
	this.setParameters = _setParameters;
	this.processDatas = processDatas;

//private methods

	function _setParameters() {
		var items = _setParameters.arguments.length;
		for (i = 0;i < items;i++) {
     parameters += "&par"+i+"="+_setParameters.arguments[i];
   	}
	}
	
	function _url()	{
		return SERVLET+"?action="+commandName+"&"+artifactIdentifierName+"="+artifactIdentifierValue+"&viewName="+viewName+"&mode="+mode+"&method="+method+parameters+""+querySuffix;
	}
	
	function _makeHttpRequest(callback_function) {
//	 	informationDiv();
		http_request = _createRequestObject();

		if (!http_request) {
		 	alert(i18nResource.getText("AjaxNotSupported") ?  i18nResource.getText("AjaxNotSupported") : "Your browser doesn't supports AJAX technology!"); 
		    return false; 
		}
		 
	   	http_request.onreadystatechange = function() { 
			if (http_request.readyState == 4) {
			    switch(http_request.status){
			    case 0: case 200:	// request is good 
			    if(mode == "xml")
			    	currentResponseValue = http_request.responseXML;		    
			    else 
			    if(mode == "text")
			    	currentResponseValue = http_request.responseText;
				if(true /*validateResponse(currentResponseXML)*/) {
					try { callback_function();
					} catch(e) {
						eval(callback_function);
					}
				}
				else {
					var exception = new InvalidXmlResponseException(_url());
					alert(exception.text);
				}
		    	return;
		    	case 404: // File Not Found
			    	alert("File not found: "+_url());            
			    	return;        
			    case 408: case 504:	// request timed out            
			    	alert("Timed out");            
			    	return;        
			    default:			// request error            
		        	alert(i18nResource.getText("AjaxWasProblem") ?  i18nResource.getText("AjaxWasProblem") : "There was a problem under the progress.");
		        	alert("status: "+http_request.status);
			    	return; // you will probably want to exit            
			    } 
		    }
			// continue with the request
//			alert(http_request.readyState);
		 } 
		 http_request.open("GET", _url(), false); 
		 http_request.send(null);
// 		 informationDiv();
	}	
	
	function _createRequestObject () {
		if (window.XMLHttpRequest) {
			// Mozilla, Safari,... 
			var request = new XMLHttpRequest(); 
	    	if (request.overrideMimeType) {
	        	request.overrideMimeType('text/xml'); 
			}
			return request;
		} 
	   	else if (window.ActiveXObject) {
	   		// IE
			var activeXObjects = ['Msxml2.XMLHTTP.6.0', 'Msxml2.XMLHTTP.5.0', 'Msxml2.XMLHTTP.4.0',	'Msxml2.XMLHTTP.3.0', 'Msxml2.XMLHTTP', 'Microsoft.XMLHTTP'];
			for(var i=0; i<activeXObjects.length; i++){
				try {
					return new ActiveXObject(activeXObjects[i]);
				} catch(err){}
			}
		}
	}
	
	function validateResponse(responseXML) {
		var validationResult = false;
		if(responseXML.text != "" && responseXML.xml != "") {
			validationResult = true;
		}		
		return validationResult;
	}

	function processDatas(elementName, tagName) {
		var element = document.getElementsByName(elementName)[0];	
   	for( i=element.length-1;i>=1; i--) {
   		element.remove(i);
   	}
   	var results = currentResponseValue.getElementsByTagName(tagName);
		for(i = 0; i<results.length; i++) {
			var opt = new Option(results[i].text, results[i].attributes[0].text);
			if(results[i].attributes[1] != null) 
				opt.selected = "selected";
			element.add(opt);
		}
	}
	
	function informationDiv() {
		if( document.getElementById("informationDiv") == null ) {
			var informationDiv = document.createElement("div");
			var warningText = i18nResource.getText("WarningTextForAjax") ?  i18nResource.getText("WarningTextForAjax") : "Please wait!";
			informationDiv.setAttribute("id", "informationDiv");
			document.body.insertBefore(informationDiv, document.body.childNodes[0]);
			warningNode = document.createTextNode(warningText);
			informationDiv.appendChild(warningNode);
			document.getElementById("informationDiv").style.visibility = "hidden";
		}
		if( document.getElementById("informationDiv").style.visibility == "hidden" ) {
			document.getElementById("informationDiv").style.visibility = "visible";
		}
		else document.getElementById("informationDiv").style.visibility = "hidden";
	}
}