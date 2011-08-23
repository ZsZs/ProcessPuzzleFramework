package com.processpuzzle.persistence.query.transformer.domain;

import hu.itkodex.commons.persistence.query.Query;

public class HQLQueryTransformer extends DefaultQueryTransformer {

   public HQLQueryTransformer() {
      filterTransformer = new HQLFilterTransformer();
      conditionTransformer = new HQLConditionTransformer();
      orderTransformer = new HQLOrderTransformer();
   }

   @Override public String createStatement( Query query ) {
      return super.createStatement( query );
   }

   @Override
   protected String buildStatementFromFragments() {
      String objectAlias = defineAliasFor( query.getTargetClass() );
      String statement = "select " + filterFragment + " from " + query.getTargetClass().getSimpleName() + " as " + objectAlias;

      if( conditionFragment != "" )
         statement += " where " + conditionFragment;
      if( orderFragment != "" )
         statement += " order by " + orderFragment;
      return statement;
   }
}
