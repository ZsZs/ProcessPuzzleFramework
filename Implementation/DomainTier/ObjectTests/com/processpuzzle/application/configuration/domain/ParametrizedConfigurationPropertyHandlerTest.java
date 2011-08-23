package com.processpuzzle.application.configuration.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.DefaultExpressionEngine;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ParametrizedConfigurationPropertyHandlerTest {
   private static final String SELECTOR_PARAMETER = "itemTwo";
   private static final String ITEM_ELEMENT_NAME = "item";
   private static final String LIST_SELECTOR = "items.item[@name=''{0}''].subItem";
   private static final String SINGLE_ATTRIBUTE_SELECTOR = "items.item[@name=''{0}''].[@description]";
   private static final String SINGLE_ELEMENT_SELECTOR = "items.item[@name=''{0}''].singleProperty";
   private static final String SUB_CONFIGURATION_SELECTOR = "items.item[@name=''{0}'']";
   private static final String EXPECTED_LIST_ITEM_ONE = "Sub Item Three";
   //private static final String EXPECTED_ITEM_TWO = "Sub Item Four";
   private static final String EXPECTED_ATTRIBUTE_PROPERTY = "description two";
   private static final String EXPECTED_ELEMENT_PROPERTY = "Single property value two";
   private static final int NUMBER_OF_SUBELEMENTS = 2;
   private static final String CONFIGURATION_FILE = "classpath:com/processpuzzle/application/configuration/domain/HierarchicalConfiguration.xml";
   private static ResourceLoader resourceLoader;
   private static XMLConfiguration configuration;
   private ParametrizedConfigurationPropertyHandler parametrizedPropertyHandler;
   
   @BeforeClass public static void beforeAllTest() throws ConfigurationException, IOException{
      resourceLoader = new DefaultResourceLoader();
      Resource xmlResource = resourceLoader.getResource( CONFIGURATION_FILE );
      configuration = new XMLConfiguration( xmlResource.getFile() );
      configuration.setExpressionEngine( new DefaultExpressionEngine() );
   }
   
   @Before public void beforeEachTests(){
      parametrizedPropertyHandler = new ParametrizedConfigurationPropertyHandler( configuration );
   }
   
   @Test public void configurationAt_LooksForConfigurationSubNode(){
      HierarchicalConfiguration subNode = parametrizedPropertyHandler.configurationAt( SUB_CONFIGURATION_SELECTOR, new Object[] { SELECTOR_PARAMETER } );
      assertThat( subNode.getRoot().getName(), equalTo( ITEM_ELEMENT_NAME ));
   }

   @Test public void getProperty_WhenAttributeIsLookedFor_SearchesForFilteredProperty() {
      String property = parametrizedPropertyHandler.getProperty( SINGLE_ATTRIBUTE_SELECTOR, new Object[] { SELECTOR_PARAMETER });
      assertThat( property, equalTo( EXPECTED_ATTRIBUTE_PROPERTY ));
   }

   @Test public void getProperty_WhenElementIsLookedFor_SearchesForFilteredProperty() {
      String property = parametrizedPropertyHandler.getProperty( SINGLE_ELEMENT_SELECTOR, new Object[] { SELECTOR_PARAMETER });
      assertThat( property, equalTo( EXPECTED_ELEMENT_PROPERTY ));
   }

   @Test public void getPropertyList_SeachesForFilteredListOfProperties() throws ConfigurationException, IOException{
      List<String> propertyList = parametrizedPropertyHandler.getPropertyList( LIST_SELECTOR, new Object[] { SELECTOR_PARAMETER } );
      
      assertThat( propertyList.size(), equalTo( NUMBER_OF_SUBELEMENTS ) );
      assertThat( propertyList, hasItem( EXPECTED_LIST_ITEM_ONE ));
      //assertThat( propertyList, hasItem( EXPECTED_ITEM_TWO ));
   }

   @Test (expected = InvalidPropertySelectorException.class )
   public void getPropertyList_WhenSelectorDoesNotReturnsProperty_ThrowsException(){
      parametrizedPropertyHandler.getPropertyList( LIST_SELECTOR, new Object[] { "none existing element" } );
   }
}
