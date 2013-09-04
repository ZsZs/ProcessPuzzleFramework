/*
Name: 
    - ArtifactTypeRepository

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

package com.processpuzzle.artifact_type.domain;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.PersistenceStrategy;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.commons.persistence.UnitOfWork;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class ArtifactTypeRepository extends GenericRepository<ArtifactType> {

   public ArtifactTypeRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public void add( ArtifactType artifactType ) {
      UnitOfWork work = new DefaultUnitOfWork( true );
      add( work, artifactType );
      work.finish();
   }

   public void addArtifactType( DefaultUnitOfWork work, ArtifactType artifactType ) {
      add( work, artifactType );
   }

   public void delete( ArtifactType artifactType ) {
      UnitOfWork work = new DefaultUnitOfWork( true );
      delete( work, artifactType );
      work.finish();
   }

   public void deleteArtifactType( DefaultUnitOfWork work, ArtifactType artifactType ) {
      delete( work, artifactType );
   }

   public void deleteArtifactType( DefaultUnitOfWork work, Integer id ) {
      ArtifactType artifactType = findById( work, id );
      if( artifactType != null )
         deleteArtifactType( work, artifactType );
   }

   public void updateArtifactTypes( DefaultUnitOfWork work, ArtifactType artifactType ) {
      update( work, artifactType );
   }

   public RepositoryResultSet<ArtifactType> findAllArtifactTypes( DefaultUnitOfWork work ) {
      return findAll( work );
   }

   public ArtifactType findByArtifactClassName( String className ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      DefaultQuery query = new DefaultQuery( ArtifactType.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "artifactClassName", className, ComparisonOperators.EQUAL_TO ) );
      RepositoryResultSet<ArtifactType> resultSet = findByQuery( work, query );
      work.finish();
      if( resultSet.size() == 1 )
         return resultSet.getEntityAt( 0 );
      else
         return null;
   }

   public ArtifactType findById( DefaultUnitOfWork work, Integer id ) {
      return super.findById( work, id );
   }

   public ArtifactType findByName( String typeName ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      ArtifactType artifactType = findArtifactTypeByName( work, typeName );
      work.finish();
      return artifactType;
   }

   public ArtifactType findArtifactTypeByName( UnitOfWork work, String typeName ) {
      ArtifactTypeIdentity identity = new ArtifactTypeIdentity();
      identity.getQueryContext().addTextValueFor( "nameValue", typeName );
      return findByIdentityExpression( work, identity );
   }

   protected Object findByIdentityExpression( DefaultUnitOfWork work, String identityExpression ) {
      return findArtifactTypeByName( work, identityExpression );
   }

   // public void addArtifactViewType(UnitOfWork work,ArtifactViewType viewType) {
   // add(work, viewType);
   // }

   // @Override
   // protected void setSupportedAggregateRootClass() {
   // // TODO Auto-generated method stub
   //      
   // }
}
