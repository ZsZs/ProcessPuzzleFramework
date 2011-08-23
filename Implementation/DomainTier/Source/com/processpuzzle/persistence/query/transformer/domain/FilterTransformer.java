package com.processpuzzle.persistence.query.transformer.domain;

import hu.itkodex.commons.persistence.query.AggregateFunction;
import hu.itkodex.commons.persistence.query.AttributeFilter;
import hu.itkodex.commons.persistence.query.AttributeFilterExpression;
import hu.itkodex.commons.persistence.query.AttributeSelector;
import hu.itkodex.commons.persistence.query.QueryTransformer;

import java.util.Iterator;

public abstract class FilterTransformer {
   protected String fragment = "";
   
   String cretateFilterFragment( String targetAlias, AttributeFilter filter){
      for( Iterator<?> iter = filter.attributesIterator(); iter.hasNext();) {
         AttributeFilterExpression filterExpression = (AttributeFilterExpression) iter.next();
         String attributeName = filterExpression.getAttributeName();
         if( filterExpression instanceof AttributeSelector ) {
            if(fragment == "" ) fragment = targetAlias + "." + attributeName;
            else fragment += ", " + targetAlias + "." + attributeName;            
         } else if( filterExpression instanceof AggregateFunction ) {
            AggregateFunction function = (AggregateFunction) filterExpression;
            String functionName = function.getNameFor( QueryTransformer.SupportedTrasformers.HQL );

            if(fragment == "" ) fragment = functionName + "( " + targetAlias + "." + attributeName + " )";
            else fragment += ", " + functionName + "( " + targetAlias + "." + attributeName + " )";            
         }
      }
      
      return fragment;
   }
}
