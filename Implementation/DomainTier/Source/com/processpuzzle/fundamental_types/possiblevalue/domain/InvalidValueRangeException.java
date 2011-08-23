package com.processpuzzle.fundamental_types.possiblevalue.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleException;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class InvalidValueRangeException extends ProcessPuzzleProgrammingException {

  /**
    * 
    */
   private static final long serialVersionUID = 8862180673362248211L;
   protected static String defaultMessagePattern = "Maximal value(''{1}'') of range should be greater than minimal value(''{0}'')";
   
   public InvalidValueRangeException( Object minValue, Object maxValue ) {
      super( ExceptionHelper.defineMessage(
            InvalidValueRangeException.class, 
            new Object[] { minValue, maxValue }, 
            defaultMessagePattern ) );
   }
}
