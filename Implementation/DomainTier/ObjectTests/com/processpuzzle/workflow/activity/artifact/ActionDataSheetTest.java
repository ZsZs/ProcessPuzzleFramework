/*
 * Created on Sep 14, 2006
 */
package com.processpuzzle.workflow.activity.artifact;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.litest.fixture.FixtureFactory;
import com.processpuzzle.litest.testcase.GenericTestSuite;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;
import com.processpuzzle.workflow.activity.artifact.ActionDataSheet;
import com.processpuzzle.workflow.activity.artifact.ActionDataSheetFactory;

/**
 * @author zsolt.zsuffa
 */
public class ActionDataSheetTest extends GenericTestSuite<ActionDataSheet<?>, ActionDataSheetTestFixture>{
   private static final String containerConfigurationPath = null;
   
   protected ActionDataSheetTest() {
      super( containerConfigurationPath );
   }

   private ActionDataSheetTestFixture testFixture;
   private ActionDataSheet<?> dataSheet;
   DefaultArtifactRepository repository;
   
   private static ProcessPuzzleContextFixture applicationContextFixture;
   private ProcessPuzzleContext applicationContext;
   private ActionDataSheetFactory actionDataSheetFactory;

   @Before public void setUp() throws Exception {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      
      FixtureFactory fixtureFactory = FixtureFactory.createInstance();
      
      testFixture = fixtureFactory.createPersistentSharedFixture( ActionDataSheetTestFixture.class );
      testFixture.setUp();
      dataSheet = testFixture.getRegisterOrder();
      repository = testFixture.getArtifactRepository();
      repository.add(work, dataSheet);
      
      applicationContextFixture = ProcessPuzzleContextFixture.getInstance( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      applicationContextFixture.setUp();
      
      applicationContext = applicationContextFixture.getApplicationContext(); 
      actionDataSheetFactory = applicationContext.getEntityFactory(ActionDataSheetFactory.class);
        
      work.finish();
   }

   @After
   public void tearDown() throws Exception {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      repository.delete(work, dataSheet);
      work.finish();
      testFixture.tearDown();
   }

   @Ignore
   @Test
   public void testCreate_ForAction() {
      assertNotNull("ActionDataShee can provide the underlying pojo - Action - object.", dataSheet.getAction());
      assertEquals("The data sheet inherits the action's name.", dataSheet.getName(), dataSheet.getAction().getName());

      ActionDataSheet<?> anotherDataSheet = actionDataSheetFactory.create("action", "protocolName");
      //ActionDataSheet anotherDataSheet = ActionDataSheet.create(testFixture.getCreator(), "Another action", null);
      assertNotNull("ActionDataSheet also creates the underlying action object.", anotherDataSheet.getAction());
   }

   @Ignore
   @Test
   public void testGetAsXml() {
      assertNotNull("", dataSheet.getAsXml());
      System.out.println(dataSheet.getAsXml());
   }
}
