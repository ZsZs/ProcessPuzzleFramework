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
