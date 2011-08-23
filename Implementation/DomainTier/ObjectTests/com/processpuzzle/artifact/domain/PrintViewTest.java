package com.processpuzzle.artifact.domain;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.artifact.domain.PrintView;

public class PrintViewTest {
   private ArtifactTestFixture artifactFixture = null;
   private TestEntityDataSheet dataSheet;
   private PrintView<?> printView;

   @Before
   public void setUp() throws Exception {
      artifactFixture = ArtifactTestFixture.getInstance();
      artifactFixture.setUp();
      dataSheet = artifactFixture.getDataSheet();
      printView = (PrintView<?>) dataSheet.getAvailableViews().get(TestEntityDataSheet_PrintView.class.getSimpleName());
   }

   @After
   public void tearDown() throws Exception {
      artifactFixture.tearDown();
      printView = null;
   }
   
   @Ignore
   @Test
   public void testPrintView_ForExistence() {
      assertNotNull(printView);
   }
}