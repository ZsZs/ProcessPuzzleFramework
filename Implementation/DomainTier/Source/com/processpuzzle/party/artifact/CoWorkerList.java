package com.processpuzzle.party.artifact;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.artifact.ArtifactList;
import com.processpuzzle.artifact_type.domain.ArtifactType;

public class CoWorkerList extends ArtifactList {

   public CoWorkerList() {
      super();
   }

   CoWorkerList(  String name, ArtifactType type, User creator ) {
      super( name, type, creator );
   }
}
