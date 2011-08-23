<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="./${skinDescriptor.stylesPath}/base.css" type="text/css">
<link rel="stylesheet" href="./${skinDescriptor.stylesPath}/frameInfo.css" type="text/css">
<link rel="stylesheet" href="./${skinDescriptor.stylesPath}/form_styles.css" type="text/css">

<script language="JavaScript" src="./JavaScript/BasicValidation/BasicValidation.js" ></script>
<script language="JavaScript" src="./JavaScript/CommonScripts/inheritFrom.js"></script>
<script language="JavaScript" src="./JavaScript/CommonScripts/HashMap.js"></script>
<script language="JavaScript" src="./JavaScript/CommonScripts/ArrayList.js"></script>
<script language="JavaScript" src="./JavaScript/CommonScripts/StringBuffer.js"></script>
<script language="JavaScript" src="./JavaScript/CommonScripts/StringUtil.js"></script>
<script language="JavaScript" src="./JavaScript/CommonScripts/Configuration.js"></script>
<script language="JavaScript" src="./JavaScript/CommonScripts/Collection.js"></script>
<script language="JavaScript" src="./JavaScript/CommonScripts/UserException.js"></script>
<script language="JavaScript" src="./JavaScript/CommonScripts/GenericBrowser.js"></script>
<script language="JavaScript" src="./JavaScript/CommonScripts/AssertUtil.js"></script>
<script language="JavaScript" src="./JavaScript/CommonScripts/XmlResource.js"></script>
<script language="JavaScript" src="./JavaScript/DataRetriever/DataRetriever.js"></script>
<script language="JavaScript" src="./JavaScript/DocumentReservationWidget/DocumentReservationWidget.js"></script>
<script language="JavaScript" src="./JavaScript/Internalization/Locale.js"></script>
<script language="JavaScript" src="./JavaScript/Internalization/LocaleUtil.js"></script>
<script language="JavaScript" src="./JavaScript/Internalization/ResourceKey.js"></script>
<script language="JavaScript" src="./JavaScript/Internalization/ResourceCache.js"></script>
<script language="JavaScript" src="./JavaScript/Internalization/XMLBundleParser.js"></script>
<script language="JavaScript" src="./JavaScript/Internalization/XMLResourceBundle.js"></script>
<script language="JavaScript" src="./JavaScript/Log4JavaScript/log4javascript.js"></script>
<script language="javascript" src="./JavaScript/ModalWindow/ModalWindowBase.js"></script>
<script language="javascript" src="./JavaScript/PageingWidget/PageingWidget.js"></script>
<script language="javascript" src="./JavaScript/ProgressBar/progressBar.js"></script>
<script language="JavaScript" src="./JavaScript/XMLforScript/xmlsax.js"></script>
<script language="JavaScript" src="./JavaScript/XMLforScript/preMadeSaxEventHandler.js"></script>
<script language="JavaScript" src="./JavaScript/SortTable/SortTable.js"></script>
<script language="javascript" src="./JavaScript/WebUIController/WebUIController.js"></script>
<script language="javascript" src="./JavaScript/WebUIController/LoadUtility.js" ></script>	
<script language="javascript" src="./JavaScript/WebUiCommands/WebUiCommand.js"></script>
<script language="javascript" src="./JavaScript/WebUiCommands/CreateNewCommissionCommand.js"></script>

<script type="text/javascript">
		var webUiController = parent.browserFrame.webUIController;
		
		function goToDataSheetByLink(artifactName, artifactId){
			webUiController.loadDocument('ActionDataSheet', artifactName, '&artifactId='+artifactId);
		}
</script>

	