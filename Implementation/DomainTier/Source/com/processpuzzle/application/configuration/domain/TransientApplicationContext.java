package com.processpuzzle.application.configuration.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.domain.Application.Action;

public abstract class TransientApplicationContext implements ApplicationContext {
   protected static Logger log = LoggerFactory.getLogger( TransientApplicationContext.class );
   protected Application application;
   protected boolean isConfigured = false;

   public TransientApplicationContext( Application application ) {
      this.application = application;
   }
   
   public void setUp( Action applicationAction ) {
      log.debug( "Setting up '" + this.getClass().getSimpleName() + "' started. Application action: " + applicationAction.toString() );
      log.debug( "Starting to set up transient components." );
      setUpTransientComponents();
      isConfigured = true;
      log.debug( "Settgin up transient components finished." );
   }

   public void tearDown(Action applicationAction) {
      log.debug( "Tearing down '" + this.getClass().getSimpleName() + "' started. Application action: "  + applicationAction.toString() );
      tearDownTransientComponents();
      isConfigured = false;
      log.debug( "Tearing down transient components finished." );
   }
   
   //Properties
   public Application getApplication() { return application; }
   public boolean isConfigured() { return isConfigured; }
   
   //Protected, private helper methods
   protected abstract void setUpTransientComponents();
   
   protected abstract void tearDownTransientComponents();
}
