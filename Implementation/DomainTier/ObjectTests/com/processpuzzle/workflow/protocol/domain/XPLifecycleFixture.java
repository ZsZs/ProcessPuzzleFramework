package com.processpuzzle.workflow.protocol.domain;


public class XPLifecycleFixture {
   public static final String XP_PROJECT_LIFECYCLE_NAME = "XPProjectLifecycle";
   public static final String XP_ITERATION_NAME = "Iteration";
   public static final String DAYLY_CYCLE_NAME = "DailyCycle";
   protected LifecycleProtocol xpLifecycle;
   protected LifecyclePhaseProtocol iteration;
   
   public XPLifecycleFixture() {
      super();
   }
   
   public void setUp() {
      xpLifecycle = ProtocolFactory.createLifecycle( XP_PROJECT_LIFECYCLE_NAME );
      iteration = ProtocolFactory.createLifecyclePhaseProtocol( XP_PROJECT_LIFECYCLE_NAME ); 
      xpLifecycle.addPhase( iteration );
   }
   
   public void tearDown() {
      
   }

   public LifecycleProtocol getLifecyle() { return xpLifecycle; }
}
