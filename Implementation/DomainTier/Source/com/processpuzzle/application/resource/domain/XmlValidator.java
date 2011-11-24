/*
Name: 
    - XmlValidator

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
