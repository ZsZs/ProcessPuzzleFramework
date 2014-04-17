/*
Name: 
    - PersistenceContext 

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

package com.processpuzzle.application.configuration.domain;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.HierarchicalConfiguration;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.commons.persistence.Entity;
import com.processpuzzle.commons.persistence.PersistenceStrategy;
import com.processpuzzle.commons.persistence.PersistentObject;
import com.processpuzzle.commons.persistence.Repository;
import com.processpuzzle.commons.persistence.RepositoryEventHandler;
import com.processpuzzle.fundamental_types.domain.OpAssertion;
import com.processpuzzle.persistence.domain.DefaultPersistenceStrategy;
import com.processpuzzle.persistence.domain.DefaultRepositoryEventHandler;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.domain.PersistentClassList;
import com.processpuzzle.persistence.domain.RepositoryClassList;
import com.processpuzzle.persistence.domain.UndefinedPersistentDataInitializationStrategyException;

public class PersistenceContext extends PersistentApplicationContext implements ApplicationContext {
   public static final String ENTITY_CLASS_PROPERTY_NAME = "entityClass";
   public static final String ENTITY_CLASS_PROPERTY_NODE_SELECTOR = "persistence/entities";
   public static final String HIBERNATE_STRATEGY = "hibernate";
   public static final String HIERARCHICAL_PROPERTY_SELECTOR_DELIMITER = ".";
   public static final String NAME_SPACE_PREFIX = "pr:";
   private ProcessPuzzleContext applicationContext;
   private Map<Class<? extends Repository<?>>, Repository<?>> availableRepositories = new HashMap<Class<? extends Repository<?>>, Repository<?>>();
   private Map<Class<? extends Repository<?>>, GenericRepository<?>> domainClassRepositoryMappings = new HashMap<Class<? extends Repository<?>>, GenericRepository<?>>();
   private List<Class<? extends PersistentObject>> persistentClasses = new ArrayList<Class<? extends PersistentObject>>();
   private PersistentDataInitializationStrategies persistentDataInitializationStrategy = null;
   private PropertyContext propertyContext = null;
   private Map<String, PersistenceStrategy> supportedStrategies = new HashMap<String, PersistenceStrategy>();
   
   // Constructors
   PersistenceContext( Application application ) {
      super( application );
      this.applicationContext = application.getContext();
      this.propertyContext = applicationContext.getPropertyContext();
   }

   // Public mutators and accessors
   
   // Properties
   public Map<Class<? extends Repository<?>>, Repository<?>> getAvailableRepositories() { return availableRepositories; }
   public int getPersistentClassesSize() { return persistentClasses.size(); }
   public Iterator<Class<? extends PersistentObject>> getPersistentClassesIterator() { return persistentClasses.iterator(); }
   public PersistentDataInitializationStrategies getPersistentDataInitializationStrategy() { return persistentDataInitializationStrategy; }

   @SuppressWarnings( "unchecked" )
   public <R extends Repository<?>> R getRepositoryInstance( Class<R> repositoryClass ) {
      R repository = (R) availableRepositories.get( repositoryClass );
      if( repository != null )
         return repository;
      else
         throw new UndeclaredRepositoryException( repositoryClass );
   }

   public Repository<?> getRepositoryByAggregateRootClass( Class<? extends Entity> entityClass ) {
      Repository<?> repository = domainClassRepositoryMappings.get( entityClass );
      if( repository != null )
         return repository;
      else{
         repository = findRepositoryBySuperClass( entityClass );
         if( repository == null ){
            throw new UndeclaredAggregateRootRepositoryMapping( entityClass );
         }else{
            return repository;
         }
      }
   }

   public PersistenceStrategy getStrategy( String strategyName ) {
      PersistenceStrategy strategy = supportedStrategies.get( strategyName );
      if( strategy == null )
         throw new Error( "PersistenceStrategy `" + strategyName + "` not found!" );
      return strategy;
   }

   public Map<String, PersistenceStrategy> getSupportedStrategies() {
      return supportedStrategies;
   }

   public boolean isConfigured() { return isConfigured; }

   // Protected, private helper methods
   @Override
   protected void setUpTransientComponents() {}

   @Override
   protected void setUpPersistentComponents() {
      determinePersistentClasses();
      determinePersistentDataInitializationStrategy();
      instantiateSupportedStrategies();
      
      Map<Class<? extends Repository<?>>, PersistenceStrategy> repositories = new HashMap<Class<? extends Repository<?>>, PersistenceStrategy>();
      repositories = determineRepositories();
      instantiateRepositories( repositories );

      configureStrategies();
      buildDomainClassRepositoryMapping();
   }

   @Override
   protected void tearDownTransientComponents() {

   }

   @SuppressWarnings( "unchecked" )
   @Override
   protected void tearDownPersistentComponents() {
      domainClassRepositoryMappings.clear();
      availableRepositories.clear();
      for( Iterator iter = supportedStrategies.entrySet().iterator(); iter.hasNext(); ){
         Map.Entry<String, DefaultPersistenceStrategy> mapEntry = (Map.Entry<String, DefaultPersistenceStrategy>) iter.next();
         DefaultPersistenceStrategy strategy = mapEntry.getValue();
         strategy.release();
      }
      supportedStrategies.clear();
   }

   @SuppressWarnings( "unchecked" )
   private void buildDomainClassRepositoryMapping() {
      for( Iterator iter = availableRepositories.entrySet().iterator(); iter.hasNext(); ){
         Map.Entry<Class, GenericRepository> availableRepositoryEntry = (Map.Entry<Class, GenericRepository>) iter.next();
         GenericRepository repository = availableRepositoryEntry.getValue();
         domainClassRepositoryMappings.put( repository.getSupportedAggregateRootClass(), repository );
      }
   }

   private void configureStrategies() {
      for( Map.Entry<String, PersistenceStrategy> strategyEntry : supportedStrategies.entrySet() ){
         Map.Entry<String, PersistenceStrategy> strategiesEntry = strategyEntry;
         PersistenceStrategy strategy = strategiesEntry.getValue();
         strategy.configure();
      }
   }

   private void determinePersistentClasses() {
      determinePersistentClassesFromConfigurationFile();
      determinePersistentClassesFromHardCodedClass();
   }

   private void determinePersistentDataInitializationStrategy() {
      String strategyProperty = propertyContext.getProperty( PropertyKeys.APPLICATION_DATABASE_CREATION.getDefaultKey() );
      if( strategyProperty.equals( PersistentDataInitializationStrategies.create.asString() ) ){
         persistentDataInitializationStrategy = PersistentDataInitializationStrategies.create;
      }else if( strategyProperty.equals( PersistentDataInitializationStrategies.dropAndCreate.asString() ) ){
         persistentDataInitializationStrategy = PersistentDataInitializationStrategies.dropAndCreate;
      }else if( strategyProperty.equals( PersistentDataInitializationStrategies.update.asString() ) ){
         persistentDataInitializationStrategy = PersistentDataInitializationStrategies.update;
      }else
         throw new UndefinedPersistentDataInitializationStrategyException( strategyProperty );
   }

   @SuppressWarnings("unchecked")
   private void determinePersistentClassesFromConfigurationFile() {
      List<String> persistentClassNames = propertyContext.getPropertyList( PropertyKeys.PERSISTENCE_PERSISTENT_CLASSES.getDefaultKey() );
      for( Iterator<String> iter = persistentClassNames.iterator(); iter.hasNext(); ){
         String className = (String) iter.next();
         Class<? extends PersistentObject> persistentClass = null;
         try{
            persistentClass = (Class<? extends PersistentObject>) Class.forName( className );
         }catch( ClassNotFoundException e ){
            throw new PersistenceContextSetUpException( propertyContext, e );
         }
         persistentClasses.add( persistentClass );
      }
   }

   @SuppressWarnings("unchecked")
   private void determinePersistentClassesFromHardCodedClass() {
      String classListClassName = propertyContext.getProperty( PropertyKeys.PERSISTENCE_PERSISTENT_CLASSLIST_CLASS.getDefaultKey() );
      if( classListClassName != null ){
         PersistentClassList persistentClassList = null;
         try{
            Class<? extends PersistentObject> classListClass = (Class<? extends PersistentObject>) Class.forName( classListClassName );
            persistentClassList = (PersistentClassList) classListClass.newInstance();
         }catch( Exception e ){
            throw new PersistenceContextSetUpException( propertyContext, e );
         }
   
         persistentClasses.addAll( persistentClassList.getAggregateRoots() );
         persistentClasses.addAll( persistentClassList.getEntities() );
         persistentClasses.addAll( persistentClassList.getValueObjects() );
      }
   }

   private Map<Class<? extends Repository<?>>, PersistenceStrategy> determineRepositories() {
      Map<Class<? extends Repository<?>>, PersistenceStrategy> repositoryClasses = new HashMap<Class<? extends Repository<?>>, PersistenceStrategy>();

      repositoryClasses.putAll( determineRepositoriesFromConfigurationFile() );
      repositoryClasses.putAll( determineRepositoryFromHardCodedClass() );
      
      return repositoryClasses;
   }

   @SuppressWarnings("unchecked")
   private Map<Class<? extends Repository<?>>, PersistenceStrategy> determineRepositoryFromHardCodedClass() {
      Map<Class<? extends Repository<?>>, PersistenceStrategy> repositoryClasses = new HashMap<Class<? extends Repository<?>>, PersistenceStrategy>();

      String repositoryClassListClassName = propertyContext.getProperty( PropertyKeys.PERSISTENCE_REPOSITORY_LIST_CLASS.getDefaultKey() );
      
      if( repositoryClassListClassName != null ){
         RepositoryClassList repositoryClassList = null;
         PersistenceStrategy strategy = null;
         try{
            Class<RepositoryClassList> classListClass = (Class<RepositoryClassList>) Class.forName( repositoryClassListClassName );
            repositoryClassList = (RepositoryClassList) classListClass.newInstance();

            Object[] parameters = { classListClass.getName() };
            String strategyName = propertyContext.getProperty( PropertyKeys.createKey( PropertyKeys.PERSISTENCE_REPOSITORY_LIST_STRATEGY.getDefaultKey(), parameters ) );
            strategy = supportedStrategies.get( strategyName );
            
         }catch( Exception e ){
            throw new PersistenceContextSetUpException( propertyContext, e );
         }
   
         for( Class<? extends Repository<?>> repositoryClass : repositoryClassList.gerRepositories() ) {
            repositoryClasses.put( repositoryClass, strategy );
         }
      }
      return repositoryClasses;
   }

   @SuppressWarnings("unchecked")
   private Map<? extends Class<Repository<?>>, PersistenceStrategy> determineRepositoriesFromConfigurationFile() {
      Map<Class<Repository<?>>, PersistenceStrategy> repositoryClasses = new HashMap<Class<Repository<?>>, PersistenceStrategy>();
      List<String> repositoryClassNames = propertyContext.getPropertyList( PropertyKeys.PERSISTENCE_REPOSITORY_CLASS.getDefaultKey() );

      for( String repositoryClassName : repositoryClassNames ){
         Class<Repository<?>> repositoryClass = null;
         try{
            repositoryClass = (Class<Repository<?>>) Class.forName( repositoryClassName );

            Object[] parameters = { repositoryClass.getName() };
            String strategyName = propertyContext.getPropertyByParameter( PropertyKeys.PERSISTENCE_REPOSITORY_CLASS_STRATEGY.getDefaultKey(), parameters );
            PersistenceStrategy strategy = supportedStrategies.get( strategyName );
            
            repositoryClasses.put( repositoryClass, strategy );
         }catch( ClassNotFoundException e ){
            throw new RepositoryInstantiationException( repositoryClassName, propertyContext, e );
         }catch( Exception e ){
            throw new RepositoryInstantiationException( repositoryClassName, propertyContext, e );
         }
      }
      return repositoryClasses;
   }

   private Repository<?> findRepositoryBySuperClass( Class<? extends Entity> entityClass ) {
      GenericRepository<?> repository = null;
      boolean found = false;
      Class<?> superClass = entityClass.getSuperclass();
      while( !found && superClass != null ){
         repository = domainClassRepositoryMappings.get( superClass );
         if( repository != null ){
            found = true;
         }else{
            superClass = superClass.getSuperclass();
         }
      }
      return repository;
   }

   private void instantiateSupportedStrategies() {
      List<String> supportedStrategyNames = propertyContext.getPropertyList( PropertyKeys.PERSISTENCE_STRATEGY_NAME.getDefaultKey() );
      Iterator<String> supportedStrategyIterator = supportedStrategyNames.iterator();
      Integer strategyIndex = 0;
      while( supportedStrategyIterator.hasNext() ){
         String supportedStrategyName = supportedStrategyIterator.next();
         Object[] parameters = { supportedStrategyName };
         List<String> eventHandlerNames = propertyContext.getPropertyListByParameter( PropertyKeys.PERSISTENCE_STRATEGY_EVENT_HANDLERS.getDefaultKey(), parameters );
         List<RepositoryEventHandler> eventHandlers = new LinkedList<RepositoryEventHandler>();
         Iterator<String> eventHandlersIterator = eventHandlerNames.iterator();
         Integer eventHandlerIndex = 0;
         while( eventHandlersIterator.hasNext() ){
            String eventHandlerName = eventHandlersIterator.next();
            String configurationAt = MessageFormat.format( PropertyKeys.REPOSITORY_EVENT_HANDLER.getDefaultKey(), new Object[] { strategyIndex.toString(), eventHandlerIndex.toString() } );
            HierarchicalConfiguration eventHandlerConfiguration = propertyContext.getConfigurationAt( configurationAt );

//            ParametrizedConfigurationPropertyHandler propertyHandler = new ParametrizedConfigurationPropertyHandler( eventHandlerConfiguration );
            String eventHandlerClassName = eventHandlerConfiguration.getString( PropertyKeys.EVENT_HANDLER_CLASS.getDefaultKey() );
            DefaultRepositoryEventHandler eventHandler = instantiateEventHandler( eventHandlerName, eventHandlerClassName, eventHandlerConfiguration );
            eventHandlers.add( eventHandler );
            eventHandlerIndex++;
         }

         PersistenceStrategy strategy = new DefaultPersistenceStrategy( supportedStrategyName, eventHandlers );
         supportedStrategies.put( supportedStrategyName, strategy );
         strategyIndex++;
      }
   }

   @SuppressWarnings( "unchecked" )
   private DefaultRepositoryEventHandler instantiateEventHandler( String eventHandlerName, String eventHandlerClassName,
         HierarchicalConfiguration eventHandlerConfiguration ) {
      OpAssertion.ppAssert( eventHandlerName != null,
            "PersistenceContext.instantitateEventHandler: RepositoryEventHandler name can't be null." );
      OpAssertion.ppAssert( eventHandlerClassName != null,
            "PersistenceContext.instantitateEventHandler: RepositoryEventHandler class name can't be null." );

      DefaultRepositoryEventHandler eventHandler = null;
      if( eventHandlerClassName != null ){
         try{
            Class eventHandlerClass = Class.forName( eventHandlerClassName );
            Class[] parameterTypes = new Class[] { String.class, HierarchicalConfiguration.class, List.class,
                  PersistentDataInitializationStrategies.class };
            Constructor eventHandlerConstructor = eventHandlerClass.getConstructor( parameterTypes );
            Object[] constructorArguments = { eventHandlerName, eventHandlerConfiguration, persistentClasses,
                  persistentDataInitializationStrategy };
            eventHandler = (DefaultRepositoryEventHandler) eventHandlerConstructor.newInstance( constructorArguments );
         }catch( ClassNotFoundException e ){
            throw new PersistenceContextSetUpException( propertyContext, e );
         }catch( SecurityException e ){
            throw new PersistenceContextSetUpException( propertyContext, e );
         }catch( NoSuchMethodException e ){
            throw new PersistenceContextSetUpException( propertyContext, e );
         }catch( IllegalArgumentException e ){
            throw new PersistenceContextSetUpException( propertyContext, e );
         }catch( InstantiationException e ){
            throw new PersistenceContextSetUpException( propertyContext, e );
         }catch( IllegalAccessException e ){
            throw new PersistenceContextSetUpException( propertyContext, e );
         }catch( InvocationTargetException e ){
            throw new PersistenceContextSetUpException( propertyContext, e );
         }
      }
      return eventHandler;
   }

   private void instantiateRepositories( Map<Class<? extends Repository<?>>, PersistenceStrategy> repositoryClasses ) {
      for( Map.Entry<Class<? extends Repository<?>>, PersistenceStrategy> repositoryEntry : repositoryClasses.entrySet() ) {
         Class<? extends Repository<?>> repositoryClass = repositoryEntry.getKey();
         PersistenceStrategy strategy = repositoryEntry.getValue();
        
         GenericRepository<?> repository = instantiateRepository( repositoryClass, strategy );
         availableRepositories.put( repositoryClass, repository );
         logger.debug( "Repository: '" + repositoryClass.getSimpleName() + "' instantiated." );
      }
   }

   @SuppressWarnings( "unchecked" )
   private GenericRepository<?> instantiateRepository( Class repositoryClass, PersistenceStrategy strategy ) {
      GenericRepository repository = null;
      if( repositoryClass != null ){
         Class[] parameterTypes = new Class[] { PersistenceStrategy.class, ProcessPuzzleContext.class };
         Constructor repositoryConstructor;
         try{
            repositoryConstructor = repositoryClass.getConstructor( parameterTypes );
            Object[] constructorArguments = { strategy, applicationContext };
            repository = (GenericRepository) repositoryConstructor.newInstance( constructorArguments );
         }catch( SecurityException e ){
            throw new PersistenceContextSetUpException( propertyContext, e );
         }catch( NoSuchMethodException e ){
            throw new PersistenceContextSetUpException( propertyContext, e );
         }catch( IllegalArgumentException e ){
            throw new PersistenceContextSetUpException( propertyContext, e );
         }catch( InstantiationException e ){
            throw new PersistenceContextSetUpException( propertyContext, e );
         }catch( IllegalAccessException e ){
            throw new PersistenceContextSetUpException( propertyContext, e );
         }catch( InvocationTargetException e ){
            throw new PersistenceContextSetUpException( propertyContext, e );
         }
      }
      return repository;
   }

}
