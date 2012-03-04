package com.processpuzzle.application.configuration.domain;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class ContextTest {
   protected static String configurationDescriptorPath = DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH; 
   protected static PropertyContext propertyContext;
   protected static Application application;

   @BeforeClass
   public static void beforeAllTests() {
      application = mock( Application.class );
      when( application.getApplicationName() ).thenReturn( "JUnitTest" );
      propertyContext = new PropertyContext( application, configurationDescriptorPath );
      propertyContext.setUp( Application.Action.start );
   }

   @AfterClass
   public static void afterAllTests() {
      propertyContext.tearDown( Application.Action.stop );
      propertyContext = null;
   }
}
