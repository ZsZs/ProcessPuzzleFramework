package com.processpuzzle.workflow.activity.artifact;

import java.util.Set;

import com.processpuzzle.artifact.domain.CustomFormView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.workflow.activity.domain.ActionEvent;

public class ActionDataSheet_EventHistory extends CustomFormView<ActionDataSheet<?>> {

   public ActionDataSheet_EventHistory( ActionDataSheet<?> artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public Set<ActionEvent> getEvents() { return parentArtifact.getEvents(); }

   public @Override void initializeView() {
      // TODO Auto-generated method stub
   }
}
