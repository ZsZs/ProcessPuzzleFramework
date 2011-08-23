package com.processpuzzle.persistence.query.domain;

public class Restrictions {
   
   public static SimpleExpression equals(String property, String value) {
      return new SimpleExpression(property, value, "=");
   }
   
   public static SimpleExpression like(String property, String value) {
      return new SimpleExpression(property, value, "like");
   }
   
   public static SimpleExpression greaterThen(String property, String value) {
      return new SimpleExpression(property, value, ">");
   }
   
   public static LogicalExpression and(Criterion crit1, Criterion crit2) {
      return new LogicalExpression(crit1, crit2);
   }

}
