package com.processpuzzle.party.domain;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.IntegerAttributeCondition;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.persistence.query.domain.QueryVariable;

public class PersonUserIdIdentity extends DefaultIdentityExpression<Person> {

	public PersonUserIdIdentity( DefaultQueryContext context ) {
		super( context );
	}

	@Override
	protected void buildQuery() {
		condition.addAttributeCondition(new IntegerAttributeCondition("systemUser.id", new QueryVariable("systemUserIdValue"),ComparisonOperators.EQUAL_TO));
	}
}
