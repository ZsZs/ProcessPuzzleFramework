package com.processpuzzle.user_session.domain;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.User;

public interface UserSessionFactory {
   public UserSession create( User user, Application application );
}
