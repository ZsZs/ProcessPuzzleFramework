/*
 * Created on Apr 15, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.control.control;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mockrunner.servlet.BasicServletTestCaseAdapter;
import com.processpuzzle.application.control.control.CommandControllerServlet;

/**
 * @author zsolt.zsuffa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CommandControllerTest extends BasicServletTestCaseAdapter {

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
   public final void testProcessRequest_ForUndefinedCommand() {
      doPost();
    //  assertNotNull("If error occures an Error helper is placed in the request.", getRequestParameter("error"));
   }

}
