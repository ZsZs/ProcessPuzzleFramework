package com.processpuzzle.application.domain;

import org.springframework.core.io.ResourceLoader;


public class AlreadyInstalledApplication extends Application {

   public AlreadyInstalledApplication( String configurationDescriptorPath ) {
      super( configurationDescriptorPath );
   }

   public AlreadyInstalledApplication( String configurationDescriptorPath, ResourceLoader resourceLoader ) {
      super( configurationDescriptorPath, resourceLoader );
   }

   protected @Override void defineApplicationDescription() {
      this.applicationName = AlreadyInstalledApplication.class.getSimpleName();
      this.applicationDescription = "Test application for testing application installer.";
      this.applicationVersion = "1.0.0";
   }   
}
