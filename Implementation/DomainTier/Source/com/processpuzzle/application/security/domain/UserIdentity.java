package com.processpuzzle.application.security.domain;

import hu.itkodex.commons.persistence.query.BooleanOperator;
import hu.itkodex.commons.persistence.query.BooleanOperators;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class UserIdentity extends DefaultIdentityExpression<User> {

//Constructors
   public UserIdentity(DefaultQueryContext context) {
      super( context );
   }

   public UserIdentity() {
      this( null );
   }

   //Private helper methods
   @Override
   protected void buildQuery() {
      condition.addAttributeCondition( new TextAttributeCondition( "userName", new QueryVariable("nameValue"), ComparisonOperators.EQUAL_TO ));
      condition.addAttributeCondition( new TextAttributeCondition( "password", new QueryVariable("passwordValue"), ComparisonOperators.EQUAL_TO));
      condition.addBooleanOperator( new BooleanOperator( BooleanOperators.AND ));
   }
}
