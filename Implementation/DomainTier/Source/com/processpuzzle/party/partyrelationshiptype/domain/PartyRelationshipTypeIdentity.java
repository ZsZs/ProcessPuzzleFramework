package com.processpuzzle.party.partyrelationshiptype.domain;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class PartyRelationshipTypeIdentity extends DefaultIdentityExpression<PartyRelationshipType> {

   public PartyRelationshipTypeIdentity() {
      super();
   }

   public PartyRelationshipTypeIdentity( DefaultQueryContext context ) {
      super( context );
   }

   @Override
   protected void buildQuery() {
      condition.addAttributeCondition( new TextAttributeCondition( "name", new QueryVariable( "nameValue" ), ComparisonOperators.EQUAL_TO ) );
   }

}
