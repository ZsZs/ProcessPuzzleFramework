package com.processpuzzle.artifact_type.domain;

import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroup;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroupFactory;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroupRepository;
import com.processpuzzle.litest.template.RepositoryTestEnvironment;
import com.processpuzzle.litest.template.RepositoryTestFixture;

public class ArtifactTypeRepositoryTestFixture extends RepositoryTestFixture<ArtifactTypeRepository, ArtifactType> {
   public static final String QUERY_VIEW_TYPE_NAME = "TestQueryViewType";
   public static final String PREDEFINED_QUERY_NAME = "TestQueryName";
   public static final String PREDEFINED_QUERY_DESCRIPTION = "TestQueryDescription";
   public static final String PREDEFINED_QUERY_STATEMENT = "TestQueryStatement";
   public static final String TYPE_NAME = "TestArtifactType";
   public static final String TYPE_GROUP_NAME = "SystemAdminitration";
   private ArtifactTypeFactory artifactTypeFactory;
   private ArtifactTypeGroup artifactTypeGroup;
   private ArtifactType testType;
   private ListQueryViewType queryViewType;

   //Constructors
   public ArtifactTypeRepositoryTestFixture( RepositoryTestEnvironment<ArtifactTypeRepository, RepositoryTestFixture<ArtifactTypeRepository,ArtifactType>> testEnvironment ) {
      super( testEnvironment );
   }

   //Properties
   public ListQueryViewType getQueryViewType() { return queryViewType; }
   
   //Protected, private helper methods
   @Override protected void afterAggregateCreation() {}
   @Override protected void afterAggregateDeletion() {}
   @Override protected void beforeAggregateCreation() {}

   @Override protected void configureBeforeSutInstantiation() {
      super.configureBeforeSutInstantiation();
      artifactTypeFactory = applicationContext.getEntityFactory( ArtifactTypeFactory.class );
      instantiateTypeGroup();
   }

   private void instantiateTypeGroup() {
      ArtifactTypeGroupFactory artifactTypeGroupFactory = applicationContext.getEntityFactory( ArtifactTypeGroupFactory.class );
      ArtifactTypeGroupRepository artifactTypeGroupRepository = applicationContext.getRepository( ArtifactTypeGroupRepository.class );
      artifactTypeGroup = artifactTypeGroupFactory.create( TYPE_GROUP_NAME );
      artifactTypeGroupRepository.add( artifactTypeGroup );
   }
   
   @Override
   protected ArtifactType createNewAggregate() throws Exception {
      testType = artifactTypeFactory.create( TYPE_NAME, TYPE_GROUP_NAME );
      testType.setSystem( true );
      
      queryViewType = new ListQueryViewType( QUERY_VIEW_TYPE_NAME );
      queryViewType.addPredefinedQuery( PREDEFINED_QUERY_NAME, PREDEFINED_QUERY_DESCRIPTION, PREDEFINED_QUERY_STATEMENT );
      testType.addViewType( queryViewType );
      
      return testType;
   }

}
