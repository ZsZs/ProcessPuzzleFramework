package com.processpuzzle.rule.domain;


public class Variable extends ValueHolderRuleElement {

   public Variable(String name, double value) {
      super(name, Double.valueOf(value).toString());
   }
   
   public Variable(String name) {
      super(name);
   }

   public Proposition equalTo(Variable variable) {
      return new Proposition("(" + name + " == " + variable.name + ")", value.equals(variable.value));
   }

   public Proposition notEqualTo(Variable variable) {
      return new Proposition("(" + name + " != " + variable.name + ")", !value.equals(variable.value));
   }

   public Proposition lessThan(Variable variable) {
      return new Proposition("(" + name + " < " + variable.name + ")", Double.valueOf(value).doubleValue() < Double.valueOf(variable.value).doubleValue());
   }

   public Proposition greaterThan(Variable variable) {
      return new Proposition("(" + name + " > " + variable.name + ")", Double.valueOf(value).doubleValue() > Double.valueOf(variable.value).doubleValue());
   }

   public Proposition greaterThanOrEqualsTo(Variable variable) {
      return new Proposition("(" + name + " >= " + variable.name + ")", Double.valueOf(value).doubleValue() >= Double.valueOf(variable.value).doubleValue());
   }

   public Proposition lessThanOrEqualsTo(Variable variable) {
      return new Proposition("(" + name + " <= " + variable.name + ")", Double.valueOf(value).doubleValue() <= Double.valueOf(variable.value).doubleValue());
   }

   public String toString() {
      return "Variable name = " + name + ", value = " + value;
   }

   public String getType() {
      return "Variable";
   }

}
