/*
Name: 
    - UserDataSheetRepository

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
