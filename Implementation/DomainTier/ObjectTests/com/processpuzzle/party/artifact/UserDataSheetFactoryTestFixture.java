package com.processpuzzle.party.artifact;

import hu.itkodex.litest.template.FactoryTestEnvironment;
import hu.itkodex.litest.template.FactoryTestFixture;

import com.processpuzzle.artifact.domain.DefaultArtifactRepository;

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
