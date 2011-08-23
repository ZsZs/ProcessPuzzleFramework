/*
 * Created on Oct 22, 2006
 */
package com.processpuzzle.workflow.activity.artifact;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import hu.itkodex.litest.fixture.FixtureFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.workflow.activity.artifact.PlanDataSheet;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;

/**
 * @author zsolt.zsuffa
 */
public class PlanDataSheetTest {
   private PlanDataSheetTestFixture testFixture = null;
   private PlanDataSheet dataSheet = null;
   DefaultArtifactRepository repository = null;

   @Before
   public void setUp() throws Exception {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      testFixture = FixtureFactory.createInstance().createPersistentSharedFixture( PlanDataSheetTestFixture.class );
      testFixture.beforeEachTests();
      dataSheet = testFixture.getOrderProcess();
      repository = testFixture.getArtifactRepository();
      repository.add(work, dataSheet);
      work.finish();
   }

   @After
   public void tearDown() throws Exception {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      repository.delete(work, dataSheet);
      work.finish();
      testFixture.afterEachTests();
   }

   @Ignore @Test
   public void testCreate_ForSuccess() {
      assertNotNull("Plan data sheet is created.", dataSheet);
      assertEquals("Plan data sheet has the same name as the undelying plan pojo.", dataSheet.getName(), dataSheet.getPlan().getName());
      assertEquals("Plan has 1 sub action:", dataSheet.getPlan().getSubActions().size(), 1);
   }

   @Ignore @Test
   public void testGetAsXml() {
      assertNotNull("", dataSheet.getAsXml());
      System.out.println(dataSheet.getAsXml());
   }
}
