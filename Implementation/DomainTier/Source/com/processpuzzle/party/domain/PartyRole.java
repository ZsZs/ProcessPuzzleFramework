/*
 * Created on 2005.09.22.
 */
package com.processpuzzle.party.domain;

import hu.itkodex.commons.persistence.ValueObject;

import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;

public class PartyRole implements ValueObject {	
	private Integer id;
	private String name;
	private String description;
	private Party party;
	private PartyRoleType roleType;
	private PartyRelationship partyRelationship;
    private int version = 0;
	
	public PartyRole(String name, PartyRoleType type) {
		this.name = name;
		this.roleType = type;
	}
	
	public PartyRole() {}
	
	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public Party getParty() {
		return party;
	}
	
	public void setParty(Party party) {
		this.party = party;
	}
	
	public PartyRoleType getRoleType() {
		return roleType;
	}
	
	public void setRoleType(PartyRoleType roleType) {
		this.roleType = roleType;
	}

	public PartyRelationship getPartyRelationship() {
		return partyRelationship;
	}

	public void setPartyRelationship(PartyRelationship partyRelationship) {
		this.partyRelationship = partyRelationship;
	}
}
