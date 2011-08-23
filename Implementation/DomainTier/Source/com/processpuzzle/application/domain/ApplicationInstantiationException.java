package com.processpuzzle.application.domain;

import java.text.MessageFormat;

public class ApplicationInstantiationException extends RuntimeException {
   private static final long serialVersionUID = -7123658907911484362L;
   private static final String message = "Application: ''{0}'' cannot be instantiated. Look at it\'s constructor. Be sure the application is not abstract. '{1}'";
   private Class<? extends Application> applicationClass;
   
   public ApplicationInstantiationException( Class<? extends Application> applicationClass, Throwable cause ) {
      this( applicationClass, "", cause );
   }

   public ApplicationInstantiationException( Class<? extends Application> applicationClass, String auxiliaryMessage, Throwable cause ) {
      super( MessageFormat.format( message, new Object[] {applicationClass.getName(), auxiliaryMessage} ), cause );
      this.applicationClass = applicationClass;
   }

   public Class<? extends Application> getApplicationClass() {
      return applicationClass;
   }
}
