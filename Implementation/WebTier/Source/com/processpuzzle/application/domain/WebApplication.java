package com.processpuzzle.application.domain;

import org.springframework.core.io.ResourceLoader;

import com.processpuzzle.application.configuration.domain.WebApplicationContext;

public abstract class WebApplication extends Application {
   protected static WebApplication soleInstance = null;

   public WebApplication( String configurationDescriptorPath, ResourceLoader resourceLoader ) {
      super( configurationDescriptorPath );
      applicationContext = new WebApplicationContext( this, configurationDescriptorPath, resourceLoader );
   }
}
