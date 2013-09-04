/*
Name: 
    - SystemArtifactsLoader

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

package com.processpuzzle.business.definition.domain;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.processpuzzle.application.resource.domain.HardCodedDataLoader;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactFolder;
import com.processpuzzle.artifact.domain.ArtifactFolderFactory;
import com.processpuzzle.artifact.domain.ArtifactFolderRepository;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact.domain.GenericArtifactRepository;
import com.processpuzzle.artifact.domain.RootArtifactFolder;
import com.processpuzzle.artifact.domain.RootArtifactFolderFactory;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroup;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroupRepository;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class SystemArtifactsLoader extends HardCodedDataLoader {
   private ArtifactTypeGroupRepository artifactTypeGroupRepository;
   private ArtifactFolderRepository artifactFolderRepository;
   private ArtifactFolderFactory artifactFolderFactory;
   private DefaultArtifactRepository artifactRepository;
   private ArtifactFolder rootFolder;
   private ArtifactFolder workflowsFolder;
   private ArtifactFolder rolesFolder;
   private ArtifactFolder artifactsFolder;
   private ArtifactFolder contentFolder;

   public SystemArtifactsLoader() {
      super();
      this.resultInPersistentObjects = true;
   }

   public void loadData() {
      super.loadData();

      artifactFolderFactory = applicationContext.getEntityFactory( ArtifactFolderFactory.class );
      artifactFolderRepository = applicationContext.getRepository( ArtifactFolderRepository.class );
      artifactRepository = applicationContext.getRepository( DefaultArtifactRepository.class );
      artifactTypeGroupRepository = applicationContext.getRepository( ArtifactTypeGroupRepository.class );

      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      createSystemFolders( work );
      work.finish();
      
      work.start();
      createSystemArtifacts( work );
      work.finish();

      work.start();
      createFoldersForInstances( work );
      work.finish();
   }

   private void createFoldersForInstances( DefaultUnitOfWork work ) {
      ArtifactTypeRepository artifactTypeRepository = applicationContext.getRepository( ArtifactTypeRepository.class );
      RepositoryResultSet<ArtifactType> artifactTypes = artifactTypeRepository.findAllArtifactTypes( work );
      
      for( ArtifactType artifactType : artifactTypes ) {
         if( !artifactType.isSingleton() ) {
            ArtifactFolder instancesFolder = artifactFolderFactory.create( artifactType.getInstanceFolderName() );
            ArtifactFolder containingFolder = discoverContainingFolder( artifactType.getInstanceFolderPath(), artifactType.getInstanceFolderName(), work );
            containingFolder.addChildArtifact( instancesFolder );
            artifactFolderRepository.add( instancesFolder );
            log.trace( "Instance folder: '" + instancesFolder.getPath() + "' was created." );
         }
      }
   }

   private ArtifactFolder createRootFolder( DefaultUnitOfWork work ) {
      RootArtifactFolderFactory rootFolderFactory = applicationContext.getEntityFactory( RootArtifactFolderFactory.class );
      rootFolder = artifactFolderFactory.create( ArtifactFolder.ROOT_ARTIFACT_FOLDER_NAME );
      artifactFolderRepository.add( work, rootFolder );
      return rootFolder;
   }

   private void createSystemArtifacts( DefaultUnitOfWork work ) {
      RepositoryResultSet<ArtifactTypeGroup> artifactGroups = artifactTypeGroupRepository.findAllArtifactTypeGroups( work );

      for( ArtifactTypeGroup artifactGroup : artifactGroups ){
         ArtifactFolder folder = artifactFolderFactory.create( artifactGroup.getName() );
         artifactsFolder.addChildArtifact( folder );
         artifactFolderRepository.update( artifactsFolder );
         artifactFolderRepository.add( folder );

         for( ArtifactType artifactType : artifactGroup.getArtifactTypes() ){
            if( artifactType.isCreateOnStartup() ) {
               Artifact<?> artifact = instantiateArtifact( artifactType );
               folder.addChildArtifact( artifact );
               artifactRepository.add( work, artifact );
               log.trace( "Artifact:'" + artifact.getName() + "' was instantiated and added to the folder:'" + folder.getPath() + "'" );
            }
         }
      }
      
      artifactFolderRepository.update( work, artifactsFolder );
   }

   private void createSystemFolders( DefaultUnitOfWork work ) {
      rootFolder = artifactFolderFactory.create( ArtifactFolder.ROOT_ARTIFACT_FOLDER_NAME );
      artifactFolderRepository.add( work, rootFolder );
      
      workflowsFolder = artifactFolderFactory.create( rootFolder, SystemArtifact.WORKFLOWS_FOLDER.getName() );
      rootFolder.addChildArtifact( workflowsFolder );
      artifactFolderRepository.add( work, workflowsFolder );

      rolesFolder = artifactFolderFactory.create( rootFolder, SystemArtifact.ROLES_FOLDER.getName() );
      rootFolder.addChildArtifact( rolesFolder );
      artifactFolderRepository.add( work, rolesFolder );

      artifactsFolder = artifactFolderFactory.create( rootFolder, SystemArtifact.ARTIFACTS_FOLDER.getName() );
      rootFolder.addChildArtifact( artifactsFolder );
      artifactFolderRepository.add( work, artifactsFolder );

      contentFolder = artifactFolderFactory.create( rootFolder, SystemArtifact.CONTENT_FOLDER.getName() );
      rootFolder.addChildArtifact( contentFolder );
      artifactFolderRepository.add( work, contentFolder );
      
      artifactFolderRepository.update( work, rootFolder );
   }

   private ArtifactFolder discoverContainingFolder( String instanceFolderPath, String instanceFolderName, DefaultUnitOfWork work ) {
      int indexOfNameStart = instanceFolderPath.lastIndexOf( instanceFolderName );
      String containingFolderPath = instanceFolderPath.substring( 0, indexOfNameStart -1 );
      ArtifactFolder containingFolder = artifactFolderRepository.findByPath( work, containingFolderPath );
      return containingFolder;
   }

   @SuppressWarnings("unchecked")
   private Artifact instantiateArtifact( ArtifactType artifactType ) {
      Artifact artifact = null;
      User creator = UserRequestManager.getInstance().currentUser();

      if( artifactType.isCreateOnStartup() ){
         String artifactClassName = artifactType.getArtifactClassName();
         if( artifactClassName != null && !artifactClassName.equals( "" ) ){
            Class<?> artifactClass = null;
            try{
               artifactClass = Class.forName( artifactClassName );
               try{
                  Class[] argumentClasses = new Class[] { String.class, ArtifactType.class, User.class };
                  Object[] arguments = new Object[] { artifactType.getName(), artifactType, creator };
                  Constructor artifactConstructor = artifactClass.getConstructor( argumentClasses );
                  artifact = (Artifact) artifactConstructor.newInstance( arguments );
               }catch( SecurityException e ){
                  throw new SystemArtifactInstantiationException( artifactClass.getName(), artifactType.getName() );
               }catch( NoSuchMethodException e ){
                  throw new SystemArtifactInstantiationException( artifactClass.getName(), artifactType.getName() );
               }catch( IllegalArgumentException e ){
                  throw new SystemArtifactInstantiationException( artifactClass.getName(), artifactType.getName() );
               }catch( InvocationTargetException e ){
                  throw new SystemArtifactInstantiationException( artifactClass.getName(), artifactType.getName() );
               }
            }catch( ClassNotFoundException e ){
               e.printStackTrace();
            }catch( InstantiationException e ){
               e.printStackTrace();
            }catch( IllegalAccessException e ){
               e.printStackTrace();
            }
         }
      }
      
      return artifact;
   }
}
