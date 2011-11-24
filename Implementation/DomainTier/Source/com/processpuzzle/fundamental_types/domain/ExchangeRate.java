/*
Name: 
    - ExchangeRate

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/**
 * 
 */
package com.processpuzzle.fundamental_types.domain;

import com.processpuzzle.fundamental_types.quantity.domain.ConversionRatio;
import com.processpuzzle.fundamental_types.quantity.money.domain.Currency;

/** 
 * @author zsolt.zsuffa
 * @uml.annotations
 *     derived_abstraction="platform:/resource/ProcessPuzzle%20Framework%20Models/Design%20Model.emx#_4kCxwNfYEduHRbluOc6gYQ"
 */
public class ExchangeRate extends ConversionRatio {
   /** 
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   private TimePeriod valid;

   /**
    * @param ratio
    * @param from
    * @param to
    * @param valid
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public ExchangeRate(double ratio, Currency from, Currency to, TimePeriod valid) {
      // begin-user-code
      super(ratio, from, to);
      this.valid = valid;
      // end-user-code
   }

   //Plese note, that this method should dinamically update the ration (exchange rate) vale.
   public double getRatio() {
      return super.getRatio();
   }

   public TimePeriod getValid() {
      return valid;
   }
}