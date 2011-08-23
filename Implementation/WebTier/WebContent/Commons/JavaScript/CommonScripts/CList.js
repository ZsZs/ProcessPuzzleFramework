/*
 * CList.js
 * $Revision: 1.1 $ $Date: 2003/06/12 18:35:06 $
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

function CList(/* Array */ aArray)
{
  this.mArray = aArray || [];
}

CList.prototype.getLength = 
function()
{
  return this.mArray.length;
};

CList.prototype.getAt =
function(/* Number */ aIndex)
{
  if (aIndex < 0 || aIndex >= this.mArray.length)
  {
    return undefined;
  }

  return this.mArray[aIndex];
};

CList.prototype.removeAll = 
function()
{
  this.mArray = [];
};

CList.prototype.removeAt =
function (/* Number */ aIndex)
{
  var length = this.mArray.length;
  if (length  == 0)
  {
    return;
  }

  switch(aIndex)
  {
  case -1:
    break;
  case 0:
    this.mArray.shift();
    break;
  case length - 1:
    this.mArray.pop();
    break;
  default:
    var head = this.mArray.slice(0, aIndex);
    var tail = this.mArray.slice(aIndex+1);
    this.mArray = head.concat(tail);
    break;
  }
};

CList.prototype.insertAt =
function (/* Object */ aObject, /* Number */ aIndex)
{
  switch(aIndex)
  {
  case -1:
    break;
  case 0:
    this.mArray.unshift();
    break;
  case length:
    this.mArray.push();
    break;
  default:
    var head = this.mArray.slice(0, aIndex - 1);
    var tail = this.mArray.slice(aIndex);
    this.mArray = head.concat([aObject]);
    this.mArray = this.mArray.concat(tail);
    break;
  }
};

CList.prototype.findIndexOf = 
function(/* Object */ aObject)
{
  var length = this.mArray.length;
  for (var i = 0; i < length; ++i)
  {
    if (this.mArray[i] == aObject)
    {
      return i;
    }
  }
  return -1;
};

CList.prototype.addUnique =
function (/* Object */ aObject)
{
  var i = this.findIndexOf(aObject);
  if (i == -1)
  {
    this.mArray[this.mArray.length] = aObject;
  }
};

CList.prototype.removeUnique =
function (/* Object */ aObject)
{
  var length = this.mArray.length;
  if (length  == 0)
  {
    return;
  }
  var i = this.findIndexOf(aObject);

  this.removeAt(i);
};

