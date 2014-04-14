/*
 * Created on May 25, 2006
 */
package com.processpuzzle.artifact_management.control;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mockrunner.servlet.BasicServletTestCaseAdapter;
import com.processpuzzle.application.control.control.CommandControllerServlet;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactActionCommandTest extends BasicServletTestCaseAdapter {

   @Before
   public void setUp() throws Exception {
      super.setUp();
      createServlet(CommandControllerServlet.class);
   }

   @After
   public void tearDown() throws Exception {
      super.tearDown();
   }

   @Test
   public final void testExecute() {
   }
}