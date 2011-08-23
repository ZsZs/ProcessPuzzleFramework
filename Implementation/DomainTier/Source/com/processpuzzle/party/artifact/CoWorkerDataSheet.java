package com.processpuzzle.party.artifact;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.party.domain.Person;

public class CoWorkerDataSheet extends PartyDataSheet<CoWorkerDataSheet, Person> {

	public CoWorkerDataSheet() {
		super();
	}
	
	public CoWorkerDataSheet( User creator, String artifactName, ArtifactType type, Person coWorker) {
		super(artifactName, type, creator, coWorker);
	}
	
	public Person getPerson() {
		return (Person) getParty();
	}
}
