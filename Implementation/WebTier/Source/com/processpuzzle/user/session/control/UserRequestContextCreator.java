package com.processpuzzle.user.session.control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.user_session.domain.UserRequestContext;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.user_session.domain.UserSession;
import com.processpuzzle.user_session.domain.UserSessionHolder;

/**
 * A Servlet Filter for ensuring the presence of a requestcontext, with associated application session
 * 
 * @author kkj
 * @since 27-01-2005 13:01:23
 */
public class UserRequestContextCreator implements Filter {
   private static final Logger log = LoggerFactory.getLogger( UserRequestContextCreator.class );
   private Application application;
   private UserRequestManager requestManager;
   private UserRequestContext requestContext;
   private UserSession userSession;
   private UserSessionHolder sessionHolder;

   public UserRequestContextCreator() {
   }

   public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException {
      log.debug( "UserRequestContextCreator.doFilter()" );

      HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

      userSession = wireUserSessionToTheCurrentRequest( httpRequest, application );
      requestContext = createNewUserRequestContext( userSession );
      try{
         filterChain.doFilter( servletRequest, servletResponse );
      }finally{
         requestManager.desctroyRequestContext( requestContext );
      }
   }

   public void init( FilterConfig filterConfig ) throws ServletException {
      requestManager = UserRequestManager.getInstance();
      application = requestManager.getApplicationInContext();
      //application = (Application) filterConfig.getServletContext().getAttribute( ApplicationLifecycleListener.APPLICATION_OBJECT );
   }

   public void destroy() {}

   //Properties
   public Application getApplication() { return application; }

   public UserRequestManager getRequestManager() { return requestManager; }
   
   public UserSession getUserSession() { return userSession; }

   // Private helper methods
   private UserRequestContext createNewUserRequestContext( UserSession userSession ) {
      return requestManager.createRequestContext( sessionHolder );
   }
   
   private User determineUser() {
      UserSession currentSession = sessionHolder.getSession(); 
      
      if( currentSession != null && currentSession.getUser() != null ) return currentSession.getUser();
      else {
         UserRepository userRepository = application.getContext().getRepository( UserRepository.class );
         User currentUser = userRepository.findAnonymousUser();
         return currentUser;         
      }
   }

   private UserSession wireUserSessionToTheCurrentRequest( HttpServletRequest httpServletRequest, Application application ) {
      sessionHolder = new HttpSessionUserSessionHolder( httpServletRequest );
      UserSession currentSession = sessionHolder.getSession(); 
      if( currentSession == null ) {
         User user = determineUser();
         return UserRequestManager.getInstance().createSession( sessionHolder, user, application );
      }else return currentSession;
   }
}
