package com.processpuzzle.persistence.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class UnknownRepositoryException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -7716783986287053587L;
   private static final String defaultMessagePattern = "Repository: ''{0}'' is unknown. Probably is not defined in one of configuration files.";

   public UnknownRepositoryException( Class<?> repositoryClass, Throwable cause) {
      super(ExceptionHelper.defineMessage(
            UnknownRepositoryException.class, 
            new Object[] {repositoryClass}, defaultMessagePattern), cause);
   }
}
