package com.processpuzzle.workflow.activity.artifact;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.workflow.activity.domain.Plan;
import com.processpuzzle.workflow.activity.domain.ProcessPlan;

public class ProcessPlanDataSheet extends PlanDataSheet {
   
   public ProcessPlanDataSheet() {
      super();     
   }

   public ProcessPlanDataSheet(String artifactName, ArtifactType type, Plan plan, User creator) {
      super(artifactName, type, plan, creator);      
   }

   public ProcessPlan getProcessPlan() {
      return (ProcessPlan)super.getPlan();
   }
}
