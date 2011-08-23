/*
 * Created on Oct 22, 2006
 */
package com.processpuzzle.workflow.activity.artifact;

import hu.itkodex.litest.fixture.GenericTestFixture;
import hu.itkodex.litest.fixture.PersistentSharedFixture;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTier_ConfigurationFixture;

/**
 * @author zsolt.zsuffa
 */
public class PlanDataSheetTestFixture extends GenericTestFixture<PlanDataSheet> implements PersistentSharedFixture<PlanDataSheet> {
   private static DomainTier_ConfigurationFixture configurationFixture;
   private static String REGISTER_ORDER_NAME = "Register order";
   private static String ACKNOWLEDGE_ORDER_NAME = "Acknowledge order";
   private static String ORDER_FULFILLMENT_NAME = "Order fulfillment";
   private static String ORDER_PROCESS_NAME = "Order process";
   private ActionDataSheet<?> registerOrder;
   private ActionDataSheet<?> acknowledgeOrder;
   private PlanDataSheet orderFulfillment;
   private PlanDataSheet orderProcess;
   private UserFactory userFactory;
   private User creator;
   private ActionDataSheetFactory actionDataSheetFactory;
   private PlanDataSheetFactory planDataSheetFactory;
   private ProcessPuzzleContext applicationContext;
   private DefaultArtifactRepository artifactRepository;

   public void beforeEachTests() {
      configurationFixture = DomainTier_ConfigurationFixture.getInstance();
      configurationFixture.setUp();

      applicationContext = DomainTier_ConfigurationFixture.getConfig();
      actionDataSheetFactory = applicationContext.getEntityFactory( ActionDataSheetFactory.class );
      planDataSheetFactory = applicationContext.getEntityFactory( PlanDataSheetFactory.class );
      userFactory = applicationContext.getEntityFactory( UserFactory.class );

      creator = userFactory.createUser( "John Smith", "psw" );
      registerOrder = actionDataSheetFactory.create( REGISTER_ORDER_NAME, "protocol" );
      acknowledgeOrder = actionDataSheetFactory.create( ACKNOWLEDGE_ORDER_NAME, "protocol" );

      orderFulfillment = planDataSheetFactory.create( creator, ORDER_FULFILLMENT_NAME, "protocol" );
      orderFulfillment.getPlan().setDescription( "This is the process of fulfillment of order: xx." );
      orderFulfillment.getPlan().addSubAction( registerOrder.getAction() );
      orderFulfillment.getPlan().addSubAction( acknowledgeOrder.getAction() );

      orderProcess = planDataSheetFactory.create( creator, ORDER_PROCESS_NAME, "protocol" );
      orderProcess.getPlan().addSubAction( orderFulfillment.getPlan() );

      artifactRepository = applicationContext.getRepository( DefaultArtifactRepository.class );
   }

   public void afterEachTests() {
      configurationFixture.tearDown();

      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      artifactRepository.delete( work, orderFulfillment );
      registerOrder = null;
      acknowledgeOrder = null;
      orderFulfillment = null;
   }

   public PlanDataSheet getOrderProcess() {
      return orderProcess;
   }

   public PlanDataSheet getOrderFulfillment() {
      return orderFulfillment;
   }

   public User getCreator() {
      return creator;
   }

   public DefaultArtifactRepository getArtifactRepository() {
      return DomainTier_ConfigurationFixture.getArtifactRepository();
   }

   @Override
   protected void configureAfterSutInstantiation() {
   // TODO Auto-generated method stub

   }

   @Override
   protected PlanDataSheet instantiateSUT() {
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
