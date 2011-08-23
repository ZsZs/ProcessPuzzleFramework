package com.processpuzzle.application.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;

public class ApplicationInstallationException extends ApplicationException {
   private static final long serialVersionUID = 3788559641958236164L;
   protected static String defaultMessagePattern = "Installing application: ''{0}'' failed.";
   
   public ApplicationInstallationException( String applicationName, Throwable cause ) {
      super( applicationName, ExceptionHelper.defineMessage(
            ApplicationInstallationException.class, 
            new Object[] {applicationName}, 
            defaultMessagePattern ), cause);
   }
   
   public ApplicationInstallationException( Application application, Throwable cause ) {
      super( application, ExceptionHelper.defineMessage(
            ApplicationInstallationException.class, 
            new Object[] { application.getApplicationName() }, 
            defaultMessagePattern ), cause);
   }
}
