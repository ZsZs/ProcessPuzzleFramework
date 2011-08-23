package com.processpuzzle.generictests;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.UnknownConfigurationPathException;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;

public abstract class ApplicationContextAwareTest {
   private static String configurationPath = null;
   private static ProcessPuzzleContextFixture applicationContextFixture;
   protected static ProcessPuzzleContext applicationContext;
   
   @BeforeClass public static void beforeAllTests() throws UnknownConfigurationPathException {
      if( configurationPath == null ) throw new UnknownConfigurationPathException();
      
      applicationContextFixture = ProcessPuzzleContextFixture.getInstance( configurationPath );
      applicationContextFixture.setUp();
      applicationContext = applicationContextFixture.getApplicationContext();
   }
   
   @AfterClass public static void afterAllTests() {
      applicationContextFixture.tearDown();
   }
}
