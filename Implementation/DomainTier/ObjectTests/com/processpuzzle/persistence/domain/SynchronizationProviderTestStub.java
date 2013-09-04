package com.processpuzzle.persistence.domain;


import java.util.List;

import org.apache.commons.configuration.HierarchicalConfiguration;

import com.processpuzzle.application.configuration.domain.PersistentDataInitializationStrategies;
import com.processpuzzle.commons.persistence.PersistentObject;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.commons.persistence.UnitOfWork;
import com.processpuzzle.commons.persistence.query.Query;

public class SynchronizationProviderTestStub extends SynchronizationProvider {

   public SynchronizationProviderTestStub( String name, HierarchicalConfiguration configuration, List<Class<?>> persistentClasses,
         PersistentDataInitializationStrategies databaseCreationStrategy ) {
      super( name, configuration, persistentClasses, databaseCreationStrategy );
      // TODO Auto-generated constructor stub
   }

   @Override
   public boolean configure() {
      super.configure();
      return true;
   }

   @Override
   public Integer add( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject entity ) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void delete( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject entity ) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public <P extends PersistentObject> RepositoryResultSet<P> findAll( UnitOfWork work, Class<P> entityClass ) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public <P extends PersistentObject> P findById( UnitOfWork work, Class<P> entityClass, Integer id ) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public RepositoryResultSet<? extends PersistentObject> findByQuery( UnitOfWork work, Query query ) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void update( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject entitiy ) {
      // TODO Auto-generated method stub
      
   }

}
