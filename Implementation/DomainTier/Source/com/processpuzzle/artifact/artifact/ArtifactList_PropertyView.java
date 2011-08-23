package com.processpuzzle.artifact.artifact;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class ArtifactList_PropertyView<A extends Artifact<?>> extends PropertyView<A> {

   private String fullName;

   public ArtifactList_PropertyView( A artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   protected void initializeProperties() {}

   public String getFullName() {
      return fullName;
   }

   public void setFullName( String fullName ) {
      this.fullName = fullName;
   }
}