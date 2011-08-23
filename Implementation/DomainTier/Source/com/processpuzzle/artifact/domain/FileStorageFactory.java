package com.processpuzzle.artifact.domain;

import com.processpuzzle.application.security.domain.User;

public class FileStorageFactory extends ArtifactFactory<FileStorage> {

   public static FileStorage createFileStorage( String originalFileName, User responsible ) {
      return FileStorage.create( originalFileName, responsible );
   }

}
