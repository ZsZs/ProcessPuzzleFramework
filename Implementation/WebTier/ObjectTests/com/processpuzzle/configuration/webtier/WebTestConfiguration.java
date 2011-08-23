/*
 * Created on Jun 26, 2006
 */
package com.processpuzzle.configuration.webtier;

import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;

import com.processpuzzle.application.configuration.domain.ApplicationContextFactory;
import com.processpuzzle.application.configuration.domain.ConfigurationSetUpException;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;

/**
 * @author zsolt.zsuffa
 */
public class WebTestConfiguration {
   static ProcessPuzzleContext config = null;
   @Mock private static Application mockApplication;
   
   static {
      MockitoAnnotations.initMocks( WebTestConfiguration.class );
      config = ApplicationContextFactory.create( mockApplication, ConfigurationConstants.CONFIGURATION_PROPERTY_FILE );
      try {
         config.setUp( Application.Action.start );
      } catch (ConfigurationSetUpException e) {
         e.printStackTrace();
      }      
   }
   
   public static ProcessPuzzleContext getConfiguration () {
      return config;
   }
}