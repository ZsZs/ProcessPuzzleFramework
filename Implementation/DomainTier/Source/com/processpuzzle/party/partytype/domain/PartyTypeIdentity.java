package com.processpuzzle.party.partytype.domain;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class PartyTypeIdentity extends DefaultIdentityExpression<PartyType> {

	public PartyTypeIdentity( DefaultQueryContext context ) {
		super( context );
	}

	@Override
	protected void buildQuery() {
		condition.addAttributeCondition(new TextAttributeCondition("name", new QueryVariable("nameValue"), ComparisonOperators.EQUAL_TO));
	}
}
