package com.processpuzzle.party.artifact;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.GenericArtifactRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class UserDataSheetRepository extends GenericArtifactRepository<UserDataSheet> {

   public UserDataSheetRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public Integer addUser( DefaultUnitOfWork work, UserDataSheet dataSheet ) {
      return super.add( work, dataSheet );
   }
   
   public UserDataSheet findByName( String name ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      UserDataSheet dataSheet = this.findByName( work, name );
      work.finish();
      return dataSheet;
   }

   public UserDataSheet findByName( DefaultUnitOfWork work, String name ) {
      return super.findByName( work, name );
   }
   
   public UserDataSheet findByPath( String path ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      DefaultQuery query = new DefaultQuery( UserDataSheet.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "path", path, ComparisonOperators.EQUAL_TO ) );
      RepositoryResultSet<UserDataSheet> possibleArtifacts = super.findByQuery( work, query );
   
      if( possibleArtifacts.size() == 1 ){
         return (UserDataSheet) possibleArtifacts.getEntityAt( 0 );
      }else
         return null;
   }
}
