package com.processpuzzle.persistence.query.domain;

public class SimpleExpression implements Criterion {
   
   private String propertyName;
   private Object value;
   private String operator;
   
   public SimpleExpression(String propertyName, Object value, String operator) {
      this.propertyName = propertyName;
      this.value = value;
      this.operator = operator;
   }

   public String renderAsOQL(Criteria criteria) {      
      StringBuffer fragment = new StringBuffer();
      fragment.append("propertyName "+operator+" "+value);
      return fragment.toString();

   }

   public String getOperator() {
      return operator;
   }

   public void setOperator(String operator) {
      this.operator = operator;
   }

   public String getPropertyName() {
      return propertyName;
   }

   public void setPropertyName(String propertyName) {
      this.propertyName = propertyName;
   }

   public Object getValue() {
      return value;
   }

   public void setValue(Object value) {
      this.value = value;
   }

}
