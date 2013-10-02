/*
Name: 
    - TimePeriodTypeMapping 

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
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.fundamental_types.domain.TimePoint;

public class TimePeriodTypeMapping implements CompositeUserType {
   private static final int[] TYPES = { Types.TIMESTAMP, Types.TIMESTAMP };

   public int[] sqlTypes() {
      return TYPES;
   }

   public Object assemble(Serializable cached, SessionImplementor session, Object owner) throws HibernateException {
      return deepCopy( cached );
   }

   public Object deepCopy(Object value) throws HibernateException {
      return value; //TimePeriod is immutable      
   }

   public Serializable disassemble(Object value, SessionImplementor session) throws HibernateException {
      return (Serializable) deepCopy(value);
   }

   public boolean equals(Object x, Object y) throws HibernateException {
      if (x == y)
         return true;
      if (x == null || y == null)
         return false;
      return x.equals(y);
   }

   public String[] getPropertyNames() {
      return new String[] { "begin", "end" };
   }

   public Type[] getPropertyTypes() {
      return new Type[] { StandardBasicTypes.TIMESTAMP, StandardBasicTypes.TIMESTAMP };
   }

   public Object getPropertyValue(Object component, int property) throws HibernateException {
      TimePeriod timePeriod = (TimePeriod) component;
      if( property == 0 )
         return timePeriod.getBegin();
      else return timePeriod.getEnd();
   }

   public int hashCode( Object object ) throws HibernateException {
      return object.hashCode();
   }

   public boolean isMutable() {
      return false;
   }

   public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
      Date begin = (Date) StandardBasicTypes.TIMESTAMP.nullSafeGet(rs, names[0], session);
      Date end = (Date) StandardBasicTypes.TIMESTAMP.nullSafeGet(rs, names[1], session);
      if (begin == null && end == null)
         return null;
      else {
         return new TimePeriod(begin != null ? new TimePoint(begin): null, end != null ? new TimePoint(end) : null);
      }
   }

   public void nullSafeSet(PreparedStatement statement, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
      if (value != null) {
         if (((TimePeriod)value).getBegin() != null) {
            StandardBasicTypes.TIMESTAMP.nullSafeSet(statement, ((TimePeriod) value).getBegin().getValue(), index, session);
         } else {
            statement.setNull(index, Types.TIMESTAMP );
         }
         if (((TimePeriod)value).getEnd() != null) {
            StandardBasicTypes.TIMESTAMP.nullSafeSet(statement, ((TimePeriod) value).getEnd().getValue(), index + 1, session);
         } else {
            statement.setNull(index + 1, Types.TIMESTAMP );
         }
      } else {
         statement.setNull(index, Types.TIMESTAMP );
         statement.setNull(index + 1, Types.TIMESTAMP );
      }
   }

   public Object replace(Object original, Object target, SessionImplementor session, Object owner) throws HibernateException {
      return null;
   }

   public Class<TimePeriod> returnedClass() {
      return TimePeriod.class;
   }

   public void setPropertyValue(Object component, int property, Object value) throws HibernateException {
      throw new UnsupportedOperationException("TimePeriod is immutable!");
   }
}
