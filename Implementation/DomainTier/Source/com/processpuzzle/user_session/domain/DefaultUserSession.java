package com.processpuzzle.user_session.domain;

import java.io.Serializable;
import java.util.*;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.User;

/**
 * Simple HashMap based implementation of ApplicationSession. The implementation is not synchronized as it is assumed that the same user will never
 * access the session concurrently.
 * 
 * @author kkj
 * @since 17-01-2005
 * @version $revision: $
 */
public class DefaultUserSession implements UserSession {
   private static final long serialVersionUID = 5450881748039100989L;
   private Map<Serializable, Serializable> sessionState;
   private Date created;
   private User user;
   private Application application;

   public DefaultUserSession( User user, Application application ) {
      sessionState = new HashMap<Serializable, Serializable>();
      created = new Date();
      this.user = user;
      this.application = application;
   }

   public void setAttribute( Serializable key, Serializable value ) {
      sessionState.put(key, value);
   }

   public Serializable getAttribute(Serializable key) {
      return (Serializable) sessionState.get(key);
   }

   public List keySet() {
      return Arrays.asList(sessionState.keySet().toArray());
   }

   public String toString() {
      return "[DefaultApplicationSession created = " + created + " state = " + sessionState.toString() + "]";
   }
   
   public User getUser() { return user; }
   public void setUser( User user ) { this.user = user; }
   
   public Application getApplication() { return application; }
}
