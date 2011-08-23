package com.processpuzzle.persistence.query.domain;

import hu.itkodex.commons.persistence.query.ConditionElementType;

public class BooleanAttributeCondition extends DefaultAttributeCondition {

   public BooleanAttributeCondition(String attributeName, Object value, ComparisonOperators operator) {
      super(attributeName, value, operator);
      // TODO Auto-generated constructor stub
   }

   @Override
   public String toString() {
      String textualRepresentation = attributeName + " " + operator.getHqlVariant();
      if( value != null ) textualRepresentation += value.toString();
      return textualRepresentation;
   }

   @Override
   public ConditionElementType getType() {
      // TODO Auto-generated method stub
      return null;
   }

}
