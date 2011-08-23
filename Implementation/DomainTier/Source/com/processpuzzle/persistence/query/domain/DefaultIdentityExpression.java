package com.processpuzzle.persistence.query.domain;

import hu.itkodex.commons.persistence.Entity;
import hu.itkodex.commons.persistence.query.IdentityExpression;

import java.lang.reflect.ParameterizedType;


public abstract class DefaultIdentityExpression<E extends Entity> extends DefaultQuery implements IdentityExpression<E> {

   @SuppressWarnings("unchecked")
   public DefaultIdentityExpression( DefaultQueryContext context ) {
      super();
      targetClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
      if( context != null) this.context = context;
      buildQuery();
   }

   public DefaultIdentityExpression() {
      this( null );
   }
   
   
   @SuppressWarnings("unchecked")
   public DefaultIdentityExpression<E> clone() {
      DefaultIdentityExpression<E> clone = (DefaultIdentityExpression<E>) super.clone();
      return clone;
   }

   protected abstract void buildQuery();   
}
