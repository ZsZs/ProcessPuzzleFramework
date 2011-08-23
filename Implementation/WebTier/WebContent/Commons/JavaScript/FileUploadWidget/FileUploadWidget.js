	
	function FileUploadWidget(widgetId) {
	
		var formwSpan = document.getElementById(widgetId);
		
		this.show = _Show;
	
		function _ConstructUploadFileDivision() {	
		
			
			if(document.getElementsByName("uploadFormName")[0] == undefined) {
			
				var hUploadFormName = document.createElement("input");
				hUploadFormName.setAttribute("type", "hidden");
				hUploadFormName.setAttribute("name", "uploadFormName");
				hUploadFormName.setAttribute("id", "uploadFormName");
			
				document.forms[0].appendChild(hUploadFormName);
			
			}
			
			if(document.getElementsByName("docType")[0] == undefined) {
			
				var hdocType = document.createElement("input");
				hdocType.setAttribute("type", "hidden");
				hdocType.setAttribute("name", "docType");
				hdocType.setAttribute("id", "docType");
			
				document.forms[0].appendChild(hdocType);
			
			}
			
			//-Upload-
			var fileElement = document.createElement("input");
			fileElement.className ="upload";
			fileElement.setAttribute("type", "file");
			fileElement.setAttribute("name", "fileToUpload"+widgetId);
			fileElement.setAttribute("id", "fileToUpload"+widgetId);
			formwSpan.appendChild(fileElement);
	
			var textElement = document.createElement("input");
			textElement.setAttribute("type", "text");
			textElement.className = "fakeText";
			textElement.setAttribute("name", "fakeFileName"+widgetId);
			textElement.setAttribute("id", "fakeFileName"+widgetId);
			formwSpan.appendChild(textElement);
	
			var browseButtonValue = "Browse";
	
			fileElement.onmouseout = _FillFakeText;
	
			var fakeBrowseButton = document.createElement("input");
			fakeBrowseButton.className = "fakeBrowse";
			fakeBrowseButton.setAttribute("type", "button");		
			fakeBrowseButton.setAttribute("name", "fakeBrowseButton"+widgetId);
			fakeBrowseButton.setAttribute("value", browseButtonValue);
			formwSpan.appendChild(fakeBrowseButton);
	
			var addButton = document.createElement("input");
			addButton.className = "buttonUpload";
			addButton.setAttribute("name", "addFile"+widgetId);
			addButton.setAttribute("type", "submit");
			var addButtonValue = "Upload";
			addButton.setAttribute("value", addButtonValue);
			addButton.onclick = new function () {return _ValidateUploadButton;}
			formwSpan.appendChild(addButton);
			
		}
		
		function _Show() {
			_ConstructUploadFileDivision();
		}
		
		function _FillFakeText() {
			document.getElementById("fakeFileName"+widgetId).value=document.getElementById("fileToUpload"+widgetId).value;
		}
		
		function _ValidateUploadButton() {
			document.getElementsByName("action")[0].value = "AddRelatedArtifact";
			document.getElementsByName("uploadFormName")[0].value = "fileToUpload"+widgetId;
			document.getElementsByName("docType")[0].value = "Document"
		
		}
	}