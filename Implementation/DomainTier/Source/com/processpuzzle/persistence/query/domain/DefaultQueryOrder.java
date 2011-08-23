package com.processpuzzle.persistence.query.domain;

import hu.itkodex.commons.persistence.query.OrderSpecifier;
import hu.itkodex.commons.persistence.query.QueryOrder;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.processpuzzle.fundamental_types.domain.HashCodeUtil;

public class DefaultQueryOrder implements QueryOrder {
   private Set<OrderSpecifier> specifiers = new HashSet<OrderSpecifier>();
   DefaultQueryOrder() {}
   
   public void addOrderSpecifier( OrderSpecifier specifier ) {
      this.specifiers.add( specifier );
   }
   
//Public accessors
   public boolean equals( Object objectToCheck ) {
      if( !(objectToCheck instanceof DefaultQueryOrder )) return false;
      DefaultQueryOrder anotherQueryOrder = (DefaultQueryOrder) objectToCheck;
      return specifiers.equals( anotherQueryOrder.specifiers );
   }
   
   public DefaultQueryOrder clone() {
      DefaultQueryOrder clone = null;
      try {
         clone = (DefaultQueryOrder) super.clone();
      } catch (CloneNotSupportedException e) {
         new Error("Assertion error!"); //Should not happen.
      }
      clone.specifiers = new HashSet<OrderSpecifier>();
      clone.specifiers.addAll( this.specifiers );
      return clone;
   }
   
   public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash( result, specifiers );
      return result;
   }
   
//Collections accessors
   public Iterator<OrderSpecifier> specifiersIterator() { return specifiers.iterator(); }
   
//Properties
}
