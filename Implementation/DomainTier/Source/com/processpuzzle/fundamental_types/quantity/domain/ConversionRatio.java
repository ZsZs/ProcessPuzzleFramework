/**
 * 
 */
package com.processpuzzle.fundamental_types.quantity.domain;


/** 
 * @author zsolt.zsuffa
 * @uml.annotations
 *     derived_abstraction="platform:/resource/ProcessPuzzle%20Framework%20Models/Design%20Model.emx#_6tzjgM4oEduFpt013Z9v0w"
 */
public class ConversionRatio {
   /** 
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   private double ratio;
   private Unit from;
   private Unit to;

   public ConversionRatio(double ratio, Unit from, Unit to) {
      this.ratio = ratio;
      this.from = from;
      this.to = to;
   }

   public Unit getFrom() {
      return from;
   }

   public double getRatio() {
      return ratio;
   }

   public Unit getTo() {
      return to;
   }
}