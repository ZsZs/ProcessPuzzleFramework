package com.processpuzzle.artifact.domain;

import java.util.Set;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.party.domain.Party;

public class MemoCustomFormView extends CustomFormView<Memo> {

   public void initializeView() {
   }

   public MemoCustomFormView( Memo artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );

   }

   public String getSubject() {
      return parentArtifact.getSubject();
   }

   public User getResponsible() {
      return parentArtifact.getResponsible();
   }

   public Set<Party<?>> getRecipients() {
      return ((Memo) parentArtifact).getRecipients();
   }

   public String getContent() {
      return ((Memo) parentArtifact).getContent();
   }
}
