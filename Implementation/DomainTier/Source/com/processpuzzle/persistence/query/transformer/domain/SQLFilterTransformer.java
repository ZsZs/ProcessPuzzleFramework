package com.processpuzzle.persistence.query.transformer.domain;

import hu.itkodex.commons.persistence.query.AttributeFilter;

public class SQLFilterTransformer extends FilterTransformer {

   public SQLFilterTransformer() {
      super();
   }

   @Override
   String cretateFilterFragment( String targetAlias, AttributeFilter filter ) {
      String filterFragment = super.cretateFilterFragment( targetAlias, filter ); 
      if( filterFragment == "") filterFragment = "*";
      
      return filterFragment;
   }
}
