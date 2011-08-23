package com.processpuzzle.address.artifact;

import com.processpuzzle.artifact.domain.ArtifactIdentity;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

public class SettlementDataSheetIdentity extends ArtifactIdentity<SettlementDataSheet> {

   public SettlementDataSheetIdentity( DefaultQueryContext context ) {
      super( context );
   }
}
