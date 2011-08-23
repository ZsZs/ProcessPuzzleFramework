package com.processpuzzle.business.definition.domain;

import org.junit.BeforeClass;

import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactFolderRepository;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.business.definition.domain.BusinessImplementationLoader;
import com.processpuzzle.sharedfixtures.domaintier.BusinessDefinitionFixture;

public class BusinessImplementationLoaderTest extends BusinessDataLoaderTest<BusinessImplementationLoader> {
   private static BusinessDefinitionFixture businessDefinitionFixture;
   private static Artifact<?> personList;
   private static DefaultArtifactRepository artifactRepository;
   private static ArtifactFolderRepository artifactFolderRepository;
   
   @BeforeClass public static void beforeAllTests() throws Exception {
      xPathKey = PropertyKeys.BUSINESS_IMPLEMENTATION.getDefaultKey();
      dataLoaderClass = BusinessImplementationLoader.class;
      
      businessDefinitionFixture = BusinessDefinitionFixture.getInstance();
      businessDefinitionFixture.setUp();
      applicationContext = businessDefinitionFixture.getApplicationContext();
      
      BusinessDataLoaderTest.beforeAllTests();
      
      artifactRepository = applicationContext.getRepository( DefaultArtifactRepository.class );
      artifactFolderRepository = applicationContext.getRepository( ArtifactFolderRepository.class );
      
      personList = artifactRepository.findByName( "PersonList" );
   }
   
}
