/*
Name: 
    - DefaultArtifactRepository

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


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.PersistenceStrategy;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.commons.persistence.UnitOfWork;
import com.processpuzzle.commons.persistence.query.OrderSpecifier;
import com.processpuzzle.commons.persistence.query.OrderingDirections;
import com.processpuzzle.commons.persistence.query.Query;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.persistence.query.domain.IntegerAttributeCondition;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;
import com.processpuzzle.workflow.activity.artifact.ActionDataSheet;

public class DefaultArtifactRepository extends GenericArtifactRepository<Artifact> {
   private Class<? extends Artifact<?>> artifactClass;

   public DefaultArtifactRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public Integer add( UnitOfWork work, Artifact artifact ) {
      determineArtifactClass( artifact );
      return super.add( work, artifactClass, artifact );
   }

   public void deleteByName( UnitOfWork work, String name ) {
      delete( work, findByName( work, name ) );
   }

   public void delete( UnitOfWork work, Artifact<?> artifact ) {
      determineArtifactClass( artifact );
      super.delete( work, artifact );
   }

   public void update( UnitOfWork work, Artifact<?> artifact ) {
      determineArtifactClass( artifact );
      update( work, artifact );
   }

   public RepositoryResultSet<Artifact> findAll( UnitOfWork work ) {
      return super.findAll( work );
   }

   public Artifact<?> findByFullName( UnitOfWork work, String targetArtifactName ) {
      String folderName = targetArtifactName.substring( 0, targetArtifactName.lastIndexOf( "." ) );
      ArtifactFolderRepository artifactFolderRepository = applicationContext.getRepository( ArtifactFolderRepository.class );
      ArtifactFolder folder = artifactFolderRepository.findByPath( work, folderName );
      String artifactName = targetArtifactName.substring( targetArtifactName.lastIndexOf( "." ) + 1 );
      return findByFullName( work, artifactName, folder );
   }

   public Artifact<?> findByFullName( UnitOfWork work, String artifactName, ArtifactFolder artifactFolder ) {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor( "name", artifactName );
   
      ArtifactIdentity<Artifact<?>> identity = new ArtifactIdentity<Artifact<?>>( context );
      return findByIdentityExpression( work, identity );
   }

   public Artifact<?> findById( Integer id ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Artifact<?> artifact = findById( work, id );
      work.finish();
      return artifact;
   }
   
   public Artifact<?> findById( UnitOfWork work, Integer id ) {
      return super.findById( work, id );
   }
   
   @Override
   public Artifact findById( UnitOfWork work, Class artifactClass, Integer id ) {
      return super.findById( work, artifactClass, id );
   }

   public Artifact<?> findByName( String name ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Artifact<?> artifact = findByName( work, name );
      work.finish();
      return artifact;
   }

   public Artifact<?> findByName( UnitOfWork work, String name ) {
      DefaultQuery query = new DefaultQuery( Artifact.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "name", name, ComparisonOperators.EQUAL_TO ) );
      RepositoryResultSet<Artifact> resultSet = super.findByQuery( work, query );
      if( resultSet.size() == 1 )
         return resultSet.getEntityAt( 0 );
      else
         return null;
   }

   public Artifact<?> findByPath( String path ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Artifact<?> folder = findByPath( work, path );
      work.finish();
      return folder;
   }
   
   public Artifact<?> findByPath( UnitOfWork work, String path ) {
      DefaultQuery query = new DefaultQuery( Artifact.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "path", path, ComparisonOperators.EQUAL_TO ) );
      RepositoryResultSet<Artifact> possibleArtifacts = super.findByQuery( work, query );
   
      if( possibleArtifacts.size() == 1 ){
         return (Artifact<?>) possibleArtifacts.getEntityAt( 0 );
      }else
         return null;
   }

   public RepositoryResultSet<Artifact> findByType( UnitOfWork work, String typeName ) {
      DefaultQuery q = new DefaultQuery( Artifact.class );
      q.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "type.name", typeName, ComparisonOperators.EQUAL_TO ) );
      return findByQuery( work, q );
   }

   public RepositoryResultSet<Artifact> findByQuery( Query query ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      RepositoryResultSet<Artifact> resultSet = findByQuery( work, query );
      work.finish();
      return resultSet;
   }
   
   public RepositoryResultSet<Artifact> findByQuery( UnitOfWork work, DefaultQuery query ) {
      return super.findByQuery( work, query );
   }

   public RepositoryResultSet<Artifact> findDataSheetsByActionId( UnitOfWork work, Integer id ) {
      DefaultQuery q = new DefaultQuery( ActionDataSheet.class );
      q.getQueryCondition().addAttributeCondition( new IntegerAttributeCondition( "action.id", id, ComparisonOperators.EQUAL_TO ) );
      return findByQuery( work, q );
   }

   protected RepositoryResultSet<Artifact> findArtifactVersionsByName( UnitOfWork work, String artifactName ) {
      DefaultQuery query = new DefaultQuery( ArtifactVersion.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "name", artifactName, ComparisonOperators.EQUAL_TO ) );
      query.getQueryOrder().addOrderSpecifier( new OrderSpecifier( "versionNumber", OrderingDirections.Ascending ) );
      return findByQuery( work, query );
   }

   protected Object findByIdentityExpression( String identityExpression ) {
      return null;
   }

   @SuppressWarnings("unchecked")
   private void determineArtifactClass( Artifact<?> artifact ) {
      artifactClass = (Class<? extends Artifact<?>>) artifact.getClass();
   }
}