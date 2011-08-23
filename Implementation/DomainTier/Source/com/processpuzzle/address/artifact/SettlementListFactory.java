package com.processpuzzle.address.artifact;

import com.processpuzzle.artifact.domain.ArtifactListFactory;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;

public class SettlementListFactory extends ArtifactListFactory<SettlementList> {

   public SettlementList create() {
      ArtifactTypeRepository artifactTypeRepository = applicationContext.getRepository( ArtifactTypeRepository.class );
      ArtifactType settlementListType = artifactTypeRepository.findByArtifactClassName( SettlementList.class.getName() );
      return (SettlementList) super.create( settlementListType );
   }
}
