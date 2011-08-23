package com.processpuzzle.user_session.domain;
//package com.itcodex.objectpuzzle.framework.application_configuration.domain.request;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.itcodex.objectpuzzle.framework.application_configuration.domain.config.ConfigHelper;
//import com.itcodex.objectpuzzle.framework.application_configuration.domain.session.ApplicationSession;
//import com.itcodex.objectpuzzle.framework.application_configuration.domain.session.StaticApplicationSessionHolder;
//
///**
// * Add RequestContext to the executing thread and remove it again, when the call returns on
// * swing actions. RequestContextFactory can be configured via Spring bean name "RequestContextFactory"
// *
// * @author kkj
// * @version $Revision: $
// * @since 06-02-2005 15:53:05
// */
//public class Context implements AroundAdvice {
//
//    private RequestContextFactory requestContextFactory;
//
//    public Context() {
//        requestContextFactory = ConfigHelper.getRequestContextFactory();
//    }
//
//    public Object invoke(JoinPoint joinPoint) throws Throwable {
//
//        System.out.println("Before joinpoint");
//
//        Object result = null;
//
//        Log log = LogFactory.getLog(joinPoint.getEnclosingStaticJoinPoint().getClass().getName());
//        if (log.isDebugEnabled()) {
//            LogFactory.getLog(joinPoint.getEnclosingStaticJoinPoint().getClass().getName()).debug("Executing swing context around aspect!!");
//        }
//        try {
//
//            ApplicationSession applicationSession = StaticApplicationSessionHolder.getApplicationSession();
//            RequestContext requestContext = requestContextFactory.create();
//            requestContext.setApplicationSession(applicationSession);
//
//            if (log.isDebugEnabled()) {
//                log.debug("Current request context: " + requestContextFactory.getRequestContext());
//            }
//
//            result = joinPoint.proceed();
//
//        } finally {
//            requestContextFactory.delete();
//        }
//
//        System.out.println("After joinpoint");
//
//        return result;
//    }
//}
