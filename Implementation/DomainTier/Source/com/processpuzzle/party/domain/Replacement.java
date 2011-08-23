package com.processpuzzle.party.domain;

import java.util.Date;

import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipType;


public class Replacement extends PartyRelationship {

	private Date validFrom;
	private Date validTo;
	
	public Replacement() {
		super();
	}

	public Replacement(String name, PartyRole client, PartyRole supplier,
			PartyRelationshipType type) {
		super(name, client, supplier, type);
	}

	public Replacement(String name) {
		super(name);
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
}
