/*
Name: 
    - PersistentApplicationContext 

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
