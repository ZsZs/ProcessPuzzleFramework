package com.processpuzzle.artifact_management.control;

import java.io.FileNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.processpuzzle.sharedfixtures.webtier.MockServletContextFixture;

public class RetrieveArtifactCommandTest {
   private static final String REQUEST_URI = "/CommandControllerServlet?action=RetrieveArtifactData&artifactId=";
   private static MockServletContextFixture servletContextFixture;
   
   public @BeforeClass static void beforeAllTests() throws FileNotFoundException {
      servletContextFixture = new MockServletContextFixture();
      servletContextFixture.setUp();
   }
   
   public @AfterClass static void afterAllTests() {
      servletContextFixture.tearDown();
   }
}
