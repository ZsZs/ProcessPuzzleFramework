/*
 * inheritFrom.js
 * $Revision: 1.1 $ $Date: 2003/06/12 18:35:47 $
 */

/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Netscape code.
 *
 * The Initial Developer of the Original Code is
 * Netscape Corporation.
 * Portions created by the Initial Developer are Copyright (C) 2003
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s): Bob Clary <bclary@netscape.com>
 *
 * ***** END LICENSE BLOCK ***** */

function inheritFrom(/* Object */ aThis, /* Object */ aParent) {
  var excp;

  for (var property in aParent)
  {
    try
    {
      aThis[property] = aParent[property];
    }
    catch(excp)
    {
    }
  }
}

Function.prototype.DeriveFrom = function (fnSuper) {
    var prop;
    if (this == fnSuper) {
        alert("Error - cannot derive from self");
        return;
    }
    for (prop in fnSuper.prototype) {
        if (typeof fnSuper.prototype[prop] == "function" &&
            !this.prototype[prop]) {
            this.prototype[prop] = fnSuper.prototype[prop];
        }
    }
    this.prototype[fnSuper.StName()] = fnSuper;
}

Function.prototype.StName = function () {
    var st;
    st = this.toString();
    st = st.substring(st.indexOf(" ") + 1, st.indexOf("("));
    if (st.charAt(0) == "(") {
        st = "function ...";
    }
    return st;
}

Function.prototype.Override = function( fnSuper, stMethod ) {
    this.prototype[fnSuper.StName() + "_" + stMethod] = fnSuper.prototype[stMethod];
}

