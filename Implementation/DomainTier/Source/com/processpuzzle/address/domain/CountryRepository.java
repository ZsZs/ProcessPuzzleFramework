/*
Name: 
    - CountryRepository

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.processpuzzle.address.domain;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.PersistenceStrategy;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.commons.persistence.UnitOfWork;
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
