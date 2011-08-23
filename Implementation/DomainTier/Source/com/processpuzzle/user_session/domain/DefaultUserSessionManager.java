package com.processpuzzle.user_session.domain;

import com.processpuzzle.application.configuration.domain.TransientApplicationContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.User;

public class DefaultUserSessionManager extends TransientApplicationContext implements UserSessionManager {
   private UserRequestContextFactory userRequestContextFactory;
   
   DefaultUserSessionManager( Application application, UserRequestContextFactory factory ) {
      super( application );
      this.userRequestContextFactory = factory;
   }

   public UserRequestContext createNewUserRequestContext( UserSession userSession ) {
      return userRequestContextFactory.create( userSession );
   }

   public void deleteCurrentUserRequestContext() {
      userRequestContextFactory.delete();
   }

   public UserRequestContext getCurrentUserRequestContext() { 
      return userRequestContextFactory.getRequestContext(); 
   }

   public UserRequestContextFactory getUserRequestContextFactory() { return userRequestContextFactory; }
   
   public User getCurrentUser() { 
      return userRequestContextFactory.getRequestContext().getUserSession().getUser(); 
   }
   
   @Override protected void setUpTransientComponents() {
   }

   @Override protected void tearDownTransientComponents() {
   }
}
