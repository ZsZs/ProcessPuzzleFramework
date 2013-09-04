/*
Name: 
    - AutoIdentifier 

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

package com.processpuzzle.util.domain;


import java.util.Calendar;
import java.util.GregorianCalendar;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.AggregateRoot;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class AutoIdentifier extends GenericEntity<AutoIdentifier> implements AggregateRoot {

   private String qualifier;

   private Integer year;

   private Integer cnt;

   private String idType;

   public AutoIdentifier(String qualifier, Integer year, Integer count, String type) {
      this.qualifier = qualifier;
      this.year = year;
      this.cnt = count;
      this.idType = type;
   }

   public AutoIdentifier() {}

   public Integer getCount() {
      return cnt;
   }

   public void setCount(Integer count) {
      this.cnt = count;
   }

   public Integer getYear() {
      return year;
   }

   public void setYear(Integer year) {
      this.year = year;
   }

   public Integer getId() {
      return id;
   }

   public String getIdType() {
      return idType;
   }

   public void setIdType(String idType) {
      this.idType = idType;
   }

   public String getQulifier() {
      return qualifier;
   }

   public void setQulifier(String qulifier) {
      this.qualifier = qulifier;
   }

   public static AutoIdentifier calculate(String idType) {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      AutoIdentifierRepository ar = (AutoIdentifierRepository) ProcessPuzzleContext.getInstance().getRepository(
            AutoIdentifierRepository.class);
      AutoIdentifier lastNumber = ar.getLastAutoIdentifierByIdType(work, idType);
      work.finish();
      int currentYear = new GregorianCalendar().get(Calendar.YEAR);

      Integer year = null;
      Integer count = null;

      if (lastNumber.getYear().intValue() < currentYear) {
         year = new Integer(currentYear);
         count = new Integer(1);
      } else if (lastNumber.getYear().intValue() == currentYear) {
         year = lastNumber.getYear();
         count = new Integer(lastNumber.getCount().intValue() + 1);
      }
      return new AutoIdentifier(lastNumber.getQulifier(), year, count, lastNumber.getIdType());
   }

   public static void actualize(AutoIdentifier current, String idType) {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      AutoIdentifierRepository ar = (AutoIdentifierRepository) ProcessPuzzleContext.getInstance().getRepository(
            AutoIdentifierRepository.class);
      AutoIdentifier lastNumber = ar.getLastAutoIdentifierByIdType(work, idType);

      lastNumber.setYear(current.getYear());
      lastNumber.setCount(current.getCount());

      ar.updateAutoIdentifier(work, lastNumber);
      work.finish();
   }

   @Override
   public String toString() {

      String countString = null;
      if (cnt.intValue() / 10000 >= 1)
         countString = "" + cnt.intValue();
      else if (cnt.intValue() / 1000 >= 1)
         countString = "0" + cnt.intValue();
      else if (cnt.intValue() / 100 >= 1)
         countString = "00" + cnt.intValue();
      else if (cnt.intValue() / 10 >= 1)
         countString = "000" + cnt.intValue();
      else
         countString = "0000" + cnt.intValue();

      return qualifier + "-" + year + "-" + countString;
   }

   protected void defineIdentityExpressions() {
      defaultIdentity = new AutoidentifierIdentity();
      identities.add(defaultIdentity);

   }

   @Override
   public DefaultIdentityExpression getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

}
