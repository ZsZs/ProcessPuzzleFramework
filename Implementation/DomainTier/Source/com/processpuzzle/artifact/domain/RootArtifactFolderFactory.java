package com.processpuzzle.artifact.domain;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;

public class RootArtifactFolderFactory extends ArtifactFactory<ArtifactFolder> {

   public ArtifactFolder create() {
      ArtifactType artifactType = findTypeFor( artifactClass );      
      if( artifactType == null ) throw new NoneExistingArtifactTypeException( artifactClass.getSimpleName(), ArtifactFolder.ROOT_ARTIFACT_FOLDER_NAME );

      Class<?>[] argumentClasses = new Class[] { ArtifactType.class, User.class };
      Object[] arguments = new Object[] { artifactType, creator };
      ArtifactFolder rootFolder = instantiateEntity( argumentClasses, arguments );
      checkEntityIdentityCollition( rootFolder.getDefaultIdentity() );
      return rootFolder;
   }
}
