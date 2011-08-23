package com.processpuzzle.fundamental_types.quantity.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleException;

public class InvalidUnitException extends ProcessPuzzleException{
   private static final long serialVersionUID = -5344421931816391205L;
   private static String defaultMessagePattern;
   
   public InvalidUnitException(Unit u, String q,Throwable cause){
      super( ExceptionHelper.defineMessage(
            InvalidUnitException.class,
            new Object[] {u, q},
            defaultMessagePattern),
            cause);
   }
   public InvalidUnitException(Unit u, String q){
      this(u,q,null); 
   }
}