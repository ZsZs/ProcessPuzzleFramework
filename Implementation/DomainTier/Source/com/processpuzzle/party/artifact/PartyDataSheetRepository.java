package com.processpuzzle.party.artifact;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;

public class PartyDataSheetRepository extends GenericRepository<PartyDataSheet<?,?>> {

   public PartyDataSheetRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public RepositoryResultSet<PartyDataSheet<?,?>> findAll() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      RepositoryResultSet<PartyDataSheet<?,?>> results = findAll( work );
      work.finish();
      return results;
   }
   
   //comment out because ant javac failed as I can see we dont use it
   /*   @SuppressWarnings("unchecked") 
   public RepositoryResultSet<PartyDataSheet<?,?>> findAll( UnitOfWork work ) {
      return super.findAll( work, (Class<? extends PartyDataSheet<?, ?>>) PartyDataSheet.class );
   }*/
}
