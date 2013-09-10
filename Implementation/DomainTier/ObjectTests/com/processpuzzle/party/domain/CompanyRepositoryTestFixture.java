package com.processpuzzle.party.domain;

import com.processpuzzle.litest.template.RepositoryTestEnvironment;
import com.processpuzzle.litest.template.RepositoryTestFixture;

public class CompanyRepositoryTestFixture extends RepositoryTestFixture<CompanyRepository, Company> {

   protected CompanyRepositoryTestFixture( RepositoryTestEnvironment<CompanyRepository, RepositoryTestFixture<CompanyRepository,Company>> testEnvironment ) {
      super( testEnvironment );
   }

   @Override
   protected Company createNewAggregate() throws Exception {
      return null;
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
