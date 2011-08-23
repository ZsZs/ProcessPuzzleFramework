package com.processpuzzle.sharedfixtures.webtier;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

import com.mockrunner.mock.web.WebMockObjectFactory;
import com.mockrunner.servlet.ServletTestModule;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.WebApplicationContext;
import com.processpuzzle.application.control.control.CommandControllerServlet;
import com.processpuzzle.sharedfixtures.domaintier.ProcessPuzzleContextFixture;

public class WebApplicationContextFixture extends ProcessPuzzleContextFixture {
   private MockServletContextFixture servletContextFixure;
   private WebMockObjectFactory factory;
   private ServletTestModule testModule;
   
   //Public instantiation methods
   public static WebApplicationContextFixture getInstance() {
      return getInstance( WebTierTestConfiguration.CONFIGURATION_DESCRIPTOR_PATH );
   }
   
   public static WebApplicationContextFixture getInstance( String configurationDescriptorPath ) {
      if( soleInstance == null ) {
         soleInstance = new WebApplicationContextFixture( configurationDescriptorPath );
      }
      
      return (WebApplicationContextFixture) soleInstance;
   }

   //Public mutators
   public void addRequestParameter( String key, String parameter ) {
      testModule.addRequestParameter( key, parameter );
   }

   public void createServlet( Class<CommandControllerServlet> servletClass ) {
      testModule.createServlet( servletClass );
   }

   public void doPost() {
      testModule.doPost();
   }

   public @Override void setUp() {
      try{ servletContextFixure.setUp();
      }catch( FileNotFoundException e ){ e.printStackTrace(); }

      
      factory = new WebMockObjectFactory();
      testModule = new ServletTestModule( factory );
      testModule.setDoChain( true );
      super.setUp();
   }

   public @Override void tearDown() {
      super.tearDown();
   }
   
   //Public accessors
   public WebMockObjectFactory getWebMockObjectFactory() { return factory; }

   public BufferedReader getOutputAsBufferedReader() { return testModule.getOutputAsBufferedReader(); }
   
   
   //Protected, private helper methods
   protected @Override ProcessPuzzleContext instantiateSUT() {
      return (ProcessPuzzleContext) new WebApplicationContext( mockApplication, configurationDescriptorPath, servletContextFixure.getResourceLoader() );
   }
   
   protected WebApplicationContextFixture( String configurationDescriptorPath ) {
      super( configurationDescriptorPath );
      servletContextFixure = new MockServletContextFixture();
   }
}
