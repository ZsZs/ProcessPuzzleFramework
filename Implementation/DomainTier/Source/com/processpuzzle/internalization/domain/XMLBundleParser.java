/*
 * =====================================================================
 * 
 * XMLBundleParser.java
 * 
 * Created by Claude Duguay Copyright (c) 2002
 * 
 * =====================================================================
 */
package com.processpuzzle.internalization.domain;

import java.io.IOException;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.xml.sax.*;
import org.xml.sax.helpers.*;


import javax.xml.parsers.*;

public class XMLBundleParser extends DefaultHandler {
   protected ResourceCache cache = new ResourceCache();
   protected String parserLanguage, parserCountry, parserVariant;
   protected String targetLanguage, targetCountry, targetVariant;
   protected StringBuffer buffer = new StringBuffer();
   protected ResourceKey key;
   protected ResourceLoader loader = null;
   protected String resourceBundlePath = null;

   public XMLBundleParser() {
      this( null );
   }
   
   public XMLBundleParser( ResourceLoader resourceLoader ) {
      this.loader = resourceLoader;
      if( loader == null ) loader = (ResourceLoader) new DefaultResourceLoader();
   }
   
   public void parse(ResourceCache cache, String fileName, ProcessPuzzleLocale targetLocale) throws InternalizationException {
      this.resourceBundlePath = fileName;
      this.cache = cache;
      targetLanguage = targetLocale.getLanguage();
      targetCountry = targetLocale.getCountry();
      targetVariant = targetLocale.getVariant();
      InputSource xmlSource;
      
      try { 
         xmlSource = new InputSource( loader.getResource( fileName ).getInputStream() ); 
         SAXParserFactory factory = SAXParserFactory.newInstance();
         SAXParser parser = factory.newSAXParser();      
         XMLReader reader = parser.getXMLReader();
         reader.setContentHandler( this );
         reader.setErrorHandler( this );
         reader.parse( xmlSource );
      } catch( IOException e ) { throw new ResourceBundleIOException( fileName, e );}      
      catch( ParserConfigurationException e ) { throw new InternalizationException( "ParserConfigurationException", e ); }
      catch( SAXException e ) { throw new InvalidResourceBundleException( fileName, e ); }
   }

   protected boolean inContext() {
      if (parserLanguage == null || parserLanguage.equals(targetLanguage)) {
         if (parserCountry == null || parserCountry.equals(targetCountry)) {
            if (parserVariant == null || parserVariant.equals(targetVariant)) {
               return true;
            }
         }
      }
      return false;
   }

   public void characters(char[] chars, int offset, int length) {
      buffer.append(chars, offset, length);
   }

   public void startElement(String uri, String lName, String qName, Attributes attrs) {
      if (qName.equals("Language")) {
         parserLanguage = attrs.getValue("name");
      }
      if (qName.equals("Country")) {
         parserCountry = attrs.getValue("name");
      }
      if (qName.equals("Variant")) {
         parserVariant = attrs.getValue("name");
      }
      if (qName.equals("Resource")) {
         key = new ResourceKey(attrs.getValue("key"), attrs.getValue("type"));
      }
   }

   public void endElement(String uri, String lName, String qName) {
      String content = buffer.toString().trim();
      buffer.setLength(0);
      if (qName.equals("Language")) {
         parserLanguage = null;
      }
      if (qName.equals("Country")) {
         parserCountry = null;
      }
      if (qName.equals("Variant")) {
         parserVariant = null;
      }
      if (qName.equals("Resource") && inContext()) {
         cache.put(key, content);
      }
   }
   
   public void error( SAXParseException exception ) {
//      throw new InvalidResourceBundleException( resourceBundlePath, exception );
      exception.printStackTrace();
   }
   
   public void fatalError( SAXParseException exception ) {
      exception.printStackTrace();      
   }
   
   public void warning( SAXParseException exception ) {
      exception.printStackTrace();      
   }
}
