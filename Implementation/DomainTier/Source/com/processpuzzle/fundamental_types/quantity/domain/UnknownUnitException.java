package com.processpuzzle.fundamental_types.quantity.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class UnknownUnitException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -5914862221709248462L;
   private static String defaultMessagePattern = "Unknown unit: ''{0}''.";
   private String unit;
   
   public UnknownUnitException( String unit ) {
      super( ExceptionHelper.defineMessage(
            UnknownUnitException.class,
            new Object[] {unit},
            defaultMessagePattern ));
      this.unit = unit;
   }
   
   public String getArgument () {return unit;}
}

