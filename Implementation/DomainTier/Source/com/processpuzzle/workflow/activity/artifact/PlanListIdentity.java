package com.processpuzzle.workflow.activity.artifact;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class PlanListIdentity extends DefaultIdentityExpression<PlanList> {

	public PlanListIdentity() {
		super();
	}

	@Override
	protected void buildQuery() {
		condition.addAttributeCondition(new TextAttributeCondition("name", new QueryVariable("nameValue"), ComparisonOperators.EQUAL_TO));
	}
}
