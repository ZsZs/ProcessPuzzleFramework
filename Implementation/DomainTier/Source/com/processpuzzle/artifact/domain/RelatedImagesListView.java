package com.processpuzzle.artifact.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class RelatedImagesListView extends ArtifactView {

   public RelatedImagesListView( Artifact<?> artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public void initializeView() {}

   public Collection getRelatedImagesPropertyViews() {
      Set<PropertyView> propertyViews = new HashSet<PropertyView>();
      Collection relatedArtifacts = getParentArtifact().getRelatedArtifacts();
      for( Iterator iterator = relatedArtifacts.iterator(); iterator.hasNext(); ){
         Artifact artifact = (Artifact) iterator.next();
         if( artifact instanceof ImageFile ){
            if( !((ImageFile) artifact).getIsInnerPicture() && !((ImageFile) artifact).getIsOuterPicture() )
               propertyViews.add( artifact.getPropertyView() );
         }
      }
      return propertyViews;
   }

   public PropertyView<?> getInnerPicturePropertyView() {
      Collection<?> relatedArtifacts = getParentArtifact().getRelatedArtifacts();
      for( Iterator<?> iterator = relatedArtifacts.iterator(); iterator.hasNext(); ){
         Artifact<?> artifact = (Artifact<?>) iterator.next();
         if( artifact instanceof ImageFile ){
            if( ((ImageFile) artifact).getIsInnerPicture() )
               return artifact.getPropertyView();
         }
      }
      return null;
   }

   public PropertyView<?> getOuterPicturePropertyView() {
      Collection<?> relatedArtifacts = getParentArtifact().getRelatedArtifacts();
      for( Iterator<?> iterator = relatedArtifacts.iterator(); iterator.hasNext(); ){
         Artifact<?> artifact = (Artifact<?>) iterator.next();
         if( artifact instanceof ImageFile ){
            if( ((ImageFile) artifact).getIsOuterPicture() )
               return artifact.getPropertyView();
         }
      }
      return null;
   }

   public boolean getHasInnerPicture() {
      return getInnerPicturePropertyView() != null;
   }

   public boolean getHasOuterPicture() {
      return getOuterPicturePropertyView() != null;
   }

   public Integer getImageCount() {
      return new Integer( getRelatedImagesPropertyViews().size() );
   }
}
