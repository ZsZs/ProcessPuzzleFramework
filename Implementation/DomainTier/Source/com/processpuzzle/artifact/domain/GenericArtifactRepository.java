/*
Name: 
    - GenericArtifactRepository

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

package com.processpuzzle.artifact.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;
import hu.itkodex.commons.persistence.UnitOfWork;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public abstract class GenericArtifactRepository<A extends Artifact> extends GenericRepository<A> {
   protected ArtifactTypeRepository artifactTypeRepository;
   protected ArtifactFolderRepository artifactFolderRepository;
   private ArtifactType artifactType;
   private boolean isInitialized = false;
   
   public GenericArtifactRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public Integer add( UnitOfWork work, A artifact ) {
      if( !isInitialized ) init();
      Integer artifactId = super.add( work, artifact );
      ArtifactFolder instanceFolder = findInstanceFolder( work );
      instanceFolder.addChildArtifact( artifact );
      artifactFolderRepository.update( work, instanceFolder );
      return artifactId;
   }

   public A findByName( UnitOfWork work, String name ) {
      if( !isInitialized ) init();
      ArtifactType type = getArtifactType();
      DefaultQuery query = new DefaultQuery( supportedAggregateRootClass );
      if( type != null ) {
         String path = type.getInstanceFolderPath() + ArtifactFolder.PATH_SEPARATOR + name;
         query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "path", path, ComparisonOperators.EQUAL_TO ));         
      }else {
         query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "name", name, ComparisonOperators.EQUAL_TO ));
      }
      
      RepositoryResultSet<A> resultSet = super.findByQuery( work, query );
      if( resultSet.size() == 1 )
         return resultSet.getEntityAt( 0 );
      else
         return null;      
   }
   
   protected ArtifactType getArtifactType() {
      if( artifactType == null )
         artifactType = artifactTypeRepository.findByArtifactClassName( supportedAggregateRootClass.getName() );
      return artifactType;
   }
   
   private ArtifactFolder findInstanceFolder( UnitOfWork work ) {
      String instanceFolderPath = getArtifactType().getInstanceFolderPath();
      return artifactFolderRepository.findByPath( work, instanceFolderPath );
   }
   
   private void init() {
      artifactTypeRepository = applicationContext.getRepository( ArtifactTypeRepository.class );
      artifactFolderRepository = applicationContext.getRepository( ArtifactFolderRepository.class );
   }
   
}
