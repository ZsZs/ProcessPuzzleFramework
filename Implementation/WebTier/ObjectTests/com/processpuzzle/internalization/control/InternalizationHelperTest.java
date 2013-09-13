package com.processpuzzle.internalization.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;

import com.processpuzzle.application.configuration.domain.ApplicationContextFactory;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContextTest;
import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.internalization.domain.NoneExistingResourceKeyException;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.internalization.domain.XMLResourceBundle;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class InternalizationHelperTest {
   private ProcessPuzzleContext config = null;
   private final String WEBTIER_CONFIGURATION = "configuration.webtier_configuration.properties";
   @Mock
   private Application application;

   @Before
   protected void setUp() throws Exception {
      MockitoAnnotations.initMocks( ProcessPuzzleContextTest.class );
      config = ApplicationContextFactory.create( application, WEBTIER_CONFIGURATION );
      config.setUp( Application.Action.start );

   }

   @Test
   public final void testI18Config() {
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      assertNotNull( applicationContext.getProperty( PropertyKeys.INTERNALIZATION_AVAILABLE_LOCALES.getXPathKey() ) );
      assertNotNull( applicationContext.getProperty( PropertyKeys.INTERNALIZATION_DEFAULT_LOCALE.getXPathKey() ) );
      assertEquals( "hu, HU", applicationContext.getProperty( PropertyKeys.INTERNALIZATION_DEFAULT_LOCALE.getXPathKey() ) );
      assertEquals( "hu|en", applicationContext.getProperty( PropertyKeys.INTERNALIZATION_AVAILABLE_LOCALES.getXPathKey() ) );
   }

   @Test
   public final void testAvailableResourceBundles() throws NoneExistingResourceKeyException {
      Map<ProcessPuzzleLocale, XMLResourceBundle> resourceBundles = config.getInternalizationContext().getResourceBundles();
      for( Iterator<ProcessPuzzleLocale> iter = resourceBundles.keySet().iterator(); iter.hasNext(); ){
         String bundleKey = (String) iter.next().toString();
         assertTrue( bundleKey.matches( "en|hu" ) );
         if( bundleKey.equals( "hu" ) ){
            XMLResourceBundle bundle = resourceBundles.get( new ProcessPuzzleLocale( "hu" ) );
            String text = bundle.getText( "testKey" );
            assertEquals( "Teszt érték", text );
         }else if( bundleKey.equals( "en" ) ){
            XMLResourceBundle bundle = resourceBundles.get( new ProcessPuzzleLocale( "en" ) );
            String text = bundle.getText( "testKey" );
            assertEquals( "Test value", text );
         }
      }
   }

   @After
   protected void tearDown() throws Exception {
   // TODO Auto-generated method stub
   }
}
