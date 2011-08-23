package com.processpuzzle.workflow.activity.artifact;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class ProcessPlanDataSheet_BaseDataView extends PlanDataSheet_BaseData {

   public ProcessPlanDataSheet_BaseDataView( ProcessPlanDataSheet artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public String getSubject() {
      return ((ProcessPlanDataSheet) parentArtifact).getProcessPlan().getSubject();

   }

   public void setSubject( String subject ) {
      ((ProcessPlanDataSheet) parentArtifact).getProcessPlan().setSubject( subject );
   }

}
