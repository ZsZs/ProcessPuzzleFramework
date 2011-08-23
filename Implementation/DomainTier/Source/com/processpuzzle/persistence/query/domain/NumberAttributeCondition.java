package com.processpuzzle.persistence.query.domain;


public abstract class NumberAttributeCondition extends DefaultAttributeCondition {

   public NumberAttributeCondition(String attributeName, Double value, ComparisonOperators operator) {
      super(attributeName, value, operator);
   }

   public NumberAttributeCondition(String attributeName, Integer value, ComparisonOperators operator) {
      super(attributeName, value, operator);
   }

   public NumberAttributeCondition(String attributeName, Long value, ComparisonOperators operator) {
      super(attributeName, value, operator);
   }

   public NumberAttributeCondition(String attributeName, int value, ComparisonOperators operator) {
      this(attributeName, new Integer(value), operator);
   }

   public NumberAttributeCondition(String attributeName, QueryVariable variable, ComparisonOperators operator) {
      super( attributeName, variable, operator);
   }

   public NumberAttributeCondition( String attributeName, ComparisonOperators operator) {
      super( attributeName, operator);
   }


//   @Override
//   public String toString() {
//      if(value instanceof Double )
//         return attributeName + " " + operator.getSymbol() + " " + ((Double)value).toString();
//      else
//         return attributeName + " " + operator.getSymbol() + " " + ((Long)value).toString();
//   }
   
      
   public abstract String toString();
}
