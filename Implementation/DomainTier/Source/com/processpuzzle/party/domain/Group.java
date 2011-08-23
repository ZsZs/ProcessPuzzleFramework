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
