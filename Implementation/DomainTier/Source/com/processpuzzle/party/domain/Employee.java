/*
 * Created on 2005.07.05.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.domain;

import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.party.partytype.domain.PartyType;

public class Employee extends Person {

	protected Employee() {
		super();
	}

	public Employee( PersonName personName, PartyType partyType, TimePoint timePoint ) {
		super( personName, partyType, timePoint );
	}
}
