package com.processpuzzle.address.artifact;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.artifact.ArtifactList;
import com.processpuzzle.artifact_type.domain.ArtifactType;

public class SettlementList extends ArtifactList<SettlementList> {
   public static String LIST_NAME  = "SettlementList";

   protected SettlementList() {
      super();
   }

   public SettlementList( String name, ArtifactType type, User creator ) {
      super(name, type, creator );
   }
}
