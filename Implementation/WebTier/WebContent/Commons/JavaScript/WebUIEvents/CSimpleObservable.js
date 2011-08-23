/*
 * CSimpleObservable.js
 * $Revision: 1.1 $ $Date: 2003/06/12 18:35:28 $
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

function CSimpleObservable(/* Boolean */ aIsAsync)
{
  this.mObservers = new CList();
  this.mIsAsync = aIsAsync || false;
}

CSimpleObservable.prototype =
{
  notify: function(aValue)
  {
    var length = this.mObservers.getLength();

    for (var i = 0; i < length; ++i)
    {
      if (this.mIsAsync)
      {
        var callwrapper = new CCallWrapper(this.mObservers.getAt(i), 30, 'observe', aValue);
        CCallWrapper.asyncExecute(callwrapper);
      }
      else
      {
        this.mObservers.getAt(i).observe(aValue);
      }
    }

  },

  addObserver: function (/* Object */ aObserver)
  {
    if (!aObserver.observe)
    {
      throw 'CObserver.addObserver: not an observer';
    }
    this.mObservers.addUnique(aObserver);
  },

  removeObserver: function (/* Object */ aObserver)
  {
    this.mObservers.removeUnique(aObserver);
  }
};

