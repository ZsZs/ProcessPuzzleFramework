/*
Name: 
    - JAXPValidator

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
