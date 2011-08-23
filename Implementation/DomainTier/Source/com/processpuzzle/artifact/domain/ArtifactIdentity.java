package com.processpuzzle.artifact.domain;

import hu.itkodex.commons.persistence.Entity;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class ArtifactIdentity<A extends Entity> extends DefaultIdentityExpression<A> {

   public ArtifactIdentity( DefaultQueryContext context ) {
      super( context );
   }

   @Override
   protected void buildQuery() {
      condition.addAttributeCondition( new TextAttributeCondition( "path", new QueryVariable("pathValue"), ComparisonOperators.EQUAL_TO ));
   }
}
