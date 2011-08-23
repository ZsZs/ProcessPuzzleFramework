package com.processpuzzle.application.configuration.domain;

public class UnknownConfigurationPathException extends Exception {
   private static final long serialVersionUID = 4007060193256628444L;

   public UnknownConfigurationPathException() {
      super( "Configuration descriptor path should be point to a valid 'configuration_descriptor.xml' file." );
   }
}
