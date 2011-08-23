package com.processpuzzle.party.partyrelationshiptype.domain;

import hu.itkodex.litest.template.RepositoryTestEnvironment;
import hu.itkodex.litest.template.RepositoryTestFixture;

import com.processpuzzle.sharedfixtures.domaintier.PartyRelationshipTypeFixture;

public class PartyRelationshipTypeRepositoryTestFixture extends RepositoryTestFixture<PartyRelationshipTypeRepository, PartyRelationshipType> {
   private PartyRelationshipTypeFixture fixture;

   protected PartyRelationshipTypeRepositoryTestFixture( RepositoryTestEnvironment<PartyRelationshipTypeRepository, RepositoryTestFixture<PartyRelationshipTypeRepository, PartyRelationshipType>> testEnvironment ) {
      super( testEnvironment );
   }

   public PartyRelationshipType getFatherSonRelationship() { return fixture.getFatherSonRelationship(); }
   
   @Override
   protected void configureBeforeSutInstantiation() {
      fixture = new PartyRelationshipTypeFixture();
      fixture.setUp();
   }

   @Override
   protected PartyRelationshipType createNewAggregate() throws Exception {
      return fixture.getFatherSonRelationship();
   }

   @Override
   protected void afterAggregateCreation() {
   }

   @Override
   protected void afterAggregateDeletion() {
   }

   @Override
   protected void beforeAggregateCreation() {
   }
}
