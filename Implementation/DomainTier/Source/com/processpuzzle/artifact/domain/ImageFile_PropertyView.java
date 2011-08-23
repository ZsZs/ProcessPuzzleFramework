package com.processpuzzle.artifact.domain;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class ImageFile_PropertyView extends FileStorageProperties<ImageFile> {

   public ImageFile_PropertyView( ImageFile artifact, String name, ArtifactViewType viewType ) {
      super( artifact, name, viewType );
   }

   public String getThumbnailSource() {
      return parentArtifact.getThumbnailSource();
   }
}
