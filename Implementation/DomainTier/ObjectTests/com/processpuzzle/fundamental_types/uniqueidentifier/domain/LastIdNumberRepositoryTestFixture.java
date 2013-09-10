package com.processpuzzle.fundamental_types.uniqueidentifier.domain;

import com.processpuzzle.litest.template.RepositoryTestEnvironment;
import com.processpuzzle.litest.template.RepositoryTestFixture;

public class LastIdNumberRepositoryTestFixture extends RepositoryTestFixture<LastIdNumberRepository, LastIdNumber> {
   public static final String orderIdType = "com.processpuzzle.order.domain.order.OrderIdentifier";
   public static final String orderLineIdType = "com.processpuzzle.order.domain.order.OrderLineIdentifier";

   protected LastIdNumberRepositoryTestFixture( RepositoryTestEnvironment<LastIdNumberRepository, RepositoryTestFixture<LastIdNumberRepository,LastIdNumber>> testEnvironment ) {
      super( testEnvironment );
   }

   //Properties
   
   //Protected, private helper methods
   @Override
   protected LastIdNumber createNewAggregate() throws Exception {
      String idType = orderLineIdType;
      Integer initialIdNumber = 1;
      
      return new LastIdNumber(idType,initialIdNumber );
   }

   @Override
   protected void afterAggregateCreation() {
   }

   @Override
   protected void afterAggregateDeletion() {
   }

   @Override
   protected void beforeAggregateCreation() {
   }
}
