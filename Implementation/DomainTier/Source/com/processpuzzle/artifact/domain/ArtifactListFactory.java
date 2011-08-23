package com.processpuzzle.artifact.domain;

import com.processpuzzle.artifact.artifact.ArtifactList;
import com.processpuzzle.artifact_type.domain.ArtifactType;

public class ArtifactListFactory<L extends ArtifactList<?>> extends ArtifactFactory<L> {
   
   public ArtifactListFactory() {
      super();
   }
   
   public L create() {
      ArtifactType artifactType = findTypeFor( artifactClass );
      return create( artifactType );
   }
   
   @SuppressWarnings("unchecked")
   public L create( ArtifactType artifactType ) {
      L artifactList = (L) artifactRepository.findByName( artifactType.getArtifactClassName() );
      if( artifactList == null ) {
         return super.create( artifactType.getArtifactClassName(), artifactType );
      } else return null;
   }
}
