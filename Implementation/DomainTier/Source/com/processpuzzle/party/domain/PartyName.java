package com.processpuzzle.party.domain;

import java.util.Date;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class PartyName extends GenericEntity<PartyName> implements Comparable<PartyName> {
   protected String name;
   protected String toUse;
   protected Date validFrom;
   protected Date validTo;

   public PartyName() {}

   public PartyName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
   
   public void rename(String name) {
      setName( name );
   }

   public String getToUse() {
      return toUse;
   }

   public void setToUse(String toUse) {
      this.toUse = toUse;
   }

   public Date getValidFrom() {
      return validFrom;
   }

   public void setValidFrom(Date validFrom) {
      this.validFrom = validFrom;
   }

   public Date getValidTo() {
      return validTo;
   }

   public void setValidTo(Date validTo) {
      this.validTo = validTo;
   }

   private void setName(String name) {
      this.name = name;
   }

   public int compareTo( PartyName partyName ) {
      int c;
      if ((c = partyName.getName().compareTo(getName())) != 0)
         return c;
      return 0;
   }

   @Override
   public <I extends DefaultIdentityExpression<PartyName>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }
}
