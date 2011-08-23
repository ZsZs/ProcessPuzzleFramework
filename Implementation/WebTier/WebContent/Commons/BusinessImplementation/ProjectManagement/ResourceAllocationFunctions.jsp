<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

var dataRetreiver = null;
var validateMethod = validate();

function validate() {
	return false;
}
	
function getGeneralResourceAllocationData(rId) {
	dataRetriever = new DataRetriever(null, "artifactId", ${artifactView.parentArtifact.id}, "ActionDataSheet_GeneralResourceAllocations", null,parent.browserFrame.webUIController.getResourceBundle());
	dataRetriever.setMethod("getGeneralResourceAllocation");
	dataRetriever.setParameters(rId);
	dataRetriever.makeHttpRequest("fillForm();");
	dataRetriever.processDatas('performerRole', 'partyRoleType');		
	dataRetriever.processDatas('unit', 'unit');	
}

function fillForm() {
	var responseXml = dataRetriever.getCurrentResponseValue();
	document.getElementsByName("quantity")[0].value = responseXml.getElementsByTagName("quantity")[0].text;
	document.getElementsByName("method")[0].value = "saveGra";
	document.getElementById("add").value = "Mentés";
}
		
function deSelectTableRows() {
	var rbGas = document.getElementsByName("rbGas");
	for (i = 0; i < rbGas.length; i++) {
		if(rbGas[i].checked)  {
			rbGas[i].checked = false;
		}
	document.getElementsByName("graId")[0].value = "";
	}
	
	document.getElementsByName("performerRole")[0].selectedIndex = 0;
	document.getElementsByName("quantity")[0].value = "";
	document.getElementsByName("unit")[0].selectedIndex = 0;
	document.getElementById("add").value = "Hozzáad";			
}
		
		