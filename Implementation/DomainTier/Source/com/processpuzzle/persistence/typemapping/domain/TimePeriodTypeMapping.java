package com.processpuzzle.persistence.typemapping.domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
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
      return new Type[] { Hibernate.TIMESTAMP, Hibernate.TIMESTAMP };
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
      Date begin = (Date) Hibernate.TIMESTAMP.nullSafeGet(rs, names[0]);
      Date end = (Date) Hibernate.TIMESTAMP.nullSafeGet(rs, names[1]);
      if (begin == null && end == null)
         return null;
      else {
         return new TimePeriod(begin != null ? new TimePoint(begin): null, end != null ? new TimePoint(end) : null);
      }
   }

   public void nullSafeSet(PreparedStatement statement, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
      if (value != null) {
         if (((TimePeriod)value).getBegin() != null) {
            Hibernate.TIMESTAMP.nullSafeSet(statement, ((TimePeriod) value).getBegin().getValue(), index);
         } else {
            statement.setNull(index, Types.TIMESTAMP );
         }
         if (((TimePeriod)value).getEnd() != null) {
            Hibernate.TIMESTAMP.nullSafeSet(statement, ((TimePeriod) value).getEnd().getValue(), index + 1);
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
