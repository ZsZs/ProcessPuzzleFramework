/*
Name: 
    - ArtifactTypeGroupRepository

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

/*
 * Created on Jul 10, 2006
 */
package com.processpuzzle.artifact_type_group.domain;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.PersistenceStrategy;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.commons.persistence.UnitOfWork;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.domain.RepositoryException;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactTypeGroupRepository extends GenericRepository<ArtifactTypeGroup> {

   public ArtifactTypeGroupRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public void add( ArtifactTypeGroup typeGroup ) {
      UnitOfWork work = new DefaultUnitOfWork( true );
      add( work, typeGroup );
      work.finish();
   }

   public void addArtifactTypeGroup( DefaultUnitOfWork work, ArtifactTypeGroup typeGroup ) {
      add( work, typeGroup );
   }

   public void delete( ArtifactTypeGroup typeGroup ) {
      UnitOfWork work = new DefaultUnitOfWork( true );
      delete( work, typeGroup );
      work.finish();
   }

   public void deleteArtifactTypeGroup( DefaultUnitOfWork work, ArtifactTypeGroup typeGroup ) {
      delete( work, typeGroup );
   }

   public void updateArtifactTypeGroup( DefaultUnitOfWork work, ArtifactTypeGroup typeGroup ) {
      update( work, typeGroup );
   }

   public ArtifactTypeGroup findById( DefaultUnitOfWork work, Integer id ) {
      return super.findById( work, id );
   }

   public RepositoryResultSet<ArtifactTypeGroup> findAllArtifactTypeGroups( DefaultUnitOfWork work ) {
      return findAll( work );
   }

   public ArtifactTypeGroup findByName( String groupName ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      ArtifactTypeGroup group = findByName( work, groupName );
      work.finish();
      return group;
   }

   public ArtifactTypeGroup findByName( UnitOfWork work, String groupName ) {
      DefaultQuery query = new DefaultQuery( ArtifactTypeGroup.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "name", groupName, ComparisonOperators.EQUAL_TO ) );
      ArtifactTypeGroup typeGroup = null;
      try{
         RepositoryResultSet<ArtifactTypeGroup> resultSet = findByQuery( work, query );
         if( resultSet.size() == 1 )
            typeGroup = resultSet.getEntityAt( 0 );
      }catch( RepositoryException e ){
         log.debug( e.getMessage() );
         return null;
      }
      return typeGroup;
   }

   protected Object findByIdentityExpression( String identityExpression ) {
      // TODO Auto-generated method stub
      return null;
   }

}
