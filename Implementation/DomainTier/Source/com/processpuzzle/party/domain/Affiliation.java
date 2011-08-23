/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.domain;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class Affiliation extends GenericEntity<Affiliation>{
   private Person person;
   private TimePoint timeFrame;

   protected Affiliation() {}

   public Person getPerson() {
      return person;
   }

   public void setPerson( Person person ) {
      this.person = person;
   }

   public TimePoint getTimeFrame() {
      return timeFrame;
   }

   public void setTimeFrame( TimePoint timeFrame ) {
      this.timeFrame = timeFrame;
   }

   @Override
   public <I extends DefaultIdentityExpression<Affiliation>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }
}
