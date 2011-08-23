package com.processpuzzle.party.artifact;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.artifact.domain.ArtifactFactoryTest;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class UserDataSheetFactoryTest extends ArtifactFactoryTest<UserDataSheetFactory, UserDataSheetFactoryTestFixture, UserDataSheet> {

   public UserDataSheetFactoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   public void create_ForSuccess() {
      UserDataSheet userDataSheet = sut.create( "admin", "password" );
      assertThat( userDataSheet, notNullValue() );
   }

   @Ignore
   @Override
   @Test( expected = EntityIdentityCollitionException.class )
   public void create_ForCollision() {
      // SETUP :
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      UserDataSheet userDataSheet = sut.create( "admin", "password" );
      fixture.getArtifactRepository().add( work, userDataSheet );
      work.finish();

      // EXERCISE :
      sut.create( "admin", "password" );

   }
}
