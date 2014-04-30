package com.processpuzzle.application.domain;

import com.processpuzzle.litest.template.RepositoryTestEnvironment;
import com.processpuzzle.litest.template.RepositoryTestFixture;

public class ApplicationEventRepositoryTestFixture extends RepositoryTestFixture<ApplicationEventRepository, ApplicationEvent> {

   public ApplicationEventRepositoryTestFixture( RepositoryTestEnvironment<ApplicationEventRepository, RepositoryTestFixture<ApplicationEventRepository,ApplicationEvent>> testEnvironment ) {
      super( testEnvironment );
   }

   @Override
   protected ApplicationEvent createNewAggregate() throws Exception {
      ApplicationEvent backupEvent = new BackupEvent( "testEvent" );
      return backupEvent;
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
