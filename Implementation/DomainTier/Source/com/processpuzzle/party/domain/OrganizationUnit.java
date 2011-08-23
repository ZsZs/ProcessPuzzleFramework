/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.domain;

import com.processpuzzle.party.partytype.domain.PartyType;

public class OrganizationUnit extends Organization {

   protected OrganizationUnit() {}

   public OrganizationUnit( OrganizationName name, PartyType partyType ) {
      super( name, partyType );
   }

   @Override
   protected void defineIdentityExpressions() {
//      defaultIdentity = new OrganizationUnitIdentity();
//      identities.add( defaultIdentity );
   }
}
