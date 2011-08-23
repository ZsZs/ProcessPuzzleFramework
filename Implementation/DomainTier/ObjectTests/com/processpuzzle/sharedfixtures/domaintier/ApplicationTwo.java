package com.processpuzzle.sharedfixtures.domaintier;

import com.processpuzzle.application.domain.Application;

public class ApplicationTwo extends Application {
   public static final String APPLICATION_NAME = ApplicationTwo.class.getSimpleName();

   public ApplicationTwo(String configurationDescriptorPath) {
      super(configurationDescriptorPath);
   }

   @Override
   protected void defineApplicationDescription() {
      applicationName = APPLICATION_NAME;
      applicationDescription = "For testting application starting procedure.";
      applicationVersion = "1.0.0";
   }

}
