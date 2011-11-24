/*
Name: 
    - ArtifactFolderFactory

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
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.artifact_type.domain.SystemArtifactTypes;
import com.processpuzzle.fundamental_types.domain.AssertionException;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class ArtifactFolderFactory extends ArtifactFactory<ArtifactFolder> {
   
   public ArtifactFolder create( String name ) {
      return create( null, name );
   }
   
   public ArtifactFolder create( ArtifactFolder parentFolder, String folderName ) {
      if( folderName == null || folderName.equals( "" ) ) throw new AssertionException( "ArtifactFolder.name can't be null or empty." );
      
      User creator = UserRequestManager.getInstance().currentUser();
      ArtifactType folderType = findArtifactFolderType();
      if( folderType == null ) throw new NoneExistingArtifactTypeException( SystemArtifactTypes.ARTIFACT_FOLDER.getName(), folderName );
      ArtifactFolder newFolder = new ArtifactFolder( folderName, folderType, creator, parentFolder );
      return newFolder;
   }
   
   //Private helper methods
   private ArtifactType findArtifactFolderType() {
      ArtifactTypeRepository typeRepository = findRepository( ArtifactTypeRepository.class );
      return typeRepository.findByName( SystemArtifactTypes.ARTIFACT_FOLDER.getName() );
   }
   
   private ArtifactFolder findArtifactFolder( String folderName, ArtifactFolder parentFolder ) {
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      ArtifactFolderRepository artifactFolderRepository = applicationContext.getRepository( ArtifactFolderRepository.class );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      String fullPath = parentFolder != null ? parentFolder.getPath() + ArtifactFolder.PATH_SEPARATOR + folderName : folderName;  
      ArtifactFolder folder = artifactFolderRepository.findByPath( work, fullPath );
      work.finish();
      return folder;
   }
}
