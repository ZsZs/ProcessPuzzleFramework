package com.processpuzzle.workflow.activity.artifact;

import java.util.Collection;

import com.processpuzzle.artifact.domain.CustomFormView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.workflow.activity.domain.Action;

public class PlanDataSheet_ActionHierarchy extends CustomFormView<PlanDataSheet> {

   public PlanDataSheet_ActionHierarchy( PlanDataSheet artifact, String name, ArtifactViewType type ) {
      super(artifact, name, type);
   }

   public void initializeView() {
   // TODO Auto-generated method stub
   }
   
   public Integer getSubjectPlanDataSheetId() {
      return parentArtifact.getId();
   }
   public Collection<Action<?>> getSubActions() { ;
      return parentArtifact.getPlan().getSubActions(); 
   }
}