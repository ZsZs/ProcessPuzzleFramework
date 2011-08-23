package com.processpuzzle.artifact.domain;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class ArtifactFolder_PropertyView extends PropertyView<ArtifactFolder> {

   public ArtifactFolder_PropertyView( ArtifactFolder artifact, String name, ArtifactViewType viewType ) {
      super( artifact, name, viewType );
   }
}
