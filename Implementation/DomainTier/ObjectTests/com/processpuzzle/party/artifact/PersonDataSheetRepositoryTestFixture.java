package com.processpuzzle.party.artifact;

import hu.itkodex.litest.template.RepositoryTestEnvironment;
import hu.itkodex.litest.template.RepositoryTestFixture;

public class PersonDataSheetRepositoryTestFixture extends RepositoryTestFixture<PersonDataSheetRepository, PersonDataSheet> {
   public static final String TEST_GIVEN_NAME = "Pista";
   public static final String TEST_FAMILY_NAME = "Kiss";
   private String artifactName;
   private static PersonDataSheetFactory personDataSheetFactory;
   private PersonDataSheet personDataSheet;

   protected PersonDataSheetRepositoryTestFixture( RepositoryTestEnvironment<PersonDataSheetRepository, RepositoryTestFixture<PersonDataSheetRepository,PersonDataSheet>> testEnvironment ) {
      super( testEnvironment );
   }

   //Properties
   public String getArtifactName() { return artifactName; }
   
   //Protected, private helper methods
   @Override
   protected void configureAfterSutInstantiation() {
      personDataSheetFactory = applicationContext.getEntityFactory( PersonDataSheetFactory.class );
   }

   @Override
   protected PersonDataSheet createNewAggregate() throws Exception {
      personDataSheet = personDataSheetFactory.create( TEST_GIVEN_NAME, TEST_FAMILY_NAME );
      artifactName = personDataSheet.getName();
      return personDataSheet;
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
