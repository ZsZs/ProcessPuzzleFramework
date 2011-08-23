package com.processpuzzle.sharedfixtures.domaintier;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleTypeFactory;

public class PartyRoleTypeFixture extends PartyTypeFixture {
   public static final String FATHER_ROLE_TYPE_NAME = "FatherRole";
   public static final String SON_ROLE_TYPE_NAME = "SonRole";
   protected PartyRoleType fatherRoleType;
   protected PartyRoleType sonRoleType;

   @Override public void setUp() {
      super.setUp();
      fatherRoleType = PartyRoleTypeFactory.create( FATHER_ROLE_TYPE_NAME, "Father of a child." );
      fatherRoleType.addPlayerPartyType( personType );      
      
      sonRoleType = PartyRoleTypeFactory.create( SON_ROLE_TYPE_NAME, "Father of a child." );
      sonRoleType.addPlayerPartyType( personType );      
   }
   
   @Override public void tearDown() {
      fatherRoleType = null;
      sonRoleType = null;
      super.tearDown();
   }

   public void saveFatherRoleType( ProcessPuzzleContext applicationContext ) {
      savePersonType( applicationContext );
      saveAggregateRoot( fatherRoleType, applicationContext );
   }

   public void saveSonRoleType( ProcessPuzzleContext applicationContext ) {
      savePersonType( applicationContext );
      saveAggregateRoot( sonRoleType, applicationContext );
   }

   public void deleteFatherRoleType( ProcessPuzzleContext applicationContext ) {
      deleteAggregateRoot( fatherRoleType, applicationContext );
   }

   public void deleteSonRoleType( ProcessPuzzleContext applicationContext ) {
      deleteAggregateRoot( sonRoleType, applicationContext );
   }

   public PartyRoleType getFatherRoleType() { return fatherRoleType; }
   public PartyRoleType getSonRoleType() { return sonRoleType; }

}
