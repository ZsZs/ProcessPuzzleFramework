package com.processpuzzle.application.domain;

import org.springframework.core.io.ResourceLoader;

public class InstallationTestApplication extends Application {

   public InstallationTestApplication( String configurationDescriptorPath ) {
      super( configurationDescriptorPath );
   }
   
   public InstallationTestApplication( String configurationDescriptorPath, ResourceLoader resourceLoader ){
      super( configurationDescriptorPath, resourceLoader );
   }

   @Override
   protected void defineApplicationDescription() {
      this.applicationName = InstallationTestApplication.class.getSimpleName();
      this.applicationDescription = "Test applicaiton for ApplicationInstaller tests.";
      this.applicationVersion = "1.0.0";
   }
}
