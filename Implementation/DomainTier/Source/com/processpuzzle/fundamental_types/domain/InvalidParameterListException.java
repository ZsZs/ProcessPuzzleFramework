package com.processpuzzle.fundamental_types.domain;


public class InvalidParameterListException extends ProcessPuzzleProgrammingException {

   private static final long serialVersionUID = 7422632907381096753L;
   private static String defaultMessagePattern = "Invalid parameter list by creating new Object via {0} : ";

   public InvalidParameterListException( Class<?> factory, Object[] params, Throwable cause) {
      super( ExceptionHelper.defineMessage(
            InvalidParameterListException.class,
            new Object[] {factory, params},
            defaultMessagePattern), 
            cause);
      //super( defineMessage( factory, params ), cause );
   }

}
