package com.processpuzzle.party.artifact;

import com.processpuzzle.artifact.artifact.ArtifactList_ListView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class PersonList_ListView extends ArtifactList_ListView{
    
    public PersonList_ListView( PersonList artifact, String name, ArtifactViewType type ) {
        super(artifact, name, type);
    }

   public void initializeView() {
   }
}
