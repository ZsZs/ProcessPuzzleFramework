package com.processpuzzle.artifact_type.domain;

import hu.itkodex.litest.template.RepositoryTestEnvironment;
import hu.itkodex.litest.template.RepositoryTestFixture;

public class ArtifactTypeRepositoryTestFixture extends RepositoryTestFixture<ArtifactTypeRepository, ArtifactType> {
   public static final String QUERY_VIEW_TYPE_NAME = "TestQueryViewType";
   public static final String PREDEFINED_QUERY_NAME = "TestQueryName";
   public static final String PREDEFINED_QUERY_DESCRIPTION = "TestQueryDescription";
   public static final String PREDEFINED_QUERY_STATEMENT = "TestQueryStatement";
   private ArtifactTypeFactory artifactTypeFactory;
   private ArtifactType testType;
   private ListQueryViewType queryViewType;

   public ListQueryViewType getQueryViewType() { return queryViewType; }
   
   protected ArtifactTypeRepositoryTestFixture( RepositoryTestEnvironment<ArtifactTypeRepository, RepositoryTestFixture<ArtifactTypeRepository,ArtifactType>> testEnvironment ) {
      super( testEnvironment );
   }

   @Override
   protected ArtifactType createNewAggregate() throws Exception {
      testType = artifactTypeFactory.createArtifactType( "TestArtifactType" );
      testType.setSystem( true );
      
      queryViewType = new ListQueryViewType( QUERY_VIEW_TYPE_NAME );
      queryViewType.addPredefinedQuery( PREDEFINED_QUERY_NAME, PREDEFINED_QUERY_DESCRIPTION, PREDEFINED_QUERY_STATEMENT );
      testType.addViewType( queryViewType );
      
      return testType;
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
