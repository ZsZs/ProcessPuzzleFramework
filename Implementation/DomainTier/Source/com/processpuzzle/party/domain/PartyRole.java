/*
Name: 
    - PartyRole

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
