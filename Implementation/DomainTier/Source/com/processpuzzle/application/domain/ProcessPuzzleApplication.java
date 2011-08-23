package com.processpuzzle.application.domain;

public class ProcessPuzzleApplication extends Application {

   public ProcessPuzzleApplication( String configurationDescriptorPath ) {
      super( configurationDescriptorPath );
   }

   @Override
   protected void defineApplicationDescription() {
      this.applicationName = "ProcessPuzzle";
      this.applicationDescription = "Workflow and Content Management Application";
      this.applicationVersion = "1.0.0";
   }
}
