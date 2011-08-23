package com.processpuzzle.generictests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.processpuzzle.application.configuration.domain.InternalizationContext;
import com.processpuzzle.application.configuration.domain.MeasurementContext;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.PropertyContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public abstract class PropertyContextAwareTest {
   protected static String configurationDescriptorPath = DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH; 
   @Mock protected static Application application;
   @Mock protected static ProcessPuzzleContext applicationContext;
   protected static PropertyContext propertyContext;
   protected static MeasurementContext measurementContext;
   protected static InternalizationContext i18Context;

   @BeforeClass
   public static void beforeAllTests() {
      MockitoAnnotations.initMocks( PropertyContextAwareTest.class );
      when( application.getContext() ).thenReturn( applicationContext );
      
      propertyContext = new PropertyContext( application, configurationDescriptorPath );
      propertyContext.setUp( Application.Action.start );
      when( applicationContext.getPropertyContext() ).thenReturn( propertyContext );
      
      measurementContext = new MeasurementContext( application );
      measurementContext.setUp( Application.Action.start );
      when( applicationContext.getMeasurementContext() ).thenReturn( measurementContext );
      
      i18Context = new InternalizationContext( application );
      i18Context.setUp( Application.Action.start );
      when( applicationContext.getInternalizationContext() ).thenReturn( i18Context );
   }

   @AfterClass
   public static void afterAllTests() {
      i18Context.tearDown( Application.Action.stop );
      i18Context = null;
      measurementContext.tearDown( Application.Action.stop );
      measurementContext = null;
      propertyContext.tearDown( Application.Action.stop );
      propertyContext = null;
   }
}
