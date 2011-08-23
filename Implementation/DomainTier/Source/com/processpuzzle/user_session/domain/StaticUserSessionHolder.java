package com.processpuzzle.user_session.domain;

import com.processpuzzle.application.security.domain.User;

/**
 * @author kkj
 * @version $Revision: 1.1.1.1 $
 * @since 17-01-2005 14:39:55
 */
public class StaticUserSessionHolder implements UserSessionHolder {
   private static UserSession userSession;

   public StaticUserSessionHolder() {}
   
   public StaticUserSessionHolder( UserSession userSession ) {
      StaticUserSessionHolder.userSession = userSession;
   }

   public UserSession getSession() { return userSession; }

   public void setSession( UserSession userSession ) { StaticUserSessionHolder.userSession = userSession; }

   public User getUser() { return userSession.getUser(); }
}
