package com.processpuzzle.address.domain;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class CountryIdentity extends DefaultIdentityExpression<Country> {

   public CountryIdentity( DefaultQueryContext context ) {
      super( context );
   }
   
   @Override
   protected void buildQuery(){
      condition.addAttributeCondition(new TextAttributeCondition("name","%1",ComparisonOperators.EQUAL_TO));
   }
   
}
