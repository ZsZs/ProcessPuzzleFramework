package com.processpuzzle.party.domain;

import com.processpuzzle.party.partytype.domain.PartyType;

public class OrganizationUnitFactory extends OrganizationFactory<OrganizationUnit> {

   public OrganizationUnitFactory() {
      super();
   }
   
   public OrganizationUnit create( OrganizationName name ) {
      PartyType organizationUnitType = findTypeFor( OrganizationUnit.class );
      return new OrganizationUnit( name, organizationUnitType );
   }

   public OrganizationUnit create( String organizationName ) {
      return create( new OrganizationName( organizationName ) );
   }
}
