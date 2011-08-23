package com.processpuzzle.application.domain;

import org.springframework.core.io.ResourceLoader;

public class AlreadyInstalledWebApplication extends WebApplication {

   public AlreadyInstalledWebApplication( String configurationDescriptorPath, ResourceLoader resourceLoader ) {
      super( configurationDescriptorPath, resourceLoader );
   }

   @Override
   protected void defineApplicationDescription() {
      this.applicationName = AlreadyInstalledApplication.class.getSimpleName();
      this.applicationDescription = "Test application for testing application installer.";
      this.applicationVersion = "1.0.0";
   }
}
