package com.processpuzzle.user_session.domain;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;


/**
 * Invocation handler for dynamic proxy used on the client side. The proxy
 * will implement the remote EJB interface and call exec(..., RequestContext)
 * instead of the actual EJB method. The implementation of exec(...) in the
 * EJB is the responsible for restoring the RequestContext to a ThreadLocal
 * and invoke the actual business method using reflection.
 *
 * @author kkj
 * @version $Revision: 1.1.1.1 $
 * @since 22-01-2005 23:37:18
 */
public class RequestContextPropagationInterceptor implements InvocationHandler {

    private RequestContextInterceptor interceptor;

    public RequestContextPropagationInterceptor(RequestContextInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("toString".equals(method.getName())) {
            return getClass() + ":" + interceptor.toString();
        }

        Object result;

        try {
            UserRequestContextFactory requestContextFactory = ConfigHelper.getRequestContextFactory();
            UserRequestContext requestContext = requestContextFactory.getRequestContext();
            if (args == null || args.length == 0) {
                result = interceptor.exec(method.getName(), args, new String[]{}, requestContext);
            } else {
                String[] argTypes = RequestContextUtil.getNames(method.getParameterTypes());
                result = interceptor.exec(method.getName(), args, argTypes, requestContext);
            }

            return result;   // null if void
        } catch (RemoteException ex) {
            throw ex;
        } catch (InvocationTargetException ex) {
            throw ex.getCause();
        }
    }

}
