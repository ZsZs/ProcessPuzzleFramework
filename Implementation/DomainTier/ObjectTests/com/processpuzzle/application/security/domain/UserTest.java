package com.processpuzzle.application.security.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ApplicationContextFactory;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class UserTest {
   private static final String USER_NAME = "John.Smith";
   private static final String USER_PASSWORD = "password";
   private static Application application;
   private static ProcessPuzzleContext applicationContext;
   private static UserFactory userFactory;
   private User user = null;
   private AnAccessControlledObject aControlledObject;
   private AnAccessControlledObject anotherControlledObject;

   @BeforeClass
   public static void beforeAllTest() {
      application = mock( Application.class );
      applicationContext = ApplicationContextFactory.create( application, DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      when( application.getContext() ).thenReturn( applicationContext );
      applicationContext.setUp( Application.Action.install );
      userFactory = applicationContext.getEntityFactory( UserFactory.class );
   }

   @Before
   public void beforeEachTests() throws Exception {
      aControlledObject = new AnAccessControlledObject( new Integer( 1 ) );
      aControlledObject.setName( "aControlledObject" );
      anotherControlledObject = new AnAccessControlledObject( new Integer( 2 ) );
      anotherControlledObject.setName( "anotherControlledObject" );

      user = userFactory.createUser( USER_NAME, USER_PASSWORD );
   }

   @After
   public void afterEachTests() throws Exception {
      user = null;
   }

   @AfterClass
   public static void afterAllTest() {
      applicationContext.tearDown( Application.Action.uninstall );
   }

   @Test
   public final void constructor_savesRequiredProperties() {
      assertThat( user.getUserName(), equalTo( USER_NAME ));
      assertThat( user.getPassword(), equalTo( USER_PASSWORD ));
   }

   @Test
   public final void addRightFor() {
      user.addRightFor( aControlledObject, true, false, true, false );
      assertNotNull( "'johnSmith' has rights for 'aControlledObject'", user.getRightFor( aControlledObject ) );

      user.addRightFor( anotherControlledObject, true, true, true, true );
      assertNotNull( "'johnSmith' has rights for 'anotherControlledObject'", user.getRightFor( anotherControlledObject ) );
   }

   @Test( expected = AccessRightConstraintViolationException.class )
   public final void testAddRightFor_ForDuplicateTrial() {
      // Attempt to add duplicate right for the same controlled object throws an exception.
      user.addRightFor( aControlledObject );
      user.addRightFor( aControlledObject );
   }

   @Test
   public final void getRightFor() {
      assertNull( "'johnSmith' doesn't have right for 'aControlledObject'", user.getRightFor( aControlledObject ) );
      user.addRightFor( aControlledObject );
      assertNotNull( "But now, yes.", user.getRightFor( aControlledObject ) );
   }
}
