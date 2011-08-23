package com.processpuzzle.party.domain;

import hu.itkodex.litest.template.RepositoryTestEnvironment;
import hu.itkodex.litest.template.RepositoryTestFixture;

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
