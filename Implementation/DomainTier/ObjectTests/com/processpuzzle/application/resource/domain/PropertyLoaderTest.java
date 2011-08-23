/*
 * Created on Jan 21, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.resource.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.resource.domain.PropertyLoadException;
import com.processpuzzle.application.resource.domain.PropertyLoader;

/**
 * @author zsolt.zsuffa
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class PropertyLoaderTest {
   private String propertyFileName = "classpath:com/itcodex/objectpuzzle/framework/resource_management/domain/test.properties";
   private PropertyLoader loader = null;

   @Before
   public void setUp() {
      loader = new PropertyLoader( propertyFileName );
      loader.loadData();
   }

   @After
   public void tearDown() {
      loader = null;
   }

   @Ignore
   @Test
   public final void loadProperty_FromAnotherPackage() {
      Properties properties = loader.getLoadedProperties();
      assertNotNull("Properties are loaded.", properties );
      assertEquals("The value of 'persistence.strategy.name' is:", "aPropertyValue", properties.get( "aProperty"));
   }

   @Ignore
   @Test
   public final void loadData_ForSimpleTextFile() {
      PropertyLoader anotherLoader = new PropertyLoader( "classpath:com/itcodex/objectpuzzle/framework/resource_management/domain/simpleTextFile.txt");
      anotherLoader.loadData();
      assertNotNull("Note that if a text file doesn't contains malformed Unicode escape sequence, it regarded as a valid property file", anotherLoader.getLoadedProperties() );
   }
   
   @Ignore
   @Test (expected = PropertyLoadException.class)
   public final void loadData_ForUnknownFile() {
      PropertyLoader anotherLoader = new PropertyLoader( "classpath:UnknownFile.xxx");
      anotherLoader.loadData();
   }
}
