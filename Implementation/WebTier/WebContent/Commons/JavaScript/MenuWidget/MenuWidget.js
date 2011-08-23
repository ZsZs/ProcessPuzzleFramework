// MenuWidget.js

function MenuWidget(theDivElement) {
	// inherit from CSimpleObservable to make this object an Observable
  	inheritFrom(this, new CSimpleObservable());

	//private contants
	var DL = "dl";
	var DLCLASSNAME = "dlcn";

	//check parameter assertions
	if(theDivElement == null || theDivElement == "") throw new InvalidParameterException();		//theDiv can't be "" or null

	//private instance variables
	var logger = null;
	var isVisible = false;
	var htmlDivElement = theDivElement;
	var definitionListElement = null; //DOM.element
	var menuItems = new Collection();
	var expandedItem = null;
	var self = this;

	//public accessors methods
	this.findMenuByName = function(menuName) {return menuItems.item(menuName);}
	this.getCountOfItems = function() {return menuItems.getCountOfObjects();}
	this.getDefinitionListElement = function() {return definitionListElement;}
	this.getContainerElement = function() { return htmlDivElement; }
	this.isVisible = function() {return isVisible;}

	//public mutators methods
	this.addCompositMenu = _AddCompositMenu;
	this.addDualStateMenu = _AddDualStateMenu;
	this.addDualStateMenuToCompositMenu = _AddDualStateMenuToCompositMenu;
	this.addSubMenu = _AddSubMenu;
	this.addSubMenuToCompositMenu = _AddSubMenuToCompositMenu;
	this.changeCaptions = _ChangeCaptions;
	this.exists = _Exists;
	this.hide = _Hide;
	this.observe = _OnMenuItemClick;
	this.removeCompositMenu = _RemoveCompositMenu;
	this.removeMenuItemFromCompositMenu = _RemoveMenuItemFromCompositMenu;
	this.removeSubMenu = _RemoveSubMenu;
	this.removeDualStateMenu = _RemoveDualStateMenu;
	this.removeAllMenus = _RemoveAllMenus;
	this.show = _Show;

	_Constructor();
	
	//Constructor methods
	function _Constructor() {
		logger = log4javascript.getLogger( ROOT_LOGGER_NAME + ".menuWidget" );
		logger.debug( "Menu Widget instantiated." );
	}
			
	//private methods
	function _OnMenuItemClick( menuItem ) {
		logger.trace( "Click on menu item:", menuItem );
		
		if( menuItem ) {
			if( expandedItem == menuItem && menuItem.callopseSubMenus ) {
				menuItem.callopseSubMenus()
				expandedItem = null;
			}
			else {
				if( menuItem.expandSubMenus ) {
					if( expandedItem && expandedItem.callopseSubMenus ) expandedItem.callopseSubMenus();
					menuItem.expandSubMenus();
					expandedItem = menuItem;
				} else {
					// else is a submenu or dualstatemenu
					// azert igy, h esetleges tovabbfejlesztes konyebb legyen
					// ide kell a notify ha esemenyvezerelt lesz a dolog
				}
			}
		}
	}

	function _AddCompositMenu( menuName, caption, expand, preferedIndex ) {
		logger.trace( "Adding compositer menu:", menuName, caption );
		if(!menuItems.exists(menuName)) {
			var compositMenu = new CompositMenuItem(menuName,caption,preferedIndex);
			compositMenu.addObserver(self);
			menuItems.add(menuName, compositMenu);
			if(isVisible && definitionListElement) {
				compositMenu.show(definitionListElement);
				if(expand && (expand==true || expand=="true")) {
					if(expandedItem && expandedItem.callopseSubMenus) expandedItem.callopseSubMenus();
					compositMenu.expandSubMenus();
					expandedItem = compositMenu;
				}
			}
		}
	}

	function _RemoveCompositMenu( compositMenuName ) {
		logger.trace( "Removing composite menu: ", compositMenuName );
		if(menuItems.exists(compositMenuName)) {
			var compositMenu = menuItems.item(compositMenuName);
			if(isVisible) compositMenu.hide();
			if(compositMenu==expandedItem) expandedItem=null;
			menuItems.remove(compositMenuName);
		}
	}

	function _AddDualStateMenu( menuName, caption, command, isOn, preferedIndex) {
		logger.trace( "Adding dual state menu: ", menuName, caption );
		if(!menuItems.exists(menuName)) {
			var dualStateMenu = new DualStateMenuItem( menuName, caption, command, isOn, preferedIndex );
			dualStateMenu.addObserver( self );
			menuItems.add(menuName, dualStateMenu);
			if( isVisible && definitionListElement ) dualStateMenu.show( definitionListElement );
		}
	}

	function _AddDualStateMenuToCompositMenu( compositMenuName, menuName, caption, command, isOn, preferedIndex ) {
		logger.trace( "Adding dual state menu to composite: ", compositMenuName,menuName );
		if( menuItems.exists( compositMenuName )) {
			var compositMenu = menuItems.item( compositMenuName );
			compositMenu.addDualStateMenu( menuName, caption, command, isOn, preferedIndex );
		}
	}

	function _RemoveMenuItemFromCompositMenu( compositMenuName, menuItemName, removeIfEmpty ) {
		logger.trace( "Removing menu item form composite: ", compositMenuName, menuItemName );
		if(menuItems.exists(compositMenuName)) {
			var compositMenu = menuItems.item(compositMenuName);
			if(compositMenu.removeMenuItem) compositMenu.removeMenuItem(menuItemName);
			if(compositMenu==expandedItem && compositMenu.callopseSubMenus && compositMenu.getCountOfItems()==0) {
				compositMenu.callopseSubMenus();
				expandedItem = null;
			}
			if(removeIfEmpty && compositMenu.getCountOfItems && compositMenu.getCountOfItems()==0)
				_RemoveCompositMenu(compositMenuName);
		}
	}

	function _AddSubMenu( menuName,caption,command,preferedIndex ) {
		logger.trace( "Adding sub menu: ", menuName, caption );
		if(!menuItems.exists(menuName)) {
			var subMenu = new SubMenuItem( menuName, caption, command, preferedIndex );
			subMenu.addObserver( self );
			menuItems.add( menuName, subMenu );
			if( isVisible && definitionListElement ) subMenu.show( definitionListElement );
		}
	}

	function _AddSubMenuToCompositMenu( compositMenuName, menuName, caption, command,preferedIndex ) {
		logger.trace( "Adding sub menu to composite: ", compositMenuName,menuName );
		if( menuItems.exists( compositMenuName )) {
			var compositMenu = menuItems.item( compositMenuName );
			compositMenu.addSubMenu(menuName,caption,command,preferedIndex);
		}
	}

	function _ChangeCaptions(controller) {
		menuItems.moveFirst();
		var aItem;
		while(aItem = menuItems.getNext()) {
			if(aItem.changeCaption != null) aItem.changeCaption(controller);
		}
	}

	function _Exists(menuName) {
		return menuItems.exists(menuName);
	}

	function _RemoveSubMenu(subMenuName) {
		logger.trace( "Removing sub menu: ", subMenuName );
		if(menuItems.exists(subMenuName)) {
			var subMenu = menuItems.item(subMenuName);
			if(isVisible) subMenu.hide();
			menuItems.remove(subMenuName);
		}
	}


	function _RemoveDualStateMenu(dualStateMenuName) {
		logger.trace( "Removing dual state menu: ", dualStateMenuName );
		if(menuItems.exists(dualStateMenuName)) {
			var dualStateMenu = menuItems.item(dualStateMenuName);
			if(isVisible) dualStateMenu.hide();
			menuItems.remove(dualStateMenuName);
		}
	}

	function _RemoveAllMenus() {
		logger.trace( "Removing all menus." );
		menuItems.moveFirst();
		var mItem
		while(mItem = menuItems.getNext()) {
			if(mItem.hide) mItem.hide();
			menuItems.remove(mItem.getName());
			menuItems.moveFirst();
		}
	}

	function _Show() {
		logger.trace( "Show menu widget." );
		if(!isVisible) {
			_InsertDefinitionListElement();
			menuItems.moveFirst();
			var aMenu;
			while(aMenu = menuItems.getNext()) {
				aMenu.show(definitionListElement);
			}
			isVisible = true;
		}
	}

	function _Hide() {
		logger.trace( "Hide menu widget." );
		if(isVisible) {
			menuItems.moveFirst();
			var aMenu;
			while(aMenu = menuItems.getNext()) {
				aMenu.hide();
			}
			_RemoveDefinitionListElement();
			isVisible = false;
		}
	}

	function _InsertDefinitionListElement() {
		if( htmlDivElement != null) {
			definitionListElement = document.createElement( DL );
			definitionListElement.setAttribute( "className", DLCLASSNAME );
			htmlDivElement.appendChild( definitionListElement );
		}
		else {
			throw new UserException("Division not defined", "MenuWidget._InsertDefinitionListElement()");
		}
	}

	function _RemoveDefinitionListElement() {
		if(htmlDivElement != null) {
			if(definitionListElement != null) {
				htmlDivElement.removeChild(definitionListElement);
				definitionListElement = null;
			}
		}
		else {
			throw new UserException("Division not defined", "MenuWidget._RemoveDefinitionListElement()");
		}
	}

}