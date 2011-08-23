package com.processpuzzle.address.artifact;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.GenericArtifactRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class SettlementDataSheetRepository extends GenericArtifactRepository<SettlementDataSheet> {

   public SettlementDataSheetRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public Integer add( SettlementDataSheet dataSheet ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Integer id = this.add( work, dataSheet );
      work.finish();
      return id;
   }

   public Integer add( DefaultUnitOfWork work, SettlementDataSheet dataSheet ) {
      return super.add( work, dataSheet );
   }

   public void delete( SettlementDataSheet dataSheet ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      this.delete( work, dataSheet );
      work.finish();
   }

   public void delete( DefaultUnitOfWork work, SettlementDataSheet dataSheet ) {
      super.delete( work, dataSheet );
   }

   public RepositoryResultSet<SettlementDataSheet> findAll( DefaultUnitOfWork work ) {
      return findAll( work, SettlementDataSheet.class );
   }

   public SettlementDataSheet findById( DefaultUnitOfWork work, Integer settlementId ) {
      return (SettlementDataSheet) findById( work, SettlementDataSheet.class, settlementId );
   }

   public RepositoryResultSet<SettlementDataSheet> findByNameStart( DefaultUnitOfWork work, String start ) {
      DefaultQuery query = new DefaultQuery( SettlementDataSheet.class );
      query.getQueryCondition().addAttributeCondition(
            new TextAttributeCondition( "settlement.name", start + "%", ComparisonOperators.LIKE ) );
      return findByQuery( work, query );
   }

   public SettlementDataSheet findByPath( String path ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      DefaultQuery query = new DefaultQuery( SettlementDataSheet.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "path", path, ComparisonOperators.EQUAL_TO ) );
      RepositoryResultSet<SettlementDataSheet> possibleArtifacts = super.findByQuery( work, query );

      if( possibleArtifacts.size() == 1 ){
         return (SettlementDataSheet) possibleArtifacts.getEntityAt( 0 );
      }else
         return null;
   }
}
