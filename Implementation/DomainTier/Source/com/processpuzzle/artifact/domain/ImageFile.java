package com.processpuzzle.artifact.domain;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class ImageFile extends FileStorage {
   
   private boolean isInnerPicture = false;
   private boolean isOuterPicture = false;

   protected ImageFile() {
      super();
   }

   public ImageFile(String name, ArtifactType type, User responsible) {
      super(name, type, responsible);
   }

   protected void instantiateVersion(String name, ArtifactType type, User responsible) {
      getVersions().put(new Integer(versions.size()+1), new ImageFileVersion(name, responsible, type));
   }

   public String getThumbnailSource() {
      return ((ImageFileVersion)latest()).getThumbnail();
   }

   public void setThumbnail(ImageFile thumbnail) {
      ((ImageFileVersion)latest()).setThumbnail(thumbnail);
   }
   
   public String getAsJpegSource() {
      return ((ImageFileVersion)latest()).getAsJpegSource();
   }

   public boolean getIsInnerPicture() {
      return isInnerPicture;
   }

   public void setIsInnerPicture(boolean isInnerPicture) {
      this.isInnerPicture = isInnerPicture;
   }

   public boolean getIsOuterPicture() {
      return isOuterPicture;
   }

   public void setIsOuterPicture(boolean isOuterPicture) {
      this.isOuterPicture = isOuterPicture;
   }
}
