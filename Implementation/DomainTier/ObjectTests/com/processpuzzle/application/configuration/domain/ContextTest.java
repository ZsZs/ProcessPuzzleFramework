package com.processpuzzle.application.configuration.domain;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class ContextTest {
   protected static String configurationDescriptorPath = DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH; 
   protected static PropertyContext propertyContext;
   @Mock protected static Application application;

   @BeforeClass
   public static void beforeAllTests() {
      MockitoAnnotations.initMocks( ContextTest.class );
      propertyContext = new PropertyContext( application, configurationDescriptorPath );
      propertyContext.setUp( Application.Action.start );
   }

   @AfterClass
   public static void afterAllTests() {
      propertyContext.tearDown( Application.Action.stop );
      propertyContext = null;
   }
}
