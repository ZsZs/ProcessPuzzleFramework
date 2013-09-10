package com.processpuzzle.litest.template;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.jdom.Document;
import org.jdom.transform.JDOMResult;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.processpuzzle.commons.xml.ClasspathResourceResolver;
import com.processpuzzle.fundamental_types.domain.ServiceMessage;
import com.processpuzzle.litest.fixture.GenericTestFixture;
import com.processpuzzle.litest.fixture.TransientFreshFixture;

public abstract class ServiceMessageTestFixture<S extends ServiceMessage> extends GenericTestFixture<S> implements TransientFreshFixture<S> {
   protected static final String CONTEXT_PATH = "com.cahoot";
   protected Class<S> messageClass;
   protected S messageAsObject;
   protected Document messageAsXml;
   protected String messagePath;
   protected Resource messageResource;
   protected Schema messageSchema;
   protected ResourceLoader resourceLoader;
   protected String schemaPath;

   protected ServiceMessageTestFixture( String messagePath, String schemaPath ) {
      this.messagePath = messagePath;
      this.schemaPath = schemaPath;
   }

   // Properties
   public Document getMessageAsXml() {
      return messageAsXml;
   }

   // Protected, private helper methods
   @Override
   protected void configureBeforeSutInstantiation() {
      resourceLoader = new DefaultResourceLoader();
      determineMessageClass();
      loadSchema();
   }

   @Override
   protected S instantiateSUT() {
      messageAsObject = setUpMessageObject();
      sut = transformXmlToObject();
      messageAsXml = transformObjectToXml();
      return sut;
   }

   protected <T> Document marshall( Class<T> marshalledObjectClass, String schemaPath, ServiceMessage message ) {
      JDOMResult marshallResult = new JDOMResult();
      try{
         JAXBContext jaxbContext = JAXBContext.newInstance( marshalledObjectClass );
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         //SchemaFactory sf = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
         //Schema schema = sf.newSchema( new URL( SCHEMA_PATH ) );
         //jaxbMarshaller.setSchema( schema );
         jaxbMarshaller.marshal( messageAsObject, marshallResult );
      }catch( Exception e ){
         throw new RuntimeException( e );
      }
      
      return marshallResult.getDocument();
   }

   protected abstract S setUpMessageObject();

   protected <T> T unmarshall( Class<T> docClass, String resourceName ) throws JAXBException, IOException {
      messageResource = resourceLoader.getResource( resourceName );
      return unmarshal( docClass, messageResource.getInputStream() );
   }

   @SuppressWarnings( "unchecked" )
   protected <T> T unmarshal( Class<T> unmarshalledObjectClass, InputStream inputStream ) throws JAXBException, IOException {
      JAXBContext jaxbContext = JAXBContext.newInstance( unmarshalledObjectClass );
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      unmarshaller.setSchema( messageSchema );
      T unmarshalledObject = (T) unmarshaller.unmarshal( inputStream );
      inputStream.close();
      return unmarshalledObject;
   }

   @SuppressWarnings( "unchecked" )
   private void determineMessageClass() {
      messageClass = (Class<S>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
   }

   private void loadSchema() {
      SchemaFactory schemaFactory = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
      try{
         schemaFactory.setResourceResolver( new ClasspathResourceResolver() );
         Resource schemaResource = resourceLoader.getResource( schemaPath );
         messageSchema = schemaFactory.newSchema( schemaResource.getFile() );
      }catch( Exception e ){
         throw new RuntimeException( e );
      }
   }

   private Document transformObjectToXml() {
      return marshall( messageClass, schemaPath, messageAsObject );
   }

   private S transformXmlToObject() {
      try{
         sut = unmarshall( messageClass, messagePath );
      }catch( JAXBException e ){
         e.printStackTrace();
      }catch( IOException e ){
         e.printStackTrace();
      }
      return sut;
   }
}
