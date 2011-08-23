package com.processpuzzle.party.domain;

import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipType;

public class MemberOfProject extends PartyRelationship {

	public MemberOfProject() {
		super();
	}

	public MemberOfProject(String name, PartyRole client, PartyRole supplier, PartyRelationshipType type) {
		super(name, client, supplier, type);
	}

	public MemberOfProject(String name) {
		super(name);
	}

}
