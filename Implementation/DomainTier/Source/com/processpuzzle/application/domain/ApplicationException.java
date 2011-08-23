package com.processpuzzle.application.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleException;

public class ApplicationException extends ProcessPuzzleException {
   private static final long serialVersionUID = -2557277926288481771L;
   protected static String defaultMessagePattern = "Application action: ''{0}'' caused an error.";
   protected Application application;
   protected String applicationName;
   
   public ApplicationException( Application application, ExceptionHelper helper ) {
      this( application, helper, null );
   }
   
   public ApplicationException( Application application, ExceptionHelper helper, Throwable cause) {
      super( helper, cause );
      this.application = application;
   }
   
   public ApplicationException( String applicationName, ExceptionHelper helper, Throwable cause) {
      super( helper, cause );
      this.applicationName = applicationName;
   }
   
   public ApplicationException( ExceptionHelper helper ) {
      super( helper, null );
   }
   
   public ApplicationException( String actionName, Throwable cause ) {
      super( ExceptionHelper.defineMessage(
            ApplicationException.class, 
            new Object[] {actionName}, 
            defaultMessagePattern), cause );
   }

   public Application getApplication() { return application; }
   public String getApplicationName() { return applicationName; }
}
