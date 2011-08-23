package com.processpuzzle.party.artifact;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;

public class OrganizationDataSheetRepository extends GenericRepository<OrganizationDataSheet<?,?>> {

   public OrganizationDataSheetRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public RepositoryResultSet<OrganizationDataSheet<?,?>> findAllOrganizationDataSheet( DefaultUnitOfWork work ) {
      return findAll( work, OrganizationUnitDataSheet.class );
   }
}
