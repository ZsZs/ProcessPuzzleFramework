package com.processpuzzle.util.domain;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class AutoidentifierIdentity extends DefaultIdentityExpression<AutoIdentifier> {

	public AutoidentifierIdentity() {
		super();
	}

	@Override
	protected void buildQuery() {
		condition.addAttributeCondition(new TextAttributeCondition("idType", new QueryVariable("idTypeValue"), ComparisonOperators.EQUAL_TO));
	}
}
