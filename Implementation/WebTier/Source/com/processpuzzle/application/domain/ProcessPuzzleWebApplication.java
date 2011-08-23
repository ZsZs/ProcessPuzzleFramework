package com.processpuzzle.application.domain;

import org.springframework.core.io.ResourceLoader;

public class ProcessPuzzleWebApplication extends WebApplication {

   public ProcessPuzzleWebApplication( String configurationDescriptorPath, ResourceLoader resourceLoader ) {
      super( configurationDescriptorPath, resourceLoader );
   }

   @Override
   protected void defineApplicationDescription() {
      this.applicationName = ProcessPuzzleWebApplication.class.getSimpleName();
      this.applicationDescription = "Full flagged Workflow and Content Management web application.";
      this.applicationVersion = "1.0.0";
   }
}
