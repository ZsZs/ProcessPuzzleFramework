<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<HTML>
<HEAD>
	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>

	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<META name="GENERATOR" content="IBM Software Development Platform">

	<TITLE>Szűrés</TITLE>

	<script language="javascript">
	
		var webUiController;
		var dataRetreiver = null;
		var validateMethod = false;
	
		function init() {	
			webUiController = parent.browserFrame.webUIController;
			if("${artifactView.forward}" != null && "${artifactView.forward}" != "") {
				document.getElementsByName("action")[0].value = "ShowListView";
				document.getElementsByName("viewName")[0].value = "${artifactView.forward}";
				document.getElementsByName("query")[0].value = "${artifactView.customQuery}";
				document.forms[0].submit();
			}			
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
			var meth = document.getElementsByName("method")[0];
			meth.value = "buildCustomQuery";
			return true;			
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
		
	</script>

</head>
<body onload="init();">

<br>

	<form name="filterForm" onsubmit="return validateMethod" method="post" action="${artifactView.codeBase}">
		<input type="hidden" name="action" value="UpdateView">
		<input type="hidden" name="artifactId" value="${artifactView.artifactId}">
		<input type="hidden" name="viewName" value="${artifactView.name }">
		<input name="method" type="hidden" value="">
		<input name="query" type="hidden" value="">
		<input name="count" type="hidden" value="1000">
		<input name="forward" type="hidden" value="ProductTypeList_ListView">

<!--  -->
		
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

		<div id="predefined" class="editableContainer" style="display: none">			

			<br><br>
			
			<div class="row">
				<c:set property="key" value="ui.filter.name" target="${i18Helper}"></c:set>
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

			<div class="row">
				<c:set property="key" value="ui.filter.execute" target="${i18Helper}"></c:set>
				<input type="submit" class="buttonSmall" value="${i18Helper.value}" style="margin-left: 150px;" onclick="validateMethod = submitPredefinedQuery('${artifactView.parentArtifact.listView.name}')">
			</div>

		</div>
		
		<br><br><br>
		
		<div id="custom" class="editableContainer" style="display: none">
			<div class="row">
				<span class="label">
					<c:set property="key" value="ui.label.identifier" target="${i18Helper}"></c:set>
					${i18Helper.value}:
				</span>
				<span class="formw"><input type="text" id="identifierEdit" value="${artifactView.identifier}" name="identifier"></span>
			</div>
		
			<div class="row">
				<span class="label">
					<c:set property="key" value="ui.label.name" target="${i18Helper}"></c:set>
					${i18Helper.value}:
				</span>
				<span class="formw"><input type="text" id="productTypeNameEdit" value="${artifactView.productTypeName}" name="productTypeName"></span>
			</div>
			
			<div class="row">
				<span class="label">
					<c:set property="key" value="ui.label.manufacturerCode" target="${i18Helper}"></c:set>
					${i18Helper.value}:
				</span>
				<span class="formw"><input type="text" id="manufacturerCodeEdit" value="${artifactView.producerCode}" name="manufacturerCode"></span>
			</div>
			
			<div class="row">
				<span class="label">
					<c:set property="key" value="ui.label.EAN" target="${i18Helper}"></c:set>
					${i18Helper.value}:
				</span>
				<span class="formw"><input type="text" id="eanCodeEdit" value="${artifactView.EANCode}" name="eanCode"></span>
			</div>

			
			<br><br>
			
			<div>
				<c:set property="key" value="ui.filter.execute" target="${i18Helper}"></c:set>
				<input type="submit" class="buttonSmall" style="margin-left: 150px;" value="${i18Helper.value}" onclick="validateMethod = submitCustomQuery('${artifactView.parentArtifact.listView.name}');">		
			</div>
		
		</div>
	</form>
</body>
</html>