package com.processpuzzle.persistence.query.domain;

import hu.itkodex.commons.persistence.query.AggregateFunction;
import hu.itkodex.commons.persistence.query.AttributeFilter;
import hu.itkodex.commons.persistence.query.AttributeFilterExpression;
import hu.itkodex.commons.persistence.query.AttributeSelector;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import com.processpuzzle.fundamental_types.domain.HashCodeUtil;

public class DefaultAttributeFilter implements AttributeFilter {
   private Set<AttributeFilterExpression> filterExpressions = new LinkedHashSet<AttributeFilterExpression>();
   
//Constructors
   DefaultAttributeFilter() {}
   
//Public accessors
   @Override
   public DefaultAttributeFilter clone() {
      DefaultAttributeFilter clone;
      try {
         clone = (DefaultAttributeFilter) super.clone();
      } catch (CloneNotSupportedException e) {
         throw new Error("Assertion failure."); //It should not happen.
      }
      clone.filterExpressions = new LinkedHashSet<AttributeFilterExpression>();
      clone.filterExpressions.addAll( this.filterExpressions );
      return clone;
   }
   
   @Override
   public boolean equals( Object object ) {
      if( !(object instanceof DefaultAttributeFilter) ) return false; 
      DefaultAttributeFilter anotherAttributeFilter = (DefaultAttributeFilter) object;
      return filterExpressions.equals( anotherAttributeFilter.filterExpressions );
   }
   
   public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash( result, filterExpressions );
      return result;
   }
   
//Public mutators
   public void addAttributeSelector( AttributeSelector selector ) {
      filterExpressions.add( selector );
   }
   
   public void addAggregateFunction( AggregateFunction function ) {
      filterExpressions.add( function );
   }
   
   public Iterator<AttributeFilterExpression> attributesIterator() {return filterExpressions.iterator();}
}
