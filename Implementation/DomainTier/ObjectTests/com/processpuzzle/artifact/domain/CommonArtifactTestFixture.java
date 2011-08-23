package com.processpuzzle.artifact.domain;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import hu.itkodex.litest.template.ArtifactTestEnvironment;
import hu.itkodex.litest.template.ArtifactTestFixture;

import java.util.Date;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeFactory;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.persistence.domain.TestEntity;

public class CommonArtifactTestFixture extends ArtifactTestFixture<TestEntityDataSheet> {
   public static final String ENTITY_NAME = "Test Entity for Artifact Test";
   public static final String ARTIFACT_CREATOR_NAME = "Gipsz Jakab";
   public static final String ARTIFACT_CREATOR_PSW = "psw";
   public static final Integer ARTIFACT_ID = 1;
   public static final String ARTIFACT_NAME = ENTITY_NAME;
   public static final String ARTIFACT_TYPE_NAME = "Test Entity";
   private User artifactCreator;
   private User artifactModifier;
   private TestEntityDataSheet artifact;
   private ArtifactType artifactType;
   private ArtifactViewType propertyViewType;
   private TestEntity aggregateRoot;
   private Date artifactCreationDate;

   public CommonArtifactTestFixture( ArtifactTestEnvironment<TestEntityDataSheet, ?> testEnvironment ) {
      super( testEnvironment );
   }

   // Properties
   public Date getArtifactCreationDate() { return artifactCreationDate; }
   
   public User getArtifactCreator() { return artifactCreator; }
   
   public User getArtifactModifier() { return artifactModifier; }

   // Protected, private helper methods
   @Override
   protected void configureAfterSutInstantiation() {
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      stubCurrentUser();
      stubAggregateRoot();
      stubArtifactType();
      setUpArtifact();
   }

   @Override
   protected TestEntityDataSheet instantiateSUT() {
      sut = artifact;
      return sut;
   }

   @Override
   protected void releaseResources() {}

   private void setUpArtifact() {
      artifact = new TestEntityDataSheet( artifactType, aggregateRoot, artifactCreator );
      artifact.setId( ARTIFACT_ID );
      artifact.setResponsible( artifactCreator );
      artifactCreationDate = artifact.getCreation();
   }

   private void stubAggregateRoot() {
      aggregateRoot = mock( TestEntity.class );
      when( aggregateRoot.getName() ).thenReturn( ENTITY_NAME );
   }

   private void stubArtifactType() {
      ArtifactTypeFactory artifactTypeFactory = new ArtifactTypeFactory();
      
      propertyViewType = ArtifactTypeFactory.createPropertyViewType( "PropertyView", null );
      
      artifactType = artifactTypeFactory.createArtifactType( ARTIFACT_TYPE_NAME, null, TestEntityDataSheet.class );
      artifactType.addViewType( propertyViewType );
   }

   private void stubCurrentUser() {
      artifactCreator = mock( User.class );
      when( artifactCreator.getUserName() ).thenReturn( ARTIFACT_CREATOR_NAME );
      when( artifactCreator.getPassword() ).thenReturn( ARTIFACT_CREATOR_PSW );
   }

}
