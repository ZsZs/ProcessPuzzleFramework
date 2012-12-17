package com.processpuzzle.application.configuration.domain;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.SystemAdministrator;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.persistence.domain.TestEntityFactory;
import com.processpuzzle.user_session.domain.StaticUserSessionHolder;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.user_session.domain.UserSessionHolder;

public class BeanContainerTest {
   private static String CONTAINER_DEFINITION_PATH = "classpath:com/processpuzzle/application/configuration/domain/BeanContainerDefinition.xml";
   private static Application application;
   private static ProcessPuzzleContext applicationContext;
   private BeanContainer container = null;
   
   @Before public final void beforeEachTests() {
      application = mock( Application.class );
      applicationContext = mock( ProcessPuzzleContext.class );
      
      defineUserRequestContext();
      defineMockApplication();
      
      ResourceLoader loader = new DefaultResourceLoader();
      Resource containerDefinitionXml = loader.getResource( CONTAINER_DEFINITION_PATH );
      container = new BeanContainer( application, containerDefinitionXml );
      container.setUp( Application.Action.start );
   }

   @Test public final void setUp_ShouldConfigureContainer() {
      assertTrue( container.isConfigured() );
      assertTrue( "BeanContainer configures a Spring 'ApplicationContext'.", container.getApplicationContext() instanceof org.springframework.context.ApplicationContext );
   }
   
   @Test public final void setUp_MakesAvailableRequiredBeans() {
      assertNotNull( container.getBean( ProcessPuzzleApplicationComponent.USER_REQUEST_FACTORY.getComponentId() ));
   }
   
   @Test( expected = UndefinedBeanException.class ) 
   public final void getBean_ThrowsExceptionWhenNotFound() {
      container.getBean( "notExistingBean" );
   }
   
   @Test public void getEntityFactoryByEntityClass_DeterminesFactoryFromEntityClass() {
      assertThat( container.getEntityFactoryByEntityClass( TestEntity.class ), instanceOf( TestEntityFactory.class ));
   }
   
   @Test public void getEntityFactory_ShouldDetermineBeanNameFromClassName() {
      UserFactory userFactory = container.getEntityFactory( UserFactory.class );
      assertThat( userFactory, notNullValue() );
   }
   
   @After public final void afterEachTests() {
      container.tearDown( Application.Action.stop );
      container = null;
   }

   private void defineMockApplication() {
      applicationContext = mock( ProcessPuzzleContext.class );
      application = mock( Application.class );
      when( application.getContext() ).thenReturn( applicationContext );
   }

   private void defineUserRequestContext() {
      UserRequestManager requestManager = UserRequestManager.getInstance();
      UserSessionHolder sessionHolder = new StaticUserSessionHolder();
      requestManager.createSession( sessionHolder, new SystemAdministrator(), application );
      requestManager.createRequestContext( sessionHolder );
   }
}
