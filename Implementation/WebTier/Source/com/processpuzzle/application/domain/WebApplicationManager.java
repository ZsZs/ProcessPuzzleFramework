package com.processpuzzle.application.domain;

import javax.servlet.ServletContext;

import org.springframework.core.io.ResourceLoader;

public class WebApplicationManager extends ApplicationManager {
   private ServletContext servletContext;
   
   public WebApplicationManager( String applicationStoragePath, ResourceLoader resourceLoader, ServletContext servletContext ) throws InstantiationException {
      super( applicationStoragePath, resourceLoader );
      this.servletContext = servletContext;
   }

   public WebApplication installWebApplication( String applicationName, 
                                  Class<? extends WebApplication> applicationClass, 
                                  String configurationDescriptorPath ) throws ApplicationInstallationException {
      return (WebApplication) super.install( applicationName, applicationClass, configurationDescriptorPath );
   }

   @Override
   protected <A extends Application> A instantiateNewApplication( String applicationName, Class<A> applicationClass, String configurationDescriptorPath ) {
      //"WebApplicatonManager can only instantiate subclasses of WebApplication."
      A application = WebApplicationFactory.create( applicationClass, configurationDescriptorPath, servletContext );
      return application;
   }
}
