package com.processpuzzle.artifact.domain;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class DocumentPropertyView extends PropertyView<Document> {

   public DocumentPropertyView( Document artifact, String name, ArtifactViewType viewType ) {
      super( artifact, name, viewType );
   }

   protected void initializeProperties() {}
}