package com.processpuzzle.application.domain;

public class InstallationTestApplication extends Application {

   public InstallationTestApplication( String configurationDescriptorPath ) {
      super( configurationDescriptorPath );
   }

   @Override
   protected void defineApplicationDescription() {
      this.applicationName = InstallationTestApplication.class.getSimpleName();
      this.applicationDescription = "Test applicaiton for ApplicationInstaller tests.";
      this.applicationVersion = "1.0.0";
   }
}
