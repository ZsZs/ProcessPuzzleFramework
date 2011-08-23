package com.processpuzzle.application.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;

public class ApplicationStartException extends ApplicationException {
   private static final long serialVersionUID = -4741192435261590084L;
   protected static String defaultMessagePattern = "Starting up application: ''{0}'' failed.";
   
   public ApplicationStartException( Application application, Throwable cause) {
      super( application, ExceptionHelper.defineMessage(
            ApplicationStartException.class, 
            new Object[] {application}, 
            defaultMessagePattern ), cause);
   }
}
