package com.processpuzzle.persistence.query.domain;

public class QueryVariable {
   public static String VARIABLE_INDICATOR_BEGIN = "{";
   public static String VARIABLE_INDICATOR_END = "}";
   protected String variableName = null;
   
   public QueryVariable( String variableName ) {
      this.variableName = variableName;
   }
   
// Class (static) methods
   public static QueryVariable createVariable( String variableName ) {
      return new QueryVariable(VARIABLE_INDICATOR_BEGIN + variableName + VARIABLE_INDICATOR_END);
   }

//Public accessors
   public String toString() { return VARIABLE_INDICATOR_BEGIN + variableName + VARIABLE_INDICATOR_END; }

//Properties
   public String getVariableName() { return variableName; }
}
