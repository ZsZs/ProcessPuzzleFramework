/*
Name: 
    - FormatSpecifier

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

package com.processpuzzle.fundamental_types.textformat.domain;

import java.util.Locale;

import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;

/** 
 * @author zsolt.zsuffa
 * @uml.annotations
 *     derived_abstraction="platform:/resource/ProcessPuzzle%20Framework%20Models/Design%20Model.emx#_HzAgcN6WEdu_hbIvzgjWfA"
 */
public abstract class FormatSpecifier {
	protected Integer id;
   /**
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   protected ProcessPuzzleLocale locale;

   /**
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   protected String defaultPattern;

   /**
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public FormatSpecifier() {
      // begin-user-code
      this(null);
      // end-user-code
   }

   /**
    * @param locale
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public FormatSpecifier(ProcessPuzzleLocale locale) {
      // begin-user-code
      if (locale == null) {
         Locale defaultLocale = Locale.getDefault();
         this.locale = new ProcessPuzzleLocale(defaultLocale.getCountry(), defaultLocale.getLanguage(), defaultLocale.getVariant());
      } else this.locale = locale;
      // end-user-code
   }

   /**
    * @param source
    * @return
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public String toString(Object source) {
      // begin-user-code
      // TODO Auto-generated method stub
      return null;
      // end-user-code
   }

   /**
    * @param source
    * @return
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public Object parse(String source) {
      // begin-user-code
      // TODO Auto-generated method stub
      return null;
      // end-user-code
   }
}