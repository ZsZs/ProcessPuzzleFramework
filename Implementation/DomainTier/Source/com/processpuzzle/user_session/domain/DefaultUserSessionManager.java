/*
Name: 
    - DefaultUserSessionManager

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
