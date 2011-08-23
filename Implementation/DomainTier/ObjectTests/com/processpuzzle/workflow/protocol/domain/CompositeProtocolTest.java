/*
 * Created on Nov 28, 2006
 */
package com.processpuzzle.workflow.protocol.domain;

import hu.itkodex.litest.fixture.FixtureFactory;

import org.junit.After;
import org.junit.Before;

public class CompositeProtocolTest {
   private static ProtocolTestFixture testFixture = FixtureFactory.createInstance().createPersistentSharedFixture( ProtocolTestFixture.class );
   private CompositeProtocol compositeProtocol = null;
   ArtifactInstance artifactInstance_1;
   ArtifactInstance artifactInstance_2;
   WorkflowDetailProtocol workflowDetailProtocol_1;   
   ProtocolCallAction actionCall_1_1_1;
   ProtocolCallAction actionCall_1_1_2;
   ActivityProtocol activityProtocol_1;
   OutputParameter activityProtocol_1_out;
   ActivityProtocol activityProtocol_2;
   InputParameter activityProtocol_2_In_1;
   InputParameter activityProtocol_2_In_2;
   
   @Before
   public void setUp() throws Exception {
//      testFixture.setUp();
//      compositeProtocol = ProtocolFactory.createWorkflowDetailProtocol("A_WorkflowDetail");
//
//      artifactInstance_1 = testFixture.getArtifactInstance_1();
//      artifactInstance_2 = testFixture.getArtifactInstance_2();
//      workflowDetailProtocol_1 = testFixture.getWorkflowDetailProtocol_1();
//      
//      actionCall_1_1_1 = workflowDetailProtocol_1.findStepByName( "phase_1.workflow_1.activity_1" );
//      activityProtocol_1 = (ActivityProtocol) actionCall_1_1_1.getReferedProtocol();
//      activityProtocol_1_out = (OutputParameter) activityProtocol_1.getParameters().get( "output_1" );
//      
//      actionCall_1_1_2 = workflowDetailProtocol_1.findStepByName( "phase_1.workflow_1.activity_2" );
//      activityProtocol_2 = (ActivityProtocol) actionCall_1_1_2.getReferedProtocol();
//      activityProtocol_2_In_1 = (InputParameter) activityProtocol_2.getParameters().get( "input_1" );      
//      activityProtocol_2_In_2 = (InputParameter) activityProtocol_2.getParameters().get( "input_2" );      
   }

   @After
   public void tearDown() throws Exception {
      testFixture.afterEachTests();
   }
   
//   public void testAddStep_ForSuccess() {
//      Protocol aProtocol = ProtocolFactory.createActivityProtocol("A_Component");
//      ProtocolCallAction callAction = compositeProtocol.addStep( aProtocol );
//      assertEquals("Adding a step to a composite protocol creates a new 'ProtocolCallAction' which refers the given component protocol.",
//            aProtocol, callAction.getReferedProtocol() );
//      assertEquals("The newly created 'ProtocolCallAction's parent is the given CompositeProtocol.", compositeProtocol, callAction.getParentProtocol() );
//      assertTrue("The compositeProtocol references the component.", compositeProtocol.getComponentProtocols().contains(aProtocol));
//   }
//   
//   public void testAddStep_ForFail() {
//      Protocol componentProtocol = ProtocolFactory.createActivityProtocol("A_Component");
//      ProtocolCallAction callAction = compositeProtocol.addStep( componentProtocol, "A_Step" );
//      
//      try {
//         compositeProtocol.addStep( componentProtocol, "A_Step" );
//         fail("NameCollitionException was not thrown.");
//      } catch (Exception e) {
//         assertTrue("Trying to add a step with an already existing name throws a", e instanceof NameCollisionException );
//      }
//   }
//   
//   public void testAddControlFlow_ForSuccess() {
//      Protocol protocol_1 = ProtocolFactory.createActivityProtocol("Protocol_1");
//      Protocol protocol_2 = ProtocolFactory.createActivityProtocol("Protocol_2");
//      ProtocolCallAction step_1 = compositeProtocol.addStep( protocol_1 );
//      ProtocolCallAction step_2 = compositeProtocol.addStep( protocol_2 );
//      ControlFlow controlFlow_1 = compositeProtocol.addControlFlow( step_1, step_2 );
//      assertTrue("Contol flow binds step_1 and step_2", (controlFlow_1.getSource().equals(step_1) && controlFlow_1.getTarget().equals( step_2 )));
//      assertTrue("Contol flow indirectly binds Protocol_1 and Protocol_2", 
//            (controlFlow_1.getSource().getReferedProtocol().equals( protocol_1 ) && controlFlow_1.getTarget().getReferedProtocol().equals( protocol_2 )));
//      assertEquals("The number of outgoing flows of step_1 is:", 1, step_1.getOutgoingControlFlows().size() );
//      assertEquals("The number of incomming flows of step_2 is:", 1, step_2.getIncommingControlFlows().size() );
//      
//      Protocol protocol_3 = ProtocolFactory.createActivityProtocol("Protocol_3");
//      ProtocolCallAction step_3 = compositeProtocol.addStep( protocol_2 );      
//      ControlFlow controlFlow_2 = compositeProtocol.addControlFlow( step_1, step_3 );
//      assertNotNull("It is possible to bind the same 'step_1' with another 'step_3'", controlFlow_2 );
//      assertEquals("Now the number of outgoing controls of 'step_1' is:", 2, step_1.getOutgoingControlFlows().size() );
//   }
//   
//   public void testAddControlFlow_ForFail() {
//      Protocol protocol_1 = ProtocolFactory.createActivityProtocol("Protocol_1");
//      Protocol protocol_2 = ProtocolFactory.createActivityProtocol("Protocol_2");
//      ProtocolCallAction step_1 = compositeProtocol.addStep( protocol_1 );
//      CompositeProtocol anotherComposite = ProtocolFactory.createLifecycleProtocol( "AnotherComposite");
//      ProtocolCallAction step_2 = anotherComposite.addStep(protocol_2);
//      try {
//         compositeProtocol.addControlFlow(step_1, step_2);
//         fail("Exception was not thrown.");
//      } catch (Exception e) {
//         assertTrue("Trying to bind two steps in different parent protocol throws exception:", e instanceof CrossParentControlFlowException );
//      }
//   }
//   
//   public void testAddObjectFlow_ForSuccess() {
//      //Please note that ProtocolTestFixture has a number of addObjectFlow() calls.
//      //Here we only check the result of these calls.
//      assertEquals(
//            "'phase_1.workflow_1.activity_1' has two outgoing ObjectFlows,", 
//            2,
//            testFixture.getWorkflowDetailProtocol_1().findStepByName("phase_1.workflow_1.activity_1").getOutputObjectFlows().size() );
//      assertEquals(
//            "which receives 'phase_1.workflow_1.activity_2', ", 
//            1,
//            testFixture.getWorkflowDetailProtocol_1().findStepByName("phase_1.workflow_1.activity_2").getInputObjectFlows().size() );
//      assertEquals(
//            "and 'phase_1.workflow_1.activity_3', ", 
//            1,
//            testFixture.getWorkflowDetailProtocol_1().findStepByName("phase_1.workflow_1.activity_3").getInputObjectFlows().size() );
//   }
//   
//   public void testAddObjectFlow_ForFailure() {
//      try {
//         workflowDetailProtocol_1.addObjectFlow( artifactInstance_1, actionCall_1_1_1, activityProtocol_1_out, actionCall_1_1_1, null );
//         fail("SelfReferencedObjectFlowException shold be thrown.");
//      } catch (Exception e) {
//         assertTrue("Trying to add a self referenced ObjectFlow throws: ", e instanceof SelfReferencedObjectFlowException );
//      }
//
//      try {
//         workflowDetailProtocol_1.addObjectFlow(artifactInstance_2, actionCall_1_1_1, activityProtocol_1_out, actionCall_1_1_2,  activityProtocol_2_In_2 );
//         fail("ObjectFlowParameterTypeMismatchException shold be thrown.");
//      } catch (Exception e) {
//         assertTrue("Mismatched in/out parameters throws: ", e instanceof ObjectFlowParameterTypeMismatchException );
//      }
//
//      try {
//         workflowDetailProtocol_1.addObjectFlow( artifactInstance_1, actionCall_1_1_1, activityProtocol_1_out, actionCall_1_1_2, activityProtocol_2_In_1 );
//         fail("DuplicateObjectFlowException shold be thrown.");
//      } catch (Exception e) {
//         assertTrue("Between action_1 and action_2 already exist a ObjectFlow. Trying to add the same object flow throws an exception", e instanceof DuplicatedObjectFlowException );
//      }
//   }
}
