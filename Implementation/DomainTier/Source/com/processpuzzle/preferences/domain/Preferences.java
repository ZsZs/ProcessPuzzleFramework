/*
Name: 
    - Preferences 

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

package com.processpuzzle.preferences.domain;

import com.processpuzzle.fundamental_types.textformat.domain.AddressFormatSpecifier;
import com.processpuzzle.fundamental_types.textformat.domain.DateFormatSpecifier;
import com.processpuzzle.fundamental_types.textformat.domain.PersonNameFormatSpecifier;
import com.processpuzzle.fundamental_types.textformat.domain.QuantityFormatSpecifier;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;

/**
 * @author zsolt.zsuffa
 * @uml.annotations derived_abstraction="platform:/resource/ProcessPuzzle%20Framework%20Models/Design%20Model.emx#_V6uBQN4CEdu0_KhhHrWiwA"
 */
public abstract class Preferences {
   /**
    * @generated "UML to Java V5.0
    *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   protected ProcessPuzzleLocale locale;
   protected QuantityFormatSpecifier quantityFormatSpecifier = null;
   protected DateFormatSpecifier dateFormatSpecifier = null;
   /**
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   protected Object numberFormatSpecifier;
   protected AddressFormatSpecifier addressFormatSpecifier = null;
   protected PersonNameFormatSpecifier personNameFormatSpecifier = null;

   public Preferences(ProcessPuzzleLocale locale) {
      this.locale = locale;
      this.quantityFormatSpecifier = new QuantityFormatSpecifier(this.locale);
      this.dateFormatSpecifier = new DateFormatSpecifier(this.locale);
   }

   /**
    * @param formatSpecifier
    * @generated  "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public void prefereQuantityFormat(QuantityFormatSpecifier formatSpecifier) {
   // begin-user-code
   // TODO Auto-generated method stub
      this.quantityFormatSpecifier = formatSpecifier;
   // end-user-code
   }

   /**
    * @param formatSpecifier
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public void prefereTimeValueFormat(DateFormatSpecifier formatSpecifier) {
   // begin-user-code
   // TODO Auto-generated method stub
      this.dateFormatSpecifier = formatSpecifier;
   // end-user-code
   }

   /**
    * @param formatSpecifier
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public void preferePersonNameFormat(PersonNameFormatSpecifier formatSpecifier) {
   // begin-user-code
   // TODO Auto-generated method stub
      this.personNameFormatSpecifier = formatSpecifier;
   // end-user-code
   }

   /**
    * @param formatSpecifier
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public void prefereAddressFormat(AddressFormatSpecifier formatSpecifier) {
      // begin-user-code
      this.addressFormatSpecifier = formatSpecifier;
      // end-user-code
   }

   //Getters and setters
   public ProcessPuzzleLocale getLocale() {
      return locale;
   }

   public QuantityFormatSpecifier getQuantityFormatSpecifier() {
      return quantityFormatSpecifier;
   }

   public DateFormatSpecifier getDateFormatSpecifier() {
      return dateFormatSpecifier;
   }

   public AddressFormatSpecifier getAddressFormatSpecifier() {
      return addressFormatSpecifier;
   }

   public PersonNameFormatSpecifier getPersonNameFormatSpecifier() {
      return personNameFormatSpecifier;
   }
}