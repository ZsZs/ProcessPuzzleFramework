/*
Name: 
    - UserSession

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
import java.util.List;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.User;

/**
 * A generic application-wide session, which can store arbitrary
 * key, value pairs - as long as they're serializable. It is a
 * requirement that the transitive closure of keys and values
 * added to an ApplicationSession are Serializable.
 *
 * @author kkj
 * @since 17-01-2005
 * @version $revision: $
 */
public interface UserSession extends Serializable {

    /**
     * Store a value in the session under the specified key
     * @param key The key to identify the value
     * @param value Some value
     */
    void setAttribute(Serializable key, Serializable value);

    /**
     * Get the named attribute from the session.
     * @param key The key of the value to get
     * @return The value or null if no such value exists
     */
    Serializable getAttribute(Serializable key);

    /**
     * @return The list of known keys in the session
     */
    List keySet();
    
    public User getUser();
    public Application getApplication();
}
