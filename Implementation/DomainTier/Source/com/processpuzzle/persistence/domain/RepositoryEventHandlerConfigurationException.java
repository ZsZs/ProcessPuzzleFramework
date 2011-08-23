package com.processpuzzle.persistence.domain;

import org.apache.commons.configuration.HierarchicalConfiguration;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;

public class RepositoryEventHandlerConfigurationException extends RepositoryException {
   private static final long serialVersionUID = 8777428972186019161L;
   protected static String defaultMessagePattern = "Unable to configure the repository event handler with settings: ''{0}''";

   public RepositoryEventHandlerConfigurationException( HierarchicalConfiguration configuration, Throwable cause ) {
      super(ExceptionHelper.defineMessage(
            RepositoryEventHandlerConfigurationException.class, 
            new Object[] {configuration.toString()}, 
            defaultMessagePattern ), cause );
   }
}
