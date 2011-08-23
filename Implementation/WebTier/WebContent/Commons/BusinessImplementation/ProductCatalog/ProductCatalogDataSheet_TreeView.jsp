<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" href="./JavaScript/TreeWidget/TreeWidget.css" type="text/css">
	<%@ include file="../../PageIncludes/DataFormPageHeader.jsp" %>
	<script language="javascript" src="./JavaScript/ProductCatalogWidget/ProductCatalogWidget.js" ></script>
	
	<title>Product Catalog Base Data</title>
	<script language="javascript">
		var widgetName = null;
		var catalogDataUri = "./CommandControllerServlet?action=RetrieveArtifactData&artifactType=ProductCatalogDataSheet&artifactId=";
		var catalogXsltUri = "./JavaScript/ProductCatalogWidget/TransformProductCatalogToTree.xsl";
		var PICTURE_FOLDER = "./JavaScript/ProductCatalogWidget/";
		var webUiController = null;
		var productCatalogWidget = null;
		var divElementForProductCatalog = document.getElementById("ProductCatalogWidget");
		var applicationConfiguration = null;

		function setUp( catalogId ) {
			widgetName = "ProductCatalogWidget";
			webUiController = parent.browserFrame.webUIController;
			//webUiController.loadResourceBundle( "JavaScript/ProductCatalogWidget/ResourceBundle", new Locale( "hu" ));

			applicationConfiguration = webUiController.getApplicationConfiguration();		
			applicationConfiguration.addProperty( CATALOG_URI_PROPERTY_NAME, catalogDataUri );
			applicationConfiguration.addProperty( CATALOG_TRANSFORMATION_URI_PROPERTY_NAME, catalogXsltUri );
			productCatalogWidget = new ProductCatalogWidget( webUiController, catalogDataUri + catalogId, widgetName );
			productCatalogWidget.setPictureFolder( PICTURE_FOLDER );
			productCatalogWidget.show();
		}
	</script>

</head>
<body onload="javascript:setUp(${artifactView.subjectCatalogDataSheetId});" >
	<div id="ProductCatalogWidget"></div>
	<div id="resultshere"></div>
	<!-- to resolve IE6 cache bug -->
	<div style="display:none;">
		<img src="./JavaScript/ProductCatalogWidget/images/artifact.gif"/>
		<img src="./JavaScript/ProductCatalogWidget/images/artifacts.gif"/>
		<img src="./JavaScript/ProductCatalogWidget/images/catalog.gif"/>
		<img src="./JavaScript/ProductCatalogWidget/images/collver.png"/>
		<img src="./JavaScript/ProductCatalogWidget/images/expver.png"/>
		<img src="./JavaScript/ProductCatalogWidget/images/folder_closed.gif"/>
		<img src="./JavaScript/ProductCatalogWidget/images/folder_closed.gif"/>
		<img src="./JavaScript/ProductCatalogWidget/images/folder_closed.gif"/>
		<img src="./JavaScript/ProductCatalogWidget/images/lastnode.gif"/>
		<img src="./JavaScript/ProductCatalogWidget/images/line.gif"/>
	</div>
</body>
</html>