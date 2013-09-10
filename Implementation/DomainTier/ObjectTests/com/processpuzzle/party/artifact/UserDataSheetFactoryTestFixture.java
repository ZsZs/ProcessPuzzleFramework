package com.processpuzzle.party.artifact;


import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.litest.template.FactoryTestEnvironment;
import com.processpuzzle.litest.template.FactoryTestFixture;

public class UserDataSheetFactoryTestFixture extends FactoryTestFixture<UserDataSheetFactory, UserDataSheet> {
   private DefaultArtifactRepository artifactRepository;
   
   public UserDataSheetFactoryTestFixture( FactoryTestEnvironment<UserDataSheetFactory, ?> testEnvironment ) {
      super( testEnvironment );
   }
   
   //Properties
   public DefaultArtifactRepository getArtifactRepository() { return artifactRepository; }

   @Override
   protected void configureAfterSutInstantiation() {
      artifactRepository = applicationContext.getRepository(DefaultArtifactRepository.class);
   }

}
