/*
 * Created on 2005.08.15.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.domain;

import com.processpuzzle.party.partytype.domain.PartyType;

public class Division extends OrganizationUnit implements Comparable<Organization> {

   public Division( OrganizationName name, PartyType partyType ) {
      super( name, partyType );
   }

   public Division() {}

   public int compareTo( Organization organization ) {
      if( !(organization instanceof Division) ) return -1;
      Division n = (Division) organization;
      int c;
      if( (c = n.getPartyName().getName().compareTo( getPartyName().getName() )) != 0 )
         return c;
      return 0;
   }

   @Override
   protected void defineIdentityExpressions() {
//      defaultIdentity = new DivisionIdentity();
//      identities.add( defaultIdentity );
   }
}
