package com.processpuzzle.persistence.query.domain;

import hu.itkodex.commons.persistence.query.AttributeCondition;
import hu.itkodex.commons.persistence.query.BooleanOperator;
import hu.itkodex.commons.persistence.query.DefaultConditionElement;
import hu.itkodex.commons.persistence.query.QueryCondition;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import com.processpuzzle.fundamental_types.domain.HashCodeUtil;

public class DefaultQueryCondition implements QueryCondition<DefaultConditionElement> {
   private Set<DefaultConditionElement> elements = new LinkedHashSet<DefaultConditionElement>();

   // Public mutators
   public void addBooleanOperator( BooleanOperator operator ) {
      elements.add( operator );
   }

   public void addAttributeCondition( AttributeCondition condition ) {
      elements.add( (DefaultConditionElement) condition );
   }

   public void addVariable( Variable variable ) {
      elements.add( variable );
   }

   // Public collection accessors
   public Iterator<DefaultConditionElement> elementsIterator() {
      return elements.iterator();
   }

   public int elementsCount() {
      return elements.size();
   }

   // Public accessors
   public boolean equals( Object objectToCheck ) {
      if( !(objectToCheck instanceof DefaultQueryCondition) )
         return false;
      DefaultQueryCondition anotherQueryCondition = (DefaultQueryCondition) objectToCheck;
      return elements.equals( anotherQueryCondition.elements );
   }

   public DefaultQueryCondition clone() {
      DefaultQueryCondition clone = new DefaultQueryCondition();
      clone.elements = new LinkedHashSet<DefaultConditionElement>();
      clone.elements.addAll( this.elements );
      return clone;
   }

   public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash( result, elements );
      return result;
   }
   
   public Set<DefaultConditionElement> getElements() {
      return elements;
   }
}
