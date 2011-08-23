package com.processpuzzle.fundamental_types.quantity.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class UnitMismatchException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -4607978491313812961L;
   private static String defaultMessagePattern = "Incompatible units: ''{0}'', ''{1}''.";
   private Unit unit1;
   private Unit unit2;
   
   public UnitMismatchException( Unit unit1, Unit unit2 ) {
      super( ExceptionHelper.defineMessage(
            UnitMismatchException.class,
            new Object[] {unit1, unit2},
            defaultMessagePattern ));
      this.unit1 = unit1;
      this.unit2 = unit2;
   }
   
   public Unit getArgumentOne () {return unit1;}
   public Unit getArgumentTwo () {return unit2;}
}
