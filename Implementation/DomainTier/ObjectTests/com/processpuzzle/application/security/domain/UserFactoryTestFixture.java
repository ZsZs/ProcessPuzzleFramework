package com.processpuzzle.application.security.domain;

import com.processpuzzle.litest.template.FactoryTestEnvironment;
import com.processpuzzle.litest.template.FactoryTestFixture;

public class UserFactoryTestFixture extends FactoryTestFixture<UserFactory, User> {
   private static final String USER_NAME = "john.smith";
   private static final String USER_PASSWORD = "psw";
   private User user = null;

   public UserFactoryTestFixture( FactoryTestEnvironment<UserFactory, ?> testEnvironment ) {
      super( testEnvironment );
   }

   @Override
   protected void configureAfterSutInstantiation() {
      user = factory.createUser( USER_NAME, USER_PASSWORD );
   }

   public User getJohnSmith() {
      return user;
   }
}
