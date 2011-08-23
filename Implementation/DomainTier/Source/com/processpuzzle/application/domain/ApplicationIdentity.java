package com.processpuzzle.application.domain;

import hu.itkodex.commons.persistence.query.BooleanOperator;
import hu.itkodex.commons.persistence.query.BooleanOperators;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class ApplicationIdentity extends DefaultIdentityExpression<Application> {
   
   public ApplicationIdentity( DefaultQueryContext context ) {
      super( context );
   }

   @Override
   protected void buildQuery() {
      condition.addAttributeCondition( new TextAttributeCondition( "applicationName", new QueryVariable("applicationNameValue"), ComparisonOperators.EQUAL_TO ));
      condition.addAttributeCondition( new TextAttributeCondition( "applicationVersion", new QueryVariable("applicationVersionValue"), ComparisonOperators.EQUAL_TO));
      condition.addBooleanOperator( new BooleanOperator( BooleanOperators.AND ));
   }
}
