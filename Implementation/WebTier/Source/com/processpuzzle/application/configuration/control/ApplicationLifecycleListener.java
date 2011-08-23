package com.processpuzzle.application.configuration.control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.context.support.ServletContextResourceLoader;

import com.processpuzzle.application.domain.ApplicationException;
import com.processpuzzle.application.domain.ApplicationUninstallationException;
import com.processpuzzle.application.domain.ProcessPuzzleWebApplication;
import com.processpuzzle.application.domain.WebApplication;
import com.processpuzzle.application.domain.WebApplicationManager;
import com.processpuzzle.persistence.domain.EntityInstantiationException;

public class ApplicationLifecycleListener implements ServletContextListener, Filter {
   public static final String APPLICATION_STORAGE_PATH = "/Configuration/ApplicationRepository.xml";
   public static final String CONFIGURATION_DESCRIPTOR_PATH = "/Configuration/configuration_descriptor.xml";
   public static final String STARTUP_FAILURE_ATTRIBUTE = "ApplicationStartUpFailure";
   public static final String TEARDOWN_FAILURE_ATTRIBUTE = "ApplicationTearDownFailure";
   public static final String STARUP_FAILURE_PAGE = "/Commons/ApplicationAdministration/ApplicationStartupError.jsp";
   public static final String APPLICATION_OBJECT = "ApplicationObject";
   public static final Class<? extends WebApplication> applicationClass = ProcessPuzzleWebApplication.class;
   protected static Logger log = LoggerFactory.getLogger( ApplicationLifecycleListener.class );
   private WebApplicationManager applicationManager;
   private WebApplication application;

   public synchronized void contextInitialized( ServletContextEvent contextEvent ) {
      log.info( "ApplicationLifecycListener.contextInitialized() - start" );
      ServletContext servletContext = contextEvent.getServletContext();

      try{
         applicationManager = new WebApplicationManager( APPLICATION_STORAGE_PATH, new ServletContextResourceLoader( servletContext ), servletContext );
         application = (WebApplication) applicationManager.installWebApplication( applicationClass.getSimpleName(), applicationClass, CONFIGURATION_DESCRIPTOR_PATH );
         servletContext.setAttribute( APPLICATION_OBJECT, application );
      } catch( InstantiationException e ){
         log.error( "Applicaton start failed.", e );
         saveStartUpExceptionInContext( contextEvent.getServletContext(), e );
      } catch( EntityInstantiationException e ) {
         log.error( "Applicaton start failed.", e );
         saveStartUpExceptionInContext( contextEvent.getServletContext(), e );
      } catch( ApplicationException e ){
         log.error( "Applicaton start failed.", e );
         saveStartUpExceptionInContext( contextEvent.getServletContext(), e );
      }
      
      log.info( "ApplicationLifecycListener.contextInitialized() - end" );
   }

   public void contextDestroyed( ServletContextEvent contextEvent ) {
      try{
         applicationManager.unInstall( application );
      }catch( ApplicationUninstallationException e ){
         log.error( "Applicaton uninstall failed.", e );
         saveTearDownExceptionInContext( contextEvent.getServletContext(), e );
      }
   }

   public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      HttpSession httpSession = (HttpSession) httpRequest.getSession();
      ServletContext servletContext = httpSession.getServletContext();

      // ApplicationLifecycleListener only checks if application setup was unsuccessfull.
      ApplicationException applicationException = (ApplicationException) servletContext.getAttribute( ApplicationLifecycleListener.STARTUP_FAILURE_ATTRIBUTE );
      if( applicationException != null ){
         request.setAttribute( "ApplicationException", applicationException );
         RequestDispatcher dispatcher = request.getRequestDispatcher( ApplicationLifecycleListener.STARUP_FAILURE_PAGE );
         dispatcher.forward( request, response );
      }else
         chain.doFilter( request, response );
   }

   public void init( FilterConfig arg0 ) throws ServletException {
   // Do nothing
   }

   public void destroy() {
      if( application != null && applicationManager != null ) applicationManager.stop( application );
   }

   // Private helper methods
   private void saveStartUpExceptionInContext( ServletContext servletContext, Exception e ) {
      servletContext.setAttribute( STARTUP_FAILURE_ATTRIBUTE, e );
   }

   private void saveTearDownExceptionInContext( ServletContext servletContext, Exception e ) {
      servletContext.setAttribute( TEARDOWN_FAILURE_ATTRIBUTE, e );
   }
}