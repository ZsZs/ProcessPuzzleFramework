package com.processpuzzle.rule.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.processpuzzle.rule.domain.Rule;
import com.processpuzzle.rule.domain.RuleContext;
import com.processpuzzle.rule.domain.RuleElement;

public class RuleTest {
   private RuleContext context1 = new RuleContext("SuiteableForUpgrade");
   private Rule rule1 = new Rule("SuiteableForUpgrade");

   @Before
   public void setUp() throws Exception {
      context1.addProposition("passengerIsEconomy", true);
      context1.addProposition("passengerIsGoldCardHolder", true);
      context1.addProposition("passengerIsSilverCardHolder", false);      
      context1.addVariable("passengerCarryOnBaggageWeight", 10.0);
      context1.addVariable("passengerCarryOnBaggageAllowance", 15.0);
      
      rule1.addProposition("passengerIsEconomy");
      rule1.addProposition("passengerIsGoldCardHolder");
      rule1.addProposition("passengerIsSilverCardHolder");   
      rule1.addOperator("OR");
      rule1.addOperator("AND");
      rule1.addVariable("passengerCarryOnBaggageAllowance");
      rule1.addVariable("passengerCarryOnBaggageWeight");
      rule1.addOperator("LESSTHANOREQUALTO");
      rule1.addOperator("AND");
      
   }
  
   @After
   public void tearDown() throws Exception {
      context1 = null;
      rule1 = null;
   }
   
   @Test
   public void testRuleEvulation() {
     RuleElement result = rule1.evaluate(context1);
     String expected = "Proposition statement = ((passengerCarryOnBaggageWeight <= passengerCarryOnBaggageAllowance) AND ((passengerIsSilverCardHolder OR passengerIsGoldCardHolder) AND passengerIsEconomy)), value = true"; 
     assertEquals( expected, result.toString());
   }   
}
