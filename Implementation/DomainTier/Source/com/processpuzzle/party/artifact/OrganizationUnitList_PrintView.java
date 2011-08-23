package com.processpuzzle.party.artifact;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class OrganizationUnitList_PrintView extends PrintView {

	public OrganizationUnitList_PrintView(Artifact<?> artifact, String name, ArtifactViewType type) {
		super(artifact, name, type);
	}

	public String buildXml() {
		return null;
	}

   public void initializeView() {
      // TODO Auto-generated method stub
   }
}
