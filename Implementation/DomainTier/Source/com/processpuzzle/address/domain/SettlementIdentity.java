package com.processpuzzle.address.domain;

import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

public class SettlementIdentity extends DefaultIdentityExpression<Settlement> {

   public SettlementIdentity( DefaultQueryContext context ) {
      super( context );
   }
   
   @Override
   protected void buildQuery(){
/* Settlements with the same name can exist within the country
      condition.addAttributeCondition(new TextAttributeCondition("name","%1",ComparisonOperators.EQUAL_TO));
      condition.addAttributeCondition(new TextAttributeCondition("country.name","%2",ComparisonOperators.EQUAL_TO));
      condition.addBooleanOperator(new BooleanOperator(BooleanOperators.AND));
 */
   }

}
