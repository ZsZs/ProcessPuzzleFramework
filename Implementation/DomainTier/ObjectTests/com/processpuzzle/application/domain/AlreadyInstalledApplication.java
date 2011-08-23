package com.processpuzzle.application.domain;


public class AlreadyInstalledApplication extends Application {

   public AlreadyInstalledApplication( String configurationDescriptorPath ) {
      super( configurationDescriptorPath );
   }

   protected @Override void defineApplicationDescription() {
      this.applicationName = AlreadyInstalledApplication.class.getSimpleName();
      this.applicationDescription = "Test application for testing application installer.";
      this.applicationVersion = "1.0.0";
   }   
}
