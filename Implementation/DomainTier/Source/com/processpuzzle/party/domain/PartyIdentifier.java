package com.processpuzzle.party.domain;

import com.processpuzzle.fundamental_types.uniqueidentifier.domain.GenericUniqueIdentifier;
import com.processpuzzle.fundamental_types.uniqueidentifier.domain.InvalidUniqueIdentifier;
import com.processpuzzle.fundamental_types.uniqueidentifier.domain.UniqueIdentifier;

public class PartyIdentifier extends GenericUniqueIdentifier implements UniqueIdentifier {

   public PartyIdentifier( String identifier ) throws InvalidUniqueIdentifier {
      super( identifier );
   }
   
   @Override
   protected void validate( String identifier ) throws InvalidUniqueIdentifier {
      // TODO Auto-generated method stub
      
   }

}
