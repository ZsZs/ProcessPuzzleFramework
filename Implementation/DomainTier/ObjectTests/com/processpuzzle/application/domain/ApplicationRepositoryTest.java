package com.processpuzzle.application.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsSame.*;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.processpuzzle.application.resource.domain.ResourceNotFoundException;
import com.processpuzzle.persistence.domain.IdentityCollisionException;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class ApplicationRepositoryTest {
   private Document applicationStorageDocument; 
   private Application application;
   private ApplicationRepository applicationRepository;
   
   @Before public void beforEachTests() throws DocumentException, IOException, InstantiationException {
      readStorageDocument();
      
      application = ApplicationFactory.create( TestApplication.class, DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );      
      applicationRepository = ApplicationRepository.getInstance( DomainTierTestConfiguration.APPLICATION_REPOSITORY_STORAGE_PATH );
      applicationRepository.add( application );      
   }

   @Test public void testInitialization_ForSuccess() {
      assertThat( applicationRepository.getStorageXmlPath(), equalTo( DomainTierTestConfiguration.APPLICATION_REPOSITORY_STORAGE_PATH ));
      assertThat( applicationRepository.getStorageXmlResource(), notNullValue() );
   }
   
   @Test( expected = ResourceNotFoundException.class )
   public void testInitiation_ForResourceNotFound() throws DocumentException, IOException, InstantiationException {
      ApplicationRepository.getInstance( "blabla" );
   }
   
   @Test public void add_ShouldSavePropertiesInXml() throws IOException, DocumentException {
      compareApplicationPropertiesInXml();
   }

   @Test (expected = IdentityCollisionException.class ) 
   public void add_ShouldThrow_IdentitiyCollisionException() throws IOException {
      applicationRepository.add( application );
   }
   
   @Test public void update_ShouldModifyPropertiesInXml() throws DocumentException, IOException {
      TestApplication testApplication = (TestApplication) application;
      testApplication.setVersion( "1.1.1" );
      testApplication.setDescription("modified description");
      testApplication.setExecutionStatus( Application.ExecutionStatus.running );
      testApplication.setInstallationStatus( Application.InstallationStatus.installed );
      testApplication.setConfigurationDescriptorPath( "another path" );
      
      applicationRepository.update( application );
      
      compareApplicationPropertiesInXml();
   }
   
   @Test public void delete_ShouldRemoveXmlElement() {
      //Note that this requirement implicitly checked by 'afterEachTests()' method.
   }
   
   @Test public void findByName_ShouldInstantiateNewInstance() {
      Application foundApplicationInstance = applicationRepository.findByName( application.getApplicationName() ); 
      assertThat( foundApplicationInstance, not( sameInstance( application )));
      assertThat( foundApplicationInstance, equalTo( application ));
   }
   
   @After public void afterEachTests() {
      applicationRepository.delete( application );
   }

   //Private helper methods
   private void compareApplicationPropertiesInXml() throws DocumentException, IOException {
      assertThat( getApplicationProperty( ApplicationRepository.VERSION_ELEMENT ), equalTo( application.getApplicationVersion() ));
      assertThat( getApplicationProperty( ApplicationRepository.DESCRIPTION_ELEMENT ), equalTo( application.getDescription() ));
      assertThat( getApplicationProperty( ApplicationRepository.CLASS_NAME_ELEMENT ), equalTo( application.getClass().getName() ));
      assertThat( getApplicationProperty( ApplicationRepository.EXECUTION_STATUS_ELEMENT ), equalTo( application.getExecutionStatus().name() ));
      assertThat( getApplicationProperty( ApplicationRepository.INSTALLATION_STATUS_ELEMENT ), equalTo( application.getInstallationStatus().name()));
   }
   
   private void readStorageDocument() throws DocumentException, IOException {
      SAXReader reader = new SAXReader();
      ResourceLoader resourceLoader = new DefaultResourceLoader();
      Resource applicationStorateResource = resourceLoader.getResource( DomainTierTestConfiguration.APPLICATION_REPOSITORY_STORAGE_PATH );
      applicationStorageDocument = reader.read( applicationStorateResource.getInputStream() );
   }
   
   private Node findApplicationNode( String attributeValue ) throws DocumentException, IOException {
      readStorageDocument();
      String xPath = "/applicationRepository/application[@name = '" + attributeValue + "']";
      Node node = null;
      node = applicationStorageDocument.selectSingleNode( xPath );         
      
      return node;
   }
   
   private String getApplicationProperty( String propertyName ) throws DocumentException, IOException {
      Node applicationNode = findApplicationNode( application.getApplicationName() ); 
      Element applicationElement = (Element) applicationNode;
      return applicationElement.element( propertyName ).getText();
   }
}
