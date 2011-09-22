package com.processpuzzle.address.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;
import hu.itkodex.commons.persistence.UnitOfWork;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class CountryRepository extends GenericRepository<Country> {

   public CountryRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public RepositoryResultSet<Country> findAllCountry( UnitOfWork work ) {
      return findAll( work );
   }

   public Country findCountryByName( String countryName ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Country country = this.findCountryByName( work, countryName );
      work.finish();
      return country;
   }

   public Country findCountryByName( UnitOfWork work, String countryName ) {
      DefaultQuery query = new DefaultQuery( Country.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "name", countryName, ComparisonOperators.EQUAL_TO ) );
      RepositoryResultSet<Country> results = findByQuery( work, query );
      if( results.size() == 1 )
         return results.getEntityAt( 0 );
      else
         return null;
   }

   // protected Object findByIdentityExpression(String identityExpression) {
   // return (Country) get("from Country c where c.name = ${name}",
   // new Object[] { "name", identityExpression });
   // }

   public void addCountry( UnitOfWork work, Country country ) {
      add( work, country );
   }

   public void updateCountry( UnitOfWork work, Country country ) {
      update( work, country );
   }

   public void deleteCountry( DefaultUnitOfWork work, Country country ) {
      delete( work, country );
   }
}
