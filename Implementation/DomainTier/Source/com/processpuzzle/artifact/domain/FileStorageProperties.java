package com.processpuzzle.artifact.domain;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.util.domain.GeneralService;

public class FileStorageProperties<F extends FileStorage> extends PropertyView<F> {

   public FileStorageProperties( F artifact, String name, ArtifactViewType viewType ) {
      super( artifact, name, viewType );
   }

   public String getSourceName() {
      return parentArtifact.getSourceName();
   }

   public String getSourcePath() {
      return parentArtifact.getSourcePath();
   }

   public String getContentType() {
      return parentArtifact.getContentType();
   }

   public String getOriginalFileName() {
      return parentArtifact.getOriginalFileName();
   }

   public String getSize() {
      return parentArtifact.getSize().toString();
   }

   public String getSource() {
      return parentArtifact.getSource();
   }

   public String getUploadDate() {
      return GeneralService.dateToString( parentArtifact.getUploadDate() );
   }
}
