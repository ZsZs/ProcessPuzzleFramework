package com.processpuzzle.party.partyrelationshiptype.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import hu.itkodex.litest.template.RepositoryTestTemplate;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class PartyRelationshipTypeRepositoryTest extends RepositoryTestTemplate<PartyRelationshipTypeRepository, PartyRelationshipTypeRepositoryTestFixture, PartyRelationshipType> {
   
   public PartyRelationshipTypeRepositoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   @Test
   public void testAdd_ForOwnedAttributesAndComponents() {
      assertEquals( root.getName(), databaseSpy.retrieveColumnFromRow( "T_PARTY_RELATIONSHIP_TYPE", root.getId(), String.class, "name" ) );
      assertEquals( root.getDescription(), databaseSpy.retrieveColumnFromRow( "T_PARTY_RELATIONSHIP_TYPE", root.getId(), String.class, "description" ) );
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
      assertNull( databaseSpy.retrieveColumnFromRow( "T_PARTY_RELATIONSHIP_TYPE", root.getId(), String.class, "name" ) );
   }

   @Override
   @Ignore
   public void testFindAll_ForResultCount() {
   // TODO Auto-generated method stub

   }

   @Override
   @Test
   public void testFindById() {
      assertNotNull( fixture.getFatherSonRelationship().getId() );
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
      root.setDescription( "Bla bla" );
      repository.update( testWork, root );
      testWork.finish();
      assertEquals( root.getDescription(), databaseSpy.retrieveColumnFromRow( "T_PARTY_RELATIONSHIP_TYPE", root.getId(), String.class, "description" ) );
   }

   @Override
   @Ignore
   public void testUpdate_ForReferencedAggregateRoots() {
   // TODO Auto-generated method stub

   }

}
