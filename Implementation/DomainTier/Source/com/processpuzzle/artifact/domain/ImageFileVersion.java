package com.processpuzzle.artifact.domain;

import java.util.Date;
import java.util.Iterator;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.util.domain.GeneralService;

public class ImageFileVersion extends FileVersion {

   public ImageFileVersion() {
      super();
   }

   public ImageFileVersion( String name, User responsible, ArtifactType type ) {
      super( name, responsible, type );
   }

   public ImageFileVersion( String name, User responsible, Date creationDate ) {
      super( name, responsible, creationDate );
   }

   public ImageFileVersion( String name, User responsible ) {
      super( name, responsible );
   }

   private ImageFile findThumbnail() {
      for( Iterator<?> iter = getRelatedArtifacts().iterator(); iter.hasNext(); ){
         Artifact<?> relatedArtifact = (Artifact<?>) iter.next();
         if( (relatedArtifact instanceof ImageFile) && (((ImageFile) relatedArtifact).getOriginalFileName().indexOf( "thumbnail" ) != -1) ){
            return (ImageFile) relatedArtifact;
         }
      }
      return null;
   }

   public String getThumbnail() {
      ImageFile thumbnail = findThumbnail();
      String thumbnailSource = ProcessPuzzleContext.getInstance().getProperty( ProcessPuzzleContext.UPLOADED_FILES_FOLDER ) + "\\"
            + thumbnail.getContainingFolder().getName() + "\\";
      return thumbnailSource + thumbnail.getOriginalFileName();
   }

   public void setThumbnail( ImageFile thumbnail ) {
      ImageFile thumbNail = findThumbnail();
      getRelatedArtifacts().remove( thumbNail );
      getRelatedArtifacts().add( thumbnail );
   }

   private ImageFile findJpeg() {
      String originalFileNameWithoutExtension = GeneralService.getFirstToken( getOriginalFileName(), "." );
      for( Iterator<?> iter = getRelatedArtifacts().iterator(); iter.hasNext(); ){
         Artifact<?> relatedArtifact = (Artifact<?>) iter.next();
         if( (relatedArtifact instanceof ImageFile) && (((ImageFile) relatedArtifact).getOriginalFileName().indexOf( originalFileNameWithoutExtension ) != -1)
               && (((ImageFile) relatedArtifact).getContentType().indexOf( "jpeg" ) != -1)
               && (((ImageFile) relatedArtifact).getOriginalFileName().indexOf( "thumbnail" ) == -1) ){
            return (ImageFile) relatedArtifact;
         }
      }
      return null;
   }

   public String getAsJpegSource() {
      String fileExtension = GeneralService.getLastToken( getOriginalFileName(), "." );
      if( fileExtension.equals( "jpg" ) ){
         String jpegSource = ProcessPuzzleContext.getInstance().getProperty( ProcessPuzzleContext.UPLOADED_FILES_FOLDER ) + "\\"
               + getContainingFolder().getName() + "\\";
         return jpegSource + getName();
      }else{
         ImageFile jpeg = findJpeg();
         String jpegSource = ProcessPuzzleContext.getInstance().getProperty( ProcessPuzzleContext.UPLOADED_FILES_FOLDER ) + "\\"
               + jpeg.getContainingFolder().getName() + "\\";
         return jpegSource + jpeg.getName();
      }
   }

}
