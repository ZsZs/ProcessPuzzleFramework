package com.processpuzzle.fitnesse.persistence;


import com.processpuzzle.commons.persistence.Repository;
import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleException;

public class NoSuchRepositoryMethodException extends ProcessPuzzleException {
   private static final long serialVersionUID = -8619931961854172635L;
   private static final String defaultMessage = "Repository ''{0}'' does not have method ''{1}''.";
   private final Class<? extends Repository<?>> repositoryClass;
   private final String methodName;

   public NoSuchRepositoryMethodException( Class<? extends Repository<?>> repositoryClass, String methodName ) {
      super( ExceptionHelper.defineMessage( 
            NoSuchRepositoryMethodException.class, new Object[] {repositoryClass.getName(), methodName }, defaultMessage ) );
      this.repositoryClass = repositoryClass;
      this.methodName = methodName;
   }

   public Class<? extends Repository<?>> getRepositoryClass() {
      return repositoryClass;
   }

   public String getMethodName() {
      return methodName;
   }

}
