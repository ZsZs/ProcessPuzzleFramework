package com.processpuzzle.fundamental_types.domain;

import hu.itkodex.commons.persistence.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

@XmlAccessorType( XmlAccessType.NONE )
public abstract class GenericEntity<E extends Entity> implements Entity {
   protected Integer id = null;
   protected int version = 0;
   protected DefaultIdentityExpression<E> defaultIdentity;
   protected Set<DefaultIdentityExpression<E>> identities = new HashSet<DefaultIdentityExpression<E>>();
   
//Constructors
   protected GenericEntity() {
//      defineIdentityExpressions();
   }

//Public accessors
   public abstract <I extends DefaultIdentityExpression<E>> I getDefaultIdentity();
   protected abstract void defineIdentityExpressions();

//Properties
   public Integer getId() { return id; }
   public void setId( Integer id ) { this.id = id; }
   
   public int getVersion() { return version; }
   public void setVersion( int version ) { this.version = version; }
   
   public Set<DefaultIdentityExpression<E>> getIdentities() { return identities; }
   
//Private helper methods
//   protected abstract void defineIdentityExpressions();
}
