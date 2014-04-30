package com.processpuzzle.application.security.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.processpuzzle.fundamental_types.domain.AssertionException;
import com.processpuzzle.litest.template.FactoryTestTemplate;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class UserFactoryTest extends FactoryTestTemplate<UserFactory, UserFactoryTestFixture, User> {

   private static final String USER_PASSWORD = "psw";
   private static final String USER_NAME = "john.smith";

   public UserFactoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   public void create_ForSuccess() {
      assertEquals( "John's user name is:", USER_NAME, templatedFixture.getJohnSmith().getUserName() );
   }

   @Override
   @Test( expected = EntityIdentityCollitionException.class )
   public void create_ForCollision() {
      UserRepository repository = (UserRepository) applicationContext.getRepository( UserRepository.class );
      DefaultUnitOfWork addWork = new DefaultUnitOfWork( true );
      repository.addUser( addWork, templatedFixture.getJohnSmith() );
      addWork.finish();

      sut.createUser( USER_NAME, USER_PASSWORD );
   }
   
   @Test( expected = AssertionException.class )
   public final void createUser_whenNameIsMissing_throws_AssertionException() {
      sut.createUser( null, "pws" );
   }

   @Test( expected = AssertionException.class )
   public final void createUser_whenPasswordIsMissing_throwsAssertionException() {
      sut.createUser( "betty.blue", "" );
   }
}
