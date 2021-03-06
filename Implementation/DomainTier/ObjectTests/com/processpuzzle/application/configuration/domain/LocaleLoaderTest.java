package com.processpuzzle.application.configuration.domain;

// TODO: Needs to be merged with InternalizationRepository ?
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class LocaleLoaderTest {
   private static Application application;
   private static ProcessPuzzleContext applicationContext;
   private static PropertyContext propertyContext;
   private InternalizationContext internalizationContext;

   @BeforeClass public static void beforeAllTests() {
      application = mock( Application.class );
      applicationContext = mock( ProcessPuzzleContext.class );
      
      when( application.getContext() ).thenReturn( applicationContext );
      
      propertyContext = new PropertyContext( application, DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH);
      propertyContext.setUp( Application.Action.start );
      when( applicationContext.getPropertyContext() ).thenReturn( propertyContext );
   }

   @Before public void beforeEachTest() throws Exception {
      MeasurementContext measurementContext = new MeasurementContext( application );
      measurementContext.setUp( Application.Action.start );
      stub( applicationContext.getMeasurementContext() ).toReturn( measurementContext );
      
      internalizationContext = new InternalizationContext( application );
      internalizationContext.setUp( Application.Action.start );
   }

   @After public void afterEachTest() throws Exception {
      internalizationContext.tearDown( Application.Action.stop );
      internalizationContext = null;
   }

   @Test
   public void testLocaleLoader_forLocalesLoaded() {
      assertTrue("Hungarian language does exist.", internalizationContext.isExist("hu"));
      assertTrue("English language does exist.", internalizationContext.isExist("en"));
      assertFalse("XY language does not exist.", internalizationContext.isExist("xy"));
   }

   @Test
   public void testLocaleLoader_forDecimalSeparator() {
      assertEquals("Hungarian decimal separator is ','", ',', internalizationContext.findLocaleByLanguageAndCountry("hu", "HU").getQuantityFormat().getDecimalSeparator());
      assertEquals("English decimal separator is '.'", '.', internalizationContext.findLocaleByLanguageAndCountry("en", "US").getQuantityFormat().getDecimalSeparator());
   }

   @Test
   public void testLocaleLoader_forDatePattern() {
      assertEquals("Great Britain date format is set via XML", "dd/MM/yyyy", internalizationContext.findLocaleByLanguageAndCountry("en", "US").getDateFormat().getPattern());
   }

}
