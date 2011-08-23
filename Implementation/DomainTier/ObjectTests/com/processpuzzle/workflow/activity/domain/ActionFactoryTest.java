package com.processpuzzle.workflow.activity.domain;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ActionFactoryTest {

   @Before
   public void setUp() throws Exception {}

   @After
   public void tearDown() throws Exception {
   }

   /*
    * public void testNodeRetrievement() { Activity action =
    * (Activity)development.getSubActionByProtocolName("ImplementDomainLogicActivity");
    * assertNotNull(action); }
    */
   @Test
   public void testCreatePlan_For_EmptyName() {
      try {
         /* Plan emptyplan = */ActionFactory.createPlan("");
         assertTrue("exception was not thrown", false);
      } catch (IllegalArgumentException ex) {
         assertTrue("calling createPlan with emty string throws illegalArgumentException", ex instanceof IllegalArgumentException);
      }
   }

}
