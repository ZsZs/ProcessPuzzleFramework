package com.processpuzzle.party.artifact;

import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class UserList_PrintView extends PrintView<UserList> {

   public UserList_PrintView( UserList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   @Override
   public String buildXml() {
      // TODO Auto-generated method stub
      return null;
   }

   public void initializeView() {

   }
}
