package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.persistence.PersistentObject;
import hu.itkodex.commons.persistence.RepositoryResultSet;
import hu.itkodex.commons.persistence.UnitOfWork;
import hu.itkodex.commons.persistence.query.Query;

import java.util.List;

import org.apache.commons.configuration.HierarchicalConfiguration;

import com.processpuzzle.application.configuration.domain.PersistentDataInitializationStrategies;

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
