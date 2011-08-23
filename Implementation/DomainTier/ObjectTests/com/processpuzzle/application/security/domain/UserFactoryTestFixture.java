package com.processpuzzle.application.security.domain;

import hu.itkodex.litest.template.FactoryTestEnvironment;
import hu.itkodex.litest.template.FactoryTestFixture;

public class UserFactoryTestFixture extends FactoryTestFixture<UserFactory, User> {
   private User johnSmith = null;

   public UserFactoryTestFixture( FactoryTestEnvironment<UserFactory, ?> testEnvironment ) {
      super( testEnvironment );
   }

   @Override
   protected void configureAfterSutInstantiation() {
      johnSmith = factory.createUser("john.smith", "psw");
   }

   public User getJohnSmith() { return johnSmith; }

}
