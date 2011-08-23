package com.processpuzzle.party.partyrelationshiptype.domain;

import hu.itkodex.litest.template.FactoryTestEnvironment;
import hu.itkodex.litest.template.FactoryTestFixture;

import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.party.partytype.domain.PartyTypeFactory;

public class PartyRelationshipTypeFactoryTestFixture extends FactoryTestFixture<PartyRelationshipTypeFactory, PartyRelationshipType> {
   public static final String COMPANY_TYPE_NAME = "CompanyType";
   public static final String FATHER_ROLE_TYPE_NAME = "FatherRole";
   public static final String FATHER_SON_RELATIONSHIP_TYPE_NAME = "FatherSonRelationship";
   public static final String PERSON_TYPE_NAME = "PersonType";
   public static final String SON_ROLE_TYPE_NAME = "SonRole";
   private PartyType companyType;
   private PartyRelationshipType fatherSonRelationship;
   private PartyType personType;
   private PartyRoleType fatherRoleType;
   private PartyRoleType sonRoleType;
   
   //Constructors
   public PartyRelationshipTypeFactoryTestFixture( FactoryTestEnvironment<PartyRelationshipTypeFactory, ?> testEnvironment ) {
      super( testEnvironment );
   }

   //Public accessor and mutator methods
   public void deleteFatherRoleType() {
      deleteAggregateRoot( fatherRoleType );
   }

   public void deleteFatherSonRelationshipType() {
      deleteAggregateRoot( fatherSonRelationship );
   }

   public void deletePersonType() {
      if( personType != null && personType.getId() != null ) {
         deleteAggregateRoot( personType );
         personType = null;
      }
   }

   public void deleteSonRoleType() {
      deleteAggregateRoot( sonRoleType );
   }

   public void saveFatherRoleType() {
      savePersonType();
      saveAggregateRoot( fatherRoleType);
   }

   public void saveFatherSonRelationshipType() {
      saveAggregateRoot( fatherSonRelationship );
   }

   public void savePersonType() { 
      if( personType.getId() == null ) saveAggregateRoot( personType ); 
   }

   public void saveSonRoleType() {
      savePersonType();
      saveAggregateRoot( sonRoleType);
   }

   //Properties
   public PartyType getCompanyType() { return companyType; }
   public PartyRelationshipType getFatherSonRelationship() { return fatherSonRelationship; }
   public PartyType getPersonType() { return personType; }
   public PartyRoleType getFatherRoleType() { return fatherRoleType; }
   public PartyRoleType getSonRoleType() { return sonRoleType; }

   //Protected, private helper methods
   @Override
   protected void configureAfterSutInstantiation() {
      PartyTypeFactory partyTypeFactory = new PartyTypeFactory();
      
      personType = partyTypeFactory.create( PERSON_TYPE_NAME );
      personType.setDescription( "Represents a natural person." );
      
      companyType = partyTypeFactory.create( COMPANY_TYPE_NAME );
      companyType.setDescription( "Represents the type of all legal business entities." );
      
      fatherRoleType = PartyRoleTypeFactory.create( FATHER_ROLE_TYPE_NAME, "Father of a child." );
      fatherRoleType.addPlayerPartyType( personType );      
      
      sonRoleType = PartyRoleTypeFactory.create( SON_ROLE_TYPE_NAME, "Father of a child." );
      sonRoleType.addPlayerPartyType( personType );
      
      fatherSonRelationship = PartyRelationshipTypeFactory.create( FATHER_SON_RELATIONSHIP_TYPE_NAME, fatherRoleType, sonRoleType );
   }

   @Override
   protected void releaseResources() {
      super.releaseResources();
      if( getFatherSonRelationship().getId() != null ) {
         deleteFatherSonRelationshipType();
         deleteFatherRoleType();
         deleteSonRoleType();
         deletePersonType();
      }
   }

}
