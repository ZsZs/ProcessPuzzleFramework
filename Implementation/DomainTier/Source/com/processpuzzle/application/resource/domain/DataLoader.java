/*
Name: 
    - DataLoader

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

/*
 * Created on Feb 3, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.resource.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.io.ResourceLoader;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.user_session.domain.UserRequestManager;

public abstract class DataLoader {
   protected ProcessPuzzleContext applicationContext;
   protected boolean resultInPersistentObjects = false;
   protected boolean isLoaded = false;
   protected Logger log = LoggerFactory.getLogger( DataLoader.class );
   protected DataLoader next;
   protected ResourceLoader resourceLoader;
   
//Constructors
   public DataLoader( ) {}

   public void loadData() {
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      
      if( next != null ) next.loadData();
      Logger logger = LoggerFactory.getLogger( this.getClass() );
      logger.info( "Starting to load data.");
      isLoaded = true;
   }

//Properties
   public DataLoader getNext() { return this.next;}
   public void setNext(DataLoader next) { this.next = next; }
   public boolean hasNext() { return next != null ? true : false; }
   public ResourceLoader getResourceLoader() {return resourceLoader;}
   public void setResourceLoader( ResourceLoader resourceLoader ) {this.resourceLoader = resourceLoader;}
   public boolean isResultInPersistentObjects() { return resultInPersistentObjects; }

   public boolean isLoaded() { return isLoaded; }
}
