package com.processpuzzle.party.partyrelationshiptype.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import hu.itkodex.litest.template.RepositoryTestTemplate;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class PartyRoleTypeRepositoryTest extends RepositoryTestTemplate<PartyRoleTypeRepository, PartyRoleTypeRepositoryTestFixture, PartyRoleType> {

   public PartyRoleTypeRepositoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override @Test public void testAdd_ForOwnedAttributesAndComponents() {
      assertEquals( root.getName(), databaseSpy.retrieveColumnFromRow("T_PARTY_ROLE_TYPE", root.getId(), String.class, "name"));
      assertEquals( root.getDescription(), databaseSpy.retrieveColumnFromRow("T_PARTY_ROLE_TYPE", root.getId(), String.class, "description"));
      
      PartyRoleConstraint roleConstraint = fixture.findContraintForPartyType( fixture.getPersonType() );
      assertThat( roleConstraint.getTypeOfParty(), notNullValue() );
      assertThat( (Integer) databaseSpy.retrieveColumnFromRow( "T_PARTY_ROLE_CONSTRAINT", 
                                                                roleConstraint.getId(), 
                                                                Integer.class, 
                                                                "PARTY_TYPE_ID" ), equalTo( fixture.getPersonType().getId() ));
   }

   @Override
   @Ignore
   public void testAdd_ForReferencedAggregateRoots() {
   // TODO Auto-generated method stub

   }

   @Override
   @Test
   public void testDelete_ForOwnedAttributesAndComponents() {
      repository.delete( testWork, root );
      testWork.finish();
      
      assertThat( databaseSpy.rowDoesNotExist( "T_PARTY_ROLE_TYPE", root.getId() ), is( true ));

      root = null;
   }

   @Override
   @Ignore
   public void testFindAll_ForResultCount() {
   // TODO Auto-generated method stub

   }

   @Override
   @Test
   public void testFindById() {
      assertNotNull( fixture.getFatherRoleType().getId() );
   }

   @Override
   @Ignore
   public void testFindById_ForEagerLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   @Ignore
   public void testFindById_ForLazyLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   @Ignore
   public void testFindByQuery_ForComponentAttributes() {
   // TODO Auto-generated method stub

   }

   @Override
   @Ignore
   public void testFindByQuery_ForDirectAttributes() {
   // TODO Auto-generated method stub

   }

   @Override
   @Test
   public void testUpdate_ForOwnedAttributesAndComponents() {
//      partyRoleType.setName("new"); holnap befejezem
//      assertNotSame(partyRoleType.getName(), retrieveColumnFromRow("T_PARTY_ROLE_TYPE", root.getId(), String.class, "naxe"));
   }

   @Override
   @Ignore
   public void testUpdate_ForReferencedAggregateRoots() {
   // TODO Auto-generated method stub

   }
   
}
