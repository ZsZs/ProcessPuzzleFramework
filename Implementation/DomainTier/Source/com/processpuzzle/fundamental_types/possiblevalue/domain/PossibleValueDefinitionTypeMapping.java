/*
Name: 
    - PossibleValueDefinitionTypeMapping

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

package com.processpuzzle.fundamental_types.possiblevalue.domain;

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

public class PossibleValueDefinitionTypeMapping implements CompositeUserType {


   public Class<PossibleValueDefinition> returnedClass() {
      return PossibleValueDefinition.class;
   }

   public boolean equals(Object x, Object y) throws HibernateException {
      if (x == y)
         return true;
      if (x == null || y == null)
         return false;
      return x.equals(y);
   }

   public Object deepCopy(Object x) throws HibernateException {
      if (x == null) return null;
      PossibleValueDefinition input = (PossibleValueDefinition) x;
      PossibleValueDefinition result = input;
      return result;
   }

   public boolean isMutable() {
      return false;
   }

   public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
      String valueDefinitionClassDiscriminator = (String) Hibernate.STRING.nullSafeGet( resultSet, names[0]);
      Double minAmount = (Double) Hibernate.DOUBLE.nullSafeGet( resultSet, names[1]);
      String minUnitSymbol = (String) Hibernate.STRING.nullSafeGet( resultSet, names[2]);
      Double maxAmount = (Double) Hibernate.DOUBLE.nullSafeGet( resultSet, names[3]);
      String maxUnitSymbol = (String) Hibernate.STRING.nullSafeGet( resultSet, names[4]);
      String possibleValues = (String) Hibernate.STRING.nullSafeGet( resultSet, names[5]);

      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      MeasurementContext measurementContext = applicationContext.getMeasurementContext();
      if( valueDefinitionClassDiscriminator != null && valueDefinitionClassDiscriminator.endsWith("QuantityRange") ) {
         if ( minAmount!=null && minUnitSymbol!=null && maxAmount!=null && maxUnitSymbol!=null) {
            Quantity minValue = new Quantity( minAmount, measurementContext.findUnitBySymbol(minUnitSymbol) );
            Quantity maxValue = new Quantity( maxAmount, measurementContext.findUnitBySymbol(maxUnitSymbol) );
            
            return new QuantityRange( minValue, maxValue );
         }
      } else if( valueDefinitionClassDiscriminator != null && valueDefinitionClassDiscriminator.endsWith( "QuantityEnumeration" )) {
         return new QuantityEnumeration(possibleValues);
      } else if( valueDefinitionClassDiscriminator != null && valueDefinitionClassDiscriminator.endsWith( "StringEnumeration" )) {
         return new StringEnumeration(possibleValues);
      }
      return null;
   }

   public void nullSafeSet(PreparedStatement statement, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
      if ( value != null ) {
         PossibleValueDefinition valueDefinition = (PossibleValueDefinition) value;
         Hibernate.STRING.nullSafeSet(statement, valueDefinition.getClass().getName(), index);
         if (valueDefinition instanceof QuantityRange) {
   
            QuantityRange range = (QuantityRange)valueDefinition;
            if ( range.getMinValue()!=null && range.getMaxValue()!=null ) {
               Hibernate.DOUBLE.nullSafeSet( statement, range.getMinValue().getAmount(), index + 1);
               Hibernate.STRING.nullSafeSet(statement, range.getMinValue().getUnit().getSymbol(), index + 2);
               Hibernate.DOUBLE.nullSafeSet( statement, range.getMaxValue().getAmount(), index + 3 );
               Hibernate.STRING.nullSafeSet(statement, range.getMaxValue().getUnit().getSymbol(), index + 4);
            } else {
               statement.setNull(index + 1, Types.DOUBLE );
               statement.setNull(index + 2, Types.VARCHAR );
               statement.setNull(index + 3, Types.DOUBLE );
               statement.setNull(index + 4, Types.VARCHAR );
            }
            statement.setNull(index + 5, Types.VARCHAR );
            
         } else if (valueDefinition instanceof StringParseable) {         
            statement.setNull(index + 1, Types.DOUBLE );
            statement.setNull(index + 2, Types.VARCHAR );
            statement.setNull(index + 3, Types.DOUBLE );
            statement.setNull(index + 4, Types.VARCHAR );
            Hibernate.STRING.nullSafeSet(statement, ((StringParseable)valueDefinition).stringValue(), index + 5);
            
         }
      } else {
         statement.setNull(index, Types.VARCHAR );
         statement.setNull(index + 1, Types.DOUBLE );
         statement.setNull(index + 2, Types.VARCHAR );
         statement.setNull(index + 3, Types.DOUBLE );
         statement.setNull(index + 4, Types.VARCHAR );
         statement.setNull(index + 5, Types.VARCHAR );
      }
   }

   public String[] getPropertyNames() {
      return new String[] { "valueDefinitionClassDiscriminator", "minAmount", "minUnit", "maxAmount", "maxUnit", "possibleValues" };
   }

   public Type[] getPropertyTypes() {
      return new Type[] { Hibernate.STRING, Hibernate.DOUBLE, Hibernate.STRING, Hibernate.DOUBLE, Hibernate.STRING, Hibernate.STRING}; 
   }

   public Object getPropertyValue( Object component, int property) throws HibernateException {
      PossibleValueDefinition valueDefinition = (PossibleValueDefinition) component;
      if( property == 0 ) return valueDefinition.getClass().getName();
      if( property == 1 ) {
         if (valueDefinition instanceof QuantityRange) {
            return ((QuantityRange)valueDefinition).getMinValue().getAmount();
         } else {
            return null;
         }
      }
      if( property == 2 ) {
         if (valueDefinition instanceof QuantityRange) {
            return ((QuantityRange)valueDefinition).getMinValue().getUnit().getSymbol();
         } else {
            return null;
         }
      }
      if( property == 3 ) {
         if (valueDefinition instanceof QuantityRange) {
            return ((QuantityRange)valueDefinition).getMaxValue().getAmount();
         } else {
            return null;
         }
      }
      if( property == 4 ) {
         if (valueDefinition instanceof QuantityRange) {
            return ((QuantityRange)valueDefinition).getMaxValue().getUnit().getSymbol();
         } else {
            return null;
         }
      }
      if( property == 5 ) {
         if (valueDefinition instanceof StringParseable) {
            return ((StringParseable)valueDefinition).stringValue();
         } else {
            return null; 
         }
      } else {
         return null;
      } 
   }

   public void setPropertyValue(Object component, int property, Object value) throws HibernateException {
      
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
      // http://www.hibernate.org/hib_docs/v3/api/org/hibernate/usertype/CompositeUserType.html#replace(java.lang.Object,%20java.lang.Object,%20org.hibernate.engine.SessionImplementor,%20java.lang.Object)
      Object[] copy = new Object[] { ((Object[]) original)[0], ((Object[]) original)[1] };
      return copy;
   }
}
