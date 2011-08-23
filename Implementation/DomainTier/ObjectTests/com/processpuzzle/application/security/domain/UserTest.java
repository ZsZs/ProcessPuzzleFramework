/*
 * Created on May 25, 2006
 */
package com.processpuzzle.application.security.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ApplicationContextFactory;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

/**
 * @author zsolt.zsuffa
 */
public class UserTest {
   private static Application application;
   private static ProcessPuzzleContext applicationContext;
   private static UserFactory userFactory;
   private User johnSmith = null;
   private AnAccessControlledObject aControlledObject;
   private AnAccessControlledObject anotherControlledObject;

   @BeforeClass
   public static void beforeAllTest() {
      applicationContext = ApplicationContextFactory.create( application, DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      applicationContext.setUp( Application.Action.start );
      userFactory = applicationContext.getEntityFactory( UserFactory.class );
   }
   
   @AfterClass
   public static void afterAllTest() {
      applicationContext.tearDown( Application.Action.stop );
   }
   
   @Before
   public void beforeEachTests() throws Exception {
      aControlledObject = new AnAccessControlledObject(new Integer(1));
      aControlledObject.setName("aControlledObject");
      anotherControlledObject = new AnAccessControlledObject(new Integer(2));
      anotherControlledObject.setName("anotherControlledObject");

      johnSmith = userFactory.createUser("John.Smith", "password");
   }

   @After
   public void afterEachTests() throws Exception {
      johnSmith = null;
   }

   @Test
   public final void constructor_ForRequiredAttributes () {
      assertEquals("After createtion the usename should be:", "John.Smith", johnSmith.getUserName());
      assertEquals("After createtion the password should be:", "password", johnSmith.getPassword());
   }

   @Test
   public final void addRightFor () {
      johnSmith.addRightFor( aControlledObject, true, false, true, false );
      assertNotNull("'johnSmith' has rights for 'aControlledObject'", johnSmith.getRightFor(aControlledObject));

      johnSmith.addRightFor( anotherControlledObject, true, true, true, true );
      assertNotNull("'johnSmith' has rights for 'anotherControlledObject'", johnSmith.getRightFor(anotherControlledObject));
   }
   
   @Test (expected = AccessRightConstraintViolationException.class)
   public final void testAddRightFor_ForDuplicateTrial() {
      //Attempt to add duplicate right for the same controlled object throws an exception.
      johnSmith.addRightFor( aControlledObject );
      johnSmith.addRightFor( aControlledObject );
   }
   
   @Test
   public final void getRightFor () {
      assertNull("'johnSmith' doesn't have right for 'aControlledObject'", johnSmith.getRightFor(aControlledObject));
      johnSmith.addRightFor(aControlledObject);
      assertNotNull("But now, yes.", johnSmith.getRightFor(aControlledObject));
   }
}
