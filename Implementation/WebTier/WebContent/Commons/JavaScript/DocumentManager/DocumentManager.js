//DocumentManager.js
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

var DocumentManager = new Class({
   Implements: [Class.Singleton, Options],
   Binds : ['changeLanguage', 'loadDocument', 'webUIMessageHandler'],
   
   options: {
      componentName : "DocumentManager",
      documentNameSeparator : "_",
      documentsPanelId : "documents-panel",
      documentsPanelTitleKey : 'DesktopConfigurator.Panel.documentsTitle'   
   },
   
   //Constructors
   initialize: function( options ) { return this.check() || this.setUp( options ); },
   setUp: function( options ){
      this.setOptions( options );

      //private instance variables
      this.controller = Class.getInstanceOf( WebUIController );
      this.currentDocumentProperties = null;
      this.locale = null;
      this.stateManager = Class.getInstanceOf( ComponentStateManager );
   },
   
   changeLanguage: function( locale ){
      this.locale = locale;
   },
   
   configure: function( locale ) {
      this.locale = locale;
      var storedState = this.stateManager.retrieveCurrentState( this.options.componentName ); 
      if( storedState ) {
         this.loadDocument( storedState );
      }
   },
   
   destroy: function() {
      this.currentDocumentProperties = null;
      this.locale = null;
   },
   
   loadDocument: function( documentProperties ){
      var documentURI = documentProperties['documentURI'];
      var documentFullURI = documentURI + this.options.documentNameSeparator + this.locale.getLanguage() + ".html";
      
      MUI.updateContent({
         element: $( this.options.documentsPanelId ),
         url: documentFullURI,
         title: this.controller.getText( this.options.documentsPanelTitleKey ),
         padding: { top: 8, right: 8, bottom: 8, left: 8 }
      });
      
      this.currentDocumentProperties = documentProperties;
      this.storeComponentState();
   },
   
   restore: function() {
      var storedState = this.stateManager.retrieveCurrentState( this.options.componentName );
      if( storedState ){
         this.loadDocument( storedState );
      }
   },
   
   //Properties
   getController: function() { return this.controller; },
   getStateManager: function() { return this.stateManager; },
   
   //Protected, private helper methods
   storeComponentState : function() {
      this.stateManager.storeCurrentState( this.options.componentName, this.currentDocumentProperties );
   }.protect()   
});