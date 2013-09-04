/*
Name: 
    - DefaultRepositoryEventHandler 

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
 * Created on Dec 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.persistence.domain;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.HierarchicalConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.processpuzzle.application.configuration.domain.PersistentDataInitializationStrategies;
import com.processpuzzle.commons.persistence.PersistentObject;
import com.processpuzzle.commons.persistence.RepositoryEventHandler;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.commons.persistence.UnitOfWork;
import com.processpuzzle.commons.persistence.query.Query;

public abstract class DefaultRepositoryEventHandler implements RepositoryEventHandler {
   private static Logger log = LoggerFactory.getLogger( DefaultRepositoryEventHandler.class );
   protected String name = "";
   protected HierarchicalConfiguration configuration = null;
   protected List<Class<?>> persistentClasses = new ArrayList<Class<?>>();
   protected PersistentDataInitializationStrategies databaseCreationStrategy = null;
   protected boolean isConfigured = false;

   // Constructors
   public DefaultRepositoryEventHandler( String name, HierarchicalConfiguration configuration, List<Class<?>> persistentClasses, PersistentDataInitializationStrategies databaseCreationStrategy ) {
      this.name = name;
      this.configuration = configuration;
      this.persistentClasses = persistentClasses;
      this.databaseCreationStrategy = databaseCreationStrategy;
   }

   public boolean configure() {
      log.info( this.getClass().getSimpleName() + ".configure() - started: " );
      return isConfigured;
   }

   public void release() {
      configuration = null;
      persistentClasses.clear();
   }

   // Public accessors
   public abstract <P extends PersistentObject> P findById( UnitOfWork work, Class<P> entityClass, Integer id );
   public abstract <P extends PersistentObject> RepositoryResultSet<P> findAll( UnitOfWork work, Class<P> entityClass );
   public abstract RepositoryResultSet<? extends PersistentObject> findByQuery( UnitOfWork work, Query query );

   // Public mutators
   public abstract Integer add( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject entity );
   public abstract void update( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject entitiy );
   public abstract void delete( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject entity );

   // Properties
   public boolean isConfigured() {
      return isConfigured;
   }

   public HierarchicalConfiguration getConfiguration() {
      return configuration;
   }

   public String getName() {
      return name;
   }
}
