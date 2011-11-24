/*
Name: 
    - ApplicationRepository

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

package com.processpuzzle.application.domain;

import hu.itkodex.commons.persistence.AggregateRoot;
import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.Repository;
import hu.itkodex.commons.persistence.RepositoryResultSet;
import hu.itkodex.commons.persistence.UnitOfWork;
import hu.itkodex.commons.persistence.query.IdentityExpression;
import hu.itkodex.commons.persistence.query.Query;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.processpuzzle.application.domain.Application.ExecutionStatus;
import com.processpuzzle.application.domain.Application.InstallationStatus;
import com.processpuzzle.application.resource.domain.ResourceNotFoundException;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.IdentityCollisionException;
import com.processpuzzle.persistence.domain.RepositoryAction;
import com.processpuzzle.persistence.domain.RepositoryException;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQuery;

public class ApplicationRepository implements Repository<Application> {
   static final String DEFAULT_STORAGE_PATH = "classpath:com/processpuzzle.application.domain.Application.xml";
   static final String APPLICATION_ELEMENT = "application";
   static final String DESCRIPTION_ELEMENT = "description";
   static final String VERSION_ELEMENT = "version";
   static final String CLASS_NAME_ELEMENT = "className";
   static final String NAME_ATTRIBUTE = "name";
   static final String EXECUTION_STATUS_ELEMENT = "executionStatus";
   static final String INSTALLATION_STATUS_ELEMENT = "installationStatus";
   static final String CONFIGURATION_PATH_ELEMENT = "configurationDescriptorPath";
   private static String xmlStoragePath;
   private static ResourceLoader resourceLoader;
   private Document xmlDocument;
   private Resource xmlResource;
   
   //Public mutators
   public static ApplicationRepository getInstance( String applicationStoragePath, ResourceLoader resourceLoader ) throws InstantiationException {
      ApplicationRepository repository = (ApplicationRepository) SingletonRegistry.getInstance( ApplicationRepository.class );
      xmlStoragePath = applicationStoragePath;
      ApplicationRepository.resourceLoader = resourceLoader;
      
      try {
         repository.setStorageXmlPath( applicationStoragePath, resourceLoader );
         repository.readXmlDocument();
      }catch( DocumentException e ) {
         throw new InstantiationException( "Instantiation of 'ApplicationRepository' caused an error." );
      } catch (IOException e) {
         throw new InstantiationException( "Instantiation of 'ApplicationRepository' caused an error." );
      }
      return repository;
   }

   public static ApplicationRepository getInstance( String applicationStoragePath ) throws InstantiationException {
      return getInstance( applicationStoragePath, (ResourceLoader) new DefaultResourceLoader() );
   }
   
   public static ApplicationRepository getInstance() throws InstantiationException {
      return getInstance( DEFAULT_STORAGE_PATH );
   }
   
   public Integer add( DefaultUnitOfWork work, Application application ) {
      if( findByName( application.getApplicationName() ) != null ) throw new IdentityCollisionException( Application.class );
      
      createApplicationElement(application);
      
      try {
         writeXmlDocument();
      } catch (IOException e) {
         throw new RepositoryException( this, RepositoryAction.add, application, e );
      }
      
      return null;
   }

   public void add( Application application ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      add( work, application );
      work.finish();
   }

   public void update( DefaultUnitOfWork work, Application application ) {
      Node applicationNode = findApplicationElement( application.getApplicationName() );
      saveApplicationProperties( (Element) applicationNode, application );
      try {
         writeXmlDocument();
      } catch (IOException e) {
         throw new RepositoryException( this, RepositoryAction.delete, application, e );
      }
   }

   public void update( Application application ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      update( work, application );
      work.finish();      
   }
   
   public void delete( DefaultUnitOfWork work, Application application ) {
      Node applicationNode = findApplicationElement( application.getApplicationName() );
      if( applicationNode != null ) {
         applicationNode.detach();
         try {
            writeXmlDocument();
         } catch (IOException e) {
            throw new RepositoryException( this, RepositoryAction.delete, application, e );
         }
      }else throw new RepositoryException( this, RepositoryAction.delete, application );            
   }

   public void delete( Application application ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      delete( work, application );
      work.finish();      
   }

   public Application findByName( String applicationName ) {
      Node node = findApplicationElement(applicationName);
      if( node == null ) return null;
      
      Application application = instantiateApplicationObject( (Element) node );
      restoreApplicationProperties( (Element) node, (Application) application );
      
      return application;
   }

   public RepositoryResultSet<Application> findAll(DefaultUnitOfWork work) {
      // TODO Auto-generated method stub
      return null;
   }

   public Application findById(DefaultUnitOfWork work, Integer id) {
      // TODO Auto-generated method stub
      return null;
   }

   @SuppressWarnings("unchecked")
   public Application findByIdentityExpression(DefaultUnitOfWork work, DefaultIdentityExpression identity) {
      // TODO Auto-generated method stub
      return null;
   }

   public RepositoryResultSet<Application> findByQuery( DefaultUnitOfWork work, DefaultQuery query ) {
      // TODO Auto-generated method stub
      return null;
   }

   //Properties
   public String getStorageXmlPath() { return xmlStoragePath; }
   public Resource getStorageXmlResource() { return xmlResource; }

   public PersistenceStrategy getPersistenceStrategy() {
      // Note: with this repository no strategy defined.
      return null;
   }

   public Class<? extends AggregateRoot> getSupportedAggregateRootClass() { return Application.class; }

   //Protected, private heleper methods
   protected ApplicationRepository() {} //Exists to defeat installation

   private Node findApplicationElement(String applicationName) {
      String xPath = "/applicationRepository/application[@name = '" + applicationName + "']";
      Node node = null;
      node = xmlDocument.selectSingleNode( xPath );
      return node;
   }
   
   private void createApplicationElement(Application application) {
      Element root = xmlDocument.getRootElement();
      
      Element applicationElement = root.addElement( APPLICATION_ELEMENT );
      applicationElement.addAttribute( NAME_ATTRIBUTE, application.getApplicationName() );
      
      Element versionElement = applicationElement.addElement( VERSION_ELEMENT );
      versionElement.setText( application.getApplicationVersion() );
      
      Element descriptionElement = applicationElement.addElement( DESCRIPTION_ELEMENT );
      descriptionElement.setText( application.getDescription() );
      
      Element classNameElement = applicationElement.addElement( CLASS_NAME_ELEMENT );
      classNameElement.setText( application.getClass().getName() );
      
      Element executionStatusElement = applicationElement.addElement( EXECUTION_STATUS_ELEMENT );
      executionStatusElement.setText( application.getExecutionStatus().name() );
      
      Element installationStatusElement = applicationElement.addElement( INSTALLATION_STATUS_ELEMENT );
      installationStatusElement.setText( application.getInstallationStatus().name() );
      
      Element configurationDescriptorPath = applicationElement.addElement( CONFIGURATION_PATH_ELEMENT );
      configurationDescriptorPath.setText( application.getConfigurationDescriptorPath() );
   }

   private void setStorageXmlPath( String xmlStoragePath, ResourceLoader resourceLoader ) {
      String previousPath = ApplicationRepository.xmlStoragePath;
      Resource prevoiusResource = this.xmlResource;
      
      ApplicationRepository.xmlStoragePath = xmlStoragePath;
      xmlResource = resourceLoader.getResource( xmlStoragePath );
      try{
         if( !xmlResource.getFile().exists() ) {
            ApplicationRepository.xmlStoragePath = previousPath;
            this.xmlResource = prevoiusResource; 
            throw new ResourceNotFoundException( xmlStoragePath );         
         }
      }catch( IOException e ){
         ApplicationRepository.xmlStoragePath = previousPath;
         this.xmlResource = prevoiusResource; 
         throw new ResourceNotFoundException( xmlStoragePath );         
      }
   }
   
   private void readXmlDocument() throws DocumentException, IOException {
      SAXReader reader = new SAXReader();
      Resource xmlResource = resourceLoader.getResource( xmlStoragePath );
      xmlDocument = reader.read( xmlResource.getInputStream() );
   }
   
   private void writeXmlDocument() throws IOException {
      OutputFormat outputFormat = OutputFormat.createPrettyPrint();
      outputFormat.setEncoding( xmlDocument.getXMLEncoding() );
      OutputStream output = new FileOutputStream( xmlResource.getFile() );
      XMLWriter writer = new XMLWriter( output, outputFormat);
      
      writer.write( xmlDocument );
      writer.close();
   }

   @SuppressWarnings("unchecked")
   private Application instantiateApplicationObject( Element applicationElement ) {
      if( applicationElement == null ) return null;
      
      String configurationDescriptorPath = applicationElement.element( CONFIGURATION_PATH_ELEMENT ).getText();
      String applicationClassName = applicationElement.element( CLASS_NAME_ELEMENT ).getText();
      Class<? extends Application> applicationClass;
      Application application = null;
      
      try {
         applicationClass = (Class<? extends Application>) Class.forName( applicationClassName );
         application = ApplicationFactory.create( applicationClass, configurationDescriptorPath, resourceLoader );
      } catch( ClassNotFoundException e ) {
         throw new RepositoryException( this, RepositoryAction.findById, application, e );
      }      
      return application;
   }

   private void saveApplicationProperties( Element applicationElement, Application application ) {
      applicationElement.element( VERSION_ELEMENT ).setText( application.getApplicationVersion() );
      applicationElement.element( DESCRIPTION_ELEMENT ).setText( application.getDescription() );
      applicationElement.element( CLASS_NAME_ELEMENT ).setText( application.getClass().getName() );
      applicationElement.element( EXECUTION_STATUS_ELEMENT ).setText( application.getExecutionStatus().name() );
      applicationElement.element( INSTALLATION_STATUS_ELEMENT ).setText( application.getInstallationStatus().name() );
      applicationElement.element( CONFIGURATION_PATH_ELEMENT ).setText( application.getConfigurationDescriptorPath() );
   }
   
   private void restoreApplicationProperties(  Element applicationElement, Application application ) {
      setApplicationAttributeValue( application, "applicationVersion", applicationElement.element( VERSION_ELEMENT ).getText() );
      setApplicationAttributeValue( application, "applicationDescription", applicationElement.element( DESCRIPTION_ELEMENT ).getText() );
      setApplicationAttributeValue( application, "installationStatus", applicationElement.element( INSTALLATION_STATUS_ELEMENT ).getText() );
      setApplicationAttributeValue( application, "executionStatus", applicationElement.element( EXECUTION_STATUS_ELEMENT ).getText() );
      setApplicationAttributeValue( application, "configurationDescriptorPath", applicationElement.element( CONFIGURATION_PATH_ELEMENT ).getText() );
   }
   
   private void setApplicationAttributeValue( Application application, String attributeName, String value ) {
      Class<? extends Application> applicationClass = Application.class;

      Field field;
      Class<?> fieldType;
      try {
         field = applicationClass.getDeclaredField( attributeName );
         field.setAccessible( true );
         fieldType = field.getType();
         if( fieldType.equals( String.class ) ) field.set( application, value );
         else if( fieldType.equals( Boolean.class ) ) field.setBoolean( application, new Boolean( value ).booleanValue() ); 
         else if( fieldType.equals( Integer.class ) ) field.setInt( application, new Integer( value ).intValue() ); 
         else if( fieldType.equals( Long.class ) ) field.setLong( application, new Long( value ).longValue() ); 
         else if( fieldType.equals( Double.class ) ) field.setDouble( application, new Double( value ).doubleValue() ); 
         else if( fieldType.equals( Application.ExecutionStatus.class ) ) field.set( application, ExecutionStatus.valueOf( value )); 
         else if( fieldType.equals( Application.InstallationStatus.class ) ) field.set( application,  InstallationStatus.valueOf( value ));
         else field.set( application, value );
      } catch (SecurityException e ) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (NoSuchFieldException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IllegalArgumentException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   @Override
   public Integer add( UnitOfWork work, Application entity ) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void delete( UnitOfWork work, Application entity ) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public RepositoryResultSet<Application> findAll( UnitOfWork work ) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Application findById( UnitOfWork work, Integer id ) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Application findByIdentityExpression( UnitOfWork work, IdentityExpression<?> identity ) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public RepositoryResultSet<Application> findByQuery( UnitOfWork work, Query query ) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void update( UnitOfWork work, Application entity ) {
      // TODO Auto-generated method stub
      
   }
}
