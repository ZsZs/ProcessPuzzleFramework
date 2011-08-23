package com.processpuzzle.application.configuration.domain;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;

import com.processpuzzle.application.domain.Application;

public class PersistentApplicationContextTest {
   private PersistentApplicationContext applicationContext;
   private Logger log = mock( Logger.class );

   @Before
   public void beforeEachTests() {
      applicationContext = TestPersistentContext.getInstance();
      ((TestPersistentContext) applicationContext).setLog( log );
   }

   @Ignore
   @Test
   public void testSetUp_ForStartAction() {
      // EXERCISE:
      applicationContext.setUp( Application.Action.start );

      // VERIFY:
      assertThat( applicationContext.isConfigured(), is( true ) );
   }

   @Ignore
   @Test
   public void testSetUp_ForInstallAction() {
      // EXERCISE:
      applicationContext.setUp( Application.Action.install );

      // VERIFY:
      assertThat( applicationContext.isConfigured(), is( true ) );
      verify( log ).debug( "Persistent components are installed." );
   }

   @Ignore
   @Test
   public void testTearDown_ForStopAction() {
      // SETUP:
      applicationContext.setUp( Application.Action.start );

      // EXERCISE:
      applicationContext.tearDown( Application.Action.stop );

      // VERIFY:
      assertThat( applicationContext.isConfigured(), is( false ) );
   }

   @Ignore
   @Test
   public void testTearDown_ForUnistallAction() {
      // SETUP:
      applicationContext.setUp( Application.Action.start );

      // EXERCISE:
      applicationContext.tearDown( Application.Action.uninstall );

      // VERIFY:
      assertThat( applicationContext.isConfigured(), is( false ) );
      verify( log ).debug( "Persistent components are uninstalled." );
   }
}
