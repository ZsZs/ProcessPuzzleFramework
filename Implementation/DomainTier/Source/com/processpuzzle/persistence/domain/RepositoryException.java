/*
 * Created on Jun 16, 2006
 */
package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.persistence.Entity;
import hu.itkodex.commons.persistence.Repository;
import hu.itkodex.commons.persistence.query.Query;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class RepositoryException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 5962926287782501763L;
   protected static String defaultMessagePattern = "Invoking action: ''{0}'' of repository: ''{1}'' with object ''{2}'' caused error.";
   protected static String findMessagePattern = "Invoking action: ''{0}'' of repository: ''{1}'' caused error.";
   private Repository<?> repository;
   private String actionName = "";
   private Entity entity;

   public RepositoryException( Repository<?> repository, RepositoryAction repositoryAction, Entity entity ) {
      this(repository, repositoryAction, entity, null );
   }

   public RepositoryException( Repository<?> repository, RepositoryAction repositoryAction, Exception e ) {
      super( ExceptionHelper.defineMessage(
            RepositoryException.class,
            new Object[] { repositoryAction.name(), repository.getClass().getName() },
            findMessagePattern),
            e);
      this.repository = repository;
      this.actionName = repositoryAction.name();
   }

   public RepositoryException( Repository<?> repository, RepositoryAction repositoryAction, Query query ) {
      super( ExceptionHelper.defineMessage(
            RepositoryException.class,
            new Object[] { repositoryAction.name(), repository.getClass().getName() },
            findMessagePattern ) );
      this.repository = repository;
      this.actionName = repositoryAction.name();
   }

   public RepositoryException( Repository<?> repository, RepositoryAction repositoryAction, Entity entity, Exception e ) {
      super( ExceptionHelper.defineMessage(
            RepositoryException.class,
            new Object[] { repositoryAction.name(), repository.getClass().getName(), entity.getId() },
            defaultMessagePattern),
            e);
      this.repository = repository;
      this.actionName = repositoryAction.name();
      this.entity = entity;
   }

   protected RepositoryException( ExceptionHelper helper, Throwable cause ) {
      super( helper, cause );
   }
   
   public String getActionName() { return actionName; }

   public Entity getEntity() { return entity; }

   public Repository<?> getRepository() { return repository; }
}
