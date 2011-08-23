/*
 * Created on 2005.09.22.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.domain;

import hu.itkodex.commons.persistence.AggregateRoot;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class RuleSet extends GenericEntity<RuleSet> implements AggregateRoot {
   private Integer id;

   public RuleSet() {
      super();
      // TODO Auto-generated constructor stub
   }

   public Integer getId() {
      return id;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public <I extends DefaultIdentityExpression<RuleSet>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

}
