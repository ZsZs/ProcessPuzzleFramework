package com.processpuzzle.persistence.query.domain;

public enum ComparisonOperators {
   EQUAL_TO("=", "="),
   NOT_EQUAL_TO("!=", "!="),
   GREATER_THAN(">", ">"),
   LESS_THAN(">", ">"),
   GREATER_OR_EQUAL_TO(">=", ">="),
   LESS_OR_EQUAL_TO("<=", "<="),
   IS_NULL("is null", "is null"),
   IS_NOT_NULL("is not null", "is not null"),
   LIKE("like", "like");
   
   private String hqlVariant = null;
   private String sqlVariant = null;
   
   ComparisonOperators(String hqlVariant, String sqlVariant ) {
      this.hqlVariant = hqlVariant;
      this.sqlVariant = sqlVariant;
   }
   
   public String getHqlVariant() { return hqlVariant; }
   public String getSqlVaritan() { return sqlVariant; }
}
