/*
Name: 
    - RelatedImagesListView

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
