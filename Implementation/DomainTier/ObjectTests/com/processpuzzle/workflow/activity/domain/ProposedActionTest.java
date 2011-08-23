package com.processpuzzle.workflow.activity.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProposedActionTest {

   @Before
   public void setUp() throws Exception {}

   @After
   public void tearDown() throws Exception {}

   /*
    * Test method for
    * 'com.itcodex.objectpuzzle.project_planning.action.domain.ProposedAction.ProposedAction(String)'
    */
   @Test
   public void testProposedAction() {
   // ProposedAction anAction = new ProposedAction("CDD development");
   // assertEquals("The action's name is 'CDD development'", "CDD development",
   // anAction.getName());
   }

   // public void testConstructorForEmptyString() {
   // boolean exceptionWasThrown = false;
   // try {
   // /* ProposedAction anAction = */ new ProposedAction("");
   // }
   // catch (IllegalArgumentException e) {
   // assertTrue("Caling constructor with empty string throws
   // 'InvalidParemeterException'", e instanceof IllegalArgumentException);
   // exceptionWasThrown = true;
   // }
   // assertTrue("The exception was thrown by the constructor.",
   // exceptionWasThrown);
   // }

   // public void testConstructorForNullObject() {
   // boolean exceptionWasThrown = false;
   // try {
   // /* ProposedAction anAction = */ new ProposedAction(null);
   // }
   // catch (IllegalArgumentException e) {
   // assertTrue("Caling constructor with null object throws
   // 'InvalidParemeterException'", e instanceof IllegalArgumentException);
   // exceptionWasThrown = true;
   // }
   // assertTrue("The exception was thrown by the constructor.",
   // exceptionWasThrown);
   // }

   @Test
   public void testGetPerformerRole() {
   // LifecycleProtocol lcp = ProtocolFactory.createLifecycleProtocol("PCR
   // Development");
   // LifecyclePhaseProtocol theLifecyclePhase =
   // ProtocolFactory.createLifecyclePhaseProtocol("Subtask", lcp);
   // WorkflowDetailProtocol theWFDetail =
   // ProtocolFactory.createWorkflowDetail("Design Prework", theLifecyclePhase);
   // Role aRole = ResourceTypeFactory.createRole("CDD Designer");
   // ActivityProtocol anActivity = ProtocolFactory.createActivity("CDD
   // Development", aRole, theWFDetail, true);
   //		
   // ProposedAction anAction = new ProposedAction("CDD development", (Protocol)
   // anActivity);
   // assertEquals("The action's owner role is 'CDD Designer'", "CDD Designer",
   // anAction.getPerformerRole().getName());
   }

   @Test
   public void testBookGeneralResource() {
   // Quantity theQuantity = new Quantity((double) 50, new Unit("kg"));
   // ConsumableResourceType theFlour = new ConsumableResourceType("Flour");
   // ProposedAction aCooking = new ProposedAction("Cooking");
   //
   // aCooking.allocateConsumableResourceGeneraly(theFlour, theQuantity);
   // assertEquals("Cooking booked 50 kg flour", 50, (int)
   // aCooking.getResouceAllocation("Flour").getQuantity().getAmount());
   }

   /*
    * public void testImplementAction() { ProposedAction proposedCddDevelopment =
    * new ProposedAction("CDD development"); ImplementedAction
    * implementedCddDevelopment =
    * ActionFactory.createImplementedAction(proposedCddDevelopment);
    * 
    * assertEquals("The planned action is 'CDD development'.",
    * proposedCddDevelopment, implementedCddDevelopment.getPlanedAction()); }
    */
}
