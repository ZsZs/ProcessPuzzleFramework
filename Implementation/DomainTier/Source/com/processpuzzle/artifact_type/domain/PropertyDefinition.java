/*
Name: 
    - PropertyDefinition

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

package com.processpuzzle.artifact_type.domain;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.processpuzzle.commons.persistence.Entity;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class PropertyDefinition extends GenericEntity<PropertyDefinition> implements Entity {
   private @XmlAttribute String name;
   private @XmlAttribute PropertyType type;
   private @XmlElement(name = "defaultValue") String value;

   public PropertyDefinition( String name, PropertyType type, String value ) {
      this.name = name;
      this.type = type;
      this.value = value;
   }

   public @Override <I extends DefaultIdentityExpression<PropertyDefinition>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   public String getName() {
      return name;
   }

   public PropertyType getType() {
      return type;
   }

   public String getValue() {
      return value;
   }

   public enum PropertyType {
      string( "java.lang.String" ), 
      integer( "java.lang.Integer" ), 
      date( "java.lang.Date" );
      
      PropertyType( String javaClassName ) {
         this.javaClassName = javaClassName;
      }
      
      private String javaClassName;
      
      public String getJavaClassName() { return javaClassName; }
   }

   protected PropertyDefinition() {}

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
   }
}
