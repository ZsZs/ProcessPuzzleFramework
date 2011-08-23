package com.processpuzzle.application.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;

public class ApplicationEventRepository extends GenericRepository<ApplicationEvent> {

   public ApplicationEventRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public ApplicationEvent findBackupEventById( DefaultUnitOfWork work, Integer id ) {
      return findById( work, BackupEvent.class, id );
   }
}
