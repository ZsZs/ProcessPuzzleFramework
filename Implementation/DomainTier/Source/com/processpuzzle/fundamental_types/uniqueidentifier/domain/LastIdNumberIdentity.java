package com.processpuzzle.fundamental_types.uniqueidentifier.domain;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class LastIdNumberIdentity extends DefaultIdentityExpression<LastIdNumber> {

   // Constructors
   public LastIdNumberIdentity(DefaultQueryContext context) {
      super( context );
   }

   @Override
   protected void buildQuery() {
      condition.addAttributeCondition( new TextAttributeCondition( "idType", new QueryVariable("idTypeValue"), ComparisonOperators.EQUAL_TO ));
   }
}
