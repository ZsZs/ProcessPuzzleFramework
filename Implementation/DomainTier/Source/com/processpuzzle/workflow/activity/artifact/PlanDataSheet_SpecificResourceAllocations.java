package com.processpuzzle.workflow.activity.artifact;

import java.util.Collection;

import com.processpuzzle.artifact.domain.CustomFormView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.workflow.activity.domain.SpecificResourceAllocation;

public class PlanDataSheet_SpecificResourceAllocations extends CustomFormView<PlanDataSheet> {

   public PlanDataSheet_SpecificResourceAllocations( PlanDataSheet artifact, String name, ArtifactViewType type) {
      super(artifact, name, type);
   }

   public Collection<SpecificResourceAllocation> getSpecificResourceAllocations() {
	   return parentArtifact.getSpecificResourceAllocations();
   }

	public void initializeView() {
		// TODO Auto-generated method stub
	}
}
