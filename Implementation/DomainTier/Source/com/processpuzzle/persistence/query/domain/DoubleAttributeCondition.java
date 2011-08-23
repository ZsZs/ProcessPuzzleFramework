package com.processpuzzle.persistence.query.domain;

import hu.itkodex.commons.persistence.query.ConditionElementType;

import com.processpuzzle.fundamental_types.domain.OpAssertion;

public class DoubleAttributeCondition extends NumberAttributeCondition {
   public DoubleAttributeCondition(String attributeName, Double value, ComparisonOperators operator) {
      super(attributeName, value, operator);
   }
   
   public DoubleAttributeCondition(String attributeName, ComparisonOperators operator) {
      this(attributeName, null, operator);

      OpAssertion.ppAssert(((operator == ComparisonOperators.IS_NULL) || (operator == ComparisonOperators.IS_NOT_NULL )), 
      "AttributeCondition without attribute value can use only: 'is null, is not null' operators.");      
   }
   
   @Override
   public String toString() {
      String textualRepresentation = attributeName + " " + operator.getHqlVariant();
      if( value != null ) textualRepresentation += " " + value.toString();
      return textualRepresentation;
   }

   @Override
   public ConditionElementType getType() {
      // TODO Auto-generated method stub
      return null;
   }

}
