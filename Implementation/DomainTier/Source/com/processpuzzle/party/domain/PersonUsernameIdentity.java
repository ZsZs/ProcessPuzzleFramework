package com.processpuzzle.party.domain;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class PersonUsernameIdentity extends DefaultIdentityExpression<Person> {

   public PersonUsernameIdentity( DefaultQueryContext context ) {
      super( context );
   }

   @Override
   protected void buildQuery() {
      condition.addAttributeCondition( new TextAttributeCondition( "systemUser.userName", new QueryVariable( "userNameValue" ),
            ComparisonOperators.EQUAL_TO ) );
   }

}
