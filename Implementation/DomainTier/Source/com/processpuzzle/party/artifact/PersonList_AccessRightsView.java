package com.processpuzzle.party.artifact;

import com.processpuzzle.artifact.domain.AccessRightsView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class PersonList_AccessRightsView extends AccessRightsView {

   public PersonList_AccessRightsView( PersonList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

}
