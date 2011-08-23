package com.processpuzzle.sharedfixtures.domaintier;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipTypeFactory;

public class PartyRelationshipTypeFixture extends PartyRoleTypeFixture {
   public static final String FATHER_SON_RELATIONSHIP_TYPE_NAME = "FatherSonRelationship";
   protected PartyRelationshipType fatherSonRelationship;

   @Override public void setUp() {
      super.setUp();
      fatherSonRelationship = PartyRelationshipTypeFactory.create( FATHER_SON_RELATIONSHIP_TYPE_NAME, fatherRoleType, sonRoleType );
   }
   
   @Override public void tearDown() {
      fatherSonRelationship = null;
      super.tearDown();
   }
   
   public void saveFatherSonRelationshipType( ProcessPuzzleContext applicationContext ) {
      saveAggregateRoot( fatherSonRelationship, applicationContext );
   }

   public void deleteFatherSonRelationshipType( ProcessPuzzleContext applicationContext ) {
      deleteAggregateRoot( fatherSonRelationship, applicationContext );
   }
   
   public PartyRelationshipType getFatherSonRelationship() { return fatherSonRelationship; }
}
