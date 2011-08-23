/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.domain;

import hu.itkodex.commons.persistence.Entity;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class Address extends GenericEntity<Address> implements Entity {
   private String usedFor;
   private String name;
   private TimePeriod valid;
   private boolean isDefault = false;

   public Address() {}

   public Address(boolean isDefault) {
      this.isDefault = isDefault;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public TimePeriod getValid() {
      return valid;
   }

   public void setValid(TimePeriod valid) {
      this.valid = valid;
   }

   public boolean getIsDefault() {
      return isDefault;
   }

   public void setIsDefault(boolean isDefault) {
      this.isDefault = isDefault;
   }

   public @Override <I extends DefaultIdentityExpression<Address>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

   public String getUsedFor() {
      return usedFor;
   }

   public void setUsedFor( String usedFor ) {
      this.usedFor = usedFor;
   }

}
