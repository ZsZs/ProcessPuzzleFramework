package com.processpuzzle.artifact.domain;

import java.util.HashSet;
import java.util.Set;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.party.domain.Party;

public class Memo extends Document {
   private Set<Party<?>> recipients = new HashSet<Party<?>>();

   public Memo() {
      super();
   }

   public Memo( String name, ArtifactType type, User responsible ) {
      super( name, type, responsible );
   }

   public Set<Party<?>> getRecipients() {
      return recipients;
   }
}
