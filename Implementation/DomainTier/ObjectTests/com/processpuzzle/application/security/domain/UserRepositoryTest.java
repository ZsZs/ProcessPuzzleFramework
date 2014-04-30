package com.processpuzzle.application.security.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.util.Iterator;

import org.junit.Test;

import com.processpuzzle.litest.template.RepositoryTestTemplate;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class UserRepositoryTest extends RepositoryTestTemplate<UserRepository, UserRepositoryTestFixture, User> {
   private static final String USER_TABLE_NAME = "T_USER";
   private static final String ACCESS_RIGHT_TABLE_NAME = "T_ACCESS_RIGHT";

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
   }

   @Override
   public void testUpdate_ForReferencedAggregateRoots() {
   }

   @Override
   public void testDelete_ForOwnedAttributesAndComponents() {
   }

   @Override
   public void testFindAll_ForResultCount() {
   }

   @Override
   public void testFindById() {
   }

   @Override
   public void testFindById_ForEagerLoadedComponents() {
   }

   @Override
   public void testFindById_ForLazyLoadedComponents() {
   }

   @Override
   public void testFindByQuery_ForComponentAttributes() {
   }

   @Override
   public void testFindByQuery_ForDirectAttributes() {
   }
   
   @Test
   public final void testFindByUserName() {
      assertNotNull( "We can find a user by it's username.", repository.findUserByName( testWork, "bela" ));
   }
}
