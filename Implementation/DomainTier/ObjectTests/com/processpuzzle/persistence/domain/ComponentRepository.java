package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;

public class ComponentRepository extends GenericRepository<Component>{

   public ComponentRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext) {
      super( strategy, applicationContext );
   }
   
   public Integer add( Component component ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Integer id = add( work, component );
      work.finish();
      return id;
   }
}
