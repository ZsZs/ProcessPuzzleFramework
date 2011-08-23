function progressBar(parentDivId, width, height, backGroundColor, borderWidth, borderColor, lineColor )
{
	var PROGRESS_BAR_WIDGET_ID = "progressBarWidget";
	var BLOCKS_ID = "blocks";
	var PROGRESS_LINE_ID = "progressLine";
	var _pDivId = parentDivId;
	var _width = width;
	var _height =  height;
	var _backGroundColor = backGroundColor;
	var _borderWidth = borderWidth;
	var _borderColor = borderColor;
	var _lineColor = lineColor;
	var _progressLine;
	var _step;
	var _pbWidget;
	var _infoLine;
	
	var parentDiv = document.getElementById(_pDivId);
	if( parentDiv )
	{
		var infoLine = document.createElement('div');
		infoLine.setAttribute("name","infoLine");
		infoLine.setAttribute("id","infoLine");
		infoLine.setAttribute("innerHTML","...");
		infoLine.style.fontWeight = "normal";
		parentDiv.appendChild(infoLine);		
		_infoLine = infoLine;
		
		var pbWidget = document.createElement('div');	
		pbWidget.setAttribute('id', PROGRESS_BAR_WIDGET_ID);
		pbWidget.style.backgroundColor = "white"; 
		pbWidget.style.position = 'relative';
		pbWidget.style.overflow = 'hidden';
		pbWidget.style.width = '300px'; 
		pbWidget.style.height = '15px';
		pbWidget.style.borderColor = 'gray'; 
		pbWidget.style.borderWidth = '1px'; 
		pbWidget.style.borderStyle = 'solid'; 
		pbWidget.style.fontSize = '1px';
		parentDiv.appendChild(pbWidget);

		var blocks = document.createElement('span');
		blocks.setAttribute("id", BLOCKS_ID);
		blocks.style.left = "42px"; 
		blocks.style.position = "absolute"; 
		blocks.style.fontSize = "1px";
		pbWidget.appendChild(blocks);
		
		var progressLine = document.createElement('span');
		progressLine.setAttribute("id", PROGRESS_LINE_ID);
		progressLine.style.backgroundColor = 'blue';
		progressLine.style.left = "-42px"; 
		progressLine.style.fontSize = "1px"; 
		progressLine.style.position = "absolute";
		progressLine.style.width = "0px"; 
		progressLine.style.height = "20px";
		blocks.appendChild(progressLine);
		_progressLine = progressLine;
		_pbWidget = pbWidget;

		var step = document.createElement('input');
		step.setAttribute("type", "hidden");
		step.setAttribute("name","stepInterval");
		step.setAttribute("id","stepInterval");
		step.setAttribute("value","1");
		parentDiv.appendChild(step);		
		_step = step;

		var stepButton = document.createElement('input');
		stepButton.setAttribute("name","stepButton");
		stepButton.setAttribute("id","stepButton");
		stepButton.setAttribute("type", "Button");
		stepButton.setAttribute("value","Step");
		stepButton.onclick = function() {_execute();};
		stepButton.style.display = "none";
		parentDiv.appendChild(stepButton);		

		var resetButton = document.createElement('input');
		resetButton.setAttribute("type", "Button");
		resetButton.setAttribute("id","resetButton");
		resetButton.setAttribute("name","resetButton");
		resetButton.setAttribute("value","Reset");
		resetButton.onclick = function() {_reset();};
		resetButton.style.display = "none";
		parentDiv.appendChild(resetButton);		

		var showOrHideButton = document.createElement('input');
		showOrHideButton.setAttribute("type", "Button");
		showOrHideButton.setAttribute("id","showHideButton");
		showOrHideButton.setAttribute("name","showHideButton");
		showOrHideButton.setAttribute("value","ShowOrHide");
		showOrHideButton.onclick = function() {_reset();};
		showOrHideButton.style.display = "none";
		parentDiv.appendChild(showOrHideButton);		

	}
	else
	{
		alert("Wrong parent div id!");
	} 

	this.execute = _execute;
	this.reset = _reset;
	
	function _execute()
	{
		if( _pbWidget )
		{
			var line = _progressLine.style.width;
			width = (parseInt(line.substring(0, line.length - 2)) + parseInt(_step.value)).toString()+"px";
			_progressLine.style.width = width;
		}
	}

	function _reset()
	{
		if( _pbWidget )
		{
			_progressLine.style.width = "0px";
			_infoLine.innerHTML = "...";
		}			
	}

}