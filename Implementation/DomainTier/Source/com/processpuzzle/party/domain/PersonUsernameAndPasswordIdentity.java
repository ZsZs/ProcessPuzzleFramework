package com.processpuzzle.party.domain;

import hu.itkodex.commons.persistence.query.BooleanOperator;
import hu.itkodex.commons.persistence.query.BooleanOperators;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class PersonUsernameAndPasswordIdentity extends DefaultIdentityExpression<Person> {

	public PersonUsernameAndPasswordIdentity( DefaultQueryContext context ) {
		super( context );
	}

	@Override
	protected void buildQuery() {
		condition.addAttributeCondition(new TextAttributeCondition("systemUser.userName", new QueryVariable("userNameValue"), ComparisonOperators.EQUAL_TO));
		condition.addAttributeCondition(new TextAttributeCondition("systemUser.password", new QueryVariable("passwordValue"), ComparisonOperators.EQUAL_TO));
		condition.addBooleanOperator(new BooleanOperator(BooleanOperators.AND));
	}
}
