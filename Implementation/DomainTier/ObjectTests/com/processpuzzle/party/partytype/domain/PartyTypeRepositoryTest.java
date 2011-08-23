package com.processpuzzle.party.partytype.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import hu.itkodex.litest.template.RepositoryTestTemplate;

import org.junit.Test;


public class PartyTypeRepositoryTest extends RepositoryTestTemplate<PartyTypeRepository, PartyTypeRepositoryTestFixture, PartyType> {
   
   protected PartyTypeRepositoryTest( String fixtureContainerConfigurationPath ) {
      super( fixtureContainerConfigurationPath );
   }

   @Override @Test
   public void testAdd_ForOwnedAttributesAndComponents() {
      assertEquals( root.getName(), databaseSpy.retrieveColumnFromRow("T_PARTY_TYPE", root.getId(), String.class, "name") );
   }

   @Override
   public void testAdd_ForReferencedAggregateRoots() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testDelete_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindAll_ForResultCount() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById_ForEagerLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById_ForLazyLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindByQuery_ForComponentAttributes() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindByQuery_ForDirectAttributes() {
   // TODO Auto-generated method stub
   }
   
   public void findByName_SholdReturnOnePartyType() {
      assertThat( repository.findByName( "PersonType" ), equalTo( fixture.getPersonType() ));
   }

   @Override
   public void testUpdate_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testUpdate_ForReferencedAggregateRoots() {
   // TODO Auto-generated method stub

   }

}
