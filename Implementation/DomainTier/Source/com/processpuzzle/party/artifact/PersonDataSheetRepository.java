/*
Name: 
    - PersonDataSheetRepository

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

package com.processpuzzle.party.artifact;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.GenericArtifactRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class PersonDataSheetRepository extends GenericArtifactRepository<PersonDataSheet> {

   public PersonDataSheetRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public Integer add( DefaultUnitOfWork work, PersonDataSheet dataSheet ) {
      return super.add( work, dataSheet );
   }

   public PersonDataSheet findByName( String personName ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      PersonDataSheet catalog = this.findByName( work, personName );
      work.finish();
      return catalog;
   }

   public PersonDataSheet findByName( DefaultUnitOfWork work, String personName ) {
      return super.findByName( work, personName );
   }

   public PersonDataSheet findById( DefaultUnitOfWork work, Integer id ) {
      return super.findById( work, id );
   }

   public RepositoryResultSet<PersonDataSheet> findByNameStart( DefaultUnitOfWork work, String start ) {
      DefaultQuery query = new DefaultQuery( PersonDataSheet.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "person.name", start + "%", ComparisonOperators.LIKE ) );
      return findByQuery( work, query );
   }

   public PersonDataSheet findByPath( String path ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      DefaultQuery query = new DefaultQuery( PersonDataSheet.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "path", path, ComparisonOperators.EQUAL_TO ) );
      RepositoryResultSet<PersonDataSheet> possibleArtifacts = super.findByQuery( work, query );

      if( possibleArtifacts.size() == 1 ){
         return (PersonDataSheet) possibleArtifacts.getEntityAt( 0 );
      }else
         return null;
   }
}
