//MenuSelectedMessage.js
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

var TabSelectedMessage = new Class({
   Extends: WebUIMessage,
   options: {
      description: "A message about the event that a tab was selected.",
      name: "TabSelectedMessage",
      tabId: null
   },
   
   //Constructors
   initialize: function( options ){
      this.setOptions( options );
      this.options.messageClass = TabSelectedMessage;
   },
   
   //Public accessors
   
   //Properties
   getTabId: function() { return this.options.tabId; }
});
