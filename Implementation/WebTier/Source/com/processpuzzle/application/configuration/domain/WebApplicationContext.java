/*
 * Created on Feb 12, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.configuration.domain;

import org.springframework.core.io.ResourceLoader;

import com.processpuzzle.application.domain.Application;

public class WebApplicationContext extends ProcessPuzzleContext {

   public WebApplicationContext(  Application application, String descriptorPath, ResourceLoader resourceLoader ) {
      super( application, descriptorPath );
      WebApplicationContext.resourceLoader = resourceLoader;
   }
   
   public void setUp( Application.Action applicationAction ) throws ConfigurationSetUpException {
      super.setUp( applicationAction );
   }

   public static ProcessPuzzleContext getInstance() {
      if( soleInstance != null ) return soleInstance;
      else throw new UninitializedApplicationContextException( ProcessPuzzleContext.class );
   }
}
