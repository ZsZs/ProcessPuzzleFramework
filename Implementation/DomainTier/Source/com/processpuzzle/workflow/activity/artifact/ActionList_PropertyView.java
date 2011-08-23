package com.processpuzzle.workflow.activity.artifact;

import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class ActionList_PropertyView extends PropertyView<ActionList> {

   public ActionList_PropertyView( ActionList artifact, String name, ArtifactViewType viewType ) {
      super( artifact, name, viewType );
   }

   protected void initializeProperties() {}

}
