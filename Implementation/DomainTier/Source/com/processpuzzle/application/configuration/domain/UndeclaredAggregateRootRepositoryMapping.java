package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class UndeclaredAggregateRootRepositoryMapping extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -1541336346507135903L;
   private static final String defaultMessagePattern = "There is no repository declared to the aggregate root: ''{0}''";

   public UndeclaredAggregateRootRepositoryMapping( Class<?> aggregateRootClass ) {
      super(ExceptionHelper.defineMessage(
            UndeclaredAggregateRootRepositoryMapping.class, 
            new Object[] {aggregateRootClass.getSimpleName()}, 
            defaultMessagePattern));
   }
}
