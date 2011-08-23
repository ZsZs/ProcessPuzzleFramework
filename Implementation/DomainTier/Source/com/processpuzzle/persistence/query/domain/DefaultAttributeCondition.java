package com.processpuzzle.persistence.query.domain;

import hu.itkodex.commons.persistence.query.AttributeCondition;
import hu.itkodex.commons.persistence.query.DefaultConditionElement;

import com.processpuzzle.fundamental_types.domain.OpAssertion;

public abstract class DefaultAttributeCondition extends DefaultConditionElement implements AttributeCondition {
   protected String attributeName = null;
   protected Object value = null;
   protected ComparisonOperators operator = null;
   
   public DefaultAttributeCondition(String attributeName, Object value, ComparisonOperators operator) {
      super();
      this.attributeName = attributeName;
      this.value = value;
      this.operator = operator;
   }
   
   public DefaultAttributeCondition(String attributeName, ComparisonOperators operator ) {
      this( attributeName, null, operator );
      OpAssertion.ppAssert(((operator == ComparisonOperators.IS_NULL) || (operator == ComparisonOperators.IS_NOT_NULL )), 
      "AttributeCondition without attribute value can use only: 'is null, is not null' operators.");      
   }

//Public accessors
   public abstract String toString();
   
//Properties
   public String getAttributeName() { return attributeName; }
   public Object getValue() { return value; }
   public ComparisonOperators getOperator() { return operator; }
}
