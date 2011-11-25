/*
Name: 
    - TimeAmountTypeMapping 

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

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

public class TimeAmountTypeMapping implements CompositeUserType {

   public Object assemble(Serializable arg0, SessionImplementor arg1, Object arg2) throws HibernateException {
      // TODO Auto-generated method stub
      return null;
   }

   public Object deepCopy(Object arg0) throws HibernateException {
      // TODO Auto-generated method stub
      return null;
   }

   public Serializable disassemble(Object arg0, SessionImplementor arg1) throws HibernateException {
      // TODO Auto-generated method stub
      return null;
   }

   public boolean equals(Object arg0, Object arg1) throws HibernateException {
      // TODO Auto-generated method stub
      return false;
   }

   public String[] getPropertyNames() {
      // TODO Auto-generated method stub
      return null;
   }

   public Type[] getPropertyTypes() {
      // TODO Auto-generated method stub
      return null;
   }

   public Object getPropertyValue(Object arg0, int arg1) throws HibernateException {
      // TODO Auto-generated method stub
      return null;
   }

   public int hashCode(Object arg0) throws HibernateException {
      // TODO Auto-generated method stub
      return 0;
   }

   public boolean isMutable() {
      // TODO Auto-generated method stub
      return false;
   }

   public Object nullSafeGet(ResultSet arg0, String[] arg1, SessionImplementor arg2, Object arg3) throws HibernateException, SQLException {
      // TODO Auto-generated method stub
      return null;
   }

   public void nullSafeSet(PreparedStatement arg0, Object arg1, int arg2, SessionImplementor arg3) throws HibernateException, SQLException {
   // TODO Auto-generated method stub

   }

   public Object replace(Object arg0, Object arg1, SessionImplementor arg2, Object arg3) throws HibernateException {
      // TODO Auto-generated method stub
      return null;
   }

   public Class<?> returnedClass() {
      // TODO Auto-generated method stub
      return null;
   }

   public void setPropertyValue(Object arg0, int arg1, Object arg2) throws HibernateException {
   // TODO Auto-generated method stub

   }

}
