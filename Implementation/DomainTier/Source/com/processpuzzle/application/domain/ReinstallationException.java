package com.processpuzzle.application.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;

public class ReinstallationException extends ApplicationException {
   private static final long serialVersionUID = -5212455240953471823L;
   protected static String defaultMessagePattern = "Application : ''{0}'' already installed. It can't be installed again.";

   public ReinstallationException( Application application ) {
      super( application, ExceptionHelper.defineMessage(
            ReinstallationException.class, 
            new Object[] { application }, 
            defaultMessagePattern ));
   }
}
