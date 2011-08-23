/**
 * 
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