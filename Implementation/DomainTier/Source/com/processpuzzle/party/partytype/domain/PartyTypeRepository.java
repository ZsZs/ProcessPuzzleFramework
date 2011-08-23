package com.processpuzzle.party.partytype.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.Repository;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class PartyTypeRepository extends GenericRepository<PartyType> implements Repository<PartyType>{

   public PartyTypeRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super(strategy, applicationContext);
   }

   public Integer add( PartyType partyType ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Integer id = addPartyType( work, partyType );
      work.finish();
      return id;
   }
   public Integer addPartyType( DefaultUnitOfWork work, PartyType partyType ) {
      return add( work, PartyType.class, partyType );
   }
   
   public void delete( PartyType partyType ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      delete( work, partyType );
      work.finish();
   }
   public PartyType findByName ( String name ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      PartyType partyType = findByName( work, name );
      work.finish();
      return partyType;
   }
   
   public PartyType findByName ( DefaultUnitOfWork work, String name ) {
      DefaultQuery query = new DefaultQuery( PartyType.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition("name", name, ComparisonOperators.EQUAL_TO ));
      RepositoryResultSet<PartyType> results = super.findByQuery( work, query );
      if( results.size() == 1 ) return results.getEntityAt(0);
      else return null;
   }

   public PartyType findByPartyClassName( String partyClassName ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      DefaultQuery query = new DefaultQuery( PartyType.class );
      TextAttributeCondition classNameCondition = new TextAttributeCondition( "partyClassName", partyClassName, ComparisonOperators.EQUAL_TO );
      query.getQueryCondition().addAttributeCondition( classNameCondition );
      RepositoryResultSet<PartyType> resultSet = findByQuery( work, query );
      work.finish();
      if( resultSet != null && resultSet.size() == 1 ) return resultSet.getEntityAt( 0 );
      else return null;
   }

}
