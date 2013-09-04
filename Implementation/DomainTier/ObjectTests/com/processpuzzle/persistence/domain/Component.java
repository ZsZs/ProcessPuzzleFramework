package com.processpuzzle.persistence.domain;


import com.processpuzzle.commons.persistence.AggregateRoot;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public abstract class Component extends GenericEntity<Component> implements AggregateRoot {
   private String name;
   private Composite parent;
   
   protected Component() {
      super();
   }
   
   Component( String name ) {
      this.name = name;
   }
   
   public String getName() { return name; }
   public Composite getParent() { return parent; }
   
   public @Override @SuppressWarnings("unchecked") DefaultIdentityExpression getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   protected @Override void defineIdentityExpressions() {
      // TODO Auto-generated method stub      
   }

   public void setParent( Composite parent ) {
      this.parent = parent;
   }
}
