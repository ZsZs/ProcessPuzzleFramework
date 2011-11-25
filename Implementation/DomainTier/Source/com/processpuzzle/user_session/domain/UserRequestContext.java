/*
Name: 
    - UserRequestContext

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

import com.processpuzzle.application.domain.Application;


/**
 * Implemented by classes that provide an application wide request context.
 * Each request context has its own ID, which is unique per request. The ID can hence
 * be used for tracking specific user requests by correlating it with user name.
 * The request context references a SecurityContext, which holds information about
 * the current user if logged on, null otherwise.
 *
 * Note: As request contexts can be serialized it is imperative that any referenced
 * objects are also serializable and that the overall size is kept at a minimum.
 *
 * @author kkj, jsb
 * @since 20-7-2004
 * @version $revision: $
 */
public interface UserRequestContext extends Serializable {

    /**
     * Get the current request id
     * @return Request ID
     */
    public String getId();

    /**
     * Get the complete stack of request ids, reflecting the call chain
     * through which this context has passed.
     * @return The current list of request contexts
     */
    public String[] getIds();

    /**
     * @return The current application session if one exists, otherwise a new one.
     */
    public UserSession getUserSession();

    /**
     * Set the current application session
     * @param applicationSession The session to set
     */
    public void setUserSession( UserSession applicationSession );

   public Application getApplication();
}