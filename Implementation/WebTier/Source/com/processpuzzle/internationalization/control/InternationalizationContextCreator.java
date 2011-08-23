package com.processpuzzle.internationalization.control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.internalization.control.InternationalizationHelper;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.user_session.domain.UserSession;
import com.processpuzzle.user_session.domain.UserSessionKeys;

public class InternationalizationContextCreator implements Filter {
   public static final String I18HELPER_KEY = "i18Helper";
   private static final Logger log = LoggerFactory.getLogger( InternationalizationContextCreator.class );
   private ProcessPuzzleContext applicationContext;

   public void init(FilterConfig arg0) throws ServletException {
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
   }

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
      log.debug("InternationalizationContextCreator.doFilter()");
      
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      HttpSession httpSession = (HttpSession) httpRequest.getSession();
      
      UserSession userSession = UserRequestManager.getInstance().currentUserSession();
      InternationalizationHelper i18Helper = retrieveI18HelperFromHttpSession( httpSession );      
      configureI18HelperWithUserSession( i18Helper, userSession );
      httpRequest.setAttribute( I18HELPER_KEY, i18Helper );
      filterChain.doFilter( request, response );
   }

   public void destroy() {}
   
   //Private helper methods
   private InternationalizationHelper retrieveI18HelperFromHttpSession( HttpSession httpSession ){
      InternationalizationHelper i18Helper = (InternationalizationHelper) httpSession.getAttribute( I18HELPER_KEY );
      if( i18Helper == null ) {
         i18Helper = new InternationalizationHelper();
         httpSession.setAttribute( I18HELPER_KEY, i18Helper );
      }
      return i18Helper;
   }
      
   private void configureI18HelperWithUserSession( InternationalizationHelper i18Helper, UserSession userSession ) {
      String preferredLocaleSpecifier = (String) userSession.getAttribute( UserSessionKeys.localeSpecifier );
      if( preferredLocaleSpecifier != null ) i18Helper.setLocale( preferredLocaleSpecifier );
      else i18Helper.setLocale( applicationContext.getDefaultLocale() );
   }   
}
