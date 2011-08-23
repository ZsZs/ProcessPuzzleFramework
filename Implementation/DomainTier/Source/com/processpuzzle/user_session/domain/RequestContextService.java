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
