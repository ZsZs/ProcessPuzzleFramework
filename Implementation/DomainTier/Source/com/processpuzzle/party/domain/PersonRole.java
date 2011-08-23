package com.processpuzzle.party.domain;

import com.processpuzzle.party.partytype.domain.Role;

public class PersonRole {
   private Integer id;
   private Person person;
   private Role role;

   public PersonRole(Person person, Role role) {
      super();
      this.person = person;
      this.role = role;
      role.addPersonRole(this);
      person.addPersonRole(this);
   }

   public PersonRole() {
      super();
   }

   public Integer getId() {
      return id;
   }

   public Person getPerson() {
      return person;
   }

   public void setPerson(Person person) {
      this.person = person;
   }

   public Role getRole() {
      return role;
   }

   public void setRole(Role role) {
      this.role = role;
   }

}
