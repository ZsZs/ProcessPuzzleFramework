package com.processpuzzle.application.configuration.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.processpuzzle.application.domain.Application;

public abstract class PersistentApplicationContext implements ApplicationContext {
   protected static Logger logger = LoggerFactory.getLogger( PersistentApplicationContext.class );
   protected Application application;
   protected Application.Action applicationAction;
   private boolean isConfigured = false;
   
   public PersistentApplicationContext( Application application ) {
      this.application = application;
   }
   
   //public mutator methods
   public void setUp( Application.Action applicationAction ) {
      logger.debug( "setUp() with action: " + applicationAction.name() + " - started." );
      this.applicationAction = applicationAction;
      setUpTransientComponents();
      
      if( applicationAction == Application.Action.install ) {
         setUpPersistentComponents();
         logger.debug( "Persistent components are installed." );
      }
      
      isConfigured = true;
      logger.debug( "setUp() with action: " + applicationAction.name() + " - finished." );
   }
   
   public void tearDown( Application.Action applicationAction ) {
      logger.debug( "tearDown() with action: " + applicationAction.name() + " - started." );
      tearDownTransientComponents();
      
      if( applicationAction == Application.Action.uninstall ) {
         tearDownPersistentComponents();
         logger.debug( "Persistent components are uninstalled.");
      }
      
      isConfigured = false;
      logger.debug( "tearDown() with action: " + applicationAction.name() + " - finished." );
   }
   
   //public properties
   public Application getApplication() { return application; }
   public boolean isConfigured() { return isConfigured; }

   //protected, private helper methods
   protected abstract void setUpTransientComponents();
   protected abstract void setUpPersistentComponents();
   
   protected abstract void tearDownTransientComponents();
   protected abstract void tearDownPersistentComponents();
}
