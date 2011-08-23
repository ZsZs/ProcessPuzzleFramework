/*
 * Created on Jul 12, 2006
 */
package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

/**
 * @author zsolt.zsuffa
 */
public class ConfigurationSetUpException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 5567878432430133490L;
   private static String defaultMessagePattern = "Setting up configuration: ''{0}'' caused an error."
      + " Look at your configuration file. Probably it is malformed."
      + " Note that this file''s root element should be <configuration>.";
   private String configurationName = "";

   ConfigurationSetUpException(String configurationName, Exception cause) {
      super( ExceptionHelper.defineMessage( 
               ConfigurationSetUpException.class, 
               new Object[] {configurationName}, 
               defaultMessagePattern ), 
               cause );
      this.configurationName = configurationName;
   }

//Properties
   public String getConfigurationName() { return configurationName; }

//Private helper methods
}