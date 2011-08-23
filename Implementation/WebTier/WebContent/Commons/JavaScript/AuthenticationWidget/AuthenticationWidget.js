// AuthenticationWidget.js

function AuthenticationWidget(widgetId, theController, theUserMenu, authenticatorURI) {
	//parameter assertions
	AssertUtil.assertParamNotNull(widgetId, "widgetId");
	AssertUtil.assertParamNotNull(theController, "theController");
	AssertUtil.assertParamNotNull(authenticatorURI, "authenticatorURI");

	//contants
	var USER_NAME_PARAMETER = "userName";
	var PASSWORD_PARAMETER = "password";

	//private fields
	var widgetDiv = document.getElementById(widgetId);
	AssertUtil.assertParamNotNull(widgetDiv, "widgetDiv");
	var controller = theController;
	var userMenu = theUserMenu;

	var uri = authenticatorURI;
	var userName = null;
	var signedInUserId = null;
	var welcomeText = "";
	var loginFailureText = "";
	var errorText = "";
	var displayedForm = null;

	//pbulic accessor methods
	this.getUser = function () {return userName;}

	//public mutator methods
	this.login = _Login;
	this.remove = _Remove;

	//private methods
	function _GetText(key,defaultValue) {
		return controller.getText(key,defaultValue);
	}

	function _Initialize () {
		welcomeText = "";
		loginFailureText = _GetText("LoginFailure", "Your user name or password is invalid. Please try again.");
		errorText = _GetText("LoginError", "Unexpected error occured. Please try again.");
	}

	function _Remove() {
		if(displayedForm) widgetDiv.removeChild(displayedForm);
		displayedForm = null;
	}

	function _DisplayLoginForm () {
		//setup label and caption texts
		var userNameLabel = _GetText("UserNameLabel", "Username: ");
		var passwordLabel = _GetText("PasswordLabel", "Password: ");
		var loginButtonCaption = _GetText("LoginButtonCaption", "Login");
		var clearButtonCaption = _GetText("ClearButtonCaption", "Clear");

		displayedForm = document.createElement("form");
		displayedForm.setAttribute("name", "login");
		displayedForm.setAttribute("id", "LoginForm");
		displayedForm.setAttribute("method", "post");
		displayedForm.onsubmit = new function () {return _Login;}

		var table = document.createElement("table");
		var tableBody = document.createElement("TBODY");
		table.appendChild(tableBody);

		//create the first row for username input.
		var tableRow = document.createElement("tr");
		var tableCell = document.createElement("td");
		var textNode = document.createTextNode(userNameLabel);
		tableCell.appendChild(textNode);
		tableRow.appendChild(tableCell);

		tableCell = document.createElement("td");
		var userNameElement = document.createElement("input");
		userNameElement.setAttribute("name", "userName");
		tableCell.appendChild(userNameElement);
		tableRow.appendChild(tableCell);

		tableBody.appendChild(tableRow);

		//create the second row for password.
		tableRow = document.createElement("tr");

		tableCell = document.createElement("td");
		var textNode = document.createTextNode(passwordLabel);
		tableCell.appendChild(textNode);
		tableRow.appendChild(tableCell);

		tableCell = document.createElement("td");
		var passwordElement = document.createElement("input");
		passwordElement.setAttribute("name", "password");
		passwordElement.setAttribute("type", "password");
		tableCell.appendChild(passwordElement);
		tableRow.appendChild(tableCell);

		tableBody.appendChild(tableRow);

		//create the third row for buttons.
		tableRow = document.createElement("tr");

		tableCell = document.createElement("td");
		tableRow.appendChild(tableCell);

		tableCell = document.createElement("td");
		var input = document.createElement("input");
		input.setAttribute("name", "login");
		input.setAttribute("type", "submit");
		input.className = "buttonSmall";
		input.setAttribute("value", loginButtonCaption);
		tableCell.appendChild(input);

/*
		input = document.createElement("input");
		input.setAttribute("name", "reset");
		input.setAttribute("type", "reset");
		input.setAttribute("value", clearButtonCaption);
		tableCell.appendChild(input);
*/
		tableRow.appendChild(tableCell);

		tableBody.appendChild(tableRow);

		displayedForm.appendChild(table);
		widgetDiv.appendChild(displayedForm);
	}

	function _DisplayLogoutForm() {
		//setup label and caption texts
		var logoutButtonCaption = _GetText("LogoutButtonCaption", "Login");

		//define form
		displayedForm = document.createElement("form");
		displayedForm.setAttribute("name", "Logout");
		displayedForm.setAttribute("id", "logoutForm");
		displayedForm.onsubmit = new function () {return _Logout;}

		var table = document.createElement("table");
		var tableBody = document.createElement("TBODY");
		table.appendChild(tableBody);

		//create the first row for failure text.
		var tableRow = document.createElement("tr");
		var tableCell = document.createElement("td");

		//display user's name
		var paragraph = document.createElement("p");
		var text = document.createTextNode(welcomeText+" "+ userName);
		paragraph.appendChild(text);
		tableCell.appendChild(paragraph);
		tableRow.appendChild(tableCell);
		tableBody.appendChild(tableRow);

		//create the second row for signout button
		var tableRow = document.createElement("tr");
		var tableCell = document.createElement("td");

		//define submit button for logout
		var button = document.createElement("input");
		button.setAttribute("name", "logout");
		button.setAttribute("type", "submit");
		button.setAttribute("value", logoutButtonCaption);
		button.className = "buttonSmall";
		tableCell.appendChild(button);
		tableRow.appendChild(tableCell);
		tableBody.appendChild(tableRow);

		displayedForm.appendChild(table);

		widgetDiv.appendChild(displayedForm);
	}

	function _DisplayFailureForm() {
		//setup label and caption texts
		var tryAgainButtonCaption = _GetText("TryAgainButtonCaption", "Login");

		//define form
		displayedForm = document.createElement("form");
		displayedForm.setAttribute("name", "Failure");
		displayedForm.setAttribute("id", "failureForm");
		displayedForm.onsubmit = new function () {return _TryAgain;}

		var table = document.createElement("table");
		var tableBody = document.createElement("TBODY");
		table.appendChild(tableBody);

		//create the first row for failure text.
		var tableRow = document.createElement("tr");
		var tableCell = document.createElement("td");

		//display user's name
		var paragraph = document.createElement("p");
		var text = document.createTextNode(loginFailureText);
		paragraph.appendChild(text);
		tableCell.appendChild(paragraph);
		tableRow.appendChild(tableCell);
		tableBody.appendChild(tableRow);

		//create the second row for try again button
		var tableRow = document.createElement("tr");
		var tableCell = document.createElement("td");

		//define submit button for back to login
		var button = document.createElement("input");
		button.setAttribute("name", "tryAgain");
		button.setAttribute("type", "submit");
		button.setAttribute("value", tryAgainButtonCaption);
		button.className = "buttonSmall";
		tableCell.appendChild(button);
		tableRow.appendChild(tableCell);
		tableBody.appendChild(tableRow);

		displayedForm.appendChild(table);

		widgetDiv.appendChild(displayedForm);
	}

	function _DisplayErrorForm() {
		//setup label and caption texts
		var tryAgainButtonCaption = _GetText("TryAgainButtonCaption", "Login");

		//define form
		displayedForm = document.createElement("form");
		displayedForm.setAttribute("name", "Error");
		displayedForm.setAttribute("id", "errorForm");
		displayedForm.onsubmit = new function () {return _TryAgain;}

		var table = document.createElement("table");
		var tableBody = document.createElement("TBODY");
		table.appendChild(tableBody);

		//create the first row for error text.
		var tableRow = document.createElement("tr");
		var tableCell = document.createElement("td");

		//display error
		var paragraph = document.createElement("p");
		var text = document.createTextNode(errorText);
		paragraph.appendChild(text);
		tableCell.appendChild(paragraph);
		tableRow.appendChild(tableCell);
		tableBody.appendChild(tableRow);

		//create the second row for try again button
		var tableRow = document.createElement("tr");
		var tableCell = document.createElement("td");

		//define submit button for back to login
		var button = document.createElement("input");
		button.setAttribute("name", "tryAgain");
		button.setAttribute("type", "submit");
		button.setAttribute("value", tryAgainButtonCaption);
		button.className = "buttonSmall";
		tableCell.appendChild(button);
		tableRow.appendChild(tableCell);
		tableBody.appendChild(tableRow);

		displayedForm.appendChild(table);

		widgetDiv.appendChild(displayedForm);
	}

	function _Login() {
		var replacementsLabel = _GetText("Replacements", "Replacements");
		try {
			var parameters = "";
			parameters = document.forms[0].elements[0].value ? "&" + USER_NAME_PARAMETER + "=" + document.forms[0].elements[0].value : "";
			parameters = document.forms[0].elements[1].value ? parameters + "&" + PASSWORD_PARAMETER + "=" + document.forms[0].elements[1].value : "";
			var xmlResponse = new XmlResource( uri + parameters );
			var loginResponse = xmlResponse.getElementsByTagName("loginResponse");
			if(loginResponse[0].getAttribute("value") == "true") {
				idNode = loginResponse[0].getElementsByTagName("id");
				signedInUserId = idNode[0].firstChild.nodeValue;
				nameNode = loginResponse[0].getElementsByTagName("name");
				userName = nameNode[0].firstChild.nodeValue;
				controller.setUserName(userName);
				locationNode = loginResponse[0].getElementsByTagName("location");
				userLocation = locationNode[0].firstChild.nodeValue;
				controller.setUserLocation(userLocation);
				prefferedLanguageNode = loginResponse[0].getElementsByTagName("prefferedLanguage");
				prefferedLanguage = prefferedLanguageNode[0].firstChild.nodeValue;
				controller.setLanguage(prefferedLanguage);
				controller.changeCaptions();
				var substituted = xmlResponse.getElementsByTagName("substituted");
				if(userMenu != null) {
					for(i=0; i<substituted.length; i++) {
						var id = substituted[i].childNodes[0].text;
						var name = substituted[i].childNodes[1].text;
						userMenu.addCompositMenu("replacements", replacementsLabel, false, 4);//0,1,2,3,4,...
//						var command = new CustomCommand("webUIController.loadInfoPanelDocument(null, controller.getText('ToDoListName')+':"+name+"', '../FrontServlet?command=PersonalToDoListShow&amp;personId="+id.toString()+"');");
						userMenu.addSubMenuToCompositMenu("replacements", "replacement"+i.toString(), name, null /* command */, i);
					}
				}
				_Remove();
				_DisplayLogoutForm();
			} else {
				_Remove();
				_DisplayFailureForm();
				userName = null;
			}
		}
		catch (e) {
			_Remove();
			_DisplayErrorForm();
		}

		return false;
	}

	function _Logout() {
		try {
			var parameters = "&method=logout&userId="+signedInUserId;
			var xmlResponse = new XmlResource( uri + parameters );
			_Remove();
			if(userMenu != null)
				userMenu.removeCompositMenu("replacements");
//			controller.loadInfoPanelDocument('ToDoList', controller.getText('ToDoListName'), '../FrontServlet?command=PersonalToDoListShow');
			_DisplayLoginForm();
			userName = null;
		}
		catch (e) {
			_Remove();
			_DisplayErrorForm();
		}
		return false;
	}

	function _TryAgain() {
		_Remove();
		_DisplayLoginForm();
		userName = null;
		return false;
	}

	_Initialize()
	_DisplayLoginForm();		//start with the login form
}