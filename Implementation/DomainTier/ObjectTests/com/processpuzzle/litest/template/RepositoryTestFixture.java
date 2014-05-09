package com.processpuzzle.litest.template;

import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
import org.hibernate.StaleStateException;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Table;

import com.processpuzzle.application.configuration.domain.PersistenceContext;
import com.processpuzzle.application.configuration.domain.PersistentDataInitializationStrategies;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.application.domain.ConfigurableApplicationFixture;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.commons.persistence.AggregateRoot;
import com.processpuzzle.commons.persistence.PersistenceProvider;
import com.processpuzzle.commons.persistence.PersistenceStrategy;
import com.processpuzzle.commons.persistence.Repository;
import com.processpuzzle.commons.persistence.RepositoryEventHandler;
import com.processpuzzle.commons.persistence.UnitOfWork;
import com.processpuzzle.commons.rdbms.DatabaseSpy;
import com.processpuzzle.litest.fixture.TransientFreshFixture;
import com.processpuzzle.persistence.domain.DefaultPersistenceStrategy;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.HibernatePersistenceProvider;
import com.processpuzzle.persistence.domain.PersistentClassList;
import com.processpuzzle.user_session.domain.UserRequestManager;

public abstract class RepositoryTestFixture<R extends Repository<A>, A extends AggregateRoot> extends GenericTemplatedFixture<R> implements TransientFreshFixture<R> {
   private static final String PERSISTENCE_STRATEGY_NAME = "TEST_STRATEGY";
   private static final String PERSISTENCE_PROVICDER_NAME = "TEST_PROVIDER";
   private static final String DATABASE_DRIVER = "org.hsqldb.jdbcDriver";
   private static final String DATABASE_URL = "jdbc:hsqldb:mem:DOMAINTIER_TEST_DB";
   private static final String DATABASE_USER = "sa";
   private ConfigurableApplicationFixture applicationFixture;
   private List<Class<?>> persistentClasses = new ArrayList<Class<?>>();
   private PersistentClassList persistentClassLists;
   private DefaultPersistenceStrategy strategy = null;
   protected ProcessPuzzleContext applicationContext;
   protected DatabaseSpy databaseSpy;
   protected R repository;
   protected Class<R> repositoryClass;
   protected A root = null;
   protected Class<A> rootClass;
   protected ResultSet rootRecord;
   protected Table rootTable;
   protected UnitOfWork setUpWork = null;
   protected UnitOfWork testWork = null;
   protected UserFactory userFactory;

   //Constructors 
   protected RepositoryTestFixture( RepositoryTestEnvironment<R, RepositoryTestFixture<R,A>> testEnvironment ) {
      super( testEnvironment );
      this.applicationFixture = testEnvironment.getApplicationFixture();
   }

   //Properties
   public ProcessPuzzleContext getApplicationContext(){ return applicationContext; }
   public DatabaseSpy getDatabaseSpy() { return databaseSpy; }
   public R getRepository() { return repository; }
   public A getRoot() { return root; }
   public ResultSet getRootRecord(){ return rootRecord; }
   public String getRootTableName(){ return rootTable.getName(); }

   // Protected abstract and helper methods
   protected abstract void afterAggregateCreation();
   protected abstract void afterAggregateDeletion();
   protected abstract void beforeAggregateCreation();
   
   @Override protected void configureBeforeSutInstantiation() {
      applicationContext = applicationFixture.getApplicationContext();
      
      saveCurrentUser();
      
      instantiateDatabaseSpy();
      determineAggregateRootClass();
      determineRepositoryClass();
      determineAggregateRootTable();
      determineRepository();
      determineUserFactory();
      
      beforeAggregateCreation();
   }

   @Override
   protected void configureAfterSutInstantiation() {
      try{
         instantiateAggregate();
      }catch( Exception e ){
         e.printStackTrace();
      }
      
      afterAggregateCreation();
      determineAggregateRootRecord();
      instantiateTestWork();
   }

   protected abstract A createNewAggregate() throws Exception;

   @SuppressWarnings( "unchecked" )
   protected Repository<A> instantiateGivenRepository( Class<A> repositoryClass ) {
      Repository<A> repository = null;
      Class<?>[] parameterTypes = new Class[] { DefaultPersistenceStrategy.class };
      Constructor<?> repositoryConstructor;

      try{
         repositoryConstructor = repositoryClass.getConstructor( parameterTypes );
         Object[] constructorArguments = { strategy };
         repository = (Repository<A>) repositoryConstructor.newInstance( constructorArguments );
      }catch( SecurityException e ){
         e.printStackTrace();
      }catch( NoSuchMethodException e ){
         e.printStackTrace();
      }catch( IllegalArgumentException e ){
         e.printStackTrace();
      }catch( InstantiationException e ){
         e.printStackTrace();
      }catch( IllegalAccessException e ){
         e.printStackTrace();
      }catch( InvocationTargetException e ){
         e.printStackTrace();
      }

      return repository;
   }

   @Override
   protected R instantiateSUT() {
      return applicationContext.getRepository( repositoryClass );
   }

   @Override
   protected void releaseResources() {
      tearDownTestWork();
      deleteAggregate();
      afterAggregateDeletion();
      repository = null;
   }

   // Private helper methods
   @SuppressWarnings( "unused" ) private DefaultPersistenceStrategy configureStrategy() {
      DefaultPersistenceStrategy strategy = null;
   
      HierarchicalConfiguration configuration = defineConfiguration();
      determinePersistentClasses();
      HibernatePersistenceProvider hibernateProvider = new HibernatePersistenceProvider( PERSISTENCE_PROVICDER_NAME, configuration, persistentClasses,
            PersistentDataInitializationStrategies.dropAndCreate );
   
      List<RepositoryEventHandler> eventHandlerList = new ArrayList<RepositoryEventHandler>();
      eventHandlerList.add( hibernateProvider );
   
      strategy = new DefaultPersistenceStrategy( "Repository test configuration", eventHandlerList );
      strategy.configure();
   
      return strategy;
   }

   private void deleteAggregate() {
      DefaultUnitOfWork tearDownWork = new DefaultUnitOfWork( true );
      try{
         if( (root != null) && (root.getId() != null) )
            repository.delete( tearDownWork, root );
         tearDownWork.finish();
      }catch( StaleStateException e ){
         e.printStackTrace();
      }catch( Throwable e ){
         e.printStackTrace();
      }
      
      root = null;
   }

   private static HierarchicalConfiguration defineConfiguration() {
      HierarchicalConfiguration configuration = new HierarchicalConfiguration();
      configuration.addProperty( "hibernate", "" );
   
      configuration.setExpressionEngine( new XPathExpressionEngine() );
      configuration.addProperty( "hibernate connection", "" );
      configuration.addProperty( "hibernate/connection driver_class", DATABASE_DRIVER );
      configuration.addProperty( "hibernate/connection url", DATABASE_URL );
      configuration.addProperty( "hibernate/connection username", DATABASE_USER );
      configuration.addProperty( "hibernate/connection password", "" );
   
      configuration.addProperty( "hibernate dialect", "org.hibernate.dialect.HSQLDialect" );
      configuration.addProperty( "hibernate current_session_context_class", "thread" );
      configuration.addProperty( "hibernate show_sql", "true" );
   
      configuration.addProperty( "hibernate hbm2ddl", "" );
      configuration.addProperty( "hibernate/hbm2ddl auto", "create-drop" );
   
      configuration.addProperty( "hibernate transaction", "" );
      configuration.addProperty( "hibernate/transaction factory_class", "org.hibernate.transaction.JDBCTransactionFactory" );
   
      configuration.addProperty( "hibernate cache", "" );
      configuration.addProperty( "hibernate/cache provider_class", "org.hibernate.cache.HashtableCacheProvider" );
      configuration.addProperty( "hibernate/cache use_second_level_cache", "true" );
      configuration.addProperty( "hibernate/cache use_query_cache", "true" );
   
      configuration.addProperty( "hibernate c3p0", "" );
      configuration.addProperty( "hibernate/c3p0 max_size", "5" );
      configuration.addProperty( "hibernate/c3p0 min_size", "2" );
      configuration.addProperty( "hibernate/c3p0 timeout", "1000" );
      configuration.addProperty( "hibernate/c3p0 max_statements", "100" );
      configuration.addProperty( "hibernate/c3p0 idle_test_period", "3000" );
      configuration.addProperty( "hibernate/c3p0 acquire_increment", "2" );
      configuration.addProperty( "hibernate/c3p0 validate", "false" );
   
      return configuration;
   }

   @SuppressWarnings("unchecked")
   private void determineAggregateRootClass() {
      rootClass = (Class<A>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
   }
   
   private void determineAggregateRootRecord(){
      rootRecord = databaseSpy.retrieveRecord( rootTable.getName(), root.getId() );
   }

   private void determineAggregateRootTable() {
      // ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      PersistenceContext persistenceContext = applicationContext.getPersistenceContext();
      String strategyName = applicationContext.getProperty( PropertyKeys.PERSISTENCE_STRATEGY_NAME.getDefaultKey() );
      PersistenceStrategy strategy = persistenceContext.getStrategy( strategyName );
      for( RepositoryEventHandler eventHandler : strategy.getEventHandlers() ){
         if( eventHandler instanceof HibernatePersistenceProvider ){
            org.hibernate.cfg.Configuration hibernateConfiguration = ((HibernatePersistenceProvider) eventHandler).getHibernateConfiguration();
            PersistentClass classMapping = hibernateConfiguration.getClassMapping( rootClass.getName() );
            if( classMapping != null ){
               rootTable = classMapping.getTable();
               break;
            }
         }
      }
   }

   private void determinePersistentClasses() {
      persistentClasses.addAll( persistentClassLists.getAggregateRoots() );
      persistentClasses.addAll( persistentClassLists.getEntities() );
      persistentClasses.addAll( persistentClassLists.getValueObjects() );
   }

   private void determineRepository() {
      repository = applicationContext.getRepository( repositoryClass );
      if( repository == null )
         fail( "Repository: " + repositoryClass.getName() + " is undefined in ProcessPuzzleContext" );
   }

   @SuppressWarnings("unchecked")
   private void determineRepositoryClass() {
      repositoryClass = (Class<R>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
   }

   private void determineUserFactory() {
      userFactory = applicationContext.getEntityFactory( UserFactory.class );
   }

   private void instantiateAggregate() throws Exception {
      setUpWork = new DefaultUnitOfWork( true );
      root = createNewAggregate();
      repository.add( setUpWork, root );
      setUpWork.finish();
   }

   private void instantiateDatabaseSpy() {
      PersistenceStrategy domainComponentsTestStrategy = applicationContext.getPersistenceContext().getStrategy( PERSISTENCE_STRATEGY_NAME );
      PersistenceProvider persistenceProvider = (PersistenceProvider) domainComponentsTestStrategy.getEventHandler( PERSISTENCE_PROVICDER_NAME );
   
      databaseSpy = new DatabaseSpy( persistenceProvider );
      databaseSpy.connect();
   }

   @SuppressWarnings( { "unchecked", "unused" } )
   private R instantiateRepository( DefaultPersistenceStrategy strategy ) {
      R repository = null;
      Class<R> repositoryClass = (Class<R>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
      Class<?>[] parameterTypes = new Class[] { DefaultPersistenceStrategy.class };
      Constructor<R> repositoryConstructor;

      try{
         repositoryConstructor = repositoryClass.getConstructor( parameterTypes );
         Object[] constructorArguments = { strategy };
         repository = repositoryConstructor.newInstance( constructorArguments );
      }catch( SecurityException e ){
         e.printStackTrace();
      }catch( NoSuchMethodException e ){
         e.printStackTrace();
      }catch( IllegalArgumentException e ){
         e.printStackTrace();
      }catch( InstantiationException e ){
         e.printStackTrace();
      }catch( IllegalAccessException e ){
         e.printStackTrace();
      }catch( InvocationTargetException e ){
         e.printStackTrace();
      }

      return repository;
   }

   private void instantiateTestWork() {
      testWork = new DefaultUnitOfWork( true );
   }

   private void saveCurrentUser() {
      User currentUser = UserRequestManager.getInstance().currentUser();
      UserRepository userRepository = applicationContext.getRepository( UserRepository.class );
      userRepository.add( currentUser );
   }

   private void tearDownTestWork() {
      if(( testWork != null ) && ( testWork.isStarted() ))
         testWork.finish();
   }
}
