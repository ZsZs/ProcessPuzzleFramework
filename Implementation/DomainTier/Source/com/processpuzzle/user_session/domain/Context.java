/*
Name: 
    - Context 

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
