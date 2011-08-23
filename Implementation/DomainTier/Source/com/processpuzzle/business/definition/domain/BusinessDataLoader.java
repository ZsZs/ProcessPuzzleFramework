package com.processpuzzle.business.definition.domain;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.xml.sax.SAXException;

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

   @SuppressWarnings("unchecked")
   public BusinessDataLoader( ResourceLoader resourceLoader, String resourcePath ) {
      super( resourceLoader, resourcePath );
      this.resultInPersistentObjects = true;
      unmarshalledDataClass = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
   }

   @Override public void loadData() throws XmlDataLoaderException {
      super.loadData();

      mappingPath = (String) applicationContext.getProperty( mappingKey );
      
      try{
         schema = loadSchema();
         unmarshallXml( resourcePath );
         
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
   
      String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
      SchemaFactory factory = SchemaFactory.newInstance( language );
      factory.setErrorHandler( new BusinessDataLoaderErrorHandler( schemaPath ));
   
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

   @SuppressWarnings("unchecked")
   private void unmarshallXml( String resourcePath ) throws BusinessDataLoaderException {
      InputStream resourceStream = null;
      try{ resourceStream = resource.getInputStream(); }
      catch( IOException e ){ throw new BusinessDataLoaderException( resourcePath, e ); }
      
      JAXBContext jaxbContext = null;
      try{       
         jaxbContext = JAXBContext.newInstance( new Class[] { unmarshalledDataClass } );
  
         Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
         unmarshaller.setSchema( schema );
         unmarshaller.setEventHandler( new BusinessDataValidationEventHandler( resourcePath, schemaPath ));
         unmarshalledData = (D) unmarshaller.unmarshal( resourceStream );
         resourceStream.close();
      }catch( JAXBException e ){
         throw new BusinessDataLoaderException( resourcePath, e );
      } catch ( Exception e ) {
         throw new BusinessDataLoaderException( resourcePath, e );
      }
   }
}
