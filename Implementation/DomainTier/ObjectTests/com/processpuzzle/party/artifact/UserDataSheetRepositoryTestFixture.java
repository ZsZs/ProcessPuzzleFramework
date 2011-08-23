package com.processpuzzle.party.artifact;

import hu.itkodex.litest.template.RepositoryTestEnvironment;
import hu.itkodex.litest.template.RepositoryTestFixture;

public class UserDataSheetRepositoryTestFixture extends RepositoryTestFixture<UserDataSheetRepository, UserDataSheet> {
   private static final String TEST_GIVEN_NAME = "Pista";
   private static final String TEST_FAMILY_NAME = "Kiss";
   private String artifactName;
   private static UserDataSheetFactory userDataSheetFactory;
   private UserDataSheet userDataSheet;

   protected UserDataSheetRepositoryTestFixture( RepositoryTestEnvironment<UserDataSheetRepository, RepositoryTestFixture<UserDataSheetRepository,UserDataSheet>> testEnvironment ) {
      super( testEnvironment );
   }
   
   public String getArtifactName() { return artifactName; }
   public UserDataSheetFactory getUserDataSheetFactory() { return userDataSheetFactory; }

   @Override
   protected UserDataSheet createNewAggregate() throws Exception {
      userDataSheet = userDataSheetFactory.create( TEST_GIVEN_NAME, TEST_FAMILY_NAME );
      artifactName = userDataSheet.getName();
      return userDataSheet;
   }

   @Override
   protected void configureAfterSutInstantiation() {
      userDataSheetFactory = applicationContext.getEntityFactory( UserDataSheetFactory.class );
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
