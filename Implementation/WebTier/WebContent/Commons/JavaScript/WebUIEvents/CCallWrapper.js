/*
 * CCallWrapper.js
 * $Revision: 1.3 $ $Date: 2003/07/07 18:32:43 $
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

function CCallWrapper(aObjectReference, 
                      aDelay,
                      aMethodName, 
                      aArgument0,
                      aArgument1,
                      aArgument2,
                      aArgument3,
                      aArgument4,
                      aArgument5,
                      aArgument6,
                      aArgument7,
                      aArgument8,
                      aArgument9
                      )
{
  this.mId = 'CCallWrapper_' + (CCallWrapper.mCounter++);
  this.mObjectReference = aObjectReference;
  this.mDelay     = aDelay;
  this.mTimerId = 0;
  this.mMethodName = aMethodName;
  this.mArgument0 = aArgument0;
  this.mArgument1 = aArgument1;
  this.mArgument2 = aArgument2;
  this.mArgument3 = aArgument3;
  this.mArgument4 = aArgument4;
  this.mArgument5 = aArgument5;
  this.mArgument6 = aArgument6;
  this.mArgument7 = aArgument7;
  this.mArgument8 = aArgument8;
  this.mArgument9 = aArgument9;
  CCallWrapper.mPendingCalls[this.mId] = this;
}

CCallWrapper.prototype.execute = function()
{
  this.mObjectReference[this.mMethodName](this.mArgument0,
                                          this.mArgument1,
                                          this.mArgument2,
                                          this.mArgument3,
                                          this.mArgument4,
                                          this.mArgument5,
                                          this.mArgument6,
                                          this.mArgument7,
                                          this.mArgument8,
                                          this.mArgument9
                                          );
  delete CCallWrapper.mPendingCalls[this.mId];
};

CCallWrapper.prototype.cancel = function()
{
  clearTimeout(this.mTimerId);
  delete CCallWrapper.mPendingCalls[this.mId];
};

CCallWrapper.asyncExecute = function (/* CCallWrapper */ callwrapper)
{
  CCallWrapper.mPendingCalls[callwrapper.mId].mTimerId = setTimeout('CCallWrapper.mPendingCalls["' + callwrapper.mId + '"].execute()', callwrapper.mDelay);
};

CCallWrapper.mCounter = 0;
CCallWrapper.mPendingCalls = {};
