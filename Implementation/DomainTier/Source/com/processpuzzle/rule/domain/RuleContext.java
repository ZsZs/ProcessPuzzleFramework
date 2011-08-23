package com.processpuzzle.rule.domain;

import java.util.Iterator;

public class RuleContext {

   private String name;
   private Queue elements = new Queue();

   public RuleContext(String name) {
      this.name = name;
   }

   public void addProposition(String statement, boolean value) {
      elements.enQueue(new Proposition(statement, value));
   }

   public void addVariable(String name, double value) {
      elements.enQueue(new Variable(name, value));
   }

   public ValueHolderRuleElement findElement(String name) {
      return (ValueHolderRuleElement) elements.findElement(name);
   }

   public String getName() {
      return name;
   }

   public String toString() {
      String resultString = "";
      for( Iterator<?> iter = elements.iterator(); iter.hasNext();) {
         RuleElement e = (RuleElement) iter.next();
         resultString += ((ValueHolderRuleElement) e).toString();
      }
      return resultString;
   }

}
