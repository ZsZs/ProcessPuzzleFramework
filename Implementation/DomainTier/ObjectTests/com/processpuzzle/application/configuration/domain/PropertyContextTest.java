package com.processpuzzle.application.configuration.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.stub;

import java.io.IOException;
import java.text.MessageFormat;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.xml.sax.InputSource;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class PropertyContextTest {
   private static String CONFIGURATION_DESCRIPTOR_PATH = "classpath:com/processpuzzle/application/configuration/domain/configuration_descriptor.xml";
   @Mock private static Application application;
   @Mock private static ProcessPuzzleContext applicationContext;
   private String expectedConnectionUrl = "";
   private String expectedWorkingDirectory = "";
   private PropertyContext propertyContext = null;
   private org.apache.commons.configuration.CombinedConfiguration configuration = null;
   private XMLConfiguration customConfig = null;
   private XMLConfiguration defaultConfig = null;

   @BeforeClass
   public static void beforeAllTests() {
      MockitoAnnotations.initMocks( PropertyContextTest.class );
      stub( application.getContext() ).toReturn( applicationContext );
   }
   
   @Before
   public void beforeEachTests() {
      propertyContext = new PropertyContext( application, CONFIGURATION_DESCRIPTOR_PATH );
      propertyContext.setUp( Application.Action.start );
      configuration = (CombinedConfiguration) propertyContext.getConfiguration();
      customConfig = (XMLConfiguration) configuration.getConfiguration("custom_configuration");
      defaultConfig = (XMLConfiguration) configuration.getConfiguration("default_configuration");
      
      determineExpectedValues();
   }
   
   @Ignore
   @Test 
   public void testSeUp_ForCombinedConfiguration() {
      assertTrue( "PropertyContext loads in a CombinedConfiguration.", configuration instanceof CombinedConfiguration );
      assertTrue( "Configuration is combined from: ", customConfig instanceof XMLConfiguration );
      assertTrue( "Configuration is combined from: ", defaultConfig instanceof XMLConfiguration );
      assertTrue( "Expression engine is:", configuration.getExpressionEngine() instanceof XPathExpressionEngine );
   }
   
   @Test ( expected = UndefinedPropertyDescriptorException.class )
   public void testSetUp_ForUndefinedDescriptorPath() {
      PropertyContext anotherContext = new PropertyContext( application, "UndefinedPath.xml" );
      anotherContext.setUp( Application.Action.start );
   }
   
   @Test
   public void testGetProperty_ForXmlConfig() {
      assertEquals( "#808080", customConfig.getProperty("colors.background"));
      assertEquals( "Even an element's attribute is a property!", "#800080", customConfig.getProperty("colors.link[@visited]"));
      assertEquals( "The number of values of 'buttons.name' property is:.", 3, customConfig.getList("buttons.name").size() );
   }
   
   @Test
   public void testGetProperty_ForHieracrhicalAccess() {
      assertTrue( "Keys should be defined with XPath syntax.", configuration.containsKey("colors/background"));
      assertEquals( "#808080", configuration.getString( "colors/background" ));
   }
   
   @Test
   public void testGetProperty_ForXPathNodeSelector() {
      assertEquals( DomainTierTestConfiguration.STRATEGY_NAME, configuration.getString( PropertyKeys.PERSISTENCE_STRATEGY_NAME.getDefaultKey() ));         
   }
   
   @Test
   public void testGetProperty_ForRequiredProperties() {
      assertEquals("ProcessPuzzle", propertyContext.getProperty( PropertyKeys.APPLICATION_NAME.getDefaultKey() ));
      assertEquals("1.0", propertyContext.getProperty( PropertyKeys.APPLICATION_VERSION.getDefaultKey() ));
      assertNotNull( propertyContext.getProperty( PropertyKeys.APPLICATION_SERVER_WORKING_FOLDER.getDefaultKey() ));
      assertNotNull("c:\\processpuzzle", propertyContext.getProperty( PropertyKeys.APPLICATION_CLIENT_WORKING_FOLDER.getDefaultKey() ));
      assertEquals("ProcessPuzzle", propertyContext.getProperty( PropertyKeys.APPLICATION_DEFAULT_USER_NAME.getDefaultKey() ));
      assertEquals("ProcessPuzzle", propertyContext.getProperty( PropertyKeys.APPLICATION_DEFAULT_USER_PASSWORD.getDefaultKey() ));
      assertEquals("dropAndCreate", propertyContext.getProperty( PropertyKeys.APPLICATION_DATABASE_CREATION.getDefaultKey() ));
   }

   @Test
   public void testAddProperty_ForXPathNodeSelector() {
      configuration.addProperty("tables/table[1] type", "system");
      configuration.addProperty("colors foreground", "#000000");
      
      assertEquals( "system", configuration.getString("tables/table[1]/type"));
      assertEquals( "#000000", configuration.getString("colors/foreground"));
   }
   
   @Test
   public void testOverrideProperty() {
      assertEquals("Check existing value", "ProcessPuzzle", propertyContext.getProperty( PropertyKeys.APPLICATION_NAME.getDefaultKey() ));
      propertyContext.overrideProperty( PropertyKeys.APPLICATION_NAME, "bakfity" );
      assertEquals("bakfity", propertyContext.getProperty( PropertyKeys.APPLICATION_NAME.getDefaultKey() ));
      //Restore original value
      propertyContext.overrideProperty( PropertyKeys.APPLICATION_NAME, "ProcessPuzzle" );
      
      assertEquals("Check existing value", PersistentDataInitializationStrategies.dropAndCreate.toString(), propertyContext.getProperty( PropertyKeys.APPLICATION_DATABASE_CREATION.getDefaultKey() ));
      propertyContext.overrideProperty( PropertyKeys.APPLICATION_NAME, PersistentDataInitializationStrategies.dropAndCreate );
      assertEquals( PersistentDataInitializationStrategies.dropAndCreate.toString(), propertyContext.getProperty( PropertyKeys.APPLICATION_NAME.getDefaultKey() ));
      //Restore original value
      propertyContext.overrideProperty( PropertyKeys.APPLICATION_NAME, PersistentDataInitializationStrategies.update );      
   }
   
   @Test
   public void testJustAConfiguration() {
      try {
         XMLConfiguration config = new XMLConfiguration("com/processpuzzle/application/configuration/domain/custom_configuration.xml");
         assertTrue( config.containsKey("colors.background"));
      } catch (ConfigurationException e) {
         // TODO Auto-generated catch block
         fail("ConfigurationException was not expected :-)");
      }
   }
   
   @Test
   public void testSetUp_ForOverride() {
      assertEquals("Properties in 'custom_configuration.xml' should override properties defined in 'default_configuration.xml'.",
            expectedWorkingDirectory, configuration.getString("application/serverWorkingFolder"));
      
      String key = MessageFormat.format( PropertyKeys.PERSISTENCE_STRATEGY_EVENT_HANDLER_PROPERTIES.getDefaultKey(), 
                   new Object[] { DomainTierTestConfiguration.STRATEGY_NAME, DomainTierTestConfiguration.PERSISTENCE_PROVIDER_NAME });
      key += "/hibernate/connection/url";
      assertEquals( expectedConnectionUrl, configuration.getString( key ));
   }
   
   @Test
   public void testShortcutGetters() {
      assertEquals("ProcessPuzzle", propertyContext.getApplicationName() );
      assertEquals("1.0", propertyContext.getApplicationVersion() );
      assertNotNull( propertyContext.getServerWorkingFolder() );
      assertNotNull("c:\\processpuzzle", propertyContext.getClientWorkingFolder() );
      assertEquals("ProcessPuzzle", propertyContext.getDefaultUserName() );
      assertEquals("ProcessPuzzle", propertyContext.getDefaultUserPassword() );      
   }

   @After
   public void afterEachTests() {
      propertyContext.tearDown( Application.Action.stop );
      propertyContext = null;
   }
   
   private void determineExpectedValues() {
      String customConfigurationPath = CONFIGURATION_DESCRIPTOR_PATH.substring( 0, CONFIGURATION_DESCRIPTOR_PATH.indexOf( "configuration_descriptor.xml" ) ) + "custom_configuration.xml";
      XPathFactory  factory=XPathFactory.newInstance();
      XPath xPath=factory.newXPath();
      ResourceLoader loader = null; 
      Resource resource = null;

      loader = (ResourceLoader) new DefaultResourceLoader();
      resource = loader.getResource( customConfigurationPath );
      
      determineConnectionUrl( xPath, resource );
      determineExpectedWorkingDirectory( xPath, resource );
   }
   
   private void determineConnectionUrl( XPath xPath, Resource resource ) {
      XPathExpression  xPathExpression = null;
      InputSource inputSource = null;

      String key = MessageFormat.format( PropertyKeys.PERSISTENCE_STRATEGY_EVENT_HANDLER_PROPERTIES.getDefaultKey(), 
            new Object[] { DomainTierTestConfiguration.STRATEGY_NAME, DomainTierTestConfiguration.PERSISTENCE_PROVIDER_NAME });
      key += "/hibernate/connection/url";
      
      try {
         inputSource = new InputSource( resource.getInputStream() );
         xPathExpression = xPath.compile( "/processPuzzleConfiguration/" + key );
         expectedConnectionUrl = xPathExpression.evaluate( inputSource );
      } catch( XPathExpressionException e ) {
         fail( "Coudn't set up expected value: 'expectedDatabaseName'" );
      } catch( IOException e ) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private void determineExpectedWorkingDirectory( XPath xPath, Resource resource ) {
      XPathExpression  xPathExpression = null;
      InputSource inputSource = null;
      
      try {
         inputSource = new InputSource( resource.getInputStream() );
         xPathExpression = xPath.compile( "/processPuzzleConfiguration/" + PropertyKeys.APPLICATION_SERVER_WORKING_FOLDER.getDefaultKey() );
         expectedWorkingDirectory = xPathExpression.evaluate( inputSource );
      } catch( XPathExpressionException e ) {
         fail( "Coudn't set up expected values: 'expectedWorkingDirectory'");
      } catch( IOException e ) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
