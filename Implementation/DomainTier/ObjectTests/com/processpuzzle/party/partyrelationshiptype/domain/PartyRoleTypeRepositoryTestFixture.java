package com.processpuzzle.party.partyrelationshiptype.domain;

import hu.itkodex.litest.template.RepositoryTestEnvironment;
import hu.itkodex.litest.template.RepositoryTestFixture;

import java.util.Iterator;

import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.party.partytype.domain.PartyTypeRepository;
import com.processpuzzle.sharedfixtures.domaintier.PartyRoleTypeFixture;

public class PartyRoleTypeRepositoryTestFixture extends RepositoryTestFixture<PartyRoleTypeRepository, PartyRoleType> {
   private PartyRoleTypeFixture fixture;
   private PartyTypeRepository partyTypeRepository;

   protected PartyRoleTypeRepositoryTestFixture( RepositoryTestEnvironment<PartyRoleTypeRepository, RepositoryTestFixture<PartyRoleTypeRepository, PartyRoleType>> testEnvironment ) {
      super( testEnvironment );
   }

   //Public accessors and mutators
   public PartyRoleConstraint findContraintForPartyType( PartyType partyType ) {
      for( Iterator<?> constraints = root.getValidPartyTypes().iterator(); constraints.hasNext(); ){
         PartyRoleConstraint constraint = (PartyRoleConstraint) constraints.next();
         if( constraint.getTypeOfParty().equals( partyType )) return constraint;
      }
      return null;
   }

   //Properties
   public PartyType getCompanyType() { return fixture.getCompanyType(); }
   public PartyRoleType getFatherRoleType() { return fixture.getFatherRoleType(); }
   public PartyType getPersonType() { return fixture.getPersonType(); }
   public PartyRoleType getSonRoleType() { return fixture.getSonRoleType(); }
   
   @Override
   protected void configureBeforeSutInstantiation() {
      fixture = new PartyRoleTypeFixture();
      fixture.setUp();
      savePersonType();
   }

   //Protected, private helper methods
   @Override
   protected PartyRoleType createNewAggregate() throws Exception {
      return fixture.getFatherRoleType();
   }

   @Override
   protected void releaseResources() {
      PartyType personType = fixture.getPersonType();
      partyTypeRepository.delete( personType );      
      fixture.tearDown();
   }

   private void savePersonType() {
      partyTypeRepository = applicationContext.getRepository( PartyTypeRepository.class );
      partyTypeRepository.add( fixture.getPersonType() );
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
