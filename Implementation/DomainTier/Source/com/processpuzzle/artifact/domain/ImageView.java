package com.processpuzzle.artifact.domain;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class ImageView extends CustomFormView<ImageFile> {

   ImageView( ImageFile artifact, String name, ArtifactViewType type) {
      super(artifact, name, type);
   }

   public String getSourcePath() {
      return ProcessPuzzleContext.getInstance().getProperty(ProcessPuzzleContext.UPLOADED_FILES_FOLDER)+"\\"+((ImageFile)parentArtifact).getContainingFolder().getName()+"\\"+((ImageFile)parentArtifact).getOriginalFileName()+".jpg";
   }

   @Override
   public void initializeView() {
      // TODO Auto-generated method stub
   }
   
}
