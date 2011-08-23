package com.processpuzzle.workflow.protocol.domain;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class ProtocolIdentity extends DefaultIdentityExpression<Protocol> {

	public ProtocolIdentity() {
		super();
	}

	@Override
	protected void buildQuery() {
		condition.addAttributeCondition(new TextAttributeCondition("name", new QueryVariable("nameValue"), ComparisonOperators.EQUAL_TO));
	}
}
