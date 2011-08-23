package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.application.domain.Application;

public class ApplicationContextFactory {
   public static ProcessPuzzleContext create( Application application, String configurationDescriptorPath ) {
      return new ProcessPuzzleContext( application, configurationDescriptorPath );
   }
}
