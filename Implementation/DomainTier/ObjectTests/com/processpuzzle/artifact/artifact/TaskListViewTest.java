package com.processpuzzle.artifact.artifact;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.workflow.activity.artifact.ActionList;
import com.processpuzzle.workflow.activity.domain.WorkflowRepository;


public class TaskListViewTest {
   
   ActionList taskList = null;
   WorkflowRepository actionRepository = null;

   @Before
   public void setUp() throws Exception {
//      ProcessPuzzleContext config = ProcessPuzzleContext.createInstance( ConfigurationConstants.CONFIGURATION_DESCRIPTOR_FILE );
      ProcessPuzzleContext config = ProcessPuzzleContext.getInstance();
          
      config.setUp( Application.Action.start );
      actionRepository = (WorkflowRepository)ProcessPuzzleContext.getInstance().getRepository(WorkflowRepository.class);
    }
   
   @Ignore
   @Test
   public void testGetListedTasksPropertyView() {
//     
//      ActivityProtocol digging = new ActivityProtocol("Digging");
//      
//      ProposedAction pAction = new ProposedAction(digging.getName(), digging);
//      pAction.setTimePeriod(new TimePeriod(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
//      
//      actionRepository.addProposedAction(pAction);
//      
//      pAction.implement();
//      
//      actionRepository.updateProposedAction(pAction);
//      
//      taskList = new TaskList("TaskList", new ArtifactType("TaskListType"));
//      
//      TaskList_ListView listView = new TaskList_ListView();
//      
//      String query = "from ImplementedAction a where a.generic.protocol is not null and a.generic.protocol.name = 'Digging'";
//      
//      List listedTasksPropertyViews = listView.getListedArtifactsPropertyView(query);
//      System.out.println(listedTasksPropertyViews);
//       
//      List result = (List)actionRepository.find(query);
//      assertNotNull(result);
//      assertNotNull(result.get(0));
//      Action action = (Action)result.get(0);
//      assertEquals(action.getName(), "Digging");
//      assertEquals(action.getProtocol().getName(), "Digging");
//
   }
   

   @After
   public void tearDown() throws Exception {}

}
