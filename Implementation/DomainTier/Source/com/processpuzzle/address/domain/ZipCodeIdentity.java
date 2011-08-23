package com.processpuzzle.address.domain;

import hu.itkodex.commons.persistence.query.BooleanOperator;
import hu.itkodex.commons.persistence.query.BooleanOperators;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.IntegerAttributeCondition;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class ZipCodeIdentity extends DefaultIdentityExpression<ZipCode> {

   public ZipCodeIdentity() {
      super();
   }

   protected void buildQuery() {
      condition.addAttributeCondition( new IntegerAttributeCondition( "zipCode", new QueryVariable( "zipValue" ), ComparisonOperators.EQUAL_TO ) );
      condition
            .addAttributeCondition( new TextAttributeCondition( "settlement.name", new QueryVariable( "settlementNameValue" ), ComparisonOperators.EQUAL_TO ) );
      condition.addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
   }
}
