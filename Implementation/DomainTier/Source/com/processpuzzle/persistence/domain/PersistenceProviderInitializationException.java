package com.processpuzzle.persistence.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class PersistenceProviderInitializationException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -7120926168654173563L;
   private static final String defaultMessagePattern = "Initialization of ''{0}'' strategy failed.";
   private String strategyName = null;

   public PersistenceProviderInitializationException( Object[] arguments ) {
//      super( ExceptionHelper.defineMessage(
//            PersistenceProviderInitializationException.class,
//            new Object[] {arguments},
//            defaultMessagePattern));
      this(arguments, null);
   }

   public PersistenceProviderInitializationException(Object[] arguments, Throwable cause) {
      super( ExceptionHelper.defineMessage(
            PersistenceProviderInitializationException.class,
            new Object[] {arguments},
            defaultMessagePattern),
            cause);
   }

   public PersistenceProviderInitializationException( Class<?> strategy, Throwable cause ) {
      super( ExceptionHelper.defineMessage(
            PersistenceProviderInitializationException.class,
            new Object[] {strategy},
            defaultMessagePattern),
            cause);
      this.strategyName = strategy.getName();
   }
   
   public String getStrategyName() { return strategyName; }
   
}
