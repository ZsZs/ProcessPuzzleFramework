/*
 * Created on Sep 14, 2006
 */
package com.processpuzzle.workflow.activity.artifact;

import hu.itkodex.litest.fixture.FixtureFactory;
import hu.itkodex.litest.fixture.GenericTestFixture;
import hu.itkodex.litest.fixture.PersistentSharedFixture;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;
import com.processpuzzle.workflow.activity.artifact.ActionDataSheet;
import com.processpuzzle.workflow.activity.artifact.ActionDataSheetFactory;

/**
 * @author zsolt.zsuffa
 */
public class ActionDataSheetTestFixture extends GenericTestFixture<ActionDataSheet<?>> implements PersistentSharedFixture<ActionDataSheet<?>>{
   private static String REGISTER_ORDER_NAME = "Register order";
   private ActionDataSheet<?> registerOrder;
   private ProcessPuzzleContext applicationContext;
   private UserFactory userFactory;
   private User creator;
   private ActionDataSheetFactory actionDataSheetFactory;
   private DefaultArtifactRepository artifactRepository;

   protected ActionDataSheetTestFixture() {
      super();
      FixtureFactory fixtureFactory = FixtureFactory.createInstance();
      nextFixture = fixtureFactory.createImmutableSharedFixture( ProcessPuzzleContextFixture.class );
   }

//   public static ActionDataSheetTestFixture getInstance() {
//      if( fixtureInstance == null )
//         fixtureInstance = new ActionDataSheetTestFixture();
//      return (ActionDataSheetTestFixture) fixtureInstance;
//   }

   public void setUp() {
      userFactory = applicationContext.getEntityFactory( UserFactory.class );
      creator = userFactory.createUser( "John Smith", "psw" );

      actionDataSheetFactory = applicationContext.getEntityFactory( ActionDataSheetFactory.class );
      registerOrder = actionDataSheetFactory.create( REGISTER_ORDER_NAME, "" );

      artifactRepository = applicationContext.getRepository( DefaultArtifactRepository.class );
   }

   public void tearDown() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      artifactRepository.delete( work, registerOrder );
      work.finish();
      registerOrder = null;
   }

   //Properties
   public ActionDataSheet<?> getRegisterOrder() { return registerOrder; }

   public User getCreator() { return creator; }

   public DefaultArtifactRepository getArtifactRepository() {
      return applicationContext.getRepository( DefaultArtifactRepository.class );
   }

   @Override
   protected ActionDataSheet<?> instantiateSUT() {
      return null;
   }

   @Override
   protected void configureAfterSutInstantiation() {
      // TODO Auto-generated method stub
      
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
