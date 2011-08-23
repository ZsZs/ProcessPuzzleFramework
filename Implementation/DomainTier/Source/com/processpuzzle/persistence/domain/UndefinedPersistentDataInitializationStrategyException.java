package com.processpuzzle.persistence.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class UndefinedPersistentDataInitializationStrategyException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -3379663255144671847L;
   private static final String defaultMessagePattern = "persistentDataInitializationStrategy property is undefined or has an unknown value: ''{0}''";

   public UndefinedPersistentDataInitializationStrategyException( String strategyValue ) {
      super(ExceptionHelper.defineMessage(
            UndefinedPersistentDataInitializationStrategyException.class, 
            new Object[] {strategyValue}, 
            defaultMessagePattern));
   }
}
