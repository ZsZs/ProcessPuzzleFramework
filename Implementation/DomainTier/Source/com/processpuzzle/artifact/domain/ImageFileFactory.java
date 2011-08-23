package com.processpuzzle.artifact.domain;


public class ImageFileFactory extends ArtifactFactory<ImageFile> {

   public ImageFile createImageFile( String originalFileName ) {
      return create( originalFileName);
   }

}
