package com.processpuzzle.fundamental_types.uniqueidentifier.domain;

import hu.itkodex.commons.persistence.AggregateRoot;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

public class LastIdNumber extends GenericEntity<LastIdNumber> implements AggregateRoot {
   private String idType;
   private Integer latestNumber;
   
   protected LastIdNumber() {}
   
   LastIdNumber( String idType, Integer latestNumber ) {
      this.idType = idType;
      this.latestNumber = latestNumber;
   }
   
   public @Override @SuppressWarnings("unchecked") LastIdNumberIdentity getDefaultIdentity() {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor("idTypeValue", idType );
      LastIdNumberIdentity identity = new LastIdNumberIdentity( context );
      return identity;
   }

   public String getIdType() {
      return idType;
   }

   public Integer getLatestNumber() {
      return latestNumber;
   }

   public void setLatestNumber( Integer latestNumber ) {
      this.latestNumber = latestNumber;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }
}
