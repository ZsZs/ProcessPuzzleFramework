package com.processpuzzle.application.security.control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.user.session.control.HttpSessionUserSessionHolder;
import com.processpuzzle.user_session.domain.UserRequestContext;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.user_session.domain.UserSession;
import com.processpuzzle.user_session.domain.UserSessionKeys;

public class AuthenticationFilter implements Filter {
   private FilterConfig config;
   public static final String USER_KEY = "userSession";

   public void init(FilterConfig filterConfig ) throws ServletException {
      config = filterConfig;
   }

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      HttpServletResponse httpResponse = (HttpServletResponse) response;
      boolean forward = true;
      
      authenticateUserWithSingleSignOn();
      authenticateUserWithCookie();
      
//      UserRequestContext requestContext = createNewRequestContext( (HttpServletRequest) request );
//      UserSession userSession = requestContext.getUserSession();
//      
//      if( checkIfUserIsAnonym( userSession ) ) {
//         String action = request.getParameter("action");
//         if (action != null && !action.equals("Login")) {
//            forward = false;
//            httpRequest.getRequestDispatcher("/FrontController/AuthenticationError.jsp").forward( httpRequest, httpResponse );
//         }         
//      }
      
      if( forward ) {
         chain.doFilter(httpRequest, httpResponse);
      }
   }

   public void destroy() {
      config = null;
   }

   public FilterConfig getConfig() {
      return config;
   } 
   
//Private helper methods
   private void authenticateUserWithSingleSignOn() {
      
   }

   private void authenticateUserWithCookie() {
      
   }
   
   @SuppressWarnings("unused")
   private UserRequestContext createNewRequestContext( HttpServletRequest httpServletRequest, Application application ){
      HttpSessionUserSessionHolder sessionHolder = new HttpSessionUserSessionHolder( httpServletRequest );
      UserRequestContext requestContext = UserRequestManager.getInstance().createRequestContext( sessionHolder );
      return requestContext;
   }
   
   @SuppressWarnings("unused")
   private boolean checkIfUserIsAnonym( UserSession userSession ) {
      if( userSession.getAttribute( UserSessionKeys.userName ) == null ) return true;
      else return false;
   }
}