/*
Name: 
    - ArtifactFolderRepository

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


import java.util.ArrayList;
import java.util.Iterator;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.PersistenceStrategy;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.commons.persistence.UnitOfWork;
import com.processpuzzle.commons.persistence.query.Query;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.domain.RepositoryAction;
import com.processpuzzle.persistence.domain.RepositoryException;
import com.processpuzzle.persistence.domain.SimpleResultSet;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class ArtifactFolderRepository extends GenericRepository<ArtifactFolder> {

   public ArtifactFolderRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContaxt ) {
      super( strategy, applicationContaxt );
   }

   public Integer add( ArtifactFolder folder ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Integer id = add( work, folder );
      work.finish();
      return id;
   }

   public Integer add( UnitOfWork work, ArtifactFolder folder ) {
      return super.add( work, ArtifactFolder.class, folder );
   }

   public void delete( ArtifactFolder folder ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      delete( work, folder );
      work.finish();      
   }
   
   public void delete( UnitOfWork work, ArtifactFolder folder ) {
      super.delete( work, folder );
   }
   
   public void update( ArtifactFolder folder ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      update( work, folder );
      work.finish();
   }
   
   public void update( UnitOfWork work, ArtifactFolder folder ) {
      super.update( work, folder );
   }

   public ArtifactFolder findByPath( String path ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      ArtifactFolder folder = findByPath( work, path );
      work.finish();
      return folder;
   }
   
   public ArtifactFolder findByPath( UnitOfWork work, String path ) {
      DefaultQuery query = new DefaultQuery( ArtifactFolder.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "path", path, ComparisonOperators.EQUAL_TO ) );
      RepositoryResultSet<ArtifactFolder> possibleFolders = super.findByQuery( work, query );
   
      if( possibleFolders.size() == 1 ){
         return (ArtifactFolder) possibleFolders.getEntityAt( 0 );
      }else
         throw new RepositoryException( null, RepositoryAction.findByQuery, query );
   }

   public ArtifactFolder findByName( String name ) {
      UnitOfWork work = new DefaultUnitOfWork( true );
      ArtifactFolder folder = findByName( work, name );
      work.finish();
      return folder;
   }
   
   public ArtifactFolder findByName( UnitOfWork work, String name ) {
      Query query = new DefaultQuery( ArtifactFolder.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "name", name, ComparisonOperators.EQUAL_TO ) );
      RepositoryResultSet<ArtifactFolder> resultSet = findByQuery( work, query );
      if( resultSet.size() == 1 ) return resultSet.getEntityAt( 0 );
      else return null;
   }
   
   public RootArtifactFolder findRootFolder() {
      Query query = new DefaultQuery( ArtifactFolder.class );
      String rootFolderName = RootArtifactFolder.ROOT_ARTIFACT_FOLDER_NAME;
      TextAttributeCondition condition = new TextAttributeCondition( "name", rootFolderName, ComparisonOperators.EQUAL_TO );
      query.getQueryCondition().addAttributeCondition( condition );

      UnitOfWork work = new DefaultUnitOfWork( true );
      RepositoryResultSet<ArtifactFolder> resultSet = super.findByQuery( work, query );
      work.finish();
      
      if( resultSet.size() == 1 ) return (RootArtifactFolder) resultSet.getEntityAt( 0 );
      else return null;
   }

   public RepositoryResultSet<ArtifactFolder> findRootArtifactFolders() {
      return null;
   }

   //Private helper methods
   @SuppressWarnings("unused")
   private RepositoryResultSet<ArtifactFolder> separateFoldersByParent( RepositoryResultSet<Artifact<?>> possibleFolders,
         String parentFolderName, int level ) {
      ArrayList<ArtifactFolder> folders = new ArrayList<ArtifactFolder>();
      for( Iterator<Artifact<?>> iter = possibleFolders.iterator(); iter.hasNext(); ){
         ArtifactFolder artifactFolder = (ArtifactFolder) iter.next();
         boolean l = true;
         ArtifactFolder folder = artifactFolder;
         for( int i = 0; i < level; i++ ){
            if( folder.getContainingFolder() != null )
               folder = folder.getContainingFolder();
            else
               l = false;
         }
         if( l && folder.getContainingFolder().getName().equals( parentFolderName ) ){
            folders.add( artifactFolder );
         }
      }
      return new SimpleResultSet<ArtifactFolder>( folders );
   }

}
