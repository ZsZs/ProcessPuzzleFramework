package com.processpuzzle.rule.domain;

import java.util.Iterator;
import java.util.Stack;


public class Rule {
   private String name;
   private Stack<RuleElement> stack = new Stack<RuleElement>();
   private Queue elements = new Queue();
   
   public Rule(String name) {
      this.name = name;
   }

   public void addProposition(String statement) {
      elements.enQueue(new Proposition(statement));
   }

   public void addVariable(String variable) {
      elements.enQueue(new Variable(variable));
   }

   public void addOperator(String operator) {
      elements.enQueue(new Operator(operator));
   }

   public RuleElement evaluate(RuleContext context) {
      for (Iterator<?> iter = elements.iterator(); iter.hasNext();) {
         RuleElement e = (RuleElement) iter.next();

         if (e.getType().equals("Proposition") || e.getType().equals("Variable")) {
            ValueHolderRuleElement element = context.findElement(e.getName());
            if (element != null)
               ((ValueHolderRuleElement)e).value = element.value;
            else
               ((ValueHolderRuleElement)e).value = null;
         }
      }
      return process();
   }

   public RuleElement process() {
      int size = elements.size();
      for (int i = 0; i < size; i++) {
         RuleElement e = (RuleElement) elements.deQueue();
        
         if (e.getType().equals("Proposition"))
            processProposition(e, stack);
         else if (e.getType().equals("Variable"))
            processVariable(e, stack);
         else if (e.getType().equals("Operator"))
            processOperator(e, stack);
      }
      return stack.pop();
   }

   private void processVariable(RuleElement variable, Stack<RuleElement> stack) {
      stack.push(variable);
   }

   private void processProposition(RuleElement proposition, Stack<RuleElement> stack) {
      stack.push(proposition);
   }

   private void processOperator(RuleElement operator, Stack<RuleElement> stack) {
      if (operator.getName().equals("AND"))
         processAnd(stack);
      else if (operator.getName().equals("OR"))
         processOr(stack);
      else if (operator.getName().equals("NOT"))
         processNot(stack);
      else if (operator.getName().equals("IS"))
         processIs(stack);
      else if (operator.getName().equals("EQUALTO"))
         processEqualTo(stack);
      else if (operator.getName().equals("EQUALTO"))
         processNotEqualTo(stack);
      else if (operator.getName().equals("LESSTHAN"))
         processLessThan(stack);
      else if (operator.getName().equals("GREATERTHAN"))
         processGreaterThan(stack);
      else if (operator.getName().equals("LESSTHANOREQUALTO"))
         processLessThanOrEqualTo(stack);
      else if (operator.getName().equals("GREATERTHANOREQUALTO"))
         processGreaterThanOrEqualTo(stack);
   }

   private void processGreaterThanOrEqualTo(Stack<RuleElement> stack) {
      Variable rhs = (Variable) stack.pop();
      Variable lhs = (Variable) stack.pop();
      stack.push(rhs.greaterThanOrEqualsTo(lhs));
   }

   private void processLessThanOrEqualTo(Stack<RuleElement> stack) {
      Variable rhs = (Variable) stack.pop();
      Variable lhs = (Variable) stack.pop();
      stack.push(rhs.lessThanOrEqualsTo(lhs));
   }

   private void processGreaterThan(Stack<RuleElement> stack) {
      Variable rhs = (Variable) stack.pop();
      Variable lhs = (Variable) stack.pop();
      stack.push(rhs.greaterThan(lhs));
   }

   private void processLessThan(Stack<RuleElement> stack) {
      Variable rhs = (Variable) stack.pop();
      Variable lhs = (Variable) stack.pop();
      stack.push(rhs.lessThan(lhs));
   }

   private void processNotEqualTo(Stack<RuleElement> stack) {
      Variable rhs = (Variable) stack.pop();
      Variable lhs = (Variable) stack.pop();
      stack.push(rhs.notEqualTo(lhs));
   }

   private void processEqualTo(Stack<RuleElement> stack) {
      Variable rhs = (Variable) stack.pop();
      Variable lhs = (Variable) stack.pop();
      stack.push(rhs.equalTo(lhs));
   }

   private void processNot(Stack<RuleElement> stack) {
      Proposition rhs = (Proposition) stack.pop();
      stack.push(rhs.not());
   }
   
   private void processIs(Stack<RuleElement> stack) {
      Proposition rhs = (Proposition) stack.pop();
      stack.push(rhs.is());
   }

   private void processOr(Stack<RuleElement> stack) {
      Proposition rhs = (Proposition) stack.pop();
      Proposition lhs = (Proposition) stack.pop();
      stack.push(rhs.or(lhs));
   }

   private void processAnd(Stack<RuleElement> stack) {
      Proposition rhs = (Proposition) stack.pop();
      Proposition lhs = (Proposition) stack.pop();
      stack.push(rhs.and(lhs));
   }

   public String toString() {
      String resultString = "";
      for( Iterator<?> iter = elements.iterator(); iter.hasNext();) {
         RuleElement e = (RuleElement) iter.next();
         resultString += e.toString();
      }
      return resultString;
   }

   public String getName() {
      return name;
   }

}
