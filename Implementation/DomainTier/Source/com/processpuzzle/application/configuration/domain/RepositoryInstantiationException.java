package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;

public class RepositoryInstantiationException extends PersistenceContextSetUpException {
   private static final long serialVersionUID = -4735044907405420147L;
   private static String defaultMessagePattern = "Instantiation of repository: ''{0}'' failed.";
   private String repositoryClassName = null;
   
   public RepositoryInstantiationException( String repositoryClassName, PropertyContext context, Throwable cause ) {
      super( ExceptionHelper.defineMessage( 
            PersistenceContextSetUpException.class, 
            new Object[] { repositoryClassName },
            defaultMessagePattern ),
            context, cause );
      this.repositoryClassName = repositoryClassName;
   }
   
   public String getRepositoryClassName() { return repositoryClassName; }
}
