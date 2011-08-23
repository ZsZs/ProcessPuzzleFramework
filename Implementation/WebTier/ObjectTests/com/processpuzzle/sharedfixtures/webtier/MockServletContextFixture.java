package com.processpuzzle.sharedfixtures.webtier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.context.support.ServletContextResourceLoader;

import com.mockrunner.mock.web.MockServletContext;
import com.mockrunner.mock.web.WebMockObjectFactory;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class MockServletContextFixture {
   private static final String DOMAIN_TIER_FORLDER = "DomainTier";
   private static final String WEB_CONTENT_FOLDER = "WebTier/WebContent";
   private static final String configurationPath = WebTierTestConfiguration.CONFIGURATION_DESCRIPTOR_PATH;
   private static final String applicationStoragePath = WebTierTestConfiguration.APPLICATION_REPOSITORY_STORAGE_PATH;
   private static WebMockObjectFactory mockFactory = null;
   private MockServletContext servletContext;
   private ResourceLoader resourceLoader;

   public MockServletContextFixture() {      
   }
   
   public MockServletContextFixture( MockServletContext servletContext ) {
      this.servletContext = servletContext;
   }

   public void setUp() throws FileNotFoundException {
      mockFactory = new WebMockObjectFactory();
      if( servletContext == null ) servletContext = mockFactory.getMockServletContext();
      
      String realPath = determineRealPath();
      servletContext.setContextPath( realPath );
      servletContext.setServletContextName( "ProcessPuzzleBrowserInterface" );
      servletContext.setRealPath( configurationPath, realPath + configurationPath );
      servletContext.setRealPath( WebTierTestConfiguration.BEAN_CONTAINER_DEFINITION_PATH, realPath + WebTierTestConfiguration.BEAN_CONTAINER_DEFINITION_PATH );
      servletContext.setRealPath( WebTierTestConfiguration.BUSINESS_DEFINITION_PATH, realPath + WebTierTestConfiguration.BUSINESS_DEFINITION_PATH );
      servletContext.setRealPath( WebTierTestConfiguration.APPLICATION_REPOSITORY_STORAGE_PATH, realPath + WebTierTestConfiguration.APPLICATION_REPOSITORY_STORAGE_PATH );
      servletContext.setResourceAsStream( WebTierTestConfiguration.BEAN_CONTAINER_DEFINITION_PATH, new FileInputStream( realPath + WebTierTestConfiguration.BEAN_CONTAINER_DEFINITION_PATH ));      
      servletContext.setResourceAsStream( WebTierTestConfiguration.BUSINESS_DEFINITION_PATH, new FileInputStream( realPath + WebTierTestConfiguration.BUSINESS_DEFINITION_PATH ));
      servletContext.setResourceAsStream( WebTierTestConfiguration.APPLICATION_REPOSITORY_STORAGE_PATH, new FileInputStream( realPath + WebTierTestConfiguration.APPLICATION_REPOSITORY_STORAGE_PATH ));
      
      resourceLoader = new ServletContextResourceLoader( servletContext );
   }
   
   public void tearDown() {
      
   }

   //Properties
   public String getApplicationStoragePath() { return applicationStoragePath; }

   public String getConfigurationDescriptorPath() { return configurationPath; }

   public String getInstalledApplicationName() {
      return WebTierTestConfiguration.INSTALLED_APPLICATION_NAME;
   }

   public ResourceLoader getResourceLoader() { return resourceLoader; }

   public ServletContext getServletContext() { return servletContext; }
   
   //Protected, private helper methods
   private static String determineRealPath() {
      ResourceLoader resourceLoader = new DefaultResourceLoader();
      Resource referenceResource = resourceLoader.getResource( DomainTierTestConfiguration.APPLICATION_REPOSITORY_STORAGE_PATH );
      File referenceFile = null;
      try{
         referenceFile = referenceResource.getFile();
      }catch( IOException e ){
         e.printStackTrace();
      }
      String realPath = referenceFile.getAbsolutePath();
      String projectRoot = realPath.substring( 0, realPath.indexOf( DOMAIN_TIER_FORLDER ));
      String contextRoot = "" + projectRoot.replace( "\\", "/" ) + WEB_CONTENT_FOLDER; 
      return contextRoot;
   }
}
