package com.processpuzzle.party.artifact;

import hu.itkodex.litest.template.FactoryTestEnvironment;
import hu.itkodex.litest.template.FactoryTestFixture;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;

public class CompanyDataSheetFactoryTestFixture extends FactoryTestFixture<CompanyDataSheetFactory, CompanyDataSheet> {
   private DefaultArtifactRepository artifactRepository;

   public CompanyDataSheetFactoryTestFixture( FactoryTestEnvironment<CompanyDataSheetFactory, ?> testEnvironment ) {
      super( testEnvironment );
   }

   //Properties
   public DefaultArtifactRepository getArtifactRepository() { return artifactRepository; }
   
   //Protected, private helper methods
   @Override
   protected void configureAfterSutInstantiation() {
      artifactRepository = ProcessPuzzleContext.getInstance().getRepository(DefaultArtifactRepository.class);
   }

}
