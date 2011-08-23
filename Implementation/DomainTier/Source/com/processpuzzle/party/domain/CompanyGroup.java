/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.domain;

import com.processpuzzle.party.partytype.domain.PartyType;

public class CompanyGroup extends Company {

   CompanyGroup( OrganizationName companyGroupName, PartyType partyType ) {
      super( companyGroupName, partyType );
   }

   protected CompanyGroup() {
      super();
   }
}
