package com.processpuzzle.artifact_type.domain;

import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.QueryVariable;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class ArtifactTypeIdentity extends DefaultIdentityExpression<ArtifactType> {

   public ArtifactTypeIdentity() {
      super();
   }

   @Override
   protected void buildQuery() {
      condition.addAttributeCondition(new TextAttributeCondition("name",new QueryVariable("nameValue"),ComparisonOperators.EQUAL_TO));
   }

}
