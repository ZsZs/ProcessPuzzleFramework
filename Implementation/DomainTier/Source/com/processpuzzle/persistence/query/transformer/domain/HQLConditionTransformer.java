package com.processpuzzle.persistence.query.transformer.domain;

import hu.itkodex.commons.persistence.query.QueryCondition;
import hu.itkodex.commons.persistence.query.QueryContext;

public class HQLConditionTransformer extends ConditionTransformer {

   @Override String createConditionFragment( String targetAlias, QueryCondition condition, QueryContext context ) {
      return super.createConditionFragment( targetAlias, condition, context );
   }
}
