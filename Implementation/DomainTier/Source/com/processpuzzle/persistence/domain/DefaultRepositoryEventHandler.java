/*
 * Created on Dec 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.persistence.PersistentObject;
import hu.itkodex.commons.persistence.RepositoryEventHandler;
import hu.itkodex.commons.persistence.RepositoryResultSet;
import hu.itkodex.commons.persistence.UnitOfWork;
import hu.itkodex.commons.persistence.query.Query;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.HierarchicalConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.processpuzzle.application.configuration.domain.PersistentDataInitializationStrategies;

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
