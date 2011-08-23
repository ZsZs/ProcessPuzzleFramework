package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class PersistenceContextSetUpException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 9051956818305544583L;
   private static String defaultMessagePattern = "Setting up PersistenceContext with configuration descriptor: ''{0}'' caused an error.";
   private String descriptorUrl = null;
   private ExceptionHelper nestedHelper = null;

   PersistenceContextSetUpException( PropertyContext context, Throwable cause ){
      super( ExceptionHelper.defineMessage( 
            PersistenceContextSetUpException.class, 
            new Object[] { context.getConfigurationDescriptorUrl() },
            defaultMessagePattern ),
            cause );
      this.descriptorUrl = context.getConfigurationDescriptorUrl();
   }
   
   PersistenceContextSetUpException( ExceptionHelper nestedHelper, PropertyContext context, Throwable cause ) {
      this( context, cause );
      this.nestedHelper = nestedHelper;
   }

   // Properties
   public String getDescriptorUrl() { return descriptorUrl; }
   public ExceptionHelper getNestedHelper() { return nestedHelper; }
}
