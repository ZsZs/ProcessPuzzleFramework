package com.processpuzzle.party.partyrelationshiptype.domain;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class PartyRoleTypeIdentity extends DefaultIdentityExpression<PartyRoleType> {

   PartyRoleTypeIdentity() {
      super();
   }

   public PartyRoleTypeIdentity( DefaultQueryContext context ) {
      super( context );
   }

   @Override
   protected void buildQuery() {
      condition.addAttributeCondition( new TextAttributeCondition( "name", new QueryVariable( "nameValue" ), ComparisonOperators.EQUAL_TO ) );
   }

}
