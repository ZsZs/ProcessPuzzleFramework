package com.processpuzzle.application.configuration.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.internalization.domain.UnsupportedLocaleException;

public class InternalizationContextTest extends ContextTest {
   private static String ERRONEOUS_CONFIGURATION_DESCRIPTOR = "classpath:com/processpuzzle/application/configuration/domain/erroneous_configuration_descriptor.xml";
   private static String CONFIGURATION_DESCRIPTOR_WITH_NO_LOCALE_DEFINITION = "classpath:com/processpuzzle/application/configuration/domain/no_locale_definition_configuration_descriptor.xml";
   private static ProcessPuzzleLocale englishUSLocale = new ProcessPuzzleLocale( "en", "US" );
   private static ProcessPuzzleLocale hungarianLocale = new ProcessPuzzleLocale( "hu", "HU" );
   @Mock private Application application;
   @Mock private ProcessPuzzleContext applicationContext;
   private InternalizationContext internalizationContext = null;
   private MeasurementContext measurementContext = null;
   
   @Before
   public void beforeEachTest() {
      MockitoAnnotations.initMocks( this );
      
      when( application.getContext() ).thenReturn( applicationContext );
      
      measurementContext = new MeasurementContext( application );
      measurementContext.setUp( Application.Action.start );
      
      when( applicationContext.getPropertyContext() ).thenReturn( propertyContext );
      when( applicationContext.getMeasurementContext() ).thenReturn( measurementContext );
      
      internalizationContext = new InternalizationContext( application );
      internalizationContext.setUp(  Application.Action.start );
   }

   @After
   public void afterEachTest() {
      internalizationContext.tearDown( Application.Action.stop );
      internalizationContext = null;
   }

   @Test
   public void testInstantiation() {
      assertFalse( "After instantiation we know the urls of resource files.", internalizationContext.getSourceUrls().isEmpty() );
      assertThat( internalizationContext.getSourceUrls().size(), equalTo( propertyContext.getPropertyList( PropertyKeys.INTERNALIZATION_RESOURCE_BUNDLE.getDefaultKey() ).size() ));
   }

   @Test
   public void setUp_ForResourceBundles() {
      Map resourceBundles = internalizationContext.getResourceBundles();
      assertFalse( "'setUp()' instantiates XMLResourceBundles from the source files.", resourceBundles.isEmpty() );
      assertEquals( "'setUp()' loads 2 resource bundles.", 3, resourceBundles.size() );

      // InternalizationContext contains a XMLResourceBundle for each supported locale
      for( Iterator iter = internalizationContext.getSupportedLocales().iterator(); iter.hasNext(); ) {
         ProcessPuzzleLocale supportedLocale = (ProcessPuzzleLocale) iter.next();
         assertTrue( resourceBundles.containsKey( supportedLocale ) );
      }
   }

   @Test(expected = InternalizationContextSetUpException.class)
   public void setUp_ForInvalidSourcePath() {
      PropertyContext erroneousPropertyContext = new PropertyContext( application, ERRONEOUS_CONFIGURATION_DESCRIPTOR );
      erroneousPropertyContext.setUp( Application.Action.start );
      when( applicationContext.getPropertyContext() ).thenReturn( erroneousPropertyContext );

      MeasurementContext measurementContext = new MeasurementContext( application );
      measurementContext.setUp( Application.Action.start );
      when( applicationContext.getMeasurementContext() ).thenReturn( measurementContext );
      
      InternalizationContext anotherContext = new InternalizationContext( application );
      anotherContext.setUp(  Application.Action.start );
   }

   @Test(expected = InternalizationContextSetUpException.class)
   public void setUp_ForNoLocaleDefinition() {
      PropertyContext erroneousPropertyContext = new PropertyContext( application, CONFIGURATION_DESCRIPTOR_WITH_NO_LOCALE_DEFINITION );
      erroneousPropertyContext.setUp( Application.Action.start );
      stub( applicationContext.getPropertyContext() ).toReturn( erroneousPropertyContext );
      
      MeasurementContext measurementContext = new MeasurementContext( application );
      measurementContext.setUp(  Application.Action.start );
      stub( applicationContext.getMeasurementContext() ).toReturn( measurementContext );
      
      InternalizationContext anotherContext = new InternalizationContext( application );
      anotherContext.setUp(  Application.Action.stop );
   }

   @Test
   public void getSupportedLocales() {
      Set<ProcessPuzzleLocale> locales = internalizationContext.getSupportedLocales();
      for( Iterator i = locales.iterator(); i.hasNext(); ) {
         System.out.println( ( (ProcessPuzzleLocale) i.next() ).getCountry() );
      }
      assertEquals( "'default_configuration.xml' defines 2 supported locale.", 3, internalizationContext.getSupportedLocales().size() );
   }

   @Test
   public void getDefaultLocale() {
      assertTrue( "The default locale is:", internalizationContext.getDefaultLocale().equals( new ProcessPuzzleLocale( "hu", "HU" ) ) );
   }

   @Test
   public void getText() {
      /*
       * Please note! Internalization resources comes from two set of files: TestResources*.xml and AnotherTestResources*.xml. InternalizationContext looks for
       * text in each resources.
       */
      assertEquals( "'TestResources_en.xml' contains text:", "Text_1", internalizationContext.getText( "Key_1", englishUSLocale ) );
      assertEquals( "'AnotherTestResources.xml' containss text:", "Text_3", internalizationContext.getText( "Key_33", englishUSLocale ) );
      assertEquals( "'TestResources_hu.xml contains text:", "Szöveg_1", internalizationContext.getText( "Kulcs_1", hungarianLocale ) );
   }

   @Test
   public void getText_ForDefaultLocale() {
      assertEquals( "If we do not specify locale the default locale is considered. Please compare the result with the previous test.", "Szöveg_1",
            internalizationContext.getText( "Kulcs_1" ) );
   }

   @Test
   public void loadSupportedLocales_ForCurrency() {
      assertEquals( "Hungarian Currency is HUF", "HUF", internalizationContext.findLocaleByLanguageAndCountry( "hu", "HU" ).getLegalTender().getSymbol() );
   }

   @Test
   public void find_ForReturningNull() {
      assertNull( "If a locale can not be found NULL is returned", internalizationContext.findLocaleByLanguage( "xy" ) );
      assertNull( "If a locale can not be found NULL is returned", internalizationContext.findLocaleByLanguageAndCountry( "en", "XY" ) );
   }

   @Test(expected = UnsupportedLocaleException.class)
   public void getText_ForUnsupportedLocale() {
      // German locale is not supported, getText() throws UnsupportedLocaleEception()
      internalizationContext.getText( "Text_1", new ProcessPuzzleLocale( "de" ) );
   }

   @Test
   public void getText_ForNoneExistingKey() {
      assertNull( "If a key doesn't exist null is returned.", internalizationContext.getText( "NoneExistingKey" ) );
   }
}
