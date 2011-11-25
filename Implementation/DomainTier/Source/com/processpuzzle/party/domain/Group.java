/*
Name: 
    - Group 

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

package com.processpuzzle.party.domain;

import java.util.HashSet;
import java.util.Set;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class Group extends GenericEntity<Group> implements Comparable<Object> {
   private String name;
   private Set<Group> subGroups = new HashSet<Group>();
   private Set<PersonGroup> personGroups = new HashSet<PersonGroup>();

   public Group(String name) {
      this.name = name;
   }

   public Group() {}

   public Set<PersonGroup> getPersonGroups() {
      return personGroups;
   }

   public void setPersonGroups(Set<PersonGroup> personGroups) {
      this.personGroups = personGroups;
   }

   public Set<Group> getSubGroups() {
      return subGroups;
   }

   public void setSubGroups(Set<Group> subGroups) {
      this.subGroups = subGroups;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void addPersonGroup(PersonGroup personGroup) {
      this.personGroups.add(personGroup);
   }

   public void deletePersonGroup(PersonGroup personGroup) {
      this.personGroups.remove(personGroup);
   }

   public void addSubGroup(Group subGroup) {
      this.subGroups.add(subGroup);
   }

   public void deleteSubGroup(Group subGroup) {
      this.subGroups.remove(subGroup);
   }

   public int compareTo(Object o) {
      if (!(o instanceof Group))
         return -1;
      Group g = (Group) o;
      int c;
      if ((c = getName().compareTo(g.getName())) != 0)
         return c;
      return 0;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public <I extends DefaultIdentityExpression<Group>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

}
