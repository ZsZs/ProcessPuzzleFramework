<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<HTML>
<HEAD>
	<link rel="stylesheet" href="./styles/base.css" type="text/css">
	<link rel="stylesheet" href="./styles/content.css" type="text/css">
	<link rel="stylesheet" href="./styles/form_styles.css" type="text/css">

	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<META name="GENERATOR" content="IBM Software Development Platform">

	<TITLE>Szűrés</TITLE>

	<script language="javascript" src="./JavaScript/BasicValidation/BasicValidation.js" ></script>
	<script language="javascript" src="./JavaScript/DataRetriever/DataRetriever.js" ></script>
	<script language="javascript" src="./JavaScript/DataRetriever/InvalidXmlResponseException.js" ></script>

	<script language="javascript">
	
		var dataRetreiver = null;
		var validateMethod = false;
		var queryPropertiesNames = new Array();
		var queryPropertiesDescs = new Array();
		
		<c:forEach var="queryProperty" items="${artifactView.queryProperties}" varStatus="index">						
					queryPropertiesNames[${index.count}] = '${queryProperty.name}';
					queryPropertiesDescs[${index.count}] = '${queryProperty.description}';
		</c:forEach>
	
		
		function init() {				
			var rbQuery = document.getElementsByName("rbQuery");
			rbQuery[0].click();
		}
		
		function getQueryDetails() {
			dataRetriever = new DataRetriever(null, "artifactId", "${artifactView.artifactId}", "${artifactView.name}", null, parent.browserFrame.webUIController.getResourceBundle());
			dataRetriever.makeHttpRequest("showPredefinedQuery();");
		}
		
		function submitPredefinedQuery(goTo) {
			var index = document.getElementsByName("predefinedFilterSelect")[0].selectedIndex;
			if(index < 0) {
				alert("Válasszon szűrőfeltételt!");
				return false;
			}
			var q = document.getElementsByName("query")[0];
			var viewName = document.getElementsByName("viewName")[0];
			viewName.value = goTo;
			return true;	
		}
		
		function submitCustomQuery(goTo){
			var queryArea = document.getElementsByName("txtStatement2")[0];		
			if (queryArea.value == ""){
				alert("Figyelem! \n\n"+"Nincs összeállított lekérdezés!");
				return false;
			}
			var q = document.getElementsByName("query")[0];
			var viewName = document.getElementsByName("viewName")[0];
			viewName.value = goTo;
			q.value = document.getElementsByName("txtStatement2")[0].value;
			return true;			
		}
		
		function addQueryRow(propertyNames) {
			
			var queryTable = document.getElementById("queryTable");
			var tBody = queryTable.getElementsByTagName("tbody")[0];
		
			var tableRow = document.createElement("tr");
			
			var propertyNameCell = document.createElement("td");
			var propertyNameSelect = document.createElement("select");
			propertyNameSelect.setAttribute("id", "propertyName");
			propertyNameSelect.setAttribute("name", "propertyName");
			
			var emptyPropertyNameOption = document.createElement("option");
				emptyPropertyNameOption.setAttribute("value", "-1");
				emptyPropertyNameOption.appendChild(document.createTextNode("---"))
				propertyNameSelect.appendChild(emptyPropertyNameOption);
			
			for(var i = 1; i < queryPropertiesNames.length; i++) {
				var propertyNameOption = document.createElement("option");
				propertyNameOption.setAttribute("value", queryPropertiesNames[i]);
				propertyNameOption.appendChild(document.createTextNode(queryPropertiesDescs[i]))
				propertyNameSelect.appendChild(propertyNameOption);
			}
			
			propertyNameCell.appendChild(propertyNameSelect);
			tableRow.appendChild(propertyNameCell);
			
			var operatorCell = document.createElement("td");
			var operatorSelect = document.createElement("select");
			operatorSelect.setAttribute("id", "operator");
			operatorSelect.setAttribute("name", "operator");
			
			var operators = new Array("=", "<", ">", "<=", ">=");
			
			for(var i = 0; i < operators.length; i++) {
				var operatorOption = document.createElement("option");
				operatorOption.setAttribute("value", operators[i]);
				operatorOption.appendChild(document.createTextNode(operators[i]));
				operatorSelect.appendChild(operatorOption);
			}
			
			operatorCell.appendChild(operatorSelect);
			tableRow.appendChild(operatorCell);
			
			var valueCell = document.createElement("td");
			var valueText = document.createElement("input");
			valueText.setAttribute("type", "text");		
			valueText.setAttribute("name", "valueTxt");
			valueText.setAttribute("id", "valueTxt");
			valueText.setAttribute("value", "");
			valueCell.appendChild(valueText);
			tableRow.appendChild(valueCell);
			
			tBody.appendChild(tableRow);
		}
		
		function choose(tmp){		
			var rbQuery = document.getElementsByName("rbQuery");				
			if (tmp == 0) {
				document.getElementById("predefined").style.display="block";
				document.getElementById("custom").style.display="none";
			}
			else if(tmp == 1) {
				document.getElementById("predefined").style.display="none";
				document.getElementById("custom").style.display="block";
			}
		}
		
		function showPredefinedQuery() {
			responseXml = dataRetriever.getCurrentResponseValue();
			
			var selectedValue = document.getElementsByName("predefinedFilterSelect")[0].options[document.getElementsByName("predefinedFilterSelect")[0].selectedIndex].value;			
			var queryStatementElement = document.getElementsByName("txtStatement")[0];
						
			var q = document.getElementsByName("query")[0];			
			var queries = responseXml.getElementsByTagName("query")			
			var targetQuery = null;
			
			for(var i = 0; i<queries.length; i++) {
				var query = queries[i];
				var id = query.getAttribute("id");
				if(id == selectedValue) {
					targetQuery = query;
					break;
				}
			}
			
			if( targetQuery != null ) {
				queryStatementElement.value = targetQuery.childNodes[1].text;
				q.value = targetQuery.childNodes[1].text;
			}
			else {
				queryStatementElement.value = "";
				q.value = "";
			}
		}
		
		function compileOQL() {
			var prefix = "from ${artifactView.targetArtifact} o where ";
			var propertyNames = document.getElementsByName("propertyName");
			var operators = document.getElementsByName("operator");
			var valueTexts = document.getElementsByName("valueText");			
			
			var queryString = prefix;
			
			for (i = 0; i < propertyNames.length; i++) {			
				var propertyName = document.getElementsByName("propertyName")[i].options[document.getElementsByName("propertyName")[i].selectedIndex].value;			
				var operator = document.getElementsByName("operator")[i].options[document.getElementsByName("operator")[i].selectedIndex].value;
				var valueTxt = document.getElementsByName("valueTxt")[i].value;
							
				queryString += propertyName + " " + operator + " '" + valueTxt + "' " ;
				
				if(i != propertyNames.length-1)
					queryString += "and ";
			}
			
			document.getElementsByName("txtStatement2")[0].value = queryString;
		}
		
	</script>

</head>
<body onload="init();">

<br>

	<form name="filterForm" onsubmit="return validateMethod" method="post" action="${artifactView.codeBase}">
		<input name="action" type="hidden" value="ShowListView">
		<input name="artifactName" type="hidden" value="${artifactView.parentArtifact.name}">
		<input name="viewName" type="hidden" value="${artifactView.name}">
		<input name="query" type="hidden" value="">
		
		<div class="editableContainer">	
	
			<div class="row">
				<c:set property="key" value="ui.filter.predefined" target="${i18Helper}"></c:set>
				<span id="predefinedFilterText" class="label">
				${i18Helper.value}
				</span>	
				<span id="predefinedSpan" class="formw"><input name="rbQuery" style="margin-left: 5px;" type="radio" value="0" onclick="choose(0);"></span>
			</div>
	
			<br>
	
			<div class="row">
				<span id="predefinedFilterText" class="label">
				<c:set property="key" value="ui.filter.custom" target="${i18Helper}"></c:set>
				${i18Helper.value}
				</span>
				<span id="customSpan" class="formw"><input name="rbQuery" style="margin-left: 5px;" type="radio" value="1" onclick="choose(1);"></span>
			</div>
		</div>

		<div id="predefined" class="editableContainer">			

			<br><br>
			
			<div class="row">
				<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}: </span>		
				<span class="formw">
					<select id="predefinedFilterSelect" name="predefinedFilterSelect" onchange="getQueryDetails();">
						<option value="-1">---</option>
						<c:forEach var="query" items="${artifactView.preDefinedQueries}">
							<c:set property="key" value="${query.value.name}" target="${i18Helper}"></c:set>
						
							<option value="${query.value.id}">${i18Helper.value}</option>
						</c:forEach>
					</select>
				</span>
			</div>
			
			<div class="row">
				<c:set property="key" value="ui.filter.query" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}: </span>
				<span class="formw"><textarea name="txtStatement" rows="5" cols="70" readonly="readonly"></textarea>
				</span>
			</div>
			
			<br>

			<c:set property="key" value="ui.filter.execute" target="${i18Helper}"></c:set>
			<input type="submit" class="buttonSmall" value="${i18Helper.value}" style="margin-left: 150px;" onclick="validateMethod = submitPredefinedQuery('${artifactView.parentArtifact.listView.name}')">

		</div>
		
		<div id="custom" class="editableContainer">

			<br><br>
		
			<div class="row">				
				<table id="queryTable" style="margin-left: 150px;">
					<thead>
						<tr>
							<c:set property="key" value="ui.filter.field" target="${i18Helper}"></c:set>
							<th>${i18Helper.value}</th>
							<c:set property="key" value="ui.filter.operator" target="${i18Helper}"></c:set>
							<th>${i18Helper.value}</th>
							<c:set property="key" value="ui.filter.value" target="${i18Helper}"></c:set>
							<th>${i18Helper.value}</th>					
						</tr>
						
					</thead>
					<tbody>
						<tr>
							<td>
								<select id="propertyName" name="propertyName"">
								<option value="-1">---</option>
								<c:forEach var="queryProperty" items="${artifactView.queryProperties}" varStatus="index">						
									<option value="${queryProperty.name}" 
									>${queryProperty.description}
									</option>
								</c:forEach>
								</select>
							</td>
							<td>
								<select id="operatorSelect" name="operator">
									<option value="&#61;">&#61;</option>
									<option value="&#60;">&#60;</option>
									<option value="&#62;">&#62;</option>
									<option value="&#60;&#61;">&#60;&#61;</option>
									<option value="&#62;&#61;">&#62;&#61;</option>
								</select>
							</td>
							<td><input type="text" id="valueTxt" name="valueTxt" value=""></td>
						</tr>
					</tbody>
				</table>
				
				<c:set property="key" value="ui.filter.and" target="${i18Helper}"></c:set>
				<input type="button" class="buttonSmall" style="margin-left: 150px;" value="${i18Helper.value}" onclick="addQueryRow();">
				<br><br>
				<c:set property="key" value="ui.filter.compile" target="${i18Helper}"></c:set>
				<input type="button" class="buttonLarge" style="margin-left: 150px;" value="${i18Helper.value}" onclick="compileOQL();">

			</div>
			
			<br>
			
			<div class="row">
				<c:set property="key" value="ui.filter.query" target="${i18Helper}"></c:set>
				<span class="label">${i18Helper.value}: </span>
				<span class="formw"><textarea name="txtStatement2" rows="5" cols="70" readonly="readonly"></textarea></span>
			</div>
			
			<br>
			
				<c:set property="key" value="ui.filter.execute" target="${i18Helper}"></c:set>
				<input type="submit" class="buttonSmall" value="${i18Helper.value}" style="margin-left: 150px;" onclick="validateMethod = submitCustomQuery('${artifactView.parentArtifact.listView.name}');">		

		</div>
	</form>
</body>
</html>