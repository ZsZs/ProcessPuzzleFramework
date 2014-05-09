/*
 * Created on Nov 28, 2006
 */
package com.processpuzzle.workflow.protocol.domain;


import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeFactory;
import com.processpuzzle.litest.fixture.GenericTestFixture;
import com.processpuzzle.litest.fixture.PersistentSharedFixture;
import com.processpuzzle.sharedfixtures.domaintier.DomainTier_ConfigurationFixture;

public class ProtocolTestFixture extends GenericTestFixture<Protocol> implements PersistentSharedFixture<Protocol>{
   public static String LIFECYCLE_NAME = "Lifecycle";
   public static String LIFECYCLE_PHASE_1_NAME = "LifecyclePhase_1";
   public static String LIFECYCLE_PHASE_2_NAME = "LifecyclePhase_2";
   public static String WORKFLOW_DETAIL_1_NAME = "WorkflowDetail_1";
   public static String WORKFLOW_DETAIL_2_NAME = "WorkflowDetail_2";
   public static String ACTIVITY_1_NAME = "Activity_1";
   public static String ACTIVITY_2_NAME = "Activity_2";
   public static String ACTIVITY_3_NAME = "Activity_3";
   public static String ARTIFACT_INSTANCE_NAME_1 = "ArtifactInstance_1";
   public static String ARTIFACT_INSTANCE_NAME_2 = "ArtifactInstance_2";

   private static DomainTier_ConfigurationFixture configurationFixture = null;
   private static ProtocolRepository repository = null;
   private ArtifactType testArtifactType_1 = null;
   private ArtifactType testArtifactType_2 = null;
   private LifecycleProtocol lifecycle = null;
   private LifecyclePhaseProtocol lifecyclePhase_1 = null;
   private LifecyclePhaseProtocol lifecyclePhase_2 = null;
   private ProtocolCallAction phaseCall_1 = null;
   private ProtocolCallAction phaseCall_2 = null;
   private WorkflowDetailProtocol workflowDetailProtocol_1 = null;
   private WorkflowDetailProtocol workflowDetailProtocol_2 = null;
   private ProtocolCallAction workflowDetailCall_1 = null;
   private ProtocolCallAction workflowDetailCall_2 = null;
   private ActivityProtocol activityProtocol_1 = null;
   private ActivityProtocol activityProtocol_2 = null;
   private ActivityProtocol activityProtocol_3 = null;
   private ProtocolCallAction activityCall_1 = null;
   private ProtocolCallAction activityCall_2 = null;
   private ProtocolCallAction activityCall_3 = null;
   private ArtifactInstance artifactInstance_1 = null;
   private ArtifactInstance artifactInstance_2 = null;
   private ArtifactTypeFactory artifactTypeFactory;

   public void beforeEachTests() {
      configurationFixture = DomainTier_ConfigurationFixture.getInstance();
      configurationFixture.setUp();
      repository = DomainTier_ConfigurationFixture.getProtocolRepository();
      artifactTypeFactory = configurationFixture.getConfig().getEntityFactory( ArtifactTypeFactory.class );

      testArtifactType_1 = artifactTypeFactory.create( "TestArtifactType_1", "SystemAdministration" );
      testArtifactType_2 = artifactTypeFactory.create( "TestArtifactType_2", "SystemAdministration" );
      artifactInstance_1 = new ArtifactInstance( testArtifactType_1, "InstanceInWorkflow_1" );
      artifactInstance_2 = new ArtifactInstance( testArtifactType_1, "InstanceInWorkflow_2" );

      // lifecycle = ProtocolFactory.createLifecycleProtocol(LIFECYCLE_NAME);
      // lifecyclePhase_1 = ProtocolFactory.createLifecyclePhaseProtocol( LIFECYCLE_PHASE_1_NAME );
      // lifecyclePhase_2 = ProtocolFactory.createLifecyclePhaseProtocol( LIFECYCLE_PHASE_2_NAME );
      // phaseCall_1 = lifecycle.addPhase( lifecyclePhase_1, "phase_1" );
      // phaseCall_2 = lifecycle.addPhase( lifecyclePhase_2, "phase_2" );
      // lifecycle.addControlFlow( phaseCall_1, phaseCall_2 );
      //      
      // workflowDetailProtocol_1 = ProtocolFactory.createWorkflowDetailProtocol( WORKFLOW_DETAIL_1_NAME );
      // workflowDetailProtocol_2 = ProtocolFactory.createWorkflowDetailProtocol( WORKFLOW_DETAIL_2_NAME );
      // workflowDetailCall_1 = lifecyclePhase_1.addWorflowDetail( workflowDetailProtocol_1, "phase_1.workflow_1" );
      // workflowDetailCall_2 = lifecyclePhase_1.addWorflowDetail( workflowDetailProtocol_2, "phase_1.workflow_2" );
      // lifecyclePhase_1.addControlFlow( workflowDetailCall_1, workflowDetailCall_2 );
      //      
      // activityProtocol_1 = ProtocolFactory.createActivityProtocol( ACTIVITY_1_NAME );
      // activityProtocol_2 = ProtocolFactory.createActivityProtocol( ACTIVITY_2_NAME );
      // activityProtocol_3 = ProtocolFactory.createActivityProtocol( ACTIVITY_3_NAME );
      // activityCall_1 = workflowDetailProtocol_1.addActivity( activityProtocol_1, "phase_1.workflow_1.activity_1" );
      // activityCall_2 = workflowDetailProtocol_1.addActivity( activityProtocol_2, "phase_1.workflow_1.activity_2" );
      // activityCall_3 = workflowDetailProtocol_1.addActivity( activityProtocol_3, "phase_1.workflow_1.activity_3" );
      // workflowDetailProtocol_1.addControlFlow( activityCall_1, activityCall_2 );
      // workflowDetailProtocol_1.addControlFlow( activityCall_2, activityCall_3 );
      // workflowDetailProtocol_1.addControlFlow( activityCall_1, activityCall_3 );
      //      
      // OutputParameter activity_1_OutputParameter = activityProtocol_1.addOutputParameter( "output_1", testArtifactType_1 );
      // InputParameter activity_2_InputParameter_1 = activityProtocol_2.addInputParameter( "input_1", testArtifactType_1 );
      // InputParameter activity_2_InputParameter_2 = activityProtocol_2.addInputParameter( "input_2", testArtifactType_2 );
      // OutputParameter activity_2_OutputParameter = activityProtocol_2.addOutputParameter( "output_1", testArtifactType_1 );
      // InputParameter activity_3_InputParameter = activityProtocol_3.addInputParameter( "input_1", testArtifactType_1 );
      //
      // try {
      // workflowDetailProtocol_1.addObjectFlow( artifactInstance_1, activityCall_1, activity_1_OutputParameter, activityCall_2, activity_2_InputParameter_1 );
      // workflowDetailProtocol_1.addObjectFlow( artifactInstance_1, activityCall_1, activity_1_OutputParameter, activityCall_3, activity_3_InputParameter );
      // } catch (DuplicatedObjectFlowException e) {
      // e.printStackTrace();
      // } catch (SelfReferencedObjectFlowException e) {
      // // TODO Auto-generated catch block
      // e.printStackTrace();
      // } catch (ObjectFlowParameterTypeMismatchException e) {
      // // TODO Auto-generated catch block
      // e.printStackTrace();
      // }
   }

   public void afterEachTests() {
      configurationFixture.tearDown();

      // workflowDetailProtocol_1.removeActivity( activityCall_1 );
      // workflowDetailProtocol_1.removeActivity( activityCall_2 );
      // workflowDetailProtocol_1.removeActivity( activityCall_3 );
      // lifecyclePhase_1.removeWorkflowDetail( workflowDetailCall_1 );
      // lifecyclePhase_1.removeWorkflowDetail( workflowDetailCall_2 );
      // lifecycle.removePhase( phaseCall_1 );
      // lifecycle.removePhase( phaseCall_2 );

      lifecycle = null;
      phaseCall_1 = null;
      phaseCall_2 = null;
      lifecyclePhase_1 = null;
      lifecyclePhase_2 = null;

      workflowDetailCall_1 = null;
      workflowDetailCall_2 = null;
      workflowDetailProtocol_1 = null;
      workflowDetailProtocol_2 = null;

      activityCall_1 = null;
      activityCall_2 = null;
      activityCall_3 = null;
      activityProtocol_1 = null;
      activityProtocol_2 = null;
      activityProtocol_3 = null;

      artifactInstance_1 = null;
      artifactInstance_2 = null;
      testArtifactType_1 = null;

   }

   public LifecycleProtocol getLifeCycle() {
      return lifecycle;
   }

   public LifecyclePhaseProtocol getLifecyclePhaseProtocol_1() {
      return lifecyclePhase_1;
   }

   public LifecyclePhaseProtocol getLifecyclePhaseProtocol_2() {
      return lifecyclePhase_2;
   }

   public WorkflowDetailProtocol getWorkflowDetailProtocol_1() {
      return workflowDetailProtocol_1;
   }

   public WorkflowDetailProtocol getWorkflowDetailProtocol_2() {
      return workflowDetailProtocol_2;
   }

   public ActivityProtocol getActivityProtocol_1() {
      return activityProtocol_1;
   }

   public ActivityProtocol getActivityProtocol_2() {
      return activityProtocol_2;
   }

   public ActivityProtocol getActivityProtocol_3() {
      return activityProtocol_3;
   }

   public ArtifactInstance getArtifactInstance_1() {
      return artifactInstance_1;
   }

   public ArtifactInstance getArtifactInstance_2() {
      return artifactInstance_2;
   }

   @Override
   protected void configureAfterSutInstantiation() {
      // TODO Auto-generated method stub
      
   }

   @Override
   protected Protocol instantiateSUT() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      // TODO Auto-generated method stub
      
   }

   @Override
   protected void releaseResources() {
      // TODO Auto-generated method stub
      
   }
}
