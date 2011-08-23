package com.processpuzzle.artifact.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class RelatedArtifactsListView extends ArtifactView<Artifact<?>> {

   @SuppressWarnings("unchecked")
   public RelatedArtifactsListView( Artifact artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public void initializeView() {}

   public Collection<PropertyView<?>> getRelatedArtifactsPropertyViews() {
      Set<PropertyView<?>> propertyViews = new HashSet<PropertyView<?>>();
      Collection<Artifact<?>> relatedArtifacts = getParentArtifact().getRelatedArtifacts();
      for( Iterator<Artifact<?>> iterator = relatedArtifacts.iterator(); iterator.hasNext(); ){
         Artifact<?> artifact = (Artifact<?>) iterator.next();
         propertyViews.add( artifact.getPropertyView() );
      }
      return propertyViews;
   }
}