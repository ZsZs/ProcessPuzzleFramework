/*
Name: 
    - Role 

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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