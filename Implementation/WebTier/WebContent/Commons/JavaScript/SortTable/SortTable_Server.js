//SortTable_Server

function SortTable_Server() {
	
	//private fields
	var hOrderBy = null;
	var hOrder = null;
	var orderBy = null;
	var order = null;
	
	this.init = _ConstructElements;
	this.setOrderBy = function( theOrderBy ) { orderBy = theOrderBy; }
	this.setOrder = function( theOrder ) { order = theOrder; }
	
	function _ConstructElements() {
		hOrderBy = document.createElement("input");
		hOrderBy.setAttribute("type", "hidden");
		hOrderBy.setAttribute("name", "orderBy");
		hOrderBy.setAttribute("id", "orderBy");
		hOrderBy.setAttribute("value", orderBy);
		
		hOrder = document.createElement("input");
		hOrder.setAttribute("type", "hidden");
		hOrder.setAttribute("name", "order");
		hOrder.setAttribute("id", "order");
		hOrder.setAttribute("value", order);
		
		document.forms[0].appendChild(hOrderBy);
		document.forms[0].appendChild(hOrder);
	}
	
	this.sortBy = function( str ) {
	
		var ord = null;
		
		if(order == "asc")
			ord = "desc";
		else
			ord = "asc";
	
		document.getElementsByName("orderBy")[0].value = str;
		document.getElementsByName("order")[0].value = ord;
		
		document.forms[0].submit();
	}
}