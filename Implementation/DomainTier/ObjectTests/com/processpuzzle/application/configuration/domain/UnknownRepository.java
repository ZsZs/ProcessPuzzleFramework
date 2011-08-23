package com.processpuzzle.application.configuration.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;

import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.domain.TestEntity;

public class UnknownRepository extends GenericRepository<TestEntity> {

   public UnknownRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }
}
