/*
Name: 
    - DefaultUserRequestContext

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

import java.util.EmptyStackException;
import java.util.Stack;

import org.apache.log4j.NDC;

import com.processpuzzle.application.domain.Application;

/**
 * Default implementation of a rampart request context. This implementation is audit friendly and will push and pop request ids onto Log4j NDC when
 * pushId and popId are called.
 * 
 * @author kkj
 * @version $Revision: 1.1.1.1 $
 * @since 26-09-2004 17:05:08
 */
public class DefaultUserRequestContext implements UserRequestContext {
   private static final long  serialVersionUID = 5685494565953921161L;
   Stack<String>              idStack;
   private UserSession userSession;

   public DefaultUserRequestContext() {
      idStack = new Stack<String>();
   }

   public String toString() {
      StringBuffer stringRep = new StringBuffer();
      stringRep.append(DefaultUserRequestContext.class.getName()).append("[ids = ").append(idStack).append("} applicationSession={").append(
            getUserSession()).append("}]");

      return stringRep.toString();
   }

   /**
    * Push a new request ID onto the stack of IDs. The ID will automatically be made available to Log4j via NDC.
    * 
    * @param id
    *           The next request ID
    */
   public void pushId(String id) {
      NDC.push(id);
      idStack.push(id);
   }

   /**
    * Pop the top level ID from the stack of IDs for this request. The ID will also be popped from the Log4j NDC.
    * 
    * @return The popped ID or null if no more IDs exist.
    */
   public String popId() {
      NDC.pop();
      try {
         return (String) idStack.pop();
      } catch (EmptyStackException e) {
         return null;
      }
   }

   public String getId() {
      try {
         return (String) idStack.peek();
      } catch (EmptyStackException e) {
         return null;
      }
   }

   public String[] getIds() {
      return (String[]) idStack.toArray(new String[idStack.size()]);
   }

   /**
    * Store an application session with this request
    * 
    * @param applicationSession
    */
   public void setUserSession(UserSession applicationSession) {
      this.userSession = applicationSession;
   }

   public UserSession getUserSession() { return userSession; }
   public Application getApplication() { return userSession.getApplication(); }
}
