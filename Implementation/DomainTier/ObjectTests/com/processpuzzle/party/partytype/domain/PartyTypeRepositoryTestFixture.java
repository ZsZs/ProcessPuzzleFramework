package com.processpuzzle.party.partytype.domain;


import com.processpuzzle.litest.template.RepositoryTestEnvironment;
import com.processpuzzle.litest.template.RepositoryTestFixture;
import com.processpuzzle.sharedfixtures.domaintier.PartyTypeFixture;

public class PartyTypeRepositoryTestFixture extends RepositoryTestFixture<PartyTypeRepository, PartyType> {
   private static PartyTypeFixture fixture;

   protected PartyTypeRepositoryTestFixture( RepositoryTestEnvironment<PartyTypeRepository, RepositoryTestFixture<PartyTypeRepository, PartyType>> testEnvironment ) {
      super( testEnvironment );
   }

   //Properties
   public PartyType getPersonType() { return fixture.getPersonType(); }
   
   //Protected, private helper methods
   @Override
   protected PartyType createNewAggregate() throws Exception {
      return fixture.getPersonType();
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
