package com.processpuzzle.party.domain;

import com.processpuzzle.party.partytype.domain.PartyType;

public class OrganizationFactory<O extends Organization> extends PartyFactory<O> {

   public OrganizationFactory() {
      super();
   }
   
   public Organization create( OrganizationName name ) {
      PartyType organizationType = findTypeFor( OrganizationUnit.class );
      return new Organization( name, organizationType );
   }
}
