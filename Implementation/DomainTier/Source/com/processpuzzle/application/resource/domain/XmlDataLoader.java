/*
 * Created on Feb 3, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.resource.domain;

import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.util.XMLErrorHandler;
import org.springframework.core.io.ResourceLoader;

/**
 * @author zsolt.zsuffa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class XmlDataLoader extends DataFromResourceLoader {
   protected Document document = null;
   protected String schemaPath = null;
   protected XMLErrorHandler errorHandler = null;
   protected XmlValidator validator = null;

   public XmlDataLoader( String path ) {
      this( path, null );
   }

   public XmlDataLoader( ResourceLoader resourceLoader, String path ) {
      this( resourceLoader, path, null );
   }

   public XmlDataLoader(  String resourcePath, String schemaPath ) {
      this( null, resourcePath, schemaPath );
   }

   public XmlDataLoader(  ResourceLoader resourceLoader, String resourcePath, String schemaPath) {
      super( resourceLoader, resourcePath );
      this.schemaPath = schemaPath;
   }

   public void loadData() throws XmlDataLoaderException {
      super.loadData();
      readXml();
   }

//Properties
   public Document getDocument() { return document; }
   public String getSchemaPath() { return schemaPath; }
   public boolean isValid() {
      if( schemaPath != null && validator != null ) {
         return validator.isValid();
      }
      else return true;
   }
   
//Private methods
   private void readXml() throws XmlDataLoaderException {
      SAXReader reader = new SAXReader();
      if( schemaPath != null ) {
         validator = new JAXPValidator( resourcePath, schemaPath );
         try {
            validator.validate();
         } catch (XmlSchemaValidationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      
      try { document = reader.read( resource.getInputStream() ); } 
      catch (DocumentException e) { throw new XmlValidationException( resourcePath, e ); }
      catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }   
}
