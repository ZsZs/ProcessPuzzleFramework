package com.processpuzzle.workflow.activity.domain;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.IntegerAttributeCondition;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.persistence.query.domain.QueryVariable;

public class ProcessPlanIdentity extends DefaultIdentityExpression<ProcessPlan> {

	public ProcessPlanIdentity( DefaultQueryContext context ) {
		super( context );
	}

	@Override
	protected void buildQuery() {
		condition.addAttributeCondition(new IntegerAttributeCondition("project.id", new QueryVariable("projectIdValue"),ComparisonOperators.EQUAL_TO));
	}

}
