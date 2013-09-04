package com.processpuzzle.workflow.activity.artifact;


import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.litest.fixture.FixtureFactory;

public class ProcessPlanDataSheetTest {
   private ActionDataSheetTestFixture fixture = null;
   
   @Before
   public void setUp() {
      fixture = FixtureFactory.createInstance().createPersistentSharedFixture( ActionDataSheetTestFixture.class );
      fixture.setUp();
   }
   
   @After
   public void tearDown() {
      fixture.tearDown();
      fixture = null;
   }
   
   @Ignore
   @Test
   public void testCreate_ForSuccess() {
//      dataSheet = ProcessPlanDataSheet.create( creator, pplanName, pplanSubject, lifecycleName);
   }
}
