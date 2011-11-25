/*
Name: 
    - QuantityTypeMapping 

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

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

import com.processpuzzle.application.configuration.domain.MeasurementContext;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class QuantityTypeMapping implements CompositeUserType {

   public Class<Quantity> returnedClass() {
      return Quantity.class;
   }

   public boolean equals(Object x, Object y) throws HibernateException {
      if (x == y)
         return true;
      if (x == null || y == null)
         return false;
      return x.equals(y);
   }

   public Object deepCopy(Object value) throws HibernateException {
      return value;
   }

   public boolean isMutable() {
      return false;
   }

   public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
      Double amount = (Double) Hibernate.DOUBLE.nullSafeGet( resultSet, names[0]);
      String unitSymbol = (String) Hibernate.STRING.nullSafeGet( resultSet, names[1]);
      if (amount == null && unitSymbol == null)
         return null;
      else {
         ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
         MeasurementContext measurementContext = applicationContext.getMeasurementContext();
         return new Quantity( amount, measurementContext.findUnitBySymbol(unitSymbol) );
      }
   }

   public void nullSafeSet(PreparedStatement statement, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
      Quantity quantity = (Quantity) value;
      
      if( quantity != null && quantity.getAmount() != null && quantity.getUnit() != null ) {
         Hibernate.DOUBLE.nullSafeSet( statement, quantity.getAmount(), index );
         Hibernate.STRING.nullSafeSet(statement, quantity.getUnit().getSymbol(), index + 1);
      } else {
         statement.setNull(index, Types.DOUBLE );
         statement.setNull(index + 1, Types.VARCHAR );
      }
   }

   public String[] getPropertyNames() {
      return new String[] { "amount", "unit" };
   }

   public Type[] getPropertyTypes() {
      return new Type[] { Hibernate.DOUBLE, Hibernate.STRING };
   }

   public Object getPropertyValue( Object component, int property) throws HibernateException {
      Quantity quantity = (Quantity) component;
      if( property == 0 ) return quantity.getAmount();
      else return quantity.getUnit();
   }

   public void setPropertyValue(Object component, int property, Object value) throws HibernateException {
      throw new UnsupportedOperationException("Quantity is immutable!");
   }
   
   public Object assemble(Serializable cached, SessionImplementor session, Object owner) throws HibernateException {
      return cached;
   }

   public Serializable disassemble(Object value, SessionImplementor session) throws HibernateException {
      return (Serializable) value;
   }

   public int hashCode(Object arg0) throws HibernateException {
      return arg0.hashCode();
   }

   public Object replace(Object original, Object target, SessionImplementor session, Object owner) throws HibernateException {
      // TODO Ez itt lehet hogy nem jo, nem talaltam ra peldat
      // http://www.hibernate.org/hib_docs/v3/api/org/hibernate/usertype/CompositeUserType.html#replace(java.lang.Object,%20java.lang.Object,%20org.hibernate.engine.SessionImplementor,%20java.lang.Object)
      Object[] copy = new Object[] { ((Object[]) original)[0], ((Object[]) original)[1] };
      return copy;
   }
}
