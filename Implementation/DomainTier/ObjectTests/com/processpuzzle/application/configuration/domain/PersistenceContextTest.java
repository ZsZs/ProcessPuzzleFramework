package com.processpuzzle.application.configuration.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import hu.itkodex.commons.persistence.AggregateRoot;
import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.Repository;
import hu.itkodex.commons.persistence.RepositoryResultSet;
import hu.itkodex.commons.persistence.UnitOfWork;
import hu.itkodex.commons.persistence.query.IdentityExpression;
import hu.itkodex.commons.persistence.query.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.persistence.domain.BidirectionalSynchronizationStrategy;
import com.processpuzzle.persistence.domain.DefaultPersistenceStrategy;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.HibernatePersistenceProvider;
import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.persistence.domain.TestEntityRepository;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class PersistenceContextTest {
//   private static String ERRONEOUS_CONFIGURATION_DESCRIPTOR = "classpath:com/itcodex/objectpuzzle/framework/application_configuration/domain/erroneous_configuration_descriptor.xml";
   @Mock private static Application application;
   @Mock private static ProcessPuzzleContext applicationContext;
   private static PropertyContext propertyContext;
   private PersistenceContext persistenceContext;
   private PersistenceStrategy strategy_1;
   private PersistenceStrategy strategy_2;

   @BeforeClass
   public static void beforeAllTests() {
      application = mock( Application.class );
      applicationContext = mock( ProcessPuzzleContext.class );
      when( application.getContext() ).thenReturn( applicationContext );
      
      propertyContext = new PropertyContext( application, "classpath:com/processpuzzle/application/configuration/domain/configuration_descriptor.xml" );
      propertyContext.setUp( Application.Action.install );
      when( applicationContext.getPropertyContext() ).thenReturn( propertyContext );
   }

   @Before
   public void beforeEachTests() {
      persistenceContext = new PersistenceContext( application );
      persistenceContext.setUp( Application.Action.install );
      strategy_1 = persistenceContext.getStrategy( DomainTierTestConfiguration.STRATEGY_NAME );
      strategy_2 = persistenceContext.getStrategy( DomainTierTestConfiguration.SECOND_STRATEGY_NAME );
   }
   
   @After
   public void afterEachTests() {
      persistenceContext.tearDown( Application.Action.stop );
      persistenceContext = null;
   }
   
   @Test
   public void setUp_ForSupportedStrategies() {
      assertEquals("According to the 'default_configuration.xml' the number of instantiated PersistentStrategies is:", 2, persistenceContext.getSupportedStrategies().size());
      assertTrue("HibernateStrategy is supported.", persistenceContext.getSupportedStrategies().containsKey( DomainTierTestConfiguration.STRATEGY_NAME ));
      assertTrue("InMemoryStrategy is supported.", persistenceContext.getSupportedStrategies().containsKey( DomainTierTestConfiguration.SECOND_STRATEGY_NAME ));
   }

   @Test
   public void setUp_ForEventHandlers() {
      PersistenceStrategy strategy_1 = persistenceContext.getSupportedStrategies().get( DomainTierTestConfiguration.STRATEGY_NAME );
      assertEquals("'strategy_1' contains only one event handler.", 1, strategy_1.getEventHandlers().size() );
      assertTrue("This event handler is:", strategy_1.getEventHandlers().get(0) instanceof HibernatePersistenceProvider );
      
      PersistenceStrategy strategy_2 = persistenceContext.getSupportedStrategies().get( DomainTierTestConfiguration.SECOND_STRATEGY_NAME );
      assertEquals("'strategy_2' contains two event handlers.", 2, strategy_2.getEventHandlers().size() );
      assertTrue("The first event handler is:", strategy_2.getEventHandlers().get(0) instanceof HibernatePersistenceProvider );
      assertTrue("The second event handler is:", strategy_2.getEventHandlers().get(1) instanceof BidirectionalSynchronizationStrategy );
   }
   
   @Ignore
   @Test
   public void setUp_ForRepositories() {
      assertEquals("According to 'default_configuration.xml' the number of instantiated repositories is:", 3, persistenceContext.getAvailableRepositories().size());
      Repository<?> repository = persistenceContext.getAvailableRepositories().get( TestEntityRepository.class );
      assertTrue("The instantiated repository is:", repository instanceof TestEntityRepository );
      assertEquals("'TestEntiryRepository' is supported by", DomainTierTestConfiguration.STRATEGY_NAME, repository.getPersistenceStrategy().getName() );
      assertEquals("'TestEntitiyRepository' supports class:", TestEntity.class, repository.getSupportedAggregateRootClass() );
   }
   
   @Test
   public void setUp_ForEventHandlerInitialization() {
      assertTrue( strategy_1.isConfigured() );
      assertTrue( strategy_2.isConfigured() );
      HibernatePersistenceProvider hibernateProvider = (HibernatePersistenceProvider) strategy_1.getEventHandlers().get( 0 );
      assertTrue( hibernateProvider.isConfigured());
      
      hibernateProvider = (HibernatePersistenceProvider) strategy_2.getEventHandlers().get( 0 );
      assertTrue( hibernateProvider.isConfigured());
      BidirectionalSynchronizationStrategy synchronizationStrategy = (BidirectionalSynchronizationStrategy) strategy_2.getEventHandlers().get( 1 );
      assertTrue( synchronizationStrategy.isConfigured() );
   }
   
   @Test
   public void setUp_ForPersistentClasses() {
      assertEquals("According to 'default_configuration.xml' and 'TestClassList' the number of persistent classes is:", 
            3, 
            persistenceContext.getPersistentClassesSize() ); //Note that, class defintions comes from two sources!
   }
   
   @Test
   public void setUp_ForClassRepositoryMapping() {
      assertNotNull("PersistenceContext builds up a Map of persisted Entity classes and RepositoryInstances.", persistenceContext.getRepositoryByAggregateRootClass(TestEntity.class ));
   }
   
   @Test
   public void setUp_ForGetPersistentDataInitializationStrategy() {
      assertEquals( PersistentDataInitializationStrategies.dropAndCreate, persistenceContext.getPersistentDataInitializationStrategy() );
   }
   
   @Test (expected = UndeclaredRepositoryException.class )
   public void getRepositoryInstance(){
      persistenceContext.getRepositoryInstance( DummyRepository.class );
   }
   
   @Test
   public void getRepositoryByAggregateRootClass() {
      assertNotNull("PersistenceContex bulds up a Map of aggregate root classes and the repositories which serves them.",
            persistenceContext.getRepositoryByAggregateRootClass( User.class ));
   }
   
   @Test
   public void getStrategy() {
      assertThat( persistenceContext.getStrategy( DomainTierTestConfiguration.STRATEGY_NAME ), notNullValue() );
      assertThat( persistenceContext.getStrategy( DomainTierTestConfiguration.STRATEGY_NAME ).getName(), equalTo( DomainTierTestConfiguration.STRATEGY_NAME ) );
   }
   
   private class DummyRepository implements Repository<AggregateRoot> {
      public Integer add(DefaultUnitOfWork work, AggregateRoot entity) { return null; }
      public void delete(DefaultUnitOfWork work, AggregateRoot entity) {}
      public RepositoryResultSet<AggregateRoot> findAll(DefaultUnitOfWork work) {return null;}
      public AggregateRoot findById(DefaultUnitOfWork work, Integer id) {return null;}
      public AggregateRoot findByIdentityExpression(DefaultUnitOfWork work, DefaultIdentityExpression<?> identity) {return null;}
      public RepositoryResultSet<AggregateRoot> findByQuery(DefaultUnitOfWork work, DefaultQuery query) {return null;}
      public void update(DefaultUnitOfWork work, AggregateRoot entity) {}
      public PersistenceStrategy getPersistenceStrategy() { return null; }
      public Class<? extends AggregateRoot> getSupportedAggregateRootClass() { return null; }
      @Override
      public Integer add( UnitOfWork work, AggregateRoot entity ) {
         // TODO Auto-generated method stub
         return null;
      }
      @Override
      public void delete( UnitOfWork work, AggregateRoot entity ) {
         // TODO Auto-generated method stub
         
      }
      @Override
      public RepositoryResultSet<AggregateRoot> findAll( UnitOfWork work ) {
         // TODO Auto-generated method stub
         return null;
      }
      @Override
      public AggregateRoot findById( UnitOfWork work, Integer id ) {
         // TODO Auto-generated method stub
         return null;
      }
      @Override
      public AggregateRoot findByIdentityExpression( UnitOfWork work, IdentityExpression<?> identity ) {
         // TODO Auto-generated method stub
         return null;
      }
      @Override
      public RepositoryResultSet<AggregateRoot> findByQuery( UnitOfWork work, Query query ) {
         // TODO Auto-generated method stub
         return null;
      }
      @Override
      public void update( UnitOfWork work, AggregateRoot entity ) {
         // TODO Auto-generated method stub
         
      }
   }
}
