/*
Name: 
    - DefaultPersistenceStrategy 

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

package com.processpuzzle.persistence.domain;


import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.processpuzzle.commons.persistence.PersistenceStrategy;
import com.processpuzzle.commons.persistence.PersistentObject;
import com.processpuzzle.commons.persistence.RepositoryEventHandler;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.commons.persistence.UnitOfWork;
import com.processpuzzle.commons.persistence.query.Query;

public class DefaultPersistenceStrategy implements PersistenceStrategy {
   private static Logger log = LoggerFactory.getLogger( DefaultPersistenceStrategy.class );
   private List<RepositoryEventHandler> eventHandlers = null;
   private String name = null;
   private boolean isConfigured = false;
   private DefaultPersistenceProvider driverPersistenceProvider = null;

   public DefaultPersistenceStrategy( String name, List<RepositoryEventHandler> eventHandlers ) {
      this.name = name;
      this.eventHandlers = eventHandlers;
   }

   // Public mutators. Unfortunatelly this has to be public.
   public boolean configure() {
      log.info( "PersistenceStrategy.configure() - started: " + name );
      for( Iterator<RepositoryEventHandler> iter = eventHandlers.iterator(); iter.hasNext(); ){
         DefaultRepositoryEventHandler eventHandler = (DefaultRepositoryEventHandler) iter.next();
         if( !eventHandler.configure() )
            return false;

         checkIfMultiplePersistenceProvider( eventHandler );
         determineIfItIsDriverPersistenceProvider( eventHandler );
      }
      isConfigured = true;

      checkIfUndefinedPersistenceProvider();

      log.info( "PersistenceStrategy.configure() - finished: " + name );
      return isConfigured;
   }

   public void release() {
      for( Iterator<RepositoryEventHandler> iter = eventHandlers.iterator(); iter.hasNext(); ){
         RepositoryEventHandler eventHandler = iter.next();
         eventHandler.release();
      }
      eventHandlers.clear();
      isConfigured = false;
      driverPersistenceProvider = null;
   }

   // Properties
   public String getName() {
      return name;
   }

   public List<RepositoryEventHandler> getEventHandlers() {
      return eventHandlers;
   }

   public boolean isConfigured() {
      return isConfigured;
   }

   public DefaultPersistenceProvider getDriverPersistenceProvider() {
      return driverPersistenceProvider;
   }

   public RepositoryEventHandler getEventHandler( String eventHandlerName ) {
      for( Iterator<RepositoryEventHandler> iter = eventHandlers.iterator(); iter.hasNext(); ){
         RepositoryEventHandler eventHandler = iter.next();
         if( eventHandler.getName().equalsIgnoreCase( eventHandlerName ) )
            return eventHandler;
      }

      return null;
   }

   // Package accessors
   @Override public <P extends PersistentObject> P findById( UnitOfWork work, Class<P> entityClass, Integer id ) {
      P persistentObjectToReturn = null;

      for( Iterator<RepositoryEventHandler> iter = eventHandlers.iterator(); iter.hasNext(); ){
         RepositoryEventHandler eventHandler = iter.next();
         P persistentObject = eventHandler.findById( work, entityClass, id );
         if( eventHandler.equals( driverPersistenceProvider ) )
            persistentObjectToReturn = persistentObject;
      }

      return persistentObjectToReturn;
   }

   @Override public <P extends PersistentObject> RepositoryResultSet<P> findAll( UnitOfWork work, Class<P> entityClass ) {
      RepositoryResultSet<P> resultSetToReturn = null;

      for( Iterator<RepositoryEventHandler> iter = eventHandlers.iterator(); iter.hasNext(); ){
         RepositoryEventHandler eventHandler = (DefaultRepositoryEventHandler) iter.next();
         RepositoryResultSet<P> resultSet = eventHandler.findAll( work, entityClass );
         if( eventHandler.equals( driverPersistenceProvider ) )
            resultSetToReturn = resultSet;
      }

      return resultSetToReturn;
   }

   @Override public RepositoryResultSet<? extends PersistentObject> findByQuery( UnitOfWork work, Query query ) {
      RepositoryResultSet<? extends PersistentObject> resultSet = null;

      for( Iterator<RepositoryEventHandler> iter = eventHandlers.iterator(); iter.hasNext(); ){
         RepositoryEventHandler eventHandler = iter.next();
         RepositoryResultSet<? extends PersistentObject> temporalResultSet = eventHandler.findByQuery( work, query );
         if( eventHandler.equals( driverPersistenceProvider ) )
            resultSet = temporalResultSet;
      }

      return resultSet;
   }

   // Package accessors
   @Override public Integer add( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject object ) {
      Integer persistedId = null;

      for( Iterator<RepositoryEventHandler> iter = eventHandlers.iterator(); iter.hasNext(); ){
         RepositoryEventHandler eventHandler = (DefaultRepositoryEventHandler) iter.next();
         Integer id = eventHandler.add( work, entityClass, object );
         if( eventHandler.equals( driverPersistenceProvider ) )
            persistedId = id;
      }

      return persistedId;
   }

   @Override public void update( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject object ) {
      for( Iterator<RepositoryEventHandler> iter = eventHandlers.iterator(); iter.hasNext(); ){
         RepositoryEventHandler eventHandler = (DefaultRepositoryEventHandler) iter.next();
         eventHandler.update( work, entityClass, object );
      }
   }

   @Override public void delete( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject object ) {
      for( Iterator<RepositoryEventHandler> iter = eventHandlers.iterator(); iter.hasNext(); ){
         RepositoryEventHandler eventHandler = (DefaultRepositoryEventHandler) iter.next();
         eventHandler.delete( work, entityClass, object );
      }
   }

   private void determineIfItIsDriverPersistenceProvider( RepositoryEventHandler eventHandler ) {
      if( driverPersistenceProvider == null && eventHandler instanceof DefaultPersistenceProvider ){
         driverPersistenceProvider = (DefaultPersistenceProvider) eventHandler;
      }
   }

   private void checkIfMultiplePersistenceProvider( RepositoryEventHandler eventHandler ) {
      if( driverPersistenceProvider != null && eventHandler instanceof DefaultPersistenceProvider )
         throw new MultiplePersistenceProviderException( name );
   }

   private void checkIfUndefinedPersistenceProvider() {
      if( driverPersistenceProvider == null )
         throw new UndefinedPersistenceProviderException( name );
   }
}
