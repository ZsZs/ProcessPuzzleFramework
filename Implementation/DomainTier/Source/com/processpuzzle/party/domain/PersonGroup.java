package com.processpuzzle.party.domain;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class PersonGroup extends GenericEntity<PersonGroup> {
   private Person person;
   private Group grp;

   public PersonGroup(Group group, Person person) {
      this.grp = group;
      this.person = person;
      group.addPersonGroup(this);
      person.addPersonGroup(this);
   }

   public PersonGroup() {}

   public Group getGrp() {
      return grp;
   }

   public void setGrp(Group group) {
      this.grp = group;
   }

   public Person getPerson() {
      return person;
   }

   public void setPerson(Person person) {
      this.person = person;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public <I extends DefaultIdentityExpression<PersonGroup>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }
}
