package com.processpuzzle.artifact.domain;

import java.util.Collection;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class VersionsView extends ArtifactView {

	public VersionsView(Artifact<?> artifact, String name, ArtifactViewType type) {
		super(artifact, name, type);
	}
   
	public Collection<?> getVersions() {
		return getParentArtifact().getVersions().values();
	}

   public void initializeView() {
      // TODO Auto-generated method stub      
   }
}