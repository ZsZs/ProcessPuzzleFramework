package com.processpuzzle.application.resource.domain;

import static org.junit.Assert.*;

import org.dom4j.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.resource.domain.XmlDataLoader;
import com.processpuzzle.application.resource.domain.XmlValidationException;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class XmlDataLoaderTest {
   private XmlDataLoader dataLoader = null;
   private XmlDataLoader anotherDataLoader = null;
   
   @Before
   public void setUp() {
      dataLoader = new TestXmlDataLoader( DomainTierTestConfiguration.TEST_XML_PATH );
      dataLoader.loadData();
      
      anotherDataLoader = new TestXmlDataLoader( DomainTierTestConfiguration.TEST_XML_PATH, DomainTierTestConfiguration.TEST_SCHEMA_PATH );
      anotherDataLoader.loadData();
   }
   
   @After
   public void tearDown() {
      dataLoader = null;
      anotherDataLoader = null;
   }

   @Ignore
   @Test
   public void testConstructor_ForResourcePath() {
      assertEquals("XmlDataLoader stores the given xml resource path.", DomainTierTestConfiguration.TEST_XML_PATH, dataLoader.getResourcePath());
   }
   
   @Ignore
   @Test
   public void testConstructor_ForSchemaPath() {
      assertEquals( "XmlDataLoader stores the given shema path.", DomainTierTestConfiguration.TEST_SCHEMA_PATH, anotherDataLoader.getSchemaPath() );
   }

   @Ignore
   @Test
   public void testLoadData_ForDocumentLoad() {
      Document xmlDocument = dataLoader.getDocument();
      assertTrue( "XmlDataLoader reads xml in and instantiates a DOM object", xmlDocument instanceof Document );
      assertTrue("The readed document has content.", xmlDocument.hasContent() );
   }
   
   @Ignore
   @Test
   public void testLoadData_ForSchemaValidation() {
      assertTrue( "If also a schema is given, then XmlDataLoader validates the xml to the schema.", anotherDataLoader.isValid() );
   }
   
   @Ignore
   @Test (expected = XmlValidationException.class)
   public void testLoadData_WithErroneousFile() {
      dataLoader = new TestXmlDataLoader( DomainTierTestConfiguration.ERRONEOUS_TEST_XML_PATH );
      dataLoader.loadData();
   }   
}
