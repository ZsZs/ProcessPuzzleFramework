package com.processpuzzle.fitnesse.persistence;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class UnconfiguredApplicationException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -4664586100268580232L;
   private static final String defaultMessage = "Application in not configured. UserRequestManager.getInstance().getApplicationContext() is null.";

   public UnconfiguredApplicationException() {
      super( ExceptionHelper.defineMessage( UnconfiguredApplicationException.class, new Object[] {}, defaultMessage ) );
   }

}
