package com.processpuzzle.workflow.protocol.domain;

import com.processpuzzle.litest.template.RepositoryTestEnvironment;
import com.processpuzzle.litest.template.RepositoryTestFixture;

public class ProtocolRepositoryTestFixture extends RepositoryTestFixture<ProtocolRepository, Protocol> {
   private XPLifecycleFixture fixture;
   private LifecycleProtocol xpLifecycle;

   protected ProtocolRepositoryTestFixture( RepositoryTestEnvironment<ProtocolRepository, RepositoryTestFixture<ProtocolRepository,Protocol>> testEnvironment ) {
      super( testEnvironment );
   }

   public LifecycleProtocol getXpLifecycle() {
      return xpLifecycle;
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      fixture = new XPLifecycleFixture();
      fixture.setUp();
      xpLifecycle = fixture.getLifecyle();
   }

   @Override
   protected Protocol createNewAggregate() throws Exception {
      return xpLifecycle;
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
