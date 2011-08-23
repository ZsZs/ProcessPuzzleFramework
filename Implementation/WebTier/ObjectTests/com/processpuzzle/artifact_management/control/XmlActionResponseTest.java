/*
 * Created on May 25, 2006
 */
package com.processpuzzle.artifact_management.control;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.processpuzzle.artifact_management.control.XmlActionResponse;

/**
 * @author zsolt.zsuffa
 */
public class XmlActionResponseTest {
   XmlActionResponse response;

   @Before
   protected void setUp() throws Exception {
      response = new XmlActionResponse();
   }

   @After
   protected void tearDown() throws Exception {
      response = null;
   }

   @Test
   public final void testGetAsString_ForHead() {
      assertTrue("The head should contain:", response.getAsString().contains("<?xml version=\"1.0\"?>"));
   }

   @Test
   public final void testGetAsString_ForActionOutcomeStatus() {
      response.setOutcome(true);
      assertTrue("The outcome should be:", response.getAsString().contains("<actionOutcomeStatus>true</actionOutcomeStatus>"));
      response.setOutcome(false);
      assertTrue("The outcome should be:", response.getAsString().contains("<actionOutcomeStatus>false</actionOutcomeStatus>"));
   }
}