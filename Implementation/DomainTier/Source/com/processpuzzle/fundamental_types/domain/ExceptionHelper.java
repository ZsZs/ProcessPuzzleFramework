package com.processpuzzle.fundamental_types.domain;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.resource.domain.ResourceNotFoundException;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class ExceptionHelper {
   private Object[] arguments = null;
   private String messagePattern = null;
   private Class<?> exceptionClass = null;

   private ExceptionHelper ( Class<?> exceptionClass, Object[] arguments, String messagePattern ) {
      this.exceptionClass = exceptionClass;
      this.arguments = arguments;
      this.messagePattern = messagePattern;
   }

   public static ExceptionHelper defineMessage(  Class<?> exceptionClass, Object[] arguments, String defaultMessagePattern ) {
      String formatPattern = retrieveMessagePattern( exceptionClass, defaultMessagePattern);
      return new ExceptionHelper( exceptionClass, arguments, formatPattern );
   }

   private static String retrieveMessagePattern( Class<?> exceptionClass, String defaultMessagePattern ) {
      ProcessPuzzleContext context = null;
      String pattern = null;
      
      UserRequestManager requestManager = UserRequestManager.getInstance();
      if( requestManager != null ) context = requestManager.getApplicationContext();         
      
      if( context != null && context.isConfigured() )
         pattern = context.getText( exceptionClass.getName() );

      if( pattern == null || pattern.equalsIgnoreCase( ResourceNotFoundException.class.getName() ))
         return defaultMessagePattern;
      else return pattern;
   }

   public Object[] getArguments() { return arguments;}
   public Class<?> getExceptionClass() { return exceptionClass; }
   public String getMessagePattern() { return messagePattern; }
}
