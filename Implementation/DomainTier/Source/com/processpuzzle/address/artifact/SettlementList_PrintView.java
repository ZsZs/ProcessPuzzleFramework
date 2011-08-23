package com.processpuzzle.address.artifact;

import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class SettlementList_PrintView extends PrintView<SettlementList> {

   public SettlementList_PrintView( SettlementList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public String buildXml() {
      return null;
   }

}
