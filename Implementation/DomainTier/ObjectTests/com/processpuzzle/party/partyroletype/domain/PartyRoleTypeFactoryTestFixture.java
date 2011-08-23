package com.processpuzzle.party.partyroletype.domain;

import hu.itkodex.litest.template.FactoryTestEnvironment;
import hu.itkodex.litest.template.FactoryTestFixture;

import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleTypeFactory;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.sharedfixtures.domaintier.PartyRoleTypeFixture;

public class PartyRoleTypeFactoryTestFixture extends FactoryTestFixture<PartyRoleTypeFactory, PartyRoleType> {
   private static PartyRoleTypeFixture fixture;

   public PartyRoleTypeFactoryTestFixture( FactoryTestEnvironment<PartyRoleTypeFactory, ?> testEnvironment ) {
      super( testEnvironment );
   }
   
   public void deleteFatherRoleType() {
      fixture.deleteFatherRoleType( applicationContext );
   }
   
   public void deletePersonType() {
      fixture.deletePersonType( applicationContext );
   }
   
   public void deleteSonRoleType() {
      fixture.deleteSonRoleType( applicationContext );
   }
   
   public void saveFatherRoleType() {
      fixture.saveFatherRoleType( applicationContext );
   }
   
   public void savePersonType() {
      fixture.savePersonType( applicationContext );
   }
   
   public void saveSonRoleType() {
      fixture.saveSonRoleType( applicationContext );
   }

   //Properties
   public PartyType getCompanyType() { return fixture.getCompanyType(); }
   public PartyRoleType getFatherRoleType() { return fixture.getFatherRoleType(); }
   public PartyType getPersonType() { return fixture.getPersonType(); }
   public PartyRoleType getSonRoleType() { return fixture.getSonRoleType(); }

   @Override
   protected void configureAfterSutInstantiation() {
      
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      super.configureBeforeSutInstantiation();
      fixture = new PartyRoleTypeFixture();
      fixture.setUp();
   }

   @Override
   protected void releaseResources() {
      if( fixture.getFatherRoleType().getId() != null ) {
         deleteFatherRoleType();
         deletePersonType();
      }
      
      super.releaseResources();
   }

}
