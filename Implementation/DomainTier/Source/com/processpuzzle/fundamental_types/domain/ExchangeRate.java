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