/*
Name: 
    - RequestContextService

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

import java.rmi.RemoteException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for EJBs that support request contexts. Will restore the incoming
 * RequstContext to a ThreadLocal for access by the actual business method(s)
 * and delegate the call.
 * 
 * @author kkj
 * @version $Revision: 1.1.1.1 $
 * @since 22-01-2005 23:18:46
 */
public class RequestContextService implements RequestContextInterceptor {
	private Logger log = LoggerFactory.getLogger(RequestContextService.class);

	public Object exec(String methodName, Object[] arguments,
			String[] argumentTypes, UserRequestContext requestContext)
			throws RemoteException, InvocationTargetException {

		Class<? extends RequestContextService> serviceClass = getClass();

		if (log.isDebugEnabled()) {
			log.debug("exec(" + methodName + " on " + serviceClass
					+ " with requestContext = " + requestContext);
		}

		try {

			Class<?>[] argTypes = RequestContextUtil.forNames(argumentTypes);

			Method serviceMethod = serviceClass.getMethod(methodName, argTypes);

			UserRequestContextFactory requestContextFactory = ConfigHelper
					.getRequestContextFactory();
			requestContextFactory.create(requestContext);

			return serviceMethod.invoke(this, arguments);

		} catch (ClassNotFoundException ex) {
			processExecReflectionException(ex);
		} catch (NoSuchMethodException ex) {
			processExecReflectionException(ex);
		} catch (IllegalAccessException ex) {
			processExecReflectionException(ex);
		}

		return null; // javac
	}

	/**
	 * Process a reflection exception.
	 * 
	 * @throws InvocationTargetException
	 *             a wrapped exception
	 */
	private void processExecReflectionException(Exception ex)
			throws InvocationTargetException {

		// the cause exception has to be a runtime exception
		throw new InvocationTargetException(new IllegalArgumentException(
				"Interceptor Service.exec() failed: " + ex));
	}

}
