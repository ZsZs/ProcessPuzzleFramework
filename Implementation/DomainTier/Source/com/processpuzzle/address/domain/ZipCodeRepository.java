/*
Name: 
    - ZipCodeRepository

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
import com.processpuzzle.commons.persistence.query.BooleanOperator;
import com.processpuzzle.commons.persistence.query.BooleanOperators;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.IntegerAttributeCondition;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class ZipCodeRepository extends GenericRepository<ZipCode> {

   public ZipCodeRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   protected Object findByIdentityExpression( String identityExpression ) {
      return null;
   }

   public RepositoryResultSet<ZipCode> findAllZipCode( DefaultUnitOfWork work ) {
      return findAll( work );
   }

   public ZipCode findZipCodeById( DefaultUnitOfWork work, Integer id ) {
      return findById( work, id );
   }

   public ZipCode findByIdentityExpression( DefaultUnitOfWork work, Integer zipCode, String settlementName ) {
      // return (ZipCode) get("from ZipCode c where c.zipCode = ${zipCode} and c.settlement.name = ${settlementName}",
      // new Object[] { "zipCode", identityExpression, "settlementName", settlementName});
      DefaultQuery query = new DefaultQuery( ZipCode.class );
      query.getQueryCondition().addAttributeCondition( new IntegerAttributeCondition( "zipCode", zipCode, ComparisonOperators.EQUAL_TO ) );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "settlement.name", settlementName, ComparisonOperators.EQUAL_TO ) );
      query.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      RepositoryResultSet<ZipCode> results = findByQuery( work, query );
      if( results.size() == 1 ){
         return results.getEntityAt( 0 );
      }else{
         return null;
      }

   }

   public void addZipCode( DefaultUnitOfWork work, ZipCode zipCode ) {
      add( work, zipCode );
   }

   public void updateZipCode( DefaultUnitOfWork work, ZipCode zipCode ) {
      update( work, zipCode );
   }

   public void deleteZipCode( DefaultUnitOfWork work, ZipCode zipCode ) {
      delete( work, zipCode );
   }

}
