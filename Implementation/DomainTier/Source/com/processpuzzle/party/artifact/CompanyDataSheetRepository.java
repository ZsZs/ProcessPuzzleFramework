package com.processpuzzle.party.artifact;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.GenericArtifactRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class CompanyDataSheetRepository extends GenericArtifactRepository<CompanyDataSheet> {

   public CompanyDataSheetRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext  ) {
      super( strategy, applicationContext );
   }

   public Integer add( DefaultUnitOfWork work, CompanyDataSheet dataSheet ) {
      return super.add( work, dataSheet );
   }

   public CompanyDataSheet findByName( String organizationName ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      CompanyDataSheet catalog = this.findByName( work, organizationName );
      work.finish();
      return catalog;
   }
   
   public CompanyDataSheet findByName( DefaultUnitOfWork work, String organizationName ) {
      return super.findByName( work, organizationName );
   }
   
   public CompanyDataSheet findById( DefaultUnitOfWork work, Integer id ) {
      return super.findById( work, id );
   }
   
   public CompanyDataSheet findByPath( String path ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      DefaultQuery query = new DefaultQuery( CompanyDataSheet.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "path", path, ComparisonOperators.EQUAL_TO ) );
      RepositoryResultSet<CompanyDataSheet> possibleArtifacts = super.findByQuery( work, query );
   
      if( possibleArtifacts.size() == 1 ){
         return (CompanyDataSheet) possibleArtifacts.getEntityAt( 0 );
      }else
         return null;
   }

}
