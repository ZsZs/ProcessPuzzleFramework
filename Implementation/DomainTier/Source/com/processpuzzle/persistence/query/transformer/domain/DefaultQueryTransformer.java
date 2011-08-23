package com.processpuzzle.persistence.query.transformer.domain;

import hu.itkodex.commons.persistence.query.AttributeFilter;
import hu.itkodex.commons.persistence.query.Query;
import hu.itkodex.commons.persistence.query.QueryCondition;
import hu.itkodex.commons.persistence.query.QueryContext;
import hu.itkodex.commons.persistence.query.QueryOrder;
import hu.itkodex.commons.persistence.query.QueryTransformer;

import com.processpuzzle.persistence.query.domain.OrderTransformer;

public abstract class DefaultQueryTransformer implements QueryTransformer {
   protected ConditionTransformer conditionTransformer = null;
   protected FilterTransformer filterTransformer = null;
   protected OrderTransformer orderTransformer = null;
   protected QueryContextTransformer contextTransformer = null;
   protected Query query = null;
   protected String filterFragment = "";
   protected String conditionFragment = "";
   protected String orderFragment = "";
   
   public String createStatement( Query query ) {
      this.query = query.clone();
      String targetAlias = defineAliasFor( query.getTargetClass() ); 
//      replaceVariablesWithConcreteValues();
      filterFragment = createFilterFragment( targetAlias, query.getAttributeFilter() );
      conditionFragment = createConditionFragment( targetAlias, query.getQueryCondition(), query.getQueryContext() );
      orderFragment = createOrderFragment( targetAlias, query.getQueryOrder() );
      return buildStatementFromFragments();
   }
   
   protected String createFilterFragment( String targetAlias, AttributeFilter filter ){
      String fragment = filterTransformer.cretateFilterFragment( targetAlias, filter );
      return fragment;
   }
   protected String createConditionFragment( String targetAlias, QueryCondition condition, QueryContext context ){
      String fragment = conditionTransformer.createConditionFragment( targetAlias, condition, context );
      return fragment;
   }
   
   protected String createOrderFragment( String targetAlias, QueryOrder order ) {
      String fragment = orderTransformer.createOrderFragment( targetAlias, order );
      return fragment;
   }
   
   protected String defineAliasFor( Class<?> targetClass ) {
      String shortClassName = targetClass.getSimpleName();
      String firstChar = shortClassName.substring(0, 1).toLowerCase();
      return firstChar + shortClassName.substring(1);
   }

   protected abstract String buildStatementFromFragments();
}
