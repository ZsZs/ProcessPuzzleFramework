/*
 * Created on Dec 7, 2005
 */
package com.processpuzzle.application.configuration.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.AccessRight;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleEnumeration;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.persistence.domain.TestEntityRepository;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.user_session.domain.UserSessionManager;

public class ProcessPuzzleContextTest {
   private static final String ANOTHER_CONFIGURATION_DESCRIPTOR = "classpath:com/processpuzzle/application/configuration/domain/another_configuration_descriptor.xml";
   private static ProcessPuzzleContext config;
   private PropertyContextOverrides propertyOverrides = new PropertyContextOverrides();
   private Application application = mock( Application.class );

   @Before
   public void beforeEachTests() throws Exception {
      config = ApplicationContextFactory.create( application, DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      
      definePropertyOverrides();
      config.setPropertyContextOverrides( propertyOverrides );
      
      when( application.getContext() ).thenReturn( config );
      config.setUp( Application.Action.start );
   }

   @Test
   public void setUp_ForInstantiation() {
      assertNotNull( "After setup we can retrieve the configuration object.", UserRequestManager.getInstance().getApplicationContext() );      
   }
   
   @Test
   public void setUp_ForAggregatedHelperObjects() {
      assertTrue( "ProcessPuzzleContext aggregates an ", config.getPropertyContext() instanceof PropertyContext );
      assertTrue( config.getPropertyContext().isConfigured() );
      assertTrue( "PropertyContext aggregates a", config.getPropertyContext().getConfiguration() instanceof org.apache.commons.configuration.Configuration );
      
      assertTrue( "ProcessPuzzleContext aggregates a", config.getInternalizationContext() instanceof InternalizationContext );
      assertTrue( config.getInternalizationContext().isConfigured() );
      
      assertTrue( "ProcessPuzzleContext aggregates a", config.getMeasurementContext() instanceof MeasurementContext );
      assertTrue( config.getMeasurementContext().isConfigured() );
      
      assertTrue( "ProcessPuzzleContext aggregates a", config.getPersistenceContext() instanceof PersistenceContext );
      assertTrue( config.getPersistenceContext().isConfigured() );

      assertTrue( "ProcessPuzzleContext aggregates a", config.getBeanContainer() instanceof BeanContainer );
      assertTrue( config.getBeanContainer().isConfigured() );
      
      assertTrue( "ProcessPuzzleContext aggregates a", config.getUserSessionManager() instanceof UserSessionManager );
      assertTrue( config.getUserSessionManager().isConfigured() );
   }

   @Test
   public void setUp_ForRequiredProperties() {
      // Check required application properties
      assertEquals( "1.0", config.getProperty( PropertyKeys.APPLICATION_VERSION.getDefaultKey() ));
      
      // Check required runtime properties
      assertNotNull( config.getProperty( PropertyKeys.RUNTIME_ENVIRONMENT.getDefaultKey() ));

      // Check required front controller properties
      assertNotNull( config.getProperty( PropertyKeys.COMMAND_MAPPING.getDefaultKey() ));

      // Check required internalization properties
      assertNotNull( config.getProperty( PropertyKeys.INTERNALIZATION_RESOURCE_BUNDLE.getDefaultKey() ));
      assertNotNull( config.getProperty( PropertyKeys.INTERNALIZATION_DEFAULT_LOCALE.getDefaultKey() ));
      assertNotNull( config.getProperty( PropertyKeys.INTERNALIZATION_AVAILABLE_LOCALES.getDefaultKey() ));
      
      // Check required mailing properties
      assertNotNull( config.getProperties( PropertyKeys.EMAIL_PROPERTIES.getDefaultKey() ));
   }

   @Test
   public void setUp_ForEmailProperties() {
      // Check availability of required properties.
      Properties emailProperties = config.getProperties( PropertyKeys.EMAIL_PROPERTIES.getDefaultKey() );
      assertNotNull( emailProperties.getProperty("smtp.host"));
      assertNotNull( emailProperties.getProperty("host.systemUser.name"));
      assertNotNull( emailProperties.getProperty("host.systemUser.password"));
      assertNotNull( emailProperties.getProperty("smtp.auth"));
   }

   @Test
   public void setUp_ForUndefinedPropertyFile() {
      config = ApplicationContextFactory.create( application, "UknownFile.properties" );
      try {
         config.setUp( Application.Action.start );                                    
      } catch( Exception e ) {
         assertTrue( e instanceof ConfigurationSetUpException );
      }
   }

   @Test
   public void setUp_ForDefaultLocale() {
      String defaultLocale = (String) config.getProperty(PropertyKeys.INTERNALIZATION_DEFAULT_LOCALE.getDefaultKey() );
      assertEquals("The current locale, setted in the configuration file is:", "hu, HU", defaultLocale);
   }

   @Test
   public void setUp_ForSupressingReInitialization() {
      PropertyContext propertyContext = config.getPropertyContext();
      
      config = ApplicationContextFactory.create( application, DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      config.setUp( Application.Action.start );
      
      assertEquals("Calling 'setUp()' again with the same configuration descriptor, doesn't creates a new PropertyContext object.", 
            propertyContext, config.getPropertyContext() );

      config = ApplicationContextFactory.create( application, ANOTHER_CONFIGURATION_DESCRIPTOR );
      config.setUp( Application.Action.start );
      assertFalse("Calling 'setUp()' again with a new configuration descriptor, doesn't creates a new PropertyContext object.", 
            propertyContext.equals( config.getPropertyContext() ));
   }

   @Test
   public void setUp_ForReInitialization() {
      PropertyContext propertyContext = config.getPropertyContext();
      
      config = ApplicationContextFactory.create( application, ANOTHER_CONFIGURATION_DESCRIPTOR );
      config.setUp( Application.Action.start );
      assertTrue("Calling 'setUp()' again with a new configuration descriptor, creates a new PropertyContext object.", 
            !propertyContext.equals( config.getPropertyContext() ));
   }

   @Test
   public void setUp_ForPropertyOverride() {
      assertEquals( TestEnums.OVERRIDEN_EMAIL_PROPERTY.textValue, config.getProperty( PropertyKeys.EMAIL_PROPERTIES.getDefaultKey() ));
   }

   @Test
   public void getText_ForTransparency() {
      ProcessPuzzleLocale englishUSLocale = new ProcessPuzzleLocale("en", "US");
      
      //config serves as a facade to the InternalizationContext
      assertEquals( config.getText( "Key_1", englishUSLocale ), config.getInternalizationContext().getText("Key_1", englishUSLocale ));
      assertEquals( config.getText( "Key_33", englishUSLocale ), config.getInternalizationContext().getText("Key_33", englishUSLocale ));
      assertEquals( "If we do not specify locale, the default is used.", config.getText("Kulcs_1"), config.getInternalizationContext().getText("Kulcs_1"));
      assertNull("If a key doesn't exit null is returned.", config.getText("NoneExistingKey"));
   }
   
   @Test
   public void getRepository() {
	  TestEntityRepository repository = config.getRepository( TestEntityRepository.class );
      assertTrue("We can retrieve an instance of 'TestRepository' class.", repository instanceof TestEntityRepository );
   }
   
   @Test
   public void getRepositoryByEntityClass() {
      assertTrue("We can retrieve the the 'TestRepository' also by the enty class.",
            config.getRepositoryByEntityClass(TestEntity.class) instanceof TestEntityRepository);
   }
   
   @Test (expected = UndeclaredAggregateRootRepositoryMapping.class)
   public void getRepositoryByEntityClass_ForException() {
      try {
      } catch (Exception e) {
         assertTrue( e instanceof UndeclaredAggregateRootRepositoryMapping );
      }
      config.getRepositoryByEntityClass( AccessRight.class );
   }
   
   @Test
   public void getProperty() {
      assertEquals("We retrieve any kind of property from the configuration.", "hu, HU", config
            .getProperty(PropertyKeys.INTERNALIZATION_DEFAULT_LOCALE.getDefaultKey() ));
   }

   @Test
   public void addProperty() {
      config.addProperty( PropertyKeys.APPLICATION_NAME.getDefaultKey(), "ac:aNewPropertyKey", "aNewPropertyValue");
      config.getProperty( "ac:application.ac:aNewPropertyKey" );
      assertEquals("ProcessPuzzleContext is a facade to PropertyContext. You can add new properties at runtime.", 
            "aNewPropertyValue", config.getProperty(PropertyKeys.APPLICATION.getDefaultKey() + "." + "aNewPropertyKey"));
   }
   
   @Test
   public final void getBean() {
      assertNotNull("'ProcessPuzzleContext.getBean()' serves as a facade of BeanContainer's corresponding method.", 
            config.getBean( ProcessPuzzleApplicationComponent.USER_REQUEST_FACTORY.getComponentId() ));
   }

   @After
   public void afterEachTest() throws Exception {}
   
   @AfterClass
   public static void afterAllTests() throws Exception {
      if(config != null) config.tearDown( Application.Action.stop );
   }

   private void definePropertyOverrides() {
      propertyOverrides.addProperty( PropertyKeys.EMAIL_PROPERTIES,  TestEnums.OVERRIDEN_EMAIL_PROPERTY );
   }
   
   private enum TestEnums implements ProcessPuzzleEnumeration {
      OVERRIDEN_EMAIL_PROPERTY ("this value has been overriden");
      
      TestEnums( String textValue ) {
         this.textValue = textValue;
      }
      
      private String textValue;

      public String asString() { return textValue; }
   }

// @Test
// public void getPersistenceConfigurationProperties() {
//    String fileName = "classpath:com/itcodex/objectpuzzle/framework/application_configuration/domain/persistence_configuration.properties";
//    PropertyLoader loader = new PropertyLoader( fileName );
//    loader.loadData();
//    
//    assertEquals("Persistence configuration properties comes from a separate property file.", 
//          fileName, ProcessPuzzleContext.getProperty(ProcessPuzzleContext.PERSISTENCE_STRATEGY_CONFIGURATION));
//
//    Properties persistenceProperties = loader.getLoadedProperties();
//    assertEquals("We can retrieve these properties directly from Configuration. They are added to the other properties.", 
//          persistenceProperties.getProperty("hibernate.connection.driver_class"), ProcessPuzzleContext.getProperty("hibernate.connection.driver_class"));
// }

// @Test
// public void getDataLoader_ForError() {
//    Configuration.setProperty( Configuration.SYSTEM_DATA_LOADER, "UnknownClass" );
//    DataLoader loader = null;
//    try {
//       Configuration.getSystemDataLoader();
//       fail("The Configuraiton.getSystemDataLoader() is supposed to throw Error.");
//    } catch (Exception e) {
//       assertTrue("We got an error.", true);
//       assertNull("Returns null", loader);
//    }
// }
   
}
