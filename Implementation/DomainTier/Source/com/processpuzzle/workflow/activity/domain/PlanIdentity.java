package com.processpuzzle.workflow.activity.domain;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class PlanIdentity extends DefaultIdentityExpression<Plan<?>> {

	public PlanIdentity( DefaultQueryContext context ) {
		super( context );
	}

	@Override
	protected void buildQuery() {
		// TODO Case insesitive modon kell az osszehasonlitast vegrehajtani!
		condition.addAttributeCondition(new TextAttributeCondition("name", new QueryVariable("name"), ComparisonOperators.EQUAL_TO));
	}
}
