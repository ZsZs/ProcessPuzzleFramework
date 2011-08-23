package com.processpuzzle.workflow.activity.domain;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.resource.resourcetype.domain.ConsumableResourceType;
import com.processpuzzle.resource.resourcetype.domain.ResourceType;

public class OilType extends GenericEntity<OilType> implements ConsumableResourceType {
   private String name;
   
   public OilType( String name ) {
      this.name = name;
   }

   @Override
   public String getDescription() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String getName() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void setDescription( String description ) {
   // TODO Auto-generated method stub

   }

   @Override
   public Integer getId() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public int compareTo( ResourceType o ) {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public <I extends DefaultIdentityExpression<OilType>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

}
