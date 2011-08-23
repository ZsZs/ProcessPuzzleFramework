package com.processpuzzle.party.partytype.domain;

import com.processpuzzle.persistence.domain.GenericFactory;

public class PartyTypeFactory extends GenericFactory<PartyType> {
   
   public PartyType create( String name ) {
      return new PartyType( name ); 
   }
}
