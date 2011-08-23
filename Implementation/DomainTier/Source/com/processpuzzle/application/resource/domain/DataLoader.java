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
