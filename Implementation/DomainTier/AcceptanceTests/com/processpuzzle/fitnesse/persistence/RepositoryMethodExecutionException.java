package com.processpuzzle.fitnesse.persistence;

import hu.itkodex.commons.persistence.Repository;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleException;

public class RepositoryMethodExecutionException extends ProcessPuzzleException {
   private static final long serialVersionUID = -862227497255341272L;
   private static final String defaultMessage = "Repository ''{0}'' thrown exception when executing method ''{1}'' with parameters ''{2}''.";
   private final Class<? extends Repository<?>> repositoryClass;
   private final String methodName;
   private final String parameters;

   public RepositoryMethodExecutionException( Class<? extends Repository<?>> repositoryClass, String methodName, String parameters, Throwable cause ) {
      super( ExceptionHelper.defineMessage(  
            NoSuchRepositoryMethodException.class, new Object[] {repositoryClass.getName(), methodName, parameters }, defaultMessage ), cause );
      this.repositoryClass = repositoryClass;
      this.methodName = methodName;
      this.parameters = parameters;
   }

   public String getMethodName() {
      return methodName;
   }

   public String getParameters() {
      return parameters;
   }
   
   public Class<? extends Repository<?>> getRepositoryClass() {
      return repositoryClass;
   }
}
