package com.processpuzzle.artifact.domain;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class ImageFile_ImageView extends CustomFormView<ImageFile> {

   public ImageFile_ImageView( ImageFile artifact, String name, ArtifactViewType type ) {
      super(artifact, name, type);
   }

   public String getAsJpegSource() {
      return ((ImageFile)parentArtifact).getAsJpegSource();
   }

   @Override
   public void initializeView() {
      // TODO Auto-generated method stub
   }
}
