package com.processpuzzle.rule.domain;

public abstract class RuleElement {

   protected String name;

   public RuleElement(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public abstract String getType();
   
}
