package com.processpuzzle.application.domain;

import org.springframework.core.io.ResourceLoader;

import com.processpuzzle.application.domain.Application;

public class TestApplication extends Application {
   public static final String APPLICATION_NAME = "TestApplication";

   public TestApplication( String configurationDescriptorPath ) {
      super( configurationDescriptorPath );
   }

   public TestApplication( String configurationDescriptorPath, ResourceLoader resourceLoader ) {
      super( configurationDescriptorPath, resourceLoader );
   }
   
   @Override
   protected void defineApplicationDescription() {
      applicationName = APPLICATION_NAME;
      applicationVersion = "1.0.0";
      applicationDescription = "Application for unit testing purposes";
   }

   public void setDescription( String description ) {
      this.applicationDescription = description;
   }

   public void setVersion( String version ) {
      this.applicationVersion = version;
   }

   public void setExecutionStatus( ExecutionStatus executionStatus ) {
      this.executionStatus = executionStatus;
   }
   
   public void setInstallationStatus( InstallationStatus installationStatus ) {
      this.installationStatus = installationStatus;
   }
   
   public void setConfigurationDescriptorPath( String configurationDescriptorPath ) {
      this.configurationDescriptorPath = configurationDescriptorPath;
   }
}
