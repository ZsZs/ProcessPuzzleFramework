package com.processpuzzle.persistence.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class PersistenceProviderException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 5129881330111644568L;
   protected static String defaultMessagePattern = "An error occured in performing: ''{0}'' action.";
   protected String actionName;

   public PersistenceProviderException( String actionName, Throwable cause ) {
      super( ExceptionHelper.defineMessage(
            PersistenceProviderException.class, 
            new Object[] {actionName}, 
            defaultMessagePattern), cause );
      this.actionName = actionName;
   }
   
   public String getActionName() { return actionName; }
}
