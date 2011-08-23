package com.processpuzzle.artifact_management.control;

import java.io.BufferedReader;
import java.io.IOException;

import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;

import com.mockrunner.servlet.BasicServletTestCaseAdapter;
import com.processpuzzle.application.configuration.domain.ApplicationContextFactory;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandControllerServlet;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.configuration.webtier.ConfigurationConstants;

public class RetrieveArtifactFolderStructureTest extends BasicServletTestCaseAdapter {
   private static ProcessPuzzleContext config = null;      
   @Mock private Application mockApplication;
   
   protected void setUp() throws Exception {
      super.setUp();
      
      MockitoAnnotations.initMocks( RetrieveArtifactFolderStructureTest.class );
      config = ApplicationContextFactory.create( mockApplication, ConfigurationConstants.CONFIGURATION_PROPERTY_FILE );
      config.setUp( Application.Action.start );
      
      createServlet(CommandControllerServlet.class);
      addRequestParameter("action","RetrieveArtifactFolderStructure");
   }

   protected void tearDown() throws Exception {
      super.tearDown();
   }

   public void test() {
      doPost();
      BufferedReader reader = getOutputAsBufferedReader();
      try {
         String s;
         while((s=reader.readLine())!=null)
         System.out.println(s);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}