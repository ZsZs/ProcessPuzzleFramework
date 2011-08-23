package com.processpuzzle.persistence.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class UndefinedPersistenceProviderException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -1714493113558725985L;
   protected static String defaultMessagePattern = "In persistence strategy: ''{0}'' no one PersistenceProvider is defined.";
   private String persistenceStrategyName;

   UndefinedPersistenceProviderException( String persistenceStrategyName ) {
      super( ExceptionHelper.defineMessage( 
                                            UndefinedPersistenceProviderException.class, 
                                            new Object[] { persistenceStrategyName }, 
                                            defaultMessagePattern ));
      this.persistenceStrategyName = persistenceStrategyName;
   }
   
   public String getPersistenceStrategyName() { return persistenceStrategyName; }
}
