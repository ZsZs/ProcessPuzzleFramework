package com.processpuzzle.application.resource.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.resource.domain.JAXPValidator;
import com.processpuzzle.application.resource.domain.XmlDataLoaderException;
import com.processpuzzle.application.resource.domain.XmlResourceNotFoundException;
import com.processpuzzle.application.resource.domain.XmlSchemaNotFoundException;
import com.processpuzzle.application.resource.domain.XmlSchemaValidationException;
import com.processpuzzle.application.resource.domain.XmlValidator;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class XmlValidatorTest {
   private XmlValidator validator = null;
   
   @Before
   public void setUp() throws XmlResourceNotFoundException, XmlSchemaNotFoundException, XmlSchemaValidationException {
      validator = new JAXPValidator( DomainTierTestConfiguration.TEST_XML_PATH, DomainTierTestConfiguration.TEST_SCHEMA_PATH );
      validator.validate();
   }

   @After
   public void tearDown() {
      validator = null;
   }
   
   @Ignore
   @Test
   public void testValidate_ForSuccess() {
      assertTrue("The given xml should be valid.", validator.isValid() );
   }
   
   @Ignore
   @Test (expected = XmlResourceNotFoundException.class )
   public void testValidate_ForUndedefineXmlResource() {
      XmlValidator anotherValidator = new JAXPValidator( DomainTierTestConfiguration.NONEXISTING_XML_FILE, DomainTierTestConfiguration.TEST_SCHEMA_PATH );
      try { anotherValidator.validate(); }
      catch( XmlResourceNotFoundException e ) {
         assertEquals("Problematic resource name is:", DomainTierTestConfiguration.NONEXISTING_XML_FILE, e.getReourceUrl() );
         throw new XmlResourceNotFoundException( e.getReourceUrl(), e );
      } catch (XmlDataLoaderException e) { fail("This exception is unexpected :-)"); }
   }

   @Ignore
   @Test (expected = XmlSchemaNotFoundException.class )
   public void testValidate_ForUndedefineXmlSchema() {
      XmlValidator anotherValidator = new JAXPValidator( DomainTierTestConfiguration.TEST_XML_PATH, DomainTierTestConfiguration.NONEXISTING_XML_FILE);
      try { anotherValidator.validate(); }
      catch( XmlSchemaNotFoundException e ) {
         assertEquals("Problematic resource name is:", DomainTierTestConfiguration.NONEXISTING_XML_FILE, e.getSchemaUrl() );
         throw new XmlSchemaNotFoundException( e.getSchemaUrl(), e );
      } catch (XmlDataLoaderException e) { fail("This exception is unexpected :-)"); }
   }

   @Ignore
   @Test (expected = XmlSchemaValidationException.class )
   public void testValidate_ForInvalidXml() {
      XmlValidator anotherValidator = new JAXPValidator( DomainTierTestConfiguration.INVALID_TEST_XML_PATH, DomainTierTestConfiguration.TEST_SCHEMA_PATH);
      try { anotherValidator.validate(); }
      catch( XmlSchemaValidationException e ) {
         assertEquals("Problematic resource name is:", DomainTierTestConfiguration.INVALID_TEST_XML_PATH, e.getResourceUrl() );
         assertEquals("Problematic resource name is:", DomainTierTestConfiguration.TEST_SCHEMA_PATH, e.getSchemaUrl() );
         throw new XmlSchemaValidationException( e.getResourceUrl(), e.getSchemaUrl() );
      } catch (XmlDataLoaderException e) { fail("This exception is unexpected :-)"); }
   }
}
