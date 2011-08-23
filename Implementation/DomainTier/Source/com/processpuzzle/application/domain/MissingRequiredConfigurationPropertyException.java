package com.processpuzzle.application.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class MissingRequiredConfigurationPropertyException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 6999302450993927981L;
   private static String defaultMessagePattern = "Required property: ''{0}'' is missing from configuration: ''{1}''.";
   private final String propertyName;
   private final String configurationFile;
   
   MissingRequiredConfigurationPropertyException( String propertyName, String configurationFile ) {
      super( ExceptionHelper.defineMessage( 
            MissingRequiredConfigurationPropertyException.class, 
            new Object[] { propertyName, configurationFile }, 
            defaultMessagePattern ));
      this.propertyName = propertyName;
      this.configurationFile = configurationFile;
   }
   
   public String getPropertyName() {
      return propertyName;
   }
   
   public String getConfigurationFile() {
      return configurationFile;
   }
}
