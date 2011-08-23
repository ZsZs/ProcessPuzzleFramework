// GenericBrowser.js

function GenericBrowser() {
	//Private variables
	var browserType;
	var browserLanguage;
	
	//Public accessors
	this.getType = function () {return browserType;}
	this.getLanguage = function () {return browserLanguage;}
	
	//Private methods
	function _DetermineBrowserType () {
		if (navigator.appName.toUpperCase().match(/MICROSOFT INTERNET EXPLORER/) != null)
			browserType = "IE";
		if (navigator.appName.toUpperCase().match(/NETSCAPE/) != null)
	  		browserType = "NS";
	}
	
	function _DetermineBrowserLanguage () {
		switch (browserType) {
		case "IE":
			browserLanguage = navigator.browserLanguage;
			break;
		case "NS":
			browserLanguage = navigator.language;
		}
	}

	_DetermineBrowserType ();
	_DetermineBrowserLanguage ();
}

GenericBrowser = new GenericBrowser();