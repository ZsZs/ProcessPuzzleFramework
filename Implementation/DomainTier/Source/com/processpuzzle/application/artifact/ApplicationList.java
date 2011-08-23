package com.processpuzzle.application.artifact;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.artifact.ArtifactList;
import com.processpuzzle.artifact_type.domain.ArtifactType;

public class ApplicationList extends ArtifactList<ApplicationList> {

   public ApplicationList() {
      super();
   }
   
   public ApplicationList(String name, ArtifactType type, User creator) {
      super( name, type, creator );
   }
}
