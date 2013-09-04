/*
Name: 
    - HibernatePersistenceProvider 

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
import java.util.Map;

import org.apache.commons.configuration.HierarchicalConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.DataException;
import org.hibernate.exception.SQLGrammarException;

import com.processpuzzle.application.configuration.domain.PersistenceContext;
import com.processpuzzle.application.configuration.domain.PersistentDataInitializationStrategies;
import com.processpuzzle.commons.persistence.Entity;
import com.processpuzzle.commons.persistence.PersistentObject;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.commons.persistence.UnitOfWork;
import com.processpuzzle.commons.persistence.query.Query;
import com.processpuzzle.persistence.query.transformer.domain.QueryTransformerFactory;

public class HibernatePersistenceProvider extends DefaultPersistenceProvider implements QLConstants {
   private static final String HIBERNATE_PROPERTY_SELECTOR = "pr:hibernate";
   private static Configuration hibernateConfiguration;
   private static SessionFactory sessionFactory;
   protected static Logger log = LoggerFactory.getLogger( HibernatePersistenceProvider.class );

   // Constructors
   public HibernatePersistenceProvider( String name, HierarchicalConfiguration configuration, List<Class<?>> persistentClasses,
         PersistentDataInitializationStrategies databaseCreationStrategy ) {
      super( name, configuration, persistentClasses, databaseCreationStrategy );
   }

   // Public mutators
   public boolean configure() {
      super.configure();
      hibernateConfiguration = new Configuration();
      addPropertiesToTheHibernateConfiguration();
      handleDatabaseInitializationStrategy();
      addEntityClassesToTheHibernateConfiguration();

      try{
         initDatabase();
      }catch( Exception e ){
         throw new PersistenceProviderInitializationException( this.getClass(), e );
      }

      isConfigured = true;
      return isConfigured();
   }

   @Override
   public Integer add( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject entity ) {
      Integer id = null;
      HibernateSessionContext sessionContext = (HibernateSessionContext) getSessionContextFor( work );
      Session session = sessionContext.getSession();
      try{
         id = (Integer) session.save( entityClass.getName(), entity );
      }catch( DataException e ){
         throw new PersistenceProviderException( "add", e );
      }catch( HibernateException e ){
         throw new PersistenceProviderException( "add", e );
      }catch( Exception e ){
         // TODO: handle exception
         e.printStackTrace();
      }
      return id;
   }

   @Override
   public void update( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject entity ) {
      Session hibernateSession = ((HibernateSessionContext) getSessionContextFor( work )).getSession();
      hibernateSession.update( entityClass.getName(), entity );
   }

   @Override
   public void delete( UnitOfWork work, Class<? extends PersistentObject> entityClass, PersistentObject entity ) {
      Session hibernateSession = ((HibernateSessionContext) getSessionContextFor( work )).getSession();
      try{
         hibernateSession.delete( entity );
      }catch( Exception e ){
         log.debug( "Deleting object of entity class: " + entityClass.getName() + "caused an error", e );
         throw new PersistenceProviderException( "delete", e );
      }
   }

   @SuppressWarnings("unchecked")
   @Override
   public <P extends PersistentObject> P findById( UnitOfWork work, Class<P> entityClass, Integer id ) {
      Session hibernateSession = ((HibernateSessionContext) getSessionContextFor( work )).getSession();
      return (P) hibernateSession.get( entityClass, id );
   }

   @SuppressWarnings( "unchecked" )
   @Override
   public <P extends PersistentObject> RepositoryResultSet<P> findAll( UnitOfWork work, Class<P> entityClass ) {
      RepositoryResultSet<P> retValues = null;
      Session hibernateSession = ((HibernateSessionContext) getSessionContextFor( work )).getSession();
      com.processpuzzle.persistence.query.domain.DefaultQuery query = new com.processpuzzle.persistence.query.domain.DefaultQuery( (Class<? extends Entity>) entityClass );
      String queryStatement = QueryTransformerFactory.createHQLQueryTransformer().createStatement( query );

      retValues = new SimpleResultSet<P>( hibernateSession.createQuery( queryStatement ).list() );
      return retValues;
   }

   @Override
   public RepositoryResultSet<? extends PersistentObject> findByQuery( UnitOfWork work, Query query ) {
      RepositoryResultSet<? extends PersistentObject> resultSet = null;
      String queryStatement = null;
      try{
         Session hibernateSession = ((HibernateSessionContext) getSessionContextFor( work )).getSession();
         queryStatement = QueryTransformerFactory.createHQLQueryTransformer().createStatement( query );
         org.hibernate.Query hibernateQuery = hibernateSession.createQuery( queryStatement );
         if( query.getFirstResult() != null )
            hibernateQuery.setFirstResult( query.getFirstResult().intValue() );
         if( query.getMaxResults() != null )
            hibernateQuery.setMaxResults( query.getMaxResults().intValue() );
         List<?> resultList = hibernateQuery.list();
         resultSet = determineResultSet( resultList );
      }catch( QueryException e ){
         log.debug( "Running query: '" + queryStatement + "' raised an exception.", e );
         throw new PersistenceProviderException( "findByQuery", e );
      }catch( SQLGrammarException e ){
         throw new PersistenceProviderException( "findByQuery", e );
      }catch( Exception e ){
         e.printStackTrace();
         throw new Error( "Unhandled exception." );
      }
      return resultSet;
   }

   // Properties
   public String getDriverClass() {
      return configuration.getString( HibernatePersistenceProviderPropertyKey.DRIVER_CLASS.getDefaultKey() );
   }

   public String getConnectionUrl() {
      return configuration.getString( HibernatePersistenceProviderPropertyKey.CONNECTION_URL.getDefaultKey() );
   }

   public String getUserName() {
      return configuration.getString( HibernatePersistenceProviderPropertyKey.USER_NAME.getDefaultKey() );
   }

   public String getPassword() {
      return configuration.getString( HibernatePersistenceProviderPropertyKey.PASSWORD.getDefaultKey() );
   }

   public Configuration getHibernateConfiguration() {
      return hibernateConfiguration;
   }

   SessionFactory getSessionFactory() {
      return sessionFactory;
   }

   // Private helper methods
   @Override
   protected SessionContext startNewSessionContext( UnitOfWork work ) {
      HibernateSessionContext sessionContext = new HibernateSessionContext( sessionFactory );
      return sessionContext;
   }

   @Override
   protected void finishSessionContext( SessionContext context ) {
   // TODO Auto-generated method stub
   }

   @SuppressWarnings( "unchecked" )
   private void addPropertiesToTheHibernateConfiguration() {
      HierarchicalConfiguration hibernateProperties = configuration.configurationAt( HIBERNATE_PROPERTY_SELECTOR );
      for( Iterator<String> iter = hibernateProperties.getKeys(); iter.hasNext(); ){
         String propertyKey = (String) iter.next();
         String propertyValue = hibernateProperties.getString( propertyKey );

         if( propertyKey != "" && propertyValue != "" ){
            propertyKey = propertyKey.toLowerCase();
            propertyKey = propertyKey.replace( PersistenceContext.NAME_SPACE_PREFIX, "" );
            propertyKey = "hibernate." + propertyKey.replace( '/', '.' );
            hibernateConfiguration.setProperty( propertyKey, propertyValue );
         }
      }
   }

   private void addEntityClassesToTheHibernateConfiguration() {
      for( Iterator<Class<?>> iter = persistentClasses.iterator(); iter.hasNext(); ){
         Class<?> persistentClass = (Class<?>) iter.next();
         try{
            log.debug( "Adding: " + persistentClass + " class to Hibernate configuration." );
            hibernateConfiguration.addClass( persistentClass );
         }catch( MappingException e ){
            throw new RepositoryEventHandlerConfigurationException( configuration, e );
         }
      }
   }

   @SuppressWarnings( "unchecked" )
   private RepositoryResultSet<? extends PersistentObject> determineResultSet( List<?> resultList ) {
      RepositoryResultSet<? extends PersistentObject> resultSet = null;
      if( resultList != null && resultList.size() > 0 ){
         Object firstObject = resultList.get( 0 );

         if( firstObject instanceof Long )
            resultSet = new AggregatedResult<PersistentLong>( PersistentLong.class, (List<PersistentLong>) resultList );
         else
            resultSet = new SimpleResultSet<PersistentObject>( (List<PersistentObject>) resultList );
      }else
         resultSet = new SimpleResultSet<PersistentObject>( (List<PersistentObject>) resultList );

      return resultSet;
   }

   private void handleDatabaseInitializationStrategy() {
      if( databaseCreationStrategy == PersistentDataInitializationStrategies.create ){
         hibernateConfiguration.setProperty( "hibernate.hbm2ddl.auto", "create" );
      }else if( databaseCreationStrategy == PersistentDataInitializationStrategies.dropAndCreate ){
         hibernateConfiguration.setProperty( "hibernate.hbm2ddl.auto", "create-drop" );
      }else if( databaseCreationStrategy == PersistentDataInitializationStrategies.update ){
         hibernateConfiguration.setProperty( "hibernate.hbm2ddl.auto", "update" );
      }
   }

   private void initDatabase() {
      try{
         sessionFactory = hibernateConfiguration.buildSessionFactory();
      }catch( Exception e ){
         log.debug( e.getMessage() );
         throw new RepositoryEventHandlerConfigurationException( configuration, e );
      }
   }

   @SuppressWarnings( { "unchecked", "unused" } )
   private void printProperties() {
      for( Iterator iter = hibernateConfiguration.getProperties().entrySet().iterator(); iter.hasNext(); ){
         Map.Entry<String, String> propertyEntry = (Map.Entry) iter.next();
         String key = propertyEntry.getKey();
         String value = propertyEntry.getValue();
         System.out.println( key + " = " + value );
      }
   }

}