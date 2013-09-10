package com.processpuzzle.artifact.domain;

import com.processpuzzle.litest.template.FactoryTestEnvironment;
import com.processpuzzle.litest.template.FactoryTestFixture;

public class ArtifactFolderFactoryTestFixture extends FactoryTestFixture<ArtifactFolderFactory, ArtifactFolder> {
   private RootArtifactFolderFactory rootFolderFactory;
   private ArtifactFolderFactory artifactFolderFactory;
   private ArtifactFolderRepository artifactFolderRepository;

   public ArtifactFolderFactoryTestFixture( FactoryTestEnvironment<ArtifactFolderFactory, ?> testEnvironment ) {
      super( testEnvironment );
   }

   @Override
   protected void configureAfterSutInstantiation() {
      rootFolderFactory = applicationContext.getEntityFactory( RootArtifactFolderFactory.class );
      artifactFolderRepository = applicationContext.getRepository( ArtifactFolderRepository.class );
      artifactFolderFactory = applicationContext.getEntityFactory( ArtifactFolderFactory.class );
   }

   public RootArtifactFolderFactory getRootFolderFactory() {
      return rootFolderFactory;
   }

   public ArtifactFolderFactory getArtifactFolderFactory() {
      return artifactFolderFactory;
   }

   public ArtifactFolderRepository getArtifactFolderRepository() {
      return artifactFolderRepository;
   }

}
