package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class UndeclaredRepositoryException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 469082909940860176L;
   private static String defaultMessagePattern = "Repository: ''{0}'' is not declared in PersistenceContext.";

   public UndeclaredRepositoryException( Class<?> repositoryClass ) {
      super(ExceptionHelper.defineMessage(
            UndeclaredRepositoryException.class, 
            new Object[] {repositoryClass.getSimpleName()}, 
               defaultMessagePattern ));
   }
}
