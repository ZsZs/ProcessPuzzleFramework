/*
Name: 
    - BidirectionalSynchronizationStrategy 

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


import java.util.List;

import org.apache.commons.configuration.HierarchicalConfiguration;

import com.processpuzzle.application.configuration.domain.PersistentDataInitializationStrategies;
import com.processpuzzle.commons.persistence.PersistentObject;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.commons.persistence.UnitOfWork;
import com.processpuzzle.commons.persistence.query.Query;

public class BidirectionalSynchronizationStrategy extends SynchronizationProvider {

   public BidirectionalSynchronizationStrategy( String name, HierarchicalConfiguration configuration, List<Class<?>> persistentClasses,
         PersistentDataInitializationStrategies databaseCreationStrategy ) {
      super( name, configuration, persistentClasses, databaseCreationStrategy );
   }

   public boolean configure() {
      isConfigured = true;
      return isConfigured();
   }

   @Override
   public Integer add( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject entity ) {
      return null;
   }

   @Override
   public void delete( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject entity ) {
   }

   @Override
   public <P extends PersistentObject> RepositoryResultSet<P> findAll( UnitOfWork work, Class<P> entityClass ) {
      return null;
   }

   @Override
   public <P extends PersistentObject> P findById( UnitOfWork work, Class<P> entityClass, Integer id ) {
      return null;
   }

   @Override
   public RepositoryResultSet<? extends PersistentObject> findByQuery( UnitOfWork work, Query query ) {
      return null;
   }

   @Override
   public void update( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject entitiy ) {
   }
}
