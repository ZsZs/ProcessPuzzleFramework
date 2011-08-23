package com.processpuzzle.party.artifact;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.party.domain.PersonName;

public class CoWorkerDataSheet_PropertyView extends PropertyView {

	public CoWorkerDataSheet_PropertyView() {
		super(null, null, null);
	}
	
	public CoWorkerDataSheet_PropertyView(Artifact<?> artifact, String name, ArtifactViewType type) {
		super(artifact, name, type);
	}
	
	public String getPrefix() {
		return ((PersonName) ((CoWorkerDataSheet)parentArtifact).getPerson().getPartyName()).getPrefix();
	}
	
	public String getFamilyName() {
		return ((PersonName) ((CoWorkerDataSheet)parentArtifact).getPerson().getPartyName()).getFamilyName();
	}
	
	public String getGivenName() {
		return ((PersonName) ((CoWorkerDataSheet)parentArtifact).getPerson().getPartyName()).getGivenName();
	}
}
