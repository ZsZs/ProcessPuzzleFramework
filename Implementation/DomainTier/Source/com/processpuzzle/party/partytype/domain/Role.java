/*
 * Created on 2005.07.05.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.partytype.domain;

import java.util.HashSet;
import java.util.Set;

import com.processpuzzle.party.domain.PersonRole;

public class Role extends PartyType {

   private Set<PersonRole> personRoles = new HashSet<PersonRole>();

   public Role(String theName) {
      super(theName);
   }

   public Role() {
      this(null);
   }

   public Set<PersonRole> getPersonRoles() {
      return personRoles;
   }

   public void setPersonRoles(Set<PersonRole> personRoles) {
      this.personRoles = personRoles;
   }

   public void addPersonRole(PersonRole personRole) {
      personRoles.add(personRole);
   }

}
