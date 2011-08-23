package com.processpuzzle.application.configuration.control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.processpuzzle.application.control.control.CommandControllerErrorHelper;
import com.processpuzzle.application.domain.ApplicationException;

public class ApplicationErrorFilter implements Filter {
   public static final String EXCEPTION_ATTRIBUTE = "ApplicationException"; 

   public void init(FilterConfig arg0) throws ServletException {
     //Do nothing   
   }

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      HttpSession httpSession = (HttpSession) httpRequest.getSession();
      ServletContext servletContext = httpSession.getServletContext(); 
      
      // ApplicationLifecycleListener only checks if application setup was unsuccessfull.
      ApplicationException applicationException = (ApplicationException) servletContext.getAttribute( ApplicationLifecycleListener.STARTUP_FAILURE_ATTRIBUTE );
      if( applicationException != null ) {
         CommandControllerErrorHelper errorHelper = new CommandControllerErrorHelper( "Application Starup Exception", applicationException.getMessage(), applicationException );
         request.setAttribute( EXCEPTION_ATTRIBUTE, errorHelper );
         RequestDispatcher dispatcher = request.getRequestDispatcher( ApplicationLifecycleListener.STARUP_FAILURE_PAGE );
         dispatcher.forward( request , response );
      } else chain.doFilter( request, response );
   }

   public void destroy() {
      //Do nothing      
   }
}
