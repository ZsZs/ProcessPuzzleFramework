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
