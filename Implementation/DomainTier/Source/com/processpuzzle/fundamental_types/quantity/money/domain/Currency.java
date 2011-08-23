/**
 * 
 */
package com.processpuzzle.fundamental_types.quantity.money.domain;

import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.fundamental_types.quantity.domain.Unit;

/** 
 * @author zsolt.zsuffa
 * @uml.annotations
 *     derived_abstraction="platform:/resource/ProcessPuzzle%20Framework%20Models/Design%20Model.emx#_vmwBcM4mEduFpt013Z9v0w"
 * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Currency extends Unit {

   /**
    * @param rate
    * @param currency
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public void addConversionRatio(double rate, Currency currency) {
   // begin-user-code
   // TODO Auto-generated method stub

   // end-user-code
   }

   /**
    * @param unit
    * @param timePoint
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public void findExchangeRate(Unit unit, TimePoint timePoint) {
   // begin-user-code
   // TODO Auto-generated method stub

   // end-user-code
   }

   public Currency(String name, String symbol) {
      super(name, symbol);
   }

}