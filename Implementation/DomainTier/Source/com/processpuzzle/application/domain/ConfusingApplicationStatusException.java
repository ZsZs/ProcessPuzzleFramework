package com.processpuzzle.application.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;

public class ConfusingApplicationStatusException extends ApplicationException {
   private static final long serialVersionUID = 2114369499989985248L;
   private static final String defaultMessagePattern = "Aplication: ''{0}'' is in: ''{1}'' status therefore action: ''{2}'' cant't be performed.";
   private String applicationName;
   private String applicationStatus;
   private String actionName;
   
   public ConfusingApplicationStatusException( String applicationName, String applicationStatus, String actionName ) {
      super( ExceptionHelper.defineMessage( 
                                            ConfusingApplicationStatusException.class, 
                                            new Object[] { applicationName, applicationStatus, actionName }, 
                                            defaultMessagePattern ));
      this.applicationName = applicationName;
      this.actionName = actionName;
   }

   public String getActionName() { return actionName; }
   public String getApplicationStatus() { return applicationStatus; }
   public String getApplicationName() {return applicationName; }
}
