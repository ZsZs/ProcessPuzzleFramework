/*
 * Created on Jul 2, 2006
 */
package com.processpuzzle.application.security.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.fundamental_types.domain.AssertionException;
import com.processpuzzle.litest.template.FactoryTestTemplate;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

/**
 * @author zsolt.zsuffa
 */
public class UserFactoryTest extends FactoryTestTemplate<UserFactory, UserFactoryTestFixture, User> {

   public UserFactoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Ignore
   @Test
   public final void createUser() {
      assertEquals( "John's user name is:", "john.smith", fixture.getJohnSmith().getUserName() );
   }

   @Ignore
   @Test( expected = AssertionException.class )
   public final void createUser_ForMissingUserName() {
      // Note that user name can't be null
      sut.createUser( null, "pws" );
   }

   @Ignore
   @Test( expected = AssertionException.class )
   public final void createUser_ForMissingPassword() {
      // Note that password can't be null
      sut.createUser( "betty.blue", "" );
   }

   @Ignore
   @Test( expected = EntityIdentityCollitionException.class )
   public final void createUser_ForNameCollision() {
      UserRepository repository = (UserRepository) applicationContext.getRepository( UserRepository.class );
      DefaultUnitOfWork addWork = new DefaultUnitOfWork( true );
      repository.addUser( addWork, fixture.getJohnSmith() );
      addWork.finish();

      // Note that with this identity( username/password ) a user already exist.
      sut.createUser( "john.smith", "psw" );
   }

   @Override
   public void create_ForCollision() {
   // TODO Auto-generated method stub

   }

   @Override
   public void create_ForSuccess() {
   // TODO Auto-generated method stub

   }
}
