package com.processpuzzle.artifact.domain;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class ArtifactNameIdentity<A extends Artifact<?>> extends DefaultIdentityExpression<A> {

	public ArtifactNameIdentity( DefaultQueryContext context ) {
		super( context );
	}

	@Override
	protected void buildQuery() {
		condition.addAttributeCondition(new TextAttributeCondition("name", new QueryVariable("nameValue"),ComparisonOperators.EQUAL_TO));

	}

}
