/*
 * Created on Nov 30, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.fundamental_types.quantity.domain;



/**
 * @author zsolt.zsuffa
 * @uml.annotations
 *     derived_abstraction="platform:/resource/ProcessPuzzle%20Framework%20Models/Design%20Model.emx#_Ogsmq84mEduFpt013Z9v0w"
 */
public class TimeValue extends Quantity {
   private long milliseconds;
   
   //Public constructors  
   public TimeValue(double theAmount, Unit theUnit) {
      super(theAmount, theUnit);
      if (theUnit.getSymbol().equals("msec")) {
         milliseconds = (long) theAmount;
      } else if (theUnit.getSymbol().equals("msec")) {
         milliseconds = (long) theAmount;
      } else if (theUnit.getSymbol().equals("s")) {
         milliseconds = (long) (theAmount*1000);
      } else if (theUnit.getSymbol().equals("min")) {
         milliseconds = (long) (theAmount*60000);
      } else if (theUnit.getSymbol().equals("h")) {
         milliseconds = (long) (theAmount*60*60*1000);
      } else if (theUnit.getSymbol().equals("d")) {
         milliseconds = (long) (theAmount*60*60*1000*24);
      } else if (theUnit.getSymbol().equals("wk")) {
         milliseconds = (long) (theAmount*60*60*1000*24*7);
      } else if (theUnit.getSymbol().equals("mth")) {
         //milliseconds = (long) (theAmount*60*60*1000*24*365);
      } else if (theUnit.getSymbol().equals("yr")) {
         milliseconds = (long) (theAmount*60*60*1000*24*365);
      }
      else {
         throw new UnitMismatchException(theUnit, theUnit);
      }
   }

   public TimeValue(Integer theAmount, Unit theUnit) {
      super(theAmount, theUnit);
      if (theUnit.getSymbol().equals("msec")) {
         milliseconds = (long) theAmount;
      } else if (theUnit.getSymbol().equals("msec")) {
         milliseconds = (long) theAmount;
      } else if (theUnit.getSymbol().equals("s")) {
         milliseconds = (long) (theAmount*1000);
      } else if (theUnit.getSymbol().equals("min")) {
         milliseconds = (long) (theAmount*60000);
      } else if (theUnit.getSymbol().equals("h")) {
         milliseconds = (long) (theAmount*60*60*1000);
      } else if (theUnit.getSymbol().equals("d")) {
         milliseconds = (long) (theAmount*60*60*1000*24);
      } else if (theUnit.getSymbol().equals("wk")) {
         milliseconds = (long) (theAmount*60*60*1000*24*7);
      } else if (theUnit.getSymbol().equals("yr")) {
         milliseconds = (long) (theAmount*60*60*1000*24*365);
      } else {
         throw new UnitMismatchException(theUnit, theUnit);
      }
   }

   /**
    * @return
    * @generated "UML to Java V5.0
    *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public int getInMilliseconds() {
      // begin-user-code
      // TODO Auto-generated method stub
      return (int)milliseconds;
      // end-user-code
   }

   /**
    * @return
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public int getInSeconds() {
      // begin-user-code
      // TODO Auto-generated method stub
      return (int)(milliseconds/1000);
      // end-user-code
   }

   /**
    * @return
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public int getInMinutes() {
      // begin-user-code
      // TODO Auto-generated method stub
      return (int)(milliseconds/(1000*60));
      // end-user-code
   }

   /**
    * @return
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public int getInHours() {
      // begin-user-code
      // TODO Auto-generated method stub
      return (int)(milliseconds/(1000*60*60));
      // end-user-code
   }

   /**
    * @return
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public int getInDays() {
      // begin-user-code
      // TODO Auto-generated method stub
      return (int)(getInHours()/24);
      // end-user-code
   }

   /**
    * @return
    * @generated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */
   public int getInWeeks() {
      return (int)(getInDays()/7);
   }
   
   public int getInYears() {
      return (int)(getInDays()/365);
   }
}
