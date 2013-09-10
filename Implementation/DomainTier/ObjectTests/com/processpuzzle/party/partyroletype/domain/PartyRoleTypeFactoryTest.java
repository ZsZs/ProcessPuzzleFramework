/*
 * Created on Jun 25, 2006
 */
package com.processpuzzle.party.partyroletype.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.processpuzzle.litest.template.FactoryTestTemplate;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleTypeFactory;
import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;
import com.processpuzzle.sharedfixtures.domaintier.PartyRoleTypeFixture;

/**
 * @author zsolt.zsuffa
 */
public class PartyRoleTypeFactoryTest extends FactoryTestTemplate<PartyRoleTypeFactory, PartyRoleTypeFactoryTestFixture, PartyRoleType> {

   protected PartyRoleTypeFactoryTest( String fixtureContainerConfigurationPath ) {
      super( fixtureContainerConfigurationPath );
      // TODO Auto-generated constructor stub
   }

   @Override @Test public void create_ForSuccess() {
      assertThat( fixture.getFatherRoleType(), notNullValue() );
      assertThat( fixture.getFatherRoleType().getName(), equalTo( PartyRoleTypeFixture.FATHER_ROLE_TYPE_NAME ));
   }

   @Override @Test( expected = EntityIdentityCollitionException.class ) public void create_ForCollision() {
      fixture.savePersonType();
      fixture.saveFatherRoleType();
      
      PartyRoleTypeFactory.create( PartyRoleTypeFixture.FATHER_ROLE_TYPE_NAME );
   }

   @Test public void create_withConstraint() {
      PartyRoleType fatherRoleType;
      fatherRoleType = PartyRoleTypeFactory.create( "FatherRole", "Natural person, who has child.", fixture.getPersonType() );
      assertNotNull( fatherRoleType.getValidPartyTypes() );
      assertEquals( 1, fatherRoleType.getValidPartyTypes().size() );
   }
   
}