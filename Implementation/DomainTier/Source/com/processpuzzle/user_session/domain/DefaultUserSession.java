/*
Name: 
    - DefaultUserSession

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
