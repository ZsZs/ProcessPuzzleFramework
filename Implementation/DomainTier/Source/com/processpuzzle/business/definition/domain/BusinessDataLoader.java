/*
Name: 
    - BusinessDataLoader

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

package com.processpuzzle.business.definition.domain;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.oxm.XmlMappingException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;

import com.processpuzzle.application.resource.domain.DataLoaderException;
import com.processpuzzle.application.resource.domain.XmlDataLoader;
import com.processpuzzle.application.resource.domain.XmlDataLoaderException;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public abstract class BusinessDataLoader<D> extends XmlDataLoader {
   protected String mappingKey;
   protected String mappingPath;
   protected String schemaKey;
   protected Schema schema;
   protected Class<D> unmarshalledDataClass;
   protected D unmarshalledData;
   private InputSource inputSource;

   @SuppressWarnings( "unchecked" )
   public BusinessDataLoader( ResourceLoader resourceLoader, String resourcePath ) {
      super( resourceLoader, resourcePath );
      this.resultInPersistentObjects = true;
      unmarshalledDataClass = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
   }

   @Override
   public void loadData() throws XmlDataLoaderException {
      super.loadData();

      mappingPath = (String) applicationContext.getProperty( mappingKey );

      try{
         schema = loadSchema();
         unmarshallXmlWithSpring();

         DefaultUnitOfWork work = new DefaultUnitOfWork( true );
         saveObjects( work );
         work.finish();
      }catch( BusinessDataSchemaException e ){
         throw new XmlDataLoaderException( this, "Found problems with the associated schema.", e );
      }catch( BusinessDataLoaderException e ){
         throw new XmlDataLoaderException( this, "Found problems with the source data.", e );
      }
   }

   protected abstract void saveObjects( DefaultUnitOfWork work );

   private Schema loadSchema() throws BusinessDataSchemaException {
      schemaPath = applicationContext.getProperty( schemaKey );
      InputStream resourceStream = null;
      Schema businessDataSchema = null;
      try{
         resourceStream = resource.getInputStream();
      }catch( IOException e ){
         throw new BusinessDataSchemaException( schemaPath, e );
      }

      if( resourceStream == null )
         throw new DataLoaderException( this, "Can't load resource: " + resourcePath );

      String language = javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
      SchemaFactory factory = SchemaFactory.newInstance( language );
      factory.setErrorHandler( new BusinessDataLoaderErrorHandler( schemaPath ) );
      factory.setResourceResolver( new SchemaResourceResolver( schemaPath, resourceLoader ) );

      try{
         Resource schemaResource = resourceLoader.getResource( schemaPath );
         StreamSource schemaSource = new StreamSource( schemaResource.getInputStream() );
         businessDataSchema = factory.newSchema( schemaSource );
      }catch( SAXException e ){
         throw new BusinessDataSchemaException( schemaPath, e );
      }catch( IOException e ){
         throw new BusinessDataSchemaException( schemaPath, e );
      }catch( Exception e ){
         throw new BusinessDataSchemaException( schemaPath, e );
      }
      return businessDataSchema;
   }

   private void openInputSource( String resourcePath ) {
      InputStream resourceStream = null;
      try{
         resourceStream = resource.getInputStream();
         inputSource = new InputSource( resourceStream );
      }catch( IOException e ){
         throw new BusinessDataLoaderException( resourcePath, e );
      }
   }
   
   @SuppressWarnings( "unchecked" )
   private void unmarshallXmlWithSpring(){
      org.springframework.oxm.Unmarshaller unmarshaller = (org.springframework.oxm.Unmarshaller) applicationContext.getBean( "jaxb2Unmarshaller" );
      openInputSource( resourcePath );
      try{
         unmarshalledData = (D) unmarshaller.unmarshal( new StreamSource( resource.getFile() ));
      }catch( XmlMappingException | IOException e ){
         e.printStackTrace();
      }
   }

   @SuppressWarnings( { "unchecked", "unused" } )
   private void unmarshallXml( String resourcePath ) throws BusinessDataLoaderException {
      openInputSource( resourcePath );

      JAXBContext jaxbContext = null;
      try{
         jaxbContext = JAXBContext.newInstance( new Class[] { unmarshalledDataClass } );

         try{
            final XMLFilter filter = new NamespaceFilter();
            final SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setNamespaceAware( true );
            final SAXParser saxParser = saxParserFactory.newSAXParser();
            final XMLReader reader = saxParser.getXMLReader();
            filter.setParent( reader );

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setSchema( schema );
            unmarshaller.setEventHandler( new BusinessDataValidationEventHandler( resourcePath, schemaPath ) );

            UnmarshallerHandler unmarshallerHandler = unmarshaller.getUnmarshallerHandler();
            filter.setContentHandler( unmarshallerHandler );
            
            filter.parse( inputSource );
            
            unmarshalledData = (D) unmarshallerHandler.getResult();
         }catch( SAXException | ParserConfigurationException e ){
            throw new RuntimeException( e );
         }
      }catch( JAXBException e ){
         throw new BusinessDataLoaderException( resourcePath, e );
      }catch( Exception e ){
         throw new BusinessDataLoaderException( resourcePath, e );
      }
   }
}
