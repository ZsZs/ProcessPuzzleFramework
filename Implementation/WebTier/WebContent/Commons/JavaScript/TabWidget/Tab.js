//Tab.js
/**
ProcessPuzzle User Interface
Backend agnostic, desktop like configurable, browser font-end based on MochaUI.
Copyright (C) 2011  Joe Kueser, Zsolt Zsuffa

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

var Tab = new Class({
   Implements : Options,
   Binds: ['onClickEventHandler', 'webUIMessageHandler'],
   
   options: {
      currentTabId: "current",
      idPrefix: "tab_",
      tabsStyle: "Tabs"
   },
   
   //Constructor
   initialize: function( theName, theCaption, theWidgetElement, theObjectToSelect ) {
   	//check parameter assertions
   	if( theCaption == null || theCaption == "") throw new InvalidParameterException( theCaption );
   	if( theName == null || theName == "") throw new InvalidParameterException( theName );	
   	if( theWidgetElement == null ) throw new InvalidParameterException( theWidgetElement );
   	if( theObjectToSelect && theObjectToSelect.activate == null ) throw new InvalidParameterException("The ObbectToSelect should have implement activate() method!");

   	//private instance variables
   	this.active = false;
   	this.anchorElement;
   	this.caption = theCaption;
   	this.id = this.options.idPrefix + theName;
   	this.listItemElement;
      this.logger = null;
      this.messageBus =  Class.getInstanceOf( WebUIMessageBus );
   	this.name = theName;
   	this.objectToSelect = theObjectToSelect;
   	this.visible = false;
   	this.widgetElement = theWidgetElement;
   },
   
	//public mutators methods
   activate: function() {
      if( this.isVisible() ) {
         this.active = true;
         this.setListItemId( this.options.currentTabId );
         if( this.objectToSelect && this.objectToSelect.activate ) this.objectToSelect.activate();
      }
   },
   
   changeCaption: function( controller ) {
      this.caption = controller.getText( this.name );
      if( this.anchorElement != null && this.anchorText != null) {
         this.anchorElement.removeChild( this.anchorText );
         var nb_caption = caption.replace(/ /g,String.fromCharCode( 160 ));
         this.anchorText = document.createTextNode( nb_caption );
         this.anchorElement.appendChild( this.anchorText );
      }
   },
   
   configure: function(){
      this.insertNewLIElement();
      this.visible = true;
   },

   deActivate: function() {
      if( this.isVisible() ) {
         this.active = false;
         this.setListItemId( "" );
         if( this.objectToSelect && this.objectToSelect.deActivate ) this.objectToSelect.deActivate();
      }
   },
   
   destroy: function() {
      if( this.isVisible() ) this.removeLIElement();
      this.visible = false;
      this.active = false;
   },
   
   equals: function( otherTab ){
   	if( !instanceOf( otherTab, Tab )) return false;
   	return this.id.equals( otherTab.id );
   },
   
   mayDeActivate: function() {
      if( this.objectToSelect && this.objectToSelect.mayDeActivate && !this.objectToSelect.mayDeActivate() ) return false;
      else return true;
   },

   onSelection: function( theTab ) {
   	this.activate();
      this.messageBus.notifySubscribers( new TabSelectedMessage( { tabId : this.name } ));
   },
   
   replaceObjectToSelect: function() {
   },
   
   //Properties
   getCaption: function() { return this.caption; },
   getId: function() { return this.id; },
   getName: function() { return this.name; },
   getObjectToSelect: function() { return this.objectToSelect; },
   isActive: function() { return this.active; },
   isVisible: function() { return this.visible; },

	//private helper methods
	insertNewLIElement: function() {
		if( !this.widgetElement ) throw new UnconfiguredWidgetException( {message : "Can't find tabwidget container element.", source : "Tab.createLIElement"} );
		
		//locate the <ul class='Tab"> list within tab division
		var ulElements = this.widgetElement.getElements( "UL." + this.options.tabsStyle );
		if( ulElements.length < 1 ) throw new UnconfiguredWidgetException( {message : "Can't find new tab's parent UL element.", source : "Tab.createLIElement"} );
		var parentULElement = ulElements[0];
		
		var nb_caption = this.caption.replace(/ /g,String.fromCharCode(160));
		this.anchorElement = new Element( 'a' );
		this.anchorElement.appendText( nb_caption );
		this.anchorElement.set( 'href', '#' );
		this.anchorElement.set( 'id', this.id );
		var thisTab = this;
		this.anchorElement.addEvent( 'click', function() { thisTab.onSelection( this ); } );

		this.listItemElement = new Element( 'li' );
		this.listItemElement.appendChild( this.anchorElement );
		
		parentULElement.grab( this.listItemElement, 'top' );
	}.protect(),

	removeLIElement: function() {
		if( this.listItemElement != null) {
			this.anchorElement.removeEvents();
			this.anchorElement = null;
			this.listItemElement.destroy();
			this.listItemElement = null;
		}
		else throw new UnconfiguredWidgetException( {message : "Can't remove tab's parent LI element.", source : "Tab.removeLIElement"} );
	}.protect(),

	replaceObjectToSelect: function( theObjectToSelect ) {
		if( this.logger.getLevel() == log4javascript.Level.TRACE ) this.logger.trace( "Trying replace object:", theObjectToSelect );
		this.objectToSelect = theObjectToSelect;
	}.protect(),
	
	setListItemId: function( listItemId ) {
		if( this.listItemElement != null ) {
			this.listItemElement.set( 'id', listItemId );
		}
		else throw new UnconfiguredWidgetException( {message : "Can't set undefined LI element's id.", source : "Tab.setListItemId"} );
	}.protect()
});