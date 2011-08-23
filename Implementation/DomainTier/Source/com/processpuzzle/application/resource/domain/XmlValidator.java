package com.processpuzzle.application.resource.domain;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.xml.sax.SAXException;

public abstract class XmlValidator {
   protected String xmlDocumentUrl = null;
   protected Resource xmlResource = null;
   protected InputStream xmlInputStream = null;
   protected String schemaUrl = null;
   protected Schema schema = null;
   protected Resource schemaResource = null;
   protected boolean isValid = false;

//Constructors
   public XmlValidator ( String xmlDocumentUrl, String schemaUrl ) {
      this.xmlDocumentUrl = xmlDocumentUrl;
      this.schemaUrl = schemaUrl;
   }

//Public mutators
   public void validate() throws XmlResourceNotFoundException, XmlSchemaNotFoundException, XmlSchemaValidationException {
      loadResources();
   }

//Properties
   public boolean isValid() { return isValid; }
   
//Private mutators
   private void loadResources() throws XmlResourceNotFoundException, XmlSchemaNotFoundException, XmlSchemaValidationException {
      ResourceLoader loader = new DefaultResourceLoader();
      xmlResource = loader.getResource( xmlDocumentUrl );
      try {
         xmlInputStream = xmlResource.getInputStream();
      } catch (IOException e) {
         throw new XmlResourceNotFoundException( xmlDocumentUrl, e );
      }

      schemaResource = loader.getResource( schemaUrl );
      SchemaFactory schemaFactory = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
      try {
         schema = schemaFactory.newSchema( schemaResource.getFile() );
      } catch (SAXException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) { throw new XmlSchemaNotFoundException( schemaUrl, e ); }
   }
}
