/*
Name: 
    - MoneyTypeMapping 

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

package com.processpuzzle.persistence.typemapping.domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

import com.processpuzzle.application.configuration.domain.MeasurementContext;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.fundamental_types.quantity.money.domain.Money;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class MoneyTypeMapping implements CompositeUserType {

   public Class<Money> returnedClass() {
      return Money.class;
   }

   public boolean equals(Object x, Object y) throws HibernateException {
      if (x == y)
         return true;
      if (x == null || y == null)
         return false;
      return x.equals(y);
   }

   public Object assemble(Serializable cached, SessionImplementor session, Object owner) throws HibernateException {
      return cached;
   }

   public Object deepCopy(Object value) throws HibernateException {
      return value;
   }

   public Serializable disassemble(Object value, SessionImplementor session) throws HibernateException {
      return (Serializable) value;
   }

   public String[] getPropertyNames() {
      return new String[] {"amount", "currency"};
   }

   public Type[] getPropertyTypes() {
      return new Type[] {StandardBasicTypes.DOUBLE, StandardBasicTypes.STRING};
   }

   public Object getPropertyValue(Object component, int property) throws HibernateException {
      Money money = (Money) component;
      if (property == 0) return money.getAmount();
      else return money.getUnit();
   }

   public int hashCode(Object hash) throws HibernateException {
      return hash.hashCode();
   }

   public boolean isMutable() {
      return false;
   }

   public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
      Double amount = (Double) StandardBasicTypes.DOUBLE.nullSafeGet(resultSet, names[0], session);
      String currencySymbol = (String) StandardBasicTypes.STRING.nullSafeGet(resultSet, names[1], session);
      if (amount == null && currencySymbol == null)
         return null;
      else {
         ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
         MeasurementContext measurementContext = applicationContext.getMeasurementContext();
         return new Money(amount, measurementContext.findUnitBySymbol(currencySymbol));
      }
   }

   public void nullSafeSet(PreparedStatement statement, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
      Money money = (Money) value;
      if (money != null && money.getAmount() != null && money.getUnit() != null) {
         StandardBasicTypes.DOUBLE.nullSafeSet(statement, money.getAmount(), index, session);
//         StandardBasicTypes.CURRENCY.nullSafeSet(statement, money.getUnit().getSymbol(), index+1);
         String currenySymbol = money.getUnit().getSymbol();
         ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
         MeasurementContext measurementContext = applicationContext.getMeasurementContext();
         if ( measurementContext.findUnitBySymbol(currenySymbol) == null ) {
            throw new MoneyTypeMappingException("Unit must have currency symbol in MonyTypeMapping");
         }
         StandardBasicTypes.STRING.nullSafeSet(statement, currenySymbol, index+1, session);
      }
      else {
         statement.setNull(index, Types.DOUBLE);
         statement.setNull(index+1, Types.VARCHAR);
      }
   }

   public Object replace(Object original, Object target, SessionImplementor session, Object owner) throws HibernateException {
      // TODO Auto-generated method stub
      Object[] copy = new Object[] {((Object[]) original)[0], ((Object[]) original)[1]};
      return copy;
   }

   public void setPropertyValue(Object component, int property, Object value) throws HibernateException {
      throw new UnsupportedOperationException("Money is immutable");
   }
}
