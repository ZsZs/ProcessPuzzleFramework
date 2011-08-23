package com.processpuzzle.persistence.typemapping.domain;

import java.io.Serializable;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

import com.processpuzzle.fundamental_types.domain.TimePoint;

public class TimePointToDateMapping implements CompositeUserType {
   public static final int[] TYPES     = { Types.DATE };

   public Class<TimePoint> returnedClass() {
      return TimePoint.class;
   }

   public boolean equals( Object x, Object y ) throws HibernateException {
      if( x == y )
         return true;
      if( x == null || y == null )
         return false;
      return x.equals( y );
   }

   public Object deepCopy( Object x ) throws HibernateException {
      if( x == null )
         return null;
      TimePoint input = (TimePoint) x;
      TimePoint result = input;
      return result;
   }

   public boolean isMutable() {
      return false;
   }

   public Object nullSafeGet( ResultSet resultSet, String[] names, SessionImplementor session, Object owner ) throws HibernateException, SQLException {
      java.util.Date savedDate = (Date) Hibernate.DATE.nullSafeGet( resultSet, names[0] );
      return new TimePoint( savedDate );
   }

   public void nullSafeSet( PreparedStatement statement, Object objectToSave, int index, SessionImplementor session ) throws HibernateException, SQLException {
      if( objectToSave == null ){
         statement.setNull( index + 1, Types.DATE );
      }else{
         Hibernate.DATE.nullSafeSet( statement, ((TimePoint) objectToSave).getValue(), index );
      }
   }

   public String[] getPropertyNames() {
      return new String[] { "date" };
   }

   public Type[] getPropertyTypes() {
      return new Type[] { Hibernate.DATE };
   }

   public Object getPropertyValue( Object component, int property ) throws HibernateException {
      TimePoint timePoint = (TimePoint) component;
      if( timePoint.getValue() != null )
         return timePoint.getValue();
      else
         return null;
   }

   public Object assemble( Serializable cached, SessionImplementor session, Object owner ) throws HibernateException {
      return deepCopy( cached );
   }

   public Serializable disassemble( Object value, SessionImplementor session ) throws HibernateException {
      return (Serializable) deepCopy( value );
   }

   public int hashCode( Object arg0 ) throws HibernateException {
      return arg0.hashCode();
   }

   public Object replace( Object original, Object target, SessionImplementor session, Object owner ) throws HibernateException {
      return null;
   }

   public void setPropertyValue( Object component, int property, Object value ) throws HibernateException {
      throw new UnsupportedOperationException( "TimePoint is immutable!" );
   }
}
