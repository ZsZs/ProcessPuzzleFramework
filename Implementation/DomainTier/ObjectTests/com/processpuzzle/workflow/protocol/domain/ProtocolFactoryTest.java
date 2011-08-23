package com.processpuzzle.workflow.protocol.domain;

import static org.junit.Assert.assertNotNull;
import hu.itkodex.litest.fixture.FixtureFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ProtocolFactoryTest {
   private static ProtocolTestFixture testFixture = FixtureFactory.createInstance().createPersistentSharedFixture( ProtocolTestFixture.class );

   @Before
   public void setUp() throws Exception {
      testFixture.beforeEachTests();
   }

   @After
   public void tearDown() throws Exception {
      testFixture.afterEachTests();
   }

   @Ignore
   @Test
   public void testCreateLifeCycle() {
      assertNotNull("Check for existence.", testFixture.getLifeCycle() );
//      assertNotNull("The creation of a new LifecycleProtocol automatically creates a nested StartAction.", testFixture.getLifeCycle().getStartAction() );
   }

}
