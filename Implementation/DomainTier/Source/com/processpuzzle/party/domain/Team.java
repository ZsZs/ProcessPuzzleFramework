/*
 * Created on Nov 16, 2006
 */
package com.processpuzzle.party.domain;

import java.util.Collection;

import com.processpuzzle.party.partytype.domain.PartyType;

public class Team extends Organization {

   protected Team() {
      super();
   }

   Team( OrganizationName name, PartyType teamType ) {
      super( name, teamType );
   }

   public Collection<Party<?>> getTeamMembers() {
      return null;
   }
}
