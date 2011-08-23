package com.processpuzzle.rule.domain;


public class ValueHolderRuleElement extends RuleElement {

   protected String value;

   public ValueHolderRuleElement(String name) {
      super(name);
   }

   public ValueHolderRuleElement(String name, String value) {
      super(name);
      this.value = value;
   }

   public String getType() {
      return null;
   }
}
