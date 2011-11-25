/*
Name: 
    - DefaultUserRequestContextFactory

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory, which can create new RequestContext objects using the create method, which can get the currently executing
 * request context using create, and which can remove the current request context. The factory is implemented around a
 * stack of RequestContext objects, pinned to the current thread. This is done to ensure that inter-component calls
 * don't result in missing contexts, when a call returns (b.c. the request context will be deleted using the Context
 * aspect)
 * 
 * @author kkj
 */
public class DefaultUserRequestContextFactory implements UserRequestContextFactory {
   private ThreadLocal<UserRequestContext> contextThreadLocal = new ThreadLocal<UserRequestContext>();
   private Logger log = LoggerFactory.getLogger( DefaultUserRequestContextFactory.class );

   public UserRequestContext create( UserSession userSession ) {
      DefaultUserRequestContext requestContext = new DefaultUserRequestContext();
      requestContext.pushId( createGUID() );
      requestContext.setUserSession( userSession );
      setRequestContext( requestContext );
      return requestContext;
   }

   public UserRequestContext create( UserRequestContext requestContext ) {
      DefaultUserRequestContext defaultRequestContext = (DefaultUserRequestContext) requestContext;
      defaultRequestContext.pushId( createGUID() );
      setRequestContext( defaultRequestContext );
      return defaultRequestContext;
   }

   public void delete() {
      DefaultUserRequestContext defaultRequestContext = (DefaultUserRequestContext) getRequestContext();
      if( defaultRequestContext != null ){
         if( defaultRequestContext.getIds().length > 1 ){
            defaultRequestContext.popId();
         }else{
            contextThreadLocal.set( null );
         }
      }else{
         log.warn( "delete() called, but no request context had been previously created." );
      }
   }

   public UserRequestContext getRequestContext() {
      return (UserRequestContext) contextThreadLocal.get();
   }

   protected void setRequestContext( UserRequestContext requestContext ) {
      contextThreadLocal.set( requestContext );
   }

   private String createGUID() {
      return new java.rmi.server.UID().toString();
   }
}
