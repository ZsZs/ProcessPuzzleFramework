package com.processpuzzle.application.security.domain;

import com.processpuzzle.litest.template.RepositoryTestEnvironment;
import com.processpuzzle.litest.template.RepositoryTestFixture;

public class UserRepositoryTestFixture extends RepositoryTestFixture<UserRepository, User> {

   public UserRepositoryTestFixture( RepositoryTestEnvironment<UserRepository, RepositoryTestFixture<UserRepository,User>> testEnvironment ) {
      super( testEnvironment );
   }

   @Override
   protected User createNewAggregate() throws Exception {
      User bela = new User("bela", "belasWeakPass");
      
      AccessControlledObject aControlledObject = new AnAccessControlledObject( new Integer(10) );
      bela.addRightFor( aControlledObject, true, false, true, false );

      AccessControlledObject anotherControlledObject = new AnAccessControlledObject( new Integer(11) );
      bela.addRightFor( anotherControlledObject, true, true, true, true );

      return bela;
   }

   @Override
   protected void afterAggregateCreation() {
   }

   @Override
   protected void afterAggregateDeletion() {
   }

   @Override
   protected void beforeAggregateCreation() {
   }
}
