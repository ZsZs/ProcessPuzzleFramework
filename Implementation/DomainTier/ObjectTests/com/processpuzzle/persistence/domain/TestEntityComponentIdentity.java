package com.processpuzzle.persistence.domain;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class TestEntityComponentIdentity extends DefaultIdentityExpression<TestEntityComponent> {
   public static final String NAME_VARIABLE = "name";

   public TestEntityComponentIdentity(DefaultQueryContext context) {
      super(context);
   }

   @Override
   protected final void buildQuery() {
      condition.addAttributeCondition( new TextAttributeCondition("name", new QueryVariable(NAME_VARIABLE), ComparisonOperators.EQUAL_TO));
   }

}
