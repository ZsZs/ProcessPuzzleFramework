package com.processpuzzle.rule.domain;

public class Operator extends RuleElement {   
   private String[] operators = {"AND", "OR", "NOT", "IS", "EQUALTO", "NOTEQUALTO", "LESSTHAN", "GREATERTHAN", "LESSTHANOREQUALTO", "GREATERTHANOREQUALTO"};

   public Operator(String operator) {
      super(operator);
      boolean isValid = false;
      for (int i = 0; i < operators.length; i++) {
         if(operators[i].equals(operator))
            isValid = true;
      }
      if(!isValid) throw new RuntimeException(operator+" is not a valid operator");
   }

   public String getType() {
      return "Operator";
   }

   public String getName() {
      return name;
   }
   
   

}
