package com.processpuzzle.persistence.query.domain;

import hu.itkodex.commons.persistence.query.ConditionElementType;

import com.processpuzzle.fundamental_types.domain.OpAssertion;

public class TextAttributeCondition extends DefaultAttributeCondition {

   public TextAttributeCondition(String attributeName, String value, ComparisonOperators operator) {
      super(attributeName, value, operator);
   }

   public TextAttributeCondition(String attributeName, QueryVariable variable, ComparisonOperators operator) {
      super(attributeName, variable, operator);
   }

   public TextAttributeCondition(String attributeName, ComparisonOperators operator) {
      super(attributeName, null, operator);
      OpAssertion.ppAssert(((operator == ComparisonOperators.IS_NULL) || (operator == ComparisonOperators.IS_NOT_NULL )), 
      "AttributeCondition without attribute value can use only: 'is null, is not null' operators.");      
   }

   @Override
   public String toString() {
      String textualRepresentation = attributeName + " " + operator.getHqlVariant();
      if( value != null ) textualRepresentation += " '" + value.toString() +"'";
      return textualRepresentation;
   }

   @Override
   public ConditionElementType getType() {
      // TODO Auto-generated method stub
      return null;
   }
}
