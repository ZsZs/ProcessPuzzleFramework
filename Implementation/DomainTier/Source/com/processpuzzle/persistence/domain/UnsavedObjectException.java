package com.processpuzzle.persistence.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class UnsavedObjectException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -8688764139893669735L;
   protected static String defaultMessagePattern = "One or more object was unsaved.";

   public UnsavedObjectException( Throwable cause ) {
      super( ExceptionHelper.defineMessage(
            UnsavedObjectException.class, 
            new Object[] {}, 
            defaultMessagePattern), cause );
   }
}
