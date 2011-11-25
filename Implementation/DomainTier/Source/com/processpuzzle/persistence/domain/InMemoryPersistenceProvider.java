/*
Name: 
    - InMemoryPersistenceProvider 

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

import hu.itkodex.commons.persistence.PersistentObject;
import hu.itkodex.commons.persistence.RepositoryResultSet;
import hu.itkodex.commons.persistence.UnitOfWork;
import hu.itkodex.commons.persistence.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.configuration.HierarchicalConfiguration;

import com.processpuzzle.application.configuration.domain.PersistentDataInitializationStrategies;

public class InMemoryPersistenceProvider extends DefaultPersistenceProvider {
   protected Map<Class<? extends PersistentObject>, Map<Integer, PersistentObject>> inMemory = new HashMap<Class<? extends PersistentObject>, Map<Integer, PersistentObject>>();
   protected static int id;

   public InMemoryPersistenceProvider( String name, HierarchicalConfiguration configuration, List<Class<?>> persistentClasses,
         PersistentDataInitializationStrategies databaseCreationStrategy ) {
      super( name, configuration, persistentClasses, databaseCreationStrategy );
   }

   @Override public <P extends PersistentObject> P findById( UnitOfWork work, Class<P> entityClass, Integer id ) {
      return null;
   }

   @SuppressWarnings( "unchecked" )
   @Override public RepositoryResultSet<PersistentObject> findAll( UnitOfWork work, Class entityClass ) {
      List result = Collections.unmodifiableList( new ArrayList<Object>( getEnityMap( entityClass ).values() ) );
      return new SimpleResultSet( result );
   }

   @SuppressWarnings( "unchecked" )
   @Override public RepositoryResultSet<PersistentObject> findByQuery( UnitOfWork work, Query query ) {
      return new SimpleResultSet( find( query ) );
   }

   @Override public Integer add( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject entity ) {
      Integer id = createId();
      getEnityMap( entityClass ).put( id, entity );
      try{
         entity.getClass().getMethod( "setId", new Class[] { id.getClass() } ).invoke( entity, new Object[] { id } );
      }catch( Exception e ){
         e.printStackTrace();
      }
      return id;
   }

   @Override public void update( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject entity ) {
      try{
         Integer id = (Integer) entity.getClass().getMethod( "getId", (Class[]) null ).invoke( entity, (Object[]) null );
         getEnityMap( entityClass ).put( id, entity );
      }catch( Exception e ){
         e.printStackTrace();
      }
   }

   @Override public void delete( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject entity ) {
      for( Iterator<Map<Integer, PersistentObject>> i = inMemory.values().iterator(); i.hasNext(); ){
         i.next().values().remove( entity );
      }
   }

   // Properties
   public String getDriverClass() { return null; }
   public String getConnectionUrl() { return null; }
   public String getUserName() { return null; }
   public String getPassword() { return null; }

   @Override
   protected SessionContext startNewSessionContext( UnitOfWork work ) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void finishSessionContext( SessionContext context ) {
   // TODO Auto-generated method stub

   }

   private synchronized Integer createId() {
      return new Integer( ++id );
   }

   private Map<Integer, PersistentObject> getEnityMap( Class<? extends PersistentObject> entityClass ) {
      Map<Integer, PersistentObject> map = inMemory.get( entityClass );
      if( map == null ){
         map = new TreeMap<Integer, PersistentObject>();
         inMemory.put( entityClass, map );
      }
      return map;
   }

   private List<?> find( Query query ) {
      return Collections.EMPTY_LIST;
   }

   // Obsolate methods
   // private Map<Integer, Object> getEnityMap(Class entityClass) {
   // Map<Integer, Object> map = inMemory.get(entityClass);
   // if (map == null) {
   // map = new TreeMap<Integer, Object>();
   // inMemory.put(entityClass, map);
   // }
   // return map;
   // }
}
