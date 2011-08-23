package com.processpuzzle.party.artifact;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.artifact.ArtifactList;
import com.processpuzzle.artifact_type.domain.ArtifactType;

public class UserList extends ArtifactList<UserList> {

   protected UserList() {
      super();
   }

   public UserList( String name, ArtifactType type, User creator ) {
      super( name, type, creator );
   }
}
