package com.processpuzzle.artifact.domain;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class ArtifactFullNameIdentity<A extends Artifact<?>> extends DefaultIdentityExpression<A> {

   public ArtifactFullNameIdentity( DefaultQueryContext context ) {
      super( context );
   }

   @Override
   protected void buildQuery() {
      condition.addAttributeCondition(new TextAttributeCondition("name", new QueryVariable("nameValue"), ComparisonOperators.EQUAL_TO));
      // TODO "from Artifact a where a.name = ${name} and a.versions[
      // maxIndex(a.versions) ].containingFolder.id = ${folderId}"
   }

}
