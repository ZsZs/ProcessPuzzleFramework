//PageingWidget

function PageingWidget( widgetId, resourceBundle ) {
	AssertUtil.assertParamNotNull(widgetId, "widgetId");
	AssertUtil.assertParamNotNull(resourceBundle, "resourceBundle");
	
	//private fields
	var containerElement = document.getElementById(widgetId);
	AssertUtil.assertParamNotNull(containerElement, "containerElement");	
	var i18Resource = resourceBundle;
	var hCount = null;
	var hStart = null;
	var previousButton = null;
	var pageingSelect = null;
	var nextButton = null;
	var currentStart = 0;
	var maxReturnSize = 25;
	var rowCount = 0;
	
	// inherit from BrowserWidget
	var parent = new BrowserWidget(document, containerElement, i18Resource );
  	inheritFrom(this, parent);

	this.show = _ConstructElements;
	this.setCurrentStart = function( theStart ) { currentStart = theStart; }
	this.setMaxReturnSize = function( theSize ) { maxReturnSize = theSize; }
	this.setRowCount = function( theCount ) { rowCount = theCount; }

	function _ConstructElements() {
	
		hCount = parent.createElement("input");
		hCount.setAttribute("type", "hidden");
		hCount.setAttribute("name", "count");
		hCount.setAttribute("id", "count");
		hCount.setAttribute("value", maxReturnSize);
				
		hStart = parent.createElement("input");
		hStart.setAttribute("type", "hidden");
		hStart.setAttribute("name", "start");
		hStart.setAttribute("value", currentStart);
	
		previousButton = parent.createButton( "<<", _Previous );
		previousButton.setAttribute("id", "previousButton");
		previousButton.disabled = false;
		if(currentStart == 0) { previousButton.disabled = true; }
		
		pageingSelect = parent.createElement("select");
		pageingSelect.setAttribute("id", "pageingSelect");
		pageingSelect.style.marginLeft = "10px"
		pageingSelect.style.marginRight = "10px"
		pageingSelect.onchange = new function () {return _ChangeCount;}
		
		var countNums = new Array("25", "50", "100", "250", "500", "1000", "10000");
		for(var i = 0; i < countNums.length; i++) {
			var countNumOption = document.createElement("option");
			countNumOption.setAttribute("value", countNums[i]);
			countNumOption.appendChild(document.createTextNode(countNums[i]))
			if(maxReturnSize == parseInt(countNums[i])) {
					countNumOption.selected = "selected";
			}
			pageingSelect.appendChild(countNumOption);
		}
		
		nextButton = parent.createButton( ">>", _Next );
		nextButton.setAttribute("id", "nextButton");
		nextButton.disabled = false;
		
		if(rowCount < currentStart+maxReturnSize) {
			nextButton.disabled = true;
		}
		
		containerElement.appendChild(hCount);
		containerElement.appendChild(hStart);
		containerElement.appendChild(previousButton);
		containerElement.appendChild(pageingSelect);
		containerElement.appendChild(nextButton);
	}
	
	function _Previous() {
		var selectedValue = pageingSelect.options[pageingSelect.selectedIndex].value
		hCount.value = selectedValue;
		hStart.value = eval(parseInt(currentStart)+"-"+parseInt(selectedValue));
		document.forms[0].submit();
	}
	
	function _Next() {
		var selectedValue = pageingSelect.options[pageingSelect.selectedIndex].value
		hCount.value = selectedValue;
		hStart.value = eval(parseInt(currentStart)+"+"+parseInt(selectedValue));
		document.forms[0].submit();
	}
	
	function _ChangeCount() {
		var selectedValue = pageingSelect.options[pageingSelect.selectedIndex].value
		hCount.value = selectedValue;
		document.forms[0].submit();
	}
}