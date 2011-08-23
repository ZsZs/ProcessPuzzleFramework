package com.processpuzzle.artifact.domain;

import com.processpuzzle.artifact_type.domain.ArtifactType;


public class DocumentFactory extends ArtifactFactory<Document> {

   public Document createDocument( String name ) {
      
      ArtifactType artifactType = super.findTypeFor( Document.class );
      return super.create( name, artifactType );      
   }

}
