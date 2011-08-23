package com.processpuzzle.user_session.domain;

import com.processpuzzle.application.configuration.domain.ApplicationContext;
import com.processpuzzle.application.security.domain.User;

public interface UserSessionManager extends ApplicationContext {
   public UserRequestContextFactory getUserRequestContextFactory();
   public UserRequestContext createNewUserRequestContext( UserSession userSession );
   public void deleteCurrentUserRequestContext();
   public UserRequestContext getCurrentUserRequestContext();
   public User getCurrentUser();
}
