package com.processpuzzle.artifact_type_group.domain;

import hu.itkodex.litest.template.RepositoryTestEnvironment;
import hu.itkodex.litest.template.RepositoryTestFixture;

public class ArtifactTypeGroupRepositoryTestFixture extends RepositoryTestFixture<ArtifactTypeGroupRepository, ArtifactTypeGroup> {
   public static final String GROUP_NAME = "TestGroup";

   public ArtifactTypeGroupRepositoryTestFixture( RepositoryTestEnvironment<ArtifactTypeGroupRepository, RepositoryTestFixture<ArtifactTypeGroupRepository, ArtifactTypeGroup>> testEnvironment ) {
      super( testEnvironment );
   }

   @Override protected ArtifactTypeGroup createNewAggregate() throws Exception {
      ArtifactTypeGroup group = new ArtifactTypeGroup( GROUP_NAME );
      return group;
   }

   @Override
   protected void afterAggregateCreation() {}

   @Override
   protected void afterAggregateDeletion() {}

   @Override
   protected void beforeAggregateCreation() {}
}
