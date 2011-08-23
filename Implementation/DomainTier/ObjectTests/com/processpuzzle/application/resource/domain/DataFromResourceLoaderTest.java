package com.processpuzzle.application.resource.domain;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.Resource;

import com.processpuzzle.application.resource.domain.DataFromResourceLoader;
import com.processpuzzle.application.resource.domain.DataLoader;
import com.processpuzzle.application.resource.domain.ResourceNotFoundException;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class DataFromResourceLoaderTest {
   private DataFromResourceLoader dataLoader = null;
   private String multipleSource = DomainTierTestConfiguration.TEST_XML_PATH + "," + DomainTierTestConfiguration.ANOTHER_TEST_XML_PATH;

   @Before
   public void setUp() {
      dataLoader = new TestDataFromResourceLoader( multipleSource ); // Note that TestDataFromResourceLoader doesn't add any functionality
      dataLoader.loadData();
   }

   @Ignore
   @Test
   public void testLoadData_ForResource() {
      assertTrue( "DataFromResourceLoader handles resource (file) as (Spring) Resource.", dataLoader.getResource() instanceof Resource );
   }

   @Ignore
   @Test
   public void testInstantiation_ForMultipleSource() {
      DataLoader nextLoader = dataLoader.getNext();
      assertNotNull( "'dataLoader' has a subsequent data loader.", nextLoader );
      assertTrue( "When multiple source are specified the chained loader's class are the same.", nextLoader instanceof TestDataFromResourceLoader );
   }

   @Ignore
   @Test
   public void testLoadData_ForMultipleSource() {
      assertNotNull( "The whole chain is invoked.", ((DataFromResourceLoader) dataLoader.getNext()).getResource() );
   }

   @Ignore
   @Test
   public void setResourcePath_shouldHandleMultiperResources() {
      // SETUP:
      dataLoader = new TestDataFromResourceLoader();

      // EXCERCISE:
      dataLoader.setResourcePath( multipleSource );

      // VERIFY:
      assertThat( dataLoader.hasNext(), is( true ) );
      assertThat( dataLoader.getResourcePath(), equalTo( DomainTierTestConfiguration.TEST_XML_PATH ) );
      assertThat( ((DataFromResourceLoader) dataLoader.getNext()).getResourcePath(), equalTo( DomainTierTestConfiguration.ANOTHER_TEST_XML_PATH ) );
   }

   @Ignore
   @Test( expected = ResourceNotFoundException.class )
   public void testLoadData_WithNoneExistingFile() {
      dataLoader = new TestDataFromResourceLoader( "Nonexisting file" );
      dataLoader.loadData();
   }

   @After
   public void tearDown() {
      dataLoader = null;
   }

}
