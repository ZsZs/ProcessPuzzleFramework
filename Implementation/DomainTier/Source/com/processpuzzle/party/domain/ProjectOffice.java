package com.processpuzzle.party.domain;

import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

public class ProjectOffice extends OrganizationUnit {

   public ProjectOffice( OrganizationName name, PartyType projectOfficeType ) {
      super( name, projectOfficeType );
   }

   protected ProjectOffice() {
      super();
   }

   public Integer getId() {
      return id;
   }

   protected @Override void defineIdentityExpressions() {
      DefaultQueryContext context = new DefaultQueryContext();
      defaultIdentity = new ProjectOfficeIdentity( context );
      identities.add( defaultIdentity );
   }
}
