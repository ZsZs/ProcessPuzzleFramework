/*
 * Created on 2005.08.15.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.domain;

import com.processpuzzle.party.partytype.domain.PartyType;

public class Department extends OrganizationUnit {

   public Department( OrganizationName name, PartyType partyType ) {
      super( name, partyType );
   }

   protected Department() {
      super();
   }

   @Override
   protected void defineIdentityExpressions() {
//      defaultIdentity = new DepartmentIdentity();
//      identities.add( defaultIdentity );
   }

}
