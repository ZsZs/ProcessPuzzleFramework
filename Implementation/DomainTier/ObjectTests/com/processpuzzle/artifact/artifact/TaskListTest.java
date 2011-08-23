package com.processpuzzle.artifact.artifact;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.workflow.activity.artifact.ActionList;
import com.processpuzzle.workflow.activity.artifact.ActionListFactory;
import com.processpuzzle.workflow.activity.artifact.ActionList_ListView;
import com.processpuzzle.workflow.activity.artifact.ActionList_PropertyView;

public class TaskListTest {
   private ProcessPuzzleContext applicationContext;
   private UserFactory userFactory;
   private ActionListFactory actionListFactory;
   private ActionList taskList = null;

   @Before
   public void setUp() throws Exception {
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      userFactory = applicationContext.getEntityFactory( UserFactory.class );
      actionListFactory = applicationContext.getEntityFactory( ActionListFactory.class );
      taskList = actionListFactory.create();  
   }

   @After
   public void tearDown() throws Exception {
      taskList = null;
   }

   @Ignore @Test
   public void testAvailableView() {
      assertTrue("A taskList has at least one view.", taskList.getAvailableViews().size() >= 1);
      assertNotNull("A PropertyView is required.", taskList.getPropertyView());
   }

   @Ignore @Test
   public void testArtifactListPropetyView() {
      User responsible = userFactory.createUser( "Nagy Béla", "password" );
      taskList.setResponsible(responsible);

      ActionList_PropertyView propertyView = (ActionList_PropertyView) taskList.getPropertyView();
      assertEquals("Nagy Béla", propertyView.getResponsibleName());
      // assertNotNull(propertyView.getCreationDate());

   }

   @Ignore @Test
   public void testArtifactList_ListView() {
      ActionList_ListView listView = (ActionList_ListView) taskList.findViewByName(ActionList_ListView.class.getName());
      assertNotNull(listView);
   }


}
