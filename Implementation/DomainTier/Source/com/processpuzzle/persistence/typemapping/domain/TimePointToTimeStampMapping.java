/*
Name: 
    - TimePointToTimeStampMapping 

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
import java.sql.Timestamp;
import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

import com.processpuzzle.fundamental_types.domain.TimePoint;

public class TimePointToTimeStampMapping implements CompositeUserType {
//   public static final int[] TYPES     = { Types.TIMESTAMP };

   public Class<TimePoint> returnedClass() { return TimePoint.class; }

   public boolean equals(Object x, Object y) throws HibernateException {
      if (x == y)
         return true;
      if (x == null || y == null)
         return false;
      return x.equals(y);
   }

   public Object deepCopy(Object x) throws HibernateException {
      if (x == null) return null;
      // Date[] result=new Date[1];
      TimePoint input = (TimePoint) x;
      // result[0]=input.getValue();
      TimePoint result = input;
      return result;
   }

   public boolean isMutable() { return false; }

   public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {

      // if (value==null) return null;
      // else{
      // Date dateValue=(Date)Hibernate.DATE.nullSafeGet(rs, names[0]);
      Timestamp timeStamp = (Timestamp) Hibernate.TIMESTAMP.nullSafeGet( rs, names[0] );
      // Calendar time=GregorianCalendar.getInstance();
      // time.setTime(timeValue);
      // Calendar cal=GregorianCalendar.getInstance();
      // cal.setTime(dateValue);
      // cal.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
      // cal.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
      // cal.set(Calendar.SECOND, time.get(Calendar.SECOND));
      // cal.set(Calendar.MILLISECOND, time.get(Calendar.MILLISECOND));
      // return new TimePoint(cal.getTime());
      return new TimePoint( com.processpuzzle.fundamental_types.domain.TimeStamp.toDate( timeStamp ));
      // }
   }

   public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
      if (value == null) {
         // st.setNull(index,Types.DATE);
         st.setNull(index + 1, Types.TIMESTAMP);
      } else {
         // Hibernate.DATE.nullSafeSet(st, ((TimePoint)value).getValue(), index);
         Hibernate.TIMESTAMP.nullSafeSet(st, ((TimePoint) value).getValue(), index);
      }
   }

   public String[] getPropertyNames() { return new String[] { "timeStamp" }; }
   public Type[] getPropertyTypes() { return new Type[] { Hibernate.TIMESTAMP }; }

   public Object getPropertyValue(Object component, int property) throws HibernateException {
      TimePoint timePoint = (TimePoint) component;
      if( timePoint.getValue() != null ) return timePoint.getValue();
      else return null;
   }


   public Object assemble(Serializable cached, SessionImplementor session, Object owner) throws HibernateException {
      return deepCopy(cached);
   }

   public Serializable disassemble(Object value, SessionImplementor session) throws HibernateException {
      return (Serializable) deepCopy(value);
   }

   public int hashCode(Object arg0) throws HibernateException {
      return arg0.hashCode();
   }

   public Object replace(Object original, Object target, SessionImplementor session, Object owner) throws HibernateException {
      return null;
   }

   public void setPropertyValue(Object component, int property, Object value) throws HibernateException {
      throw new UnsupportedOperationException("TimePoint is immutable!");
   }

//   public int[] sqlTypes() { return TYPES; }
}
