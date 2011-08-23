package com.processpuzzle.workflow.activity.artifact;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class ProcessPlanDataSheet_PropertyView extends PlanDataSheet_PropertyView {

	public ProcessPlanDataSheet_PropertyView( ProcessPlanDataSheet artifact, String name, ArtifactViewType viewType) {
		super(artifact, name, viewType);

	}

	public String getSubject() {
		return ((ProcessPlanDataSheet) parentArtifact).getProcessPlan().getSubject();
	}
}
