/*
Name: 
    - TransientApplicationContext 

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
