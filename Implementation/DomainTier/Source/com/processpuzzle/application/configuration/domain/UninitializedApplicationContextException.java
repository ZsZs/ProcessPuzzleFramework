package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class UninitializedApplicationContextException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 7426216880413293667L;
   private static String defaultMessagePattern = "ApplicationContext: ''{0}'' is uninitialized. Invoke setUp() before usint it.";
   private Class<? extends ApplicationContext> applicationContextClass;
   
   public UninitializedApplicationContextException( Class< ? extends ApplicationContext> applicationContextClass ) {
      super( ExceptionHelper.defineMessage( UninitializedApplicationContextException.class, new Object[] {applicationContextClass.getName()}, defaultMessagePattern ));
      this.applicationContextClass = applicationContextClass;
   }
   
   public Class<? extends ApplicationContext> getApplicationContextClass() { return applicationContextClass; }
}
