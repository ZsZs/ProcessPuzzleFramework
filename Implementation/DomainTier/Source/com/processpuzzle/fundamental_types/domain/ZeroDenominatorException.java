package com.processpuzzle.fundamental_types.domain;


public class ZeroDenominatorException extends ProcessPuzzleProgrammingException {

   private static final long serialVersionUID = 4739267820836284971L;
   private static String defaultMessagePattern = "Division by Zero is impossible!";
   
   public ZeroDenominatorException() {
      super( ExceptionHelper.defineMessage(
            ZeroDenominatorException.class,
            new Object[] {},
            defaultMessagePattern));
   }
}
