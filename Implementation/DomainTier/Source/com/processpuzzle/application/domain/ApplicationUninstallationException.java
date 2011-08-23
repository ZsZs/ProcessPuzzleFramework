package com.processpuzzle.application.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleException;

public class ApplicationUninstallationException extends ProcessPuzzleException {
   private static final long serialVersionUID = 484929754260458232L;
   private static final String defaultMessagePattern = "Unistalling application: ''{%0}'' failed.";
   private Application application;
   
   ApplicationUninstallationException( Application application, Throwable cause ) {
      super( ExceptionHelper.defineMessage( 
             ApplicationUninstallationException.class, new Object[] {application.getApplicationName()}, defaultMessagePattern ), cause );
      this.application = application;
   }
   
   public Application getApplication() { return application; }
}
