package com.processpuzzle.rule.domain;

public class Proposition extends ValueHolderRuleElement {

   public Proposition(String name, boolean value) {
      super(name, Boolean.valueOf(value).toString());
   }
   
   public Proposition(String name) {
      super(name);
   }

   public Proposition and(Proposition proposition) {
      return new Proposition("(" + name + " AND " + proposition.name + ")", Boolean.valueOf(value).booleanValue() && Boolean.valueOf(proposition.value).booleanValue());
   }

   public Proposition or(Proposition proposition) {
      return new Proposition("(" + name + " OR " + proposition.name + ")", Boolean.valueOf(value).booleanValue() || Boolean.valueOf(proposition.value).booleanValue());
   }

   public Proposition not() {
      return new Proposition("(NOT " + name+")", !Boolean.valueOf(value).booleanValue());
   }
   
   public Proposition is() {
      return new Proposition("(IS " + name+")", Boolean.valueOf(value).booleanValue());
   }

   public Proposition xor(Proposition proposition) {
      if (value == proposition.value)
         return new Proposition("(" + name + " XOR " + proposition.name + ")", false);
      else
         return new Proposition("(" + name + " XOR " + proposition.name + ")", true);
   }

   public String toString() {
      return "Proposition statement = " + name + ", value = " + value;
   }

   public String getType() {
      return "Proposition";
   }

}
