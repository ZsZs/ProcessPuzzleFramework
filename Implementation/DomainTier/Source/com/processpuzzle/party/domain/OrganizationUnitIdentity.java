package com.processpuzzle.party.domain;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class OrganizationUnitIdentity extends DefaultIdentityExpression<OrganizationUnit> {

	public OrganizationUnitIdentity() {
		super();
	}

	@Override
	protected void buildQuery() {
		condition.addAttributeCondition(new TextAttributeCondition("organizationName", new QueryVariable("organizationNameValue"),ComparisonOperators.EQUAL_TO));
	}

}
