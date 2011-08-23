package com.processpuzzle.persistence.query.domain;

import hu.itkodex.commons.persistence.query.ConditionElementType;

public class IntegerAttributeCondition extends NumberAttributeCondition {

  public IntegerAttributeCondition(String attributeName, Long value, ComparisonOperators operator) {
      super(attributeName, value, operator);
   }
  
  public IntegerAttributeCondition(String attributeName, int value, ComparisonOperators operator) {
     this(attributeName, new Long(value), operator);
  }
  
  public IntegerAttributeCondition(String attributeName, QueryVariable variable, ComparisonOperators operator) {
     super( attributeName, variable, operator );
  }
 
  public IntegerAttributeCondition(String attributeName, ComparisonOperators operator) {
     super( attributeName, operator);
  }

  public String toString(){
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