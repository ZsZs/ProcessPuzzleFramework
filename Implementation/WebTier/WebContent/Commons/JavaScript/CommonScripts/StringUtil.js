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

function StringUtil(){}
new StringUtil();
StringUtil.isEmpty = function(s){	var r = /^\s*$/
    return r.test(s);
}

StringUtil.contains = function (stringValue, searchValue) {
	var upperStringValue = stringValue.toUpperCase();
	var upperSearchValue = searchValue.toUpperCase();
	var result = upperStringValue.indexOf( upperSearchValue );
	if (result != -1 )
		return true;
	else return false;
}
StringUtil.hashCode = function (theString) {
   var hash = 0;
   var len = theString.length;
   for ( var i=0; i<len; i++ ) {
      hash = 31 * hash + theString.charCodeAt(i);
   }
   return hash;
}

String.prototype.ltrim = function strltrim() {return this.replace(/^\s+/,'');}
String.prototype.rtrim = function strrtrim() {return this.replace(/\s+$/,'');}
String.prototype.trim = function strtrim() {return this.replace(/^\s+/,'').replace(/\s+$/,'');}
String.prototype.hashCode = function hashCode () {return StringUtil.hashCode(this);}
String.prototype.equals = function equals (compareTo) {return this == compareTo;}