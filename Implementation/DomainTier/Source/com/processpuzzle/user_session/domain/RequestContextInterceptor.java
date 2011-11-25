/*
Name: 
    - RequestContextInterceptor

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

import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;


/**
 * This interface will be implemented by each Service class.
 * <p/>
 * It effectively provides for the server-side skeleton interceptor.
 * <p/>
 * It is not a remote interface for potential support of
 * local-method invocation interceptor.
 *
 * @author Wenbo Zhu (rmicontext.interceptor.ServiceInterceptorInterface)
 * @author kkj
 * @version 1.0
 */
public interface RequestContextInterceptor {

    /**
     * The interceptor method that decodes the incoming message on the Service side.
     *
     * @param methodName     The method name
     * @param arguments      The arguments
     * @param argumentTypes  The argument class names to be used to identify an implementation Method
     * @param requestContext The context to be set
     * @return The return value of the method invocation
     * @throws java.rmi.RemoteException if any RMI error
     * @throws java.lang.reflect.InvocationTargetException
     *                                  that wrapps the cause exception of the invocation
     */
    Object exec(String methodName, Object[] arguments, String[] argumentTypes, UserRequestContext requestContext)
            throws RemoteException, InvocationTargetException;
}
