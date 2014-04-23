package com.processpuzzle.party.partyrelationshiptype.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.processpuzzle.litest.template.FactoryTestTemplate;
import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.sharedfixtures.domaintier.PartyRelationshipTypeFixture;

public class PartyRelationshipTypeFactoryTest extends FactoryTestTemplate<PartyRelationshipTypeFactory, PartyRelationshipTypeFactoryTestFixture, PartyRelationshipType> {

   public PartyRelationshipTypeFactoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   @Test
   public void create_ForSuccess() {
      assertNotNull( fixture.getFatherSonRelationship() );
      assertThat( fixture.getFatherSonRelationship().getName(), equalTo( PartyRelationshipTypeFixture.FATHER_SON_RELATIONSHIP_TYPE_NAME ) );
   }

   @Override
   @Test( expected = EntityIdentityCollitionException.class )
   public void create_ForCollision() {
      fixture.savePersonType();
      fixture.saveFatherRoleType();
      fixture.saveSonRoleType();
      fixture.saveFatherSonRelationshipType();

      PartyRelationshipTypeFactory.create( PartyRelationshipTypeFixture.FATHER_SON_RELATIONSHIP_TYPE_NAME, fixture.getFatherRoleType(),
            fixture.getSonRoleType() );
   }
}
