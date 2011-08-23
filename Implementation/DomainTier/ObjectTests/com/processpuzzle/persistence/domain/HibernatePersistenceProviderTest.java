package com.processpuzzle.persistence.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import hu.itkodex.commons.persistence.PersistentObject;
import hu.itkodex.commons.persistence.RepositoryResultSet;
import hu.itkodex.commons.persistence.query.Count;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.MeasurementContext;
import com.processpuzzle.application.configuration.domain.PersistentDataInitializationStrategies;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.PropertyContext;
import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.IntegerAttributeCondition;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.sharedfixtures.domaintier.TestUserRequestFixture;

public class HibernatePersistenceProviderTest {
   private static String PERSISTENCE_STRATEGY = DomainTierTestConfiguration.STRATEGY_NAME;
   private static String MASTER_PERSISTENCE_PROVIDER = DomainTierTestConfiguration.PERSISTENCE_PROVIDER_NAME;
   private static String DATABASE_DRIVER = "org.hsqldb.jdbcDriver";
   private static String QUERY_TEST_ENTITY = "SELECT * FROM {0} WHERE ID = {1}";
   private static Integer NUMBER_OF_BULK_ENTITITES = 33;
   private static Integer COLLECTION_PAGE_SIZE = 10;
   private static TestUserRequestFixture userRequestFixture;
   private static PropertyContext propertyContext;
   private static MeasurementContext measurementContext;
   private static HierarchicalConfiguration providerConfiguration;
   private static List<Class<?>> persistentClasses = new ArrayList<Class<?>>();
   private static Connection databaseConnection = null;
   private static ProcessPuzzleContext applicationContext;
   private static Application application;
   private HibernatePersistenceProvider persistenceProvider = null;
   private TestEntity testEntity_1 = null;
   private TestEntity testEntity_2 = null;

   @BeforeClass
   public static void beforeAllTests() {
      applicationContext = mock( ProcessPuzzleContext.class );
      application = mock( Application.class );
      
      //when( application.getContext() ).thenReturn( applicationContext );

      propertyContext = new PropertyContext( application, DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      propertyContext.setUp( Application.Action.start );
      //when( applicationContext.getPropertyContext() ).thenReturn( propertyContext );
      
      measurementContext = new MeasurementContext( application );
      measurementContext.setUp( Application.Action.start );
      //when( applicationContext.getMeasurementContext() ).thenReturn( measurementContext );

      userRequestFixture = TestUserRequestFixture.getInstance( application );
      userRequestFixture.setUp();
      
      Object[] parametersForKey = { PERSISTENCE_STRATEGY, MASTER_PERSISTENCE_PROVIDER };
      String configurationAt = PropertyKeys.createKey( PropertyKeys.PERSISTENCE_STRATEGY_EVENT_HANDLER_PROPERTIES.getDefaultKey(), parametersForKey );
      providerConfiguration = propertyContext.getConfigurationAt( configurationAt );

      persistentClasses.add( TestEntity.class );
      persistentClasses.add( TestEntityComponent.class );
      persistentClasses.add( GenericEntity.class );
   }

   @Before
   public void beforeEachTests() {
      configureHibernatePersistenceProvider();
      createJDBCConnection();
      saveTestEntities();
   }

   @Test
   public void testConfigure() {
      assertTrue( persistenceProvider.isConfigured() );
      assertEquals( "configure() sets the properties of hibernate Confifiguration, given in HierarchicalConfiguration. Eg.:",
            DATABASE_DRIVER, persistenceProvider.getHibernateConfiguration().getProperty( "hibernate.connection.driver_class" ) );

      assertNotNull( "configure() also adds classes which are persited by Hibernate. Eg.:", persistenceProvider.getHibernateConfiguration()
            .getClassMapping( TestEntity.class.getName() ) );

      assertNotNull( "configure() initializes a Hibernate SessionFactory.", persistenceProvider.getSessionFactory() );
      assertFalse( persistenceProvider.getSessionFactory().isClosed() );
   }

   @Test
   public void testConfigure_ForHbm2DdlProperty() {
      persistenceProvider = new HibernatePersistenceProvider( "HIBERNATE_PROVIDER", providerConfiguration, persistentClasses,
            PersistentDataInitializationStrategies.update );
      persistenceProvider.configure();
      assertEquals( "We have overriden the 'hibernate.hbm2dd.auto' property to:", PersistentDataInitializationStrategies.update.asString(),
            persistenceProvider.getHibernateConfiguration().getProperty( "hibernate.hbm2ddl.auto" ) );
   }

   @Test
   public void testAdd() {
      assertNotNull( "Hibernate gives an id to all new persisted objects.", testEntity_1.getId() );
      assertEquals( "'TestEntity_1' name field is persited in database.", testEntity_1.getName(), retrieveColumnFromRow( "T_TEST_ENTITY",
            testEntity_1.getId(), String.class, "name" ) );
      assertEquals( "'testEntity_1' textAttribute is persited in database.", testEntity_1.getTextAttribute(), retrieveColumnFromRow(
            "T_TEST_ENTITY", testEntity_1.getId(), String.class, "textAttribute" ) );
      assertEquals( "'testEntity_1' numberAttribute is persited in database.", testEntity_1.getNumberAttribute(), retrieveColumnFromRow(
            "T_TEST_ENTITY", testEntity_1.getId(), Integer.class, "numberAttribute" ) );
      assertEquals( "'testEntity_1' enitiyComponentWithCascade is persisted in database.", testEntity_1.getEnitiyComponentWithCascade()
            .getId(), retrieveColumnFromRow( "T_TEST_ENTITY_COMPONENT", testEntity_1.getEnitiyComponentWithCascade().getId(),
            Integer.class, "id" ) );
      assertEquals(
            "'testEntity_1' enitiyComponentWithoutCascade is persisted in database, but note that it was 'added' separately. See: beforeEachTests()",
            testEntity_1.getEnitiyComponentWithoutCascade().getId(), retrieveColumnFromRow( "T_TEST_ENTITY_COMPONENT", testEntity_1
                  .getEnitiyComponentWithoutCascade().getId(), Integer.class, "id" ) );
   }

   @Test
   public void testUpdate() {
      DefaultUnitOfWork modifyWork = new DefaultUnitOfWork( true );

      modifyTestEntity( testEntity_1, "NewEntityName", "newTextValue", 5311, new GregorianCalendar( 1965, 6, 22 ).getTime() );
      testEntity_1.getEnitiyComponentWithCascade().setName( "NewEntityComponentName" );
      testEntity_1.getEnitiyComponentWithoutCascade().setName( "NewEntityComponentName" );

      modifyWork.start();
      persistenceProvider.update( modifyWork, TestEntity.class, testEntity_1 );
      modifyWork.finish();

      assertEquals( "'testEntity_1' name is chnaged in database.", "NewEntityName", retrieveColumnFromRow( "T_TEST_ENTITY", testEntity_1
            .getId(), String.class, "name" ) );
      assertEquals( "'testEntity_1' textAttribute is modified in database.", "newTextValue", retrieveColumnFromRow( "T_TEST_ENTITY",
            testEntity_1.getId(), String.class, "textAttribute" ) );
      assertEquals( "'testEntity_1' numberAttribute is modified in database.", new Integer(5311), retrieveColumnFromRow( "T_TEST_ENTITY", testEntity_1
            .getId(), Integer.class, "numberAttribute" ) );
      assertEquals( "'testEntity_1' dateAttribute is modified in database.", new GregorianCalendar( 1965, 6, 22 ).getTime(),
            retrieveColumnFromRow( "T_TEST_ENTITY", testEntity_1.getId(), Date.class, "dateAttribute" ) );
      assertEquals(
            "'testEntity_1' enitiyComponentWithCascade is also modified in database.",
            "NewEntityComponentName",
            retrieveColumnFromRow( "T_TEST_ENTITY_COMPONENT", testEntity_1.getEnitiyComponentWithCascade().getId(), String.class, "name" ) );
      assertEquals( "'testEntity_1' enitiyComponentWithoutCascade is NOT modified in database.", "TestEntity_1_Component_2",
            retrieveColumnFromRow( "T_TEST_ENTITY_COMPONENT", testEntity_1.getEnitiyComponentWithoutCascade().getId(), String.class,
                  "name" ) );
   }

   @Test
   public void testDelete() {
      TestEntity entityToDelete = createTestEntity( "EntityToDelete" );
      DefaultUnitOfWork addWork = new DefaultUnitOfWork( true );
      persistenceProvider.add( addWork, TestEntityComponent.class, entityToDelete.getEnitiyComponentWithoutCascade() );
      persistenceProvider.add( addWork, TestEntity.class, entityToDelete );
      addWork.finish();
      Integer id = entityToDelete.getId();

      DefaultUnitOfWork deleteWork = new DefaultUnitOfWork( true );
      persistenceProvider.delete( deleteWork, TestEntity.class, entityToDelete );
      deleteWork.finish();

      assertNull( "We can't find 'entityToDelete' in the database.", retrieveColumnFromRow( "T_TEST_ENTITY", id, Integer.class, "id" ) );
      assertNotNull( "EntityComponentWithoutCascade remains in the database.", retrieveColumnFromRow( "T_TEST_ENTITY_COMPONENT",
            entityToDelete.getEnitiyComponentWithoutCascade().getId(), Integer.class, "id" ) );
   }

   @Test
   public void testFindById() {
      DefaultUnitOfWork findWork = new DefaultUnitOfWork( true );
      assertNotNull( persistenceProvider.findById( findWork, TestEntity.class, testEntity_1.getId() ) );
      assertEquals( testEntity_1, persistenceProvider.findById( findWork, TestEntity.class, testEntity_1.getId() ) );
      findWork.finish();
   }

   @Test public void testFindAll() {
      DefaultUnitOfWork findAllWork = new DefaultUnitOfWork( true );
      RepositoryResultSet<TestEntity> resultSet = persistenceProvider.findAll( findAllWork, TestEntity.class );
      findAllWork.finish();

      assertEquals( "The total number of TestEntities is:", 2, resultSet.size() );
   }

   @Test public void testFindByQuery() {
      DefaultQuery query = new DefaultQuery( TestEntity.class );
      IntegerAttributeCondition condition = new IntegerAttributeCondition( "numberAttribute", 2007, ComparisonOperators.EQUAL_TO );
      query.getQueryCondition().addAttributeCondition( condition );

      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      RepositoryResultSet<? extends PersistentObject> resultSet = persistenceProvider.findByQuery( work, query );
      work.finish();

      assertEquals( "We expect two hits.", 2, resultSet.size() );
      assertEquals( "We found:", testEntity_1, resultSet.getEntityAt( 0 ) );
      assertEquals( "and:", testEntity_2, resultSet.getEntityAt( 1 ) );
   }
   
   @Test public void findByQuery_ShouldConsiderPagingInfo() {
      //SETUP: 
      createAndSaveBulkOfTestEntities();
      DefaultQuery query = new DefaultQuery( TestEntity.class );
      query.setFirstResult( 5 );
      query.setMaxResults( COLLECTION_PAGE_SIZE );
      
      //EXCERCISE:
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      RepositoryResultSet<? extends PersistentObject> resultSet = persistenceProvider.findByQuery( work, query );
      work.finish();

      //VERIFY:
      assertThat( resultSet.size(), equalTo( COLLECTION_PAGE_SIZE ));
      assertThat( ((TestEntity) resultSet.getEntityAt( 0 )).getName().contains( "4" ), is( true ) );
   }
   
   @Test public void findByQuery_ShouldAccepCountFunction() {
      //SETUP: 
      createAndSaveBulkOfTestEntities();
      DefaultQuery query = new DefaultQuery( TestEntity.class );
      query.getAttributeFilter().addAggregateFunction( new Count( "name" ));
      
      //EXCERCISE:
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      RepositoryResultSet<? extends PersistentObject> resultSet = persistenceProvider.findByQuery( work, query );
      work.finish();

      //VERIFY:
      Long count = ((PersistentLong) resultSet.getEntityAt( 0 )).getValue();
      assertThat( count.intValue(), equalTo( NUMBER_OF_BULK_ENTITITES + 2) );
   }

   @Test
   public void testTransactionHandling() {
      DefaultUnitOfWork updateWork = new DefaultUnitOfWork( true );
      TestEntity entity = (TestEntity) persistenceProvider.findById( updateWork, TestEntity.class, testEntity_1.getId() );
      entity.rename( "a new name" );
      persistenceProvider.update( updateWork, TestEntity.class, entity );
      updateWork.discard();

      DefaultUnitOfWork newWork = new DefaultUnitOfWork( true );
      entity = (TestEntity) persistenceProvider.findById( newWork, TestEntity.class, testEntity_1.getId() );
      newWork.finish();
      assertEquals( "New was not saved, the name remains as before 'updateWork'.", testEntity_1.getName(), entity.getName() );
   }

   @Test
   public void testGetDriverClass_ForSuccess() {
      String expectedDriverClass = providerConfiguration.getString( "hibernate/connection/driver_class" );
      assertThat( persistenceProvider.getDriverClass(), equalTo( expectedDriverClass ) );
   }

   @After
   public void afterEachTests() {
      DefaultUnitOfWork deletion = new DefaultUnitOfWork( false );
      deletion.start();
      persistenceProvider.delete( deletion, TestEntity.class, testEntity_1 );
      deletion.finish();
      persistenceProvider = null;
   }

   @AfterClass
   public static void afterAllTests() {
      propertyContext.tearDown( Application.Action.stop );
   }

   // Private helper methods
   private void configureHibernatePersistenceProvider() {
      persistenceProvider = new HibernatePersistenceProvider( "HIBERNATE_PROVIDER", providerConfiguration, persistentClasses,
            PersistentDataInitializationStrategies.dropAndCreate );
      persistenceProvider.configure();
   }
   
   private void createAndSaveBulkOfTestEntities() {
      String entityBaseName = "BulkTestEntity_";
      DefaultUnitOfWork setUpWork = new DefaultUnitOfWork( true );
      
      for( int i = 1; i <= NUMBER_OF_BULK_ENTITITES; i++ ){
         String entityName = entityBaseName + new Integer( i ).toString();
         TestEntity testEntity = new TestEntity( entityName );
         persistenceProvider.add( setUpWork, TestEntity.class, testEntity );
      }
      setUpWork.finish();
   }

   private void createJDBCConnection() {
      String driverClass = persistenceProvider.getDriverClass();
      String connectionUrl = persistenceProvider.getConnectionUrl();
      String user = persistenceProvider.getUserName();
      String password = persistenceProvider.getPassword();

      try{
         Class.forName( driverClass );
         databaseConnection = DriverManager.getConnection( connectionUrl, user, password );
      }catch( SQLException e ){
         // TODO Auto-generated catch block
         e.printStackTrace();
      }catch( ClassNotFoundException e ){
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private void saveTestEntities() {
      testEntity_1 = createTestEntity( "TestEntity_1" );
      testEntity_2 = createTestEntity( "TestEntity_2" );

      DefaultUnitOfWork setUpWork = new DefaultUnitOfWork( false );
      setUpWork.start();
      persistenceProvider.add( setUpWork, TestEntityComponent.class, testEntity_1.getEnitiyComponentWithoutCascade() );
      persistenceProvider.add( setUpWork, TestEntity.class, testEntity_1 );
      persistenceProvider.add( setUpWork, TestEntityComponent.class, testEntity_2.getEnitiyComponentWithoutCascade() );
      persistenceProvider.add( setUpWork, TestEntity.class, testEntity_2 );
      setUpWork.finish();
   }

   @SuppressWarnings("unchecked")
   private <D> D retrieveColumnFromRow( String table, Integer id, Class<D> dataType, String columnName ) {
      ResultSet resultSet = null;
      
      try{
         Statement statement = databaseConnection.createStatement();
         String queryStatement = MessageFormat.format( QUERY_TEST_ENTITY, new Object[] { table, id.toString() } );
         resultSet = statement.executeQuery( queryStatement );
         resultSet.next();
         switch( hu.itkodex.commons.rdbms.DatabaseSpy.DataType.getTypeIndex( dataType )) {
            case DATE:
               return (D) resultSet.getDate( columnName );
            case INTEGER:
               return (D) new Integer( resultSet.getInt( columnName ) );
            case STRING:
               return (D) resultSet.getString( columnName );
            default:
               return (D) resultSet.getString( columnName );
         }
      }catch( SQLException e ){
         return null;
      }
   }

   private TestEntity createTestEntity( String name ) {
      TestEntity testEntity = new TestEntity( name );
      testEntity.setDateAttribute( new GregorianCalendar( 1960, 12, 9 ).getTime() );
      testEntity.setNumberAttribute( 2007 );
      testEntity.setTextAttribute( "any text" );
      testEntity.setEnitiyComponentWithCascade( new TestEntityComponent( "TestEntity_1_Component_1" ) );
      testEntity.setEnitiyComponentWithoutCascade( new TestEntityComponent( "TestEntity_1_Component_2" ) );
      testEntity.addComponent( new TestEntityComponent( "bulk_1" ) );
      testEntity.addComponent( new TestEntityComponent( "bulk_2" ) );
      return testEntity;
   }

   private void modifyTestEntity( TestEntity testEntity, String name, String text, Integer number, Date date ) {
      testEntity.rename( name );
      testEntity.setTextAttribute( text );
      testEntity.setNumberAttribute( number );
      testEntity.setDateAttribute( date );
   }

   @SuppressWarnings( { "unused", "unchecked" } )
   private static void printPropertyContext() {
      for( Iterator<String> iter = propertyContext.getConfiguration().getKeys(); iter.hasNext(); ){
         String key = (String) iter.next();
         System.err.println( key + " = " + propertyContext.getProperty( key ) );
      }
   }

}
