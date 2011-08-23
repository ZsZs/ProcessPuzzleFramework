package com.processpuzzle.party.artifact;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.artifact.domain.ArtifactFactoryTest;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class PersonDataSheetFactoryTest extends ArtifactFactoryTest<PersonDataSheetFactory, PersonDataSheetFactoryTestFixture, PersonDataSheet> {

   public PersonDataSheetFactoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   public void create_ForSuccess() {
      PersonDataSheet personDataSheet = fixture.createTestPersonDataSheet();

      assertThat( personDataSheet, notNullValue() );
      assertThat( personDataSheet.getName(), equalTo( "Keszeg József" ) );
   }

   @Ignore
   @Override
   @Test
   public void create_ForCollision() {}

   @Ignore
   @Test
   public void getPerson() {
      PersonDataSheet personDataSheet = fixture.createTestPersonDataSheet();

      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      fixture.getArtifactRepository().add( work, personDataSheet );
      work.finish();

      Integer personId = personDataSheet.getId();
      work = new DefaultUnitOfWork( true );
      personDataSheet = (PersonDataSheet) fixture.getArtifactRepository().findById( work, PersonDataSheet.class, personId );
      Person person = (Person) personDataSheet.getPerson();
      work.finish();

      assertThat( person, notNullValue() );
      assertThat( person.getId(), notNullValue() );
   }
}
