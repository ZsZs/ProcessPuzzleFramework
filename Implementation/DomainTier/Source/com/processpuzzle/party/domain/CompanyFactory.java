package com.processpuzzle.party.domain;

import com.processpuzzle.party.partytype.domain.PartyType;

public class CompanyFactory extends PartyFactory<Company> {

   public CompanyFactory() {
      super();
   }
   
   public Company create( String organizationName ) {
      return create( new OrganizationName( organizationName ) );
   }

   public Company create( OrganizationName name ) {
      PartyType companyType = findTypeFor( Company.class );
      return new Company( name, companyType );
   }

}
