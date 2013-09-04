package com.processpuzzle.persistence.domain;


import com.processpuzzle.commons.persistence.Entity;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

public class TestEntityComponent extends GenericEntity<TestEntityComponent> implements Entity {
   private String name;

   // Constructors
   public TestEntityComponent( String name ) {
      this.name = name;
   }

   protected TestEntityComponent() {} // Hibernate

   // Public accessors
   @SuppressWarnings("unchecked")
   @Override
   public TestEntityComponentIdentity getDefaultIdentity() {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor( TestEntityComponentIdentity.NAME_VARIABLE, name );
      TestEntityComponentIdentity identity = new TestEntityComponentIdentity( context );
      return identity;
   }

   public boolean equals( Object object ) {
      TestEntityComponent otherEntity = (TestEntityComponent) object;
      if( this.name.equals( otherEntity.name ) )
         return true;
      else
         return false;
   }

   // Properties
   public String getName() {
      return name;
   }

   public void setName( String name ) {
      this.name = name;
   }

   protected void defineIdentityExpressions() {
   // TODO Auto-generated method stub

   }

}