package com.processpuzzle.fitnesse.application;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.processpuzzle.application.domain.ApplicationException;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class ApplicationTestCoordinatorTest {
   private static final String TEST_PAGE_NAME = "FirstTestPage";
   private ApplicationTestCoordinator testCoordinator;
   
   @Before
   public void beforeEachTest() {
      testCoordinator = new ApplicationTestCoordinator();
      testCoordinator.configurationPath( DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
   }

   @Test
   public void notifyOnTestPageSetUp_TheFirstCallSetsUpApplication() throws ApplicationException {
      testCoordinator.notifyOnTestPageSetUp( TEST_PAGE_NAME );
      
      assertThat( testCoordinator.getApplication(), notNullValue() );
   }

   @Test
   public void notifyOnTestPageSetUp_SubseqentCallsIncrementsIndex() throws ApplicationException {
      testCoordinator.notifyOnTestPageSetUp( TEST_PAGE_NAME );
      testCoordinator.notifyOnTestPageSetUp( TEST_PAGE_NAME );
      
      assertThat( testCoordinator.getHitCountFor( TEST_PAGE_NAME ), equalTo( 2 ));
   }
   
   @After
   public void afterEachTest() {
      testCoordinator.terminate();
   }
}
