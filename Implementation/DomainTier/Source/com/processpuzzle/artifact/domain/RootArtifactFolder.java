package com.processpuzzle.artifact.domain;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;

public class RootArtifactFolder extends ArtifactFolder {
   
   protected RootArtifactFolder() {
      //Required by Hibernate.
   }
   
   public RootArtifactFolder( ArtifactType type, User creator ) {
      super( ROOT_ARTIFACT_FOLDER_NAME, type, creator, null );
   }
   
   public @Override ArtifactFolder getContainingFolder() {
      throw new NoParentFolderException( this );
   }   
}
