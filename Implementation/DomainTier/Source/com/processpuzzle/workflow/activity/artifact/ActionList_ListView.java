package com.processpuzzle.workflow.activity.artifact;

import com.processpuzzle.artifact.domain.ArtifactListView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class ActionList_ListView extends ArtifactListView<ActionList> {

   public ActionList_ListView( ActionList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public void initializeView() {
   }
}