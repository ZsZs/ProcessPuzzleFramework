package com.processpuzzle.sharedfixtures.domaintier;

import com.processpuzzle.application.domain.Application;

public class DomainTierTestApplication extends Application {
   public static final String APPLICATION_NAME = DomainTierTestApplication.class.getSimpleName();

   public DomainTierTestApplication( String configurationDescriptorPath ) {
      super( configurationDescriptorPath );
   }

   @Override
   protected void defineApplicationDescription() {
      applicationName = APPLICATION_NAME;
      applicationDescription = "For testing ProcessPuzzle domain tier functionality.";
      applicationVersion = "1.0.0";
   }
}
