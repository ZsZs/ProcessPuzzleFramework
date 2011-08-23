package com.processpuzzle.persistence.query.transformer.domain;

import hu.itkodex.commons.persistence.query.Query;

public class SQLQueryTransformer extends DefaultQueryTransformer {

   public SQLQueryTransformer() {
      filterTransformer = new SQLFilterTransformer();
      conditionTransformer = new SQLConditionTransformer();
      orderTransformer = new SQLOrderTransformer();
   }

   @Override
   public String createStatement( Query query ) {
      return super.createStatement(query);
   }

   @Override
   protected String buildStatementFromFragments() {
      String statement = "SELECT " + filterFragment + " FROM " + query.getTargetClass().getSimpleName();
      if(conditionFragment != "") statement += " WHERE " + conditionFragment;
      return statement;
   }
}
