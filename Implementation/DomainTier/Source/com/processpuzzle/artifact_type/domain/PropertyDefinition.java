package com.processpuzzle.artifact_type.domain;

import hu.itkodex.commons.persistence.Entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

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
