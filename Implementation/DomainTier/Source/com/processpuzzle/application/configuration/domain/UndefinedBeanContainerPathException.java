package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class UndefinedBeanContainerPathException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 7030311720641321562L;
   private static String defaultMessagePattern = "Retrieving Bean Container's configuration path from: '''{0}''' configuration failed.";
   private String configurationName = "";

   public UndefinedBeanContainerPathException( String configurationName ) {
      super( ExceptionHelper.defineMessage( UndefinedBeanContainerPathException.class, new Object[] {configurationName}, defaultMessagePattern ));
      this.configurationName = configurationName;
   }
   
   public String getConfigurationName() { return configurationName; }
}
