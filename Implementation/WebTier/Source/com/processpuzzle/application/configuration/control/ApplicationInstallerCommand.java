package com.processpuzzle.application.configuration.control;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.support.ServletContextResourceLoader;

import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.control.control.CommandInterface;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.domain.ApplicationManager;
import com.processpuzzle.application.domain.WebApplication;

public class ApplicationInstallerCommand implements CommandInterface {
   public static final String COMMAND_NAME = "ApplicationInstaller";
   private static final String APPLICATION_NAME_KEY = "application";
   private static final String APPLICATION_STORAGE_PATH = "/Configuration";
   private static final String CONFIGURATION_DESCRIPTOR_PATH = "/Configuration/configuration_descriptor.xml";
   private ServletContext servletContext;
   private Application application;

   public void init( CommandDispatcher dispatcher ) {
      this.servletContext = dispatcher.getServletContext();
   }

   public String execute( CommandDispatcher dispatcher ) throws Exception {
      String responsePage = "";
      String applicationName = determineApplicationName( dispatcher.getRequest() );
      ApplicationManager applicationInstaller = new ApplicationManager( APPLICATION_STORAGE_PATH, new ServletContextResourceLoader( servletContext ) );      
      
      application = applicationInstaller.install( applicationName, WebApplication.class, CONFIGURATION_DESCRIPTOR_PATH );
      return responsePage;
   }

   public String getName() { return this.getClass().getName(); }
   public Application getApplication() { return application; }

   //Protected, private helper methods
   private String determineApplicationName( HttpServletRequest request ) {
      Properties requestParameters = new ServletHelper().extractProperties( request );
      String applicationName = requestParameters.getProperty( APPLICATION_NAME_KEY );
      
      return applicationName;
   }   
}
