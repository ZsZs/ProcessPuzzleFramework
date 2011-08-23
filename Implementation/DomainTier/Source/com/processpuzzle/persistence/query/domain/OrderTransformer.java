package com.processpuzzle.persistence.query.domain;

import hu.itkodex.commons.persistence.query.OrderSpecifier;
import hu.itkodex.commons.persistence.query.QueryOrder;

import java.util.Iterator;

public abstract class OrderTransformer {

   public String createOrderFragment( String targetAlias, QueryOrder order ) {
      String orderFragment = "";

      for( Iterator<?> iter = order.specifiersIterator(); iter.hasNext(); ){
         OrderSpecifier specifier = (OrderSpecifier) iter.next();
         if( orderFragment == "" )
            orderFragment = targetAlias + "." + specifier.getAttributeName() + " " + specifier.getDirection().getAsHQL();
         else
            orderFragment += ", " + targetAlias + "." + specifier.getAttributeName() + " " + specifier.getDirection().getAsHQL();
      }
      return orderFragment;
   }
}
