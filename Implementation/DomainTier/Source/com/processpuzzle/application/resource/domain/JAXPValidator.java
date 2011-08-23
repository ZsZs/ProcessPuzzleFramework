package com.processpuzzle.application.resource.domain;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class JAXPValidator extends XmlValidator {

   public JAXPValidator( String xmlDocumentUrl, String schemaUrl ) {
      super( xmlDocumentUrl, schemaUrl );
   }

   public void validate() throws XmlResourceNotFoundException, XmlSchemaNotFoundException, XmlSchemaValidationException {
      super.validate();
 
      try {
         System.setProperty("javax.xml.parsers.DocumentBuilderFactory", "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");

         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         factory.setNamespaceAware(true);
         factory.setSchema(schema);

         DocumentBuilder builder = factory.newDocumentBuilder();
         Validator handler = new Validator();
         builder.setErrorHandler(handler);
         builder.parse( xmlInputStream );
         
         if (handler.validationError == true) { 
            isValid = false;
            throw new XmlSchemaValidationException( xmlDocumentUrl, schemaUrl );
         } 
         else { isValid = true; }
      } catch (java.io.IOException ioe) {
         System.out.println("IOException " + ioe.getMessage());
      } catch (SAXException e) {
         System.out.println("SAXException" + e.getMessage());
      } catch (ParserConfigurationException e) {
         System.out.println("ParserConfigurationException " + e.getMessage());
      }
   }

   private class Validator extends DefaultHandler {
      public boolean validationError = false;
      public SAXParseException saxParseException = null;

      public void error(SAXParseException exception) throws SAXException {
         validationError = true;
         saxParseException = exception;
      }

      public void fatalError(SAXParseException exception) throws SAXException {
         validationError = true;
         saxParseException = exception;
      }

      public void warning(SAXParseException exception) throws SAXException {}
   }
}
