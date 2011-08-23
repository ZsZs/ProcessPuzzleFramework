package com.processpuzzle.party.artifact;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class CoWorkerList_PrintView extends PrintView {

	public CoWorkerList_PrintView(Artifact<?> artifact, String name, ArtifactViewType type) {
		super(artifact, name, type);
	}
	
	@Override
	public String buildXml() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void initializeView() {
		
	}

}
