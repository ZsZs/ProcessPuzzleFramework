/*
Name: 
    - LastIdNumberRepository

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

package com.processpuzzle.fundamental_types.uniqueidentifier.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class LastIdNumberRepository extends GenericRepository<LastIdNumber> {
   private boolean isInitialized = false;
   
   // Constuctors   
   public LastIdNumberRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext) {
      super(strategy, applicationContext);
    }


   public LastIdNumber findLatestIdByType( String idType ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      LastIdNumber  lastIdNumber = findLatestIdByType(work, idType);
      work.finish();
      return lastIdNumber;
   }
   
   public LastIdNumber findLatestIdByType( DefaultUnitOfWork work, String idType ) {
      if (!isInitialized) {
         initialize();
      }
      DefaultQuery query = new DefaultQuery( LastIdNumber.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition("idType", idType, ComparisonOperators.EQUAL_TO ));
      RepositoryResultSet<LastIdNumber> results = super.findByQuery( work, query );
      if( results.size() == 1 ) return results.getEntityAt(0);
      else return null;  
    }

   public LastIdNumber initializeLastIdNumber( String idType, Integer initialIdNumber) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      LastIdNumber lastIdNumber = initializeLastIdNumber(work, idType, initialIdNumber);
      work.finish();
      return lastIdNumber;
   }
   
   public LastIdNumber initializeLastIdNumber( DefaultUnitOfWork work, String idType, Integer initialIdNumber) {
      LastIdNumber lastIdNumber = new LastIdNumber( idType, initialIdNumber );
      addLastIdNumber( work, lastIdNumber );

      return lastIdNumber;
   }
      
   private Integer addLastIdNumber(DefaultUnitOfWork work, LastIdNumber lastIdNumber) {
      return super.add( work, lastIdNumber);
   }
   
   public LastIdNumber incrementLastIdNumber( String idType ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      LastIdNumber lastIdNumber = incrementLastIdNumber(work, idType);
      work.finish();
      return lastIdNumber;
   }

   public LastIdNumber incrementLastIdNumber( DefaultUnitOfWork work, String idType ) {
      LastIdNumber idNumberByType = findLatestIdByType( work, idType);
      Integer currentLatestNumberByType = idNumberByType.getLatestNumber();
      
      idNumberByType.setLatestNumber( currentLatestNumberByType+1 );
      
      updateLastIdNumber( work, idNumberByType );
      
      return idNumberByType;
   }

   private void updateLastIdNumber( DefaultUnitOfWork work, LastIdNumber lastIdNumber ) {
      super.update( work, lastIdNumber );
   }

   public void deleteLastIdNumberByType( String idType) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      deleteLastIdNumberByType( work, idType );
      work.finish();
      
   }
   public void deleteLastIdNumberByType( DefaultUnitOfWork work, String idType) {
      LastIdNumber lastIdNumber = findLatestIdByType( work, idType);
      if (lastIdNumber != null) {
         super.delete( work, lastIdNumber );
      }
   }

   private void initialize() {
      initializeDefaultOrderIdentifier();
      isInitialized = true;
   }

   private void initializeDefaultOrderIdentifier() {
      initializeLastIdNumber( "com.processpuzzle.order.domain.order.OrderIdentifier", 0 );
      initializeLastIdNumber( "com.processpuzzle.order.domain.order.OrderLineIdentifier", 0 );
   }

}
