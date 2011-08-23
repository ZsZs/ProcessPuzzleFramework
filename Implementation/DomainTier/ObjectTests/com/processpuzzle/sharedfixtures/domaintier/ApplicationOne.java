package com.processpuzzle.sharedfixtures.domaintier;

import com.processpuzzle.application.domain.Application;

public class ApplicationOne extends Application {
   public static final String APPLICATION_NAME = ApplicationOne.class.getSimpleName();

   public ApplicationOne(String configurationDescriptorPath) {
      super(configurationDescriptorPath);
      // TODO Auto-generated constructor stub
   }

   @Override
   protected void defineApplicationDescription() {
      applicationName = APPLICATION_NAME;
      applicationDescription = "For testing application installation";
      applicationVersion = "1.0.0";
   }
}
