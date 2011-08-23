package com.processpuzzle.artifact.artifact;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.artifact.domain.ArtifactSubClass;
import com.processpuzzle.artifact_type.domain.ArtifactTypeTestFixture;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;
import com.processpuzzle.workflow.activity.artifact.ActionList_ListView;

public class TaskList_ListViewTest {
   public static String FIRST_TASKDATASHEET_NAME = "First TaskDataSheets";
   public static String SECOND_TASKDATASHEET_NAME = "Second TaskDataSheets";
   private static String BULK_TASKDATASHEET_NAME_PREFIX = "Bulk_TaskDataSheets_";
   private ProcessPuzzleContextFixture applicationContextFixture;
   private ProcessPuzzleContext applicationContext;
   private ArtifactTypeTestFixture typeFixture;
   private ActionList_ListView listView;
   private UserFactory userFactory;
   private User person;

   @Before
   public void setUp() throws Exception {
      applicationContextFixture = ProcessPuzzleContextFixture.getInstance( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      applicationContextFixture.setUp();
      
      applicationContext = applicationContextFixture.getApplicationContext();
      
      typeFixture = ArtifactTypeTestFixture.getInstance( applicationContext );
      typeFixture.setUp();

      userFactory = applicationContext.getEntityFactory( UserFactory.class );
      person = userFactory.createUser( "P", "psw");
      // taskList = new TaskList("aTaskList");
      createTaskDataSheets();
      // listView = new TaskList_ListView(taskList, "aListView");
      listView = new ActionList_ListView( null, "aListView", null );
   }

   @After
   public void tearDown() throws Exception {
      typeFixture.tearDown();
      // taskList = null;
      listView = null;
      deleteTaskDataSheets();
   }

   @Ignore
   @Test
   public final void testQuery_ForStatement() {
      List<?> lista = listView.getListedArtifactsPropertyViews();
      assertEquals("The list view has 50 element", 50, lista.size());
   }

   /* ************************************************** */
   private void createTaskDataSheets() {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      // Create named ProcessDataSheets

      // Create numbered ProcessDataSheets
      for (int i = 0; i < 51; i++) {
         ArtifactSubClass anArtifact = new ArtifactSubClass(BULK_TASKDATASHEET_NAME_PREFIX + i, typeFixture.getArtifactSubClassType(), person);
//         repository.addArtifact(work, anArtifact);
      }
      work.finish();
   }

   private void deleteTaskDataSheets() {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);

      // delete numbered ProcessDataSheets
      for (int i = 0; i < 51; i++) {
//         repository.deleteArtifactByName(work, BULK_TASKDATASHEET_NAME_PREFIX + i);
      }
      work.finish();
   }
}