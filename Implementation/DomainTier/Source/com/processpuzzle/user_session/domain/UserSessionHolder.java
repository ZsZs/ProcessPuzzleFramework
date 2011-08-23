package com.processpuzzle.user_session.domain;

public interface UserSessionHolder {
   public void setSession( UserSession userSession );
   public UserSession getSession();
}
