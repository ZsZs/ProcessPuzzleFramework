package com.processpuzzle.party.artifact;

import com.processpuzzle.litest.template.RepositoryTestEnvironment;
import com.processpuzzle.litest.template.RepositoryTestFixture;

public class CompanyDataSheetRepositoryTestFixture extends RepositoryTestFixture<CompanyDataSheetRepository, CompanyDataSheet> {
   private static final String TEST_ORGANIZATION_NAME = "ITKodex Kft.";
   private String artifactName;
   private static CompanyDataSheetFactory companyDataSheetFactory;
   private CompanyDataSheet companyDataSheet;

   protected CompanyDataSheetRepositoryTestFixture( RepositoryTestEnvironment<CompanyDataSheetRepository, RepositoryTestFixture<CompanyDataSheetRepository,CompanyDataSheet>> testEnvironment ) {
      super( testEnvironment );
   }
   
   //Properties
   public String getArtifactName() { return artifactName; }

   @Override
   protected void configureBeforeSutInstantiation() {
      companyDataSheetFactory = applicationContext.getEntityFactory( CompanyDataSheetFactory.class );
   }

   @Override
   protected CompanyDataSheet createNewAggregate() throws Exception {
      companyDataSheet = companyDataSheetFactory.create( TEST_ORGANIZATION_NAME );
      artifactName = companyDataSheet.getName();
      return companyDataSheet;
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
