package com.processpuzzle.application.security.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import hu.itkodex.litest.template.RepositoryTestTemplate;

import java.util.Iterator;

import org.junit.Test;

import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class UserRepositoryTest extends RepositoryTestTemplate<UserRepository, UserRepositoryTestFixture, User> {
   private static final String USER_TABLE_NAME = "T_USER";
   private static final String ACCESS_RIGHT_TABLE_NAME = "T_ACCESS_RIGHT";
   // private static ProcessPuzzleContext configuration = null;
   // private static UserRepository repository = null;
   // private User testUser = null;
   // private AccessRight right = null;
   // private AnAccessControlledObject aControlledObject = null;

   public UserRepositoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   @Test
   public void testAdd_ForOwnedAttributesAndComponents() {
      assertEquals( root.getUserName(), databaseSpy.retrieveColumnFromRow( USER_TABLE_NAME, root.getId(), String.class, "userName"));
      assertEquals( root.getPassword(), databaseSpy.retrieveColumnFromRow( USER_TABLE_NAME, root.getId(), String.class, "password"));
      
      for (Iterator<AccessRight> iter = root.getAccessRightsIterator(); iter.hasNext();) {
         AccessRight right = iter.next();
         assertEquals( root.getId(), databaseSpy.retrieveColumnFromRow(ACCESS_RIGHT_TABLE_NAME, right.getId(), Integer.class, "USER_ID"));
         assertEquals( right.getCanRead(), databaseSpy.retrieveColumnFromRow(ACCESS_RIGHT_TABLE_NAME, right.getId(), Boolean.class, "canRead"));
         assertEquals( right.getCanCreate(), databaseSpy.retrieveColumnFromRow(ACCESS_RIGHT_TABLE_NAME, right.getId(), Boolean.class, "canCreate"));
         assertEquals( right.getCanDelete(), databaseSpy.retrieveColumnFromRow(ACCESS_RIGHT_TABLE_NAME, right.getId(), Boolean.class, "canDelete"));
         assertEquals( right.getCanModify(), databaseSpy.retrieveColumnFromRow(ACCESS_RIGHT_TABLE_NAME, right.getId(), Boolean.class, "canModify"));
      }
   }

   @Override
   public void testAdd_ForReferencedAggregateRoots() {}

   @Override
   public void testUpdate_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testUpdate_ForReferencedAggregateRoots() {
   // TODO Auto-generated method stub

   }

   // @Test
   // public void findUserById() {
   // UnitOfWork work = new UnitOfWork(true);
   // assertNotNull("We can find a user by it's id.", repository.findById(work, testUser.getId()));
   // assertNotSame("The find method creates a new object for the same user.", testUser, repository.findById(work, testUser.getId()));
   // }

   @Override
   public void testDelete_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindAll_ForResultCount() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById_ForEagerLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById_ForLazyLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindByQuery_ForComponentAttributes() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindByQuery_ForDirectAttributes() {
   // TODO Auto-generated method stub

   }
   
   @Test
   public final void testFindByUserName() {
      assertNotNull( "We can find a user by it's username.", repository.findUserByName( testWork, "bela" ));
   }

   // @Before
   // public void setUp() throws Exception {
   // configuration = ProcessPuzzleContext.getInstance();
   // configuration.setUp(TestConfigurationConstants.CONFIGURATION_PROPERTY_FILE);
   // repository = (UserRepository) configuration.getRepository(UserRepository.class);
   // UnitOfWork work = new UnitOfWork(true);
   //
   // aControlledObject = new AnAccessControlledObject(new Integer(1));
   // aControlledObject.setName("anObject");
   // testUser = UserFactory.createUser("testUser", "psw");
   // right = testUser.addRightFor(aControlledObject);
   // right.setCanCreate(true);
   // right.setCanDelete(false);
   // repository.addUser(work, testUser);
   // work.finish();
   // }
   //
   // @After
   // public void tearDown() throws Exception {
   // UnitOfWork work = new UnitOfWork(true);
   // repository.deleteUser(work, testUser);
   // work.finish();
   // }
   //
   // @Test
   // public void testAdd_ForOwnedAttributesAndComponents() {
   // assertNotNull("If we add a user to the repository it get's an id.", testUser.getId());
   // assertEquals(testUser.getUserName(), retrieveColumnFromRow("T_USER", testUser.getId(), String.class, "userName"));
   // assertEquals(testUser.getPassword(), retrieveColumnFromRow("T_USER", testUser.getId(), String.class, "password"));
   // assertEquals(testUser.getLocation(), retrieveColumnFromRow("T_USER", testUser.getId(), String.class, "location"));
   // assertEquals(testUser.getLanguage(), retrieveColumnFromRow("T_USER", testUser.getId(), String.class, "language"));
   // assertEquals(testUser.getCountry(), retrieveColumnFromRow("T_USER", testUser.getId(), String.class, "country"));
   // // assertEquals(right.getCanCreate(), retrieveColumnFromRow("T_ACCESS_RIGHT", right.getId(), Boolean.class, "canCreate"));
   // // assertEquals(right.getCanDelete(), retrieveColumnFromRow("T_ACCESS_RIGHT", right.getId(), Boolean.class, "canDelete"));
   // }

   /*
    * @Test public void updateUser() { testUser.changePassword("newPsw"); testUser.getRightFor(aControlledObject).setCanDelete(true); UnitOfWork work =
    * new UnitOfWork(true); repository.updateUser(work, testUser); Integer userId = testUser.getId(); testUser = null;
    * 
    * testUser = repository.findUserById(work, userId); assertEquals("The value of password shold be updated:", "newPsw", testUser.getPassword());
    * assertTrue("The access right for 'aControlledObject' was updated.", testUser.getRightFor(aControlledObject).getCanDelete()); }
    * 
    * @Test public void findUserByName() { UnitOfWork work = new UnitOfWork(true); assertNotNull("We can find a user by it's name.",
    * repository.findUserByName(work, testUser.getUserName())); work.finish(); }
    * 
    * @Test public void findAllUsers() { User anotherUser = UserFactory.createUser("anotherUser", "bakfity"); UnitOfWork work = new UnitOfWork(true);
    * int numberOfUsersBeforeAdd = repository.findAllUser(work).size(); repository.addUser(work, anotherUser); assertEquals("Now the number of users
    * in the repository is:", numberOfUsersBeforeAdd + 1, repository.findAllUser(work).size()); repository.deleteUser(work, anotherUser);
    * work.finish(); }
    * 
    * @Test public void findRightsForControlledObject() { User anotherUser = UserFactory.createUser("anotherUser", "bakfity"); AccessRight rights =
    * anotherUser.addRightFor(aControlledObject); rights.setCanCreate(false); rights.setCanDelete(false); rights.setCanRead(true);
    * rights.setCanModify(true);
    * 
    * UnitOfWork work = new UnitOfWork(true); repository.addUser(work, anotherUser);
    * 
    * assertEquals("The number of rights for 'aControlledObject' is:", 2, repository.findUserWhoHasAccessTo(work, aControlledObject).size());
    * work.finish(); }
    * 
    */
}
