package com.processpuzzle.workflow.activity.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.processpuzzle.workflow.protocol.domain.LifecycleProtocol;

public class PlanTest {

   @Before
   public void setUp() throws Exception {}

   @After
   public void tearDown() throws Exception {}

   @Test
   public void testGetSubActions() {
   // Plan aPlan = ActionFactory.createPlan("aPlan");
   // Action action1 = ActionFactory.createProposedAction("action1");
   // Action action2 = ActionFactory.createProposedAction("action2");
   // aPlan.addSubAction(action1);
   // aPlan.addSubAction(action2);
   // assertEquals("The Plan has two actions.", 2,
   // aPlan.getSubActions().size());
   // assertTrue("The Plan's subaction collection contains action2.",
   // aPlan.getSubActions().contains(action2));
   }

   @Test
   public void testPlan_for_ImplementedStateChange() {
   // Plan aPlan = ActionFactory.createPlan("aPlan");
   // assertTrue("after initialization, aPlan's state is proposed",
   // aPlan.getGeneric().getStatus() instanceof ProposedStatus);
   // ProposedAction pAction = ActionFactory.createProposedAction("pAction");
   // aPlan.addSubAction(pAction);
   // assertTrue("aPlan's state is still proposed",
   // aPlan.getGeneric().getStatus() instanceof ProposedStatus);
   // pAction.implement();
   // assertTrue("aPlan's state is now implemented",
   // aPlan.getGeneric().getStatus() instanceof ImplementedStatus);
   }

   @Test
   public void testPlan_for_CompletedStateChange() {
   // Plan aPlan = ActionFactory.createPlan("aPlan");
   // ProposedAction pAction1 = ActionFactory.createProposedAction("pAction2");
   // ProposedAction pAction2 = ActionFactory.createProposedAction("pAction2");
   // aPlan.addSubAction(pAction1);
   // aPlan.addSubAction(pAction2);
   // assertTrue("after initialization, aPlan's state is proposed",
   // aPlan.getGeneric().getStatus() instanceof ProposedStatus);
   // ImplementedAction iAction = pAction1.implement();
   // ImplementedAction iAction2 = pAction2.implement();
   // assertTrue("aPlan's state is implemented", aPlan.getGeneric().getStatus()
   // instanceof ImplementedStatus);
   // iAction.complete();
   // assertTrue("aPlan's state is still implemented",
   // aPlan.getGeneric().getStatus() instanceof ImplementedStatus);
   // iAction2.complete();
   // assertTrue("aPlan's state is now completed",
   // aPlan.getGeneric().getStatus() instanceof CompletedStatus);

   }

   public void testCreateProcessByProtocol(LifecycleProtocol lcp) {

   }

}
