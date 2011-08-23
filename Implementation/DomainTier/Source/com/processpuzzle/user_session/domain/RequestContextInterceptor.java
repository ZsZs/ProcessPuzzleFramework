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
