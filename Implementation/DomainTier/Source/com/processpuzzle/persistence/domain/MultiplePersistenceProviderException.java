package com.processpuzzle.persistence.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class MultiplePersistenceProviderException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 2053366754902004360L;
   protected static String defaultMessagePattern = "Persistence strategy: ''{0}'' was configured to set up with multiple PersistenceProviders. This is not allowed";
   private String persistenceStrategyName;
   
   public MultiplePersistenceProviderException( String persistenceStrategyName ) {
      super( ExceptionHelper.defineMessage( 
                                            MultiplePersistenceProviderException.class, 
                                            new Object[] { persistenceStrategyName }, 
                                            defaultMessagePattern ));
      this.persistenceStrategyName = persistenceStrategyName;
   }

   public String getPersistenceStrategyName() { return persistenceStrategyName; }
}
