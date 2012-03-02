package com.processpuzzle.artifact_type_group.domain;

import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

import hu.itkodex.commons.persistence.UnitOfWork;
import hu.itkodex.litest.template.RepositoryTestEnvironment;
import hu.itkodex.litest.template.RepositoryTestFixture;

public class ArtifactTypeGroupRepositoryTestFixture extends RepositoryTestFixture<ArtifactTypeGroupRepository, ArtifactTypeGroup> {
   public static final String ARTIFACT_TYPE_ONE = "ArtifactTypeOne";
   public static final String ARTIFACT_TYPE_TWO = "ArtifactTypeTwo";
   public static final String GROUP_NAME = "TestGroup";
   private ArtifactType artifactTypeOne;
   private ArtifactType artifactTypeTwo;
   private ArtifactTypeRepository artifactTypeRepository;

   public ArtifactTypeGroupRepositoryTestFixture( RepositoryTestEnvironment<ArtifactTypeGroupRepository, RepositoryTestFixture<ArtifactTypeGroupRepository, ArtifactTypeGroup>> testEnvironment ) {
      super( testEnvironment );
   }

   @Override protected ArtifactTypeGroup createNewAggregate() throws Exception {
      ArtifactTypeGroup group = new ArtifactTypeGroup( GROUP_NAME );      
      return group;
   }

   @Override protected void afterAggregateCreation() {
      addArtifactTypesToGroup();
   }

   @Override protected void afterAggregateDeletion() {}

   @Override protected void beforeAggregateCreation() {
      lookUpRepositoriesAndFactories();
      instantiateArtifactTypes();
   }

   private void addArtifactTypesToGroup() {
      root.addType( artifactTypeOne );
      root.addType( artifactTypeTwo );
      
      UnitOfWork work = new DefaultUnitOfWork( true );
      repository.update( work, root );
      artifactTypeRepository.add( work, artifactTypeOne );
      artifactTypeRepository.add( work, artifactTypeTwo );
      work.finish();
   }

   private void instantiateArtifactTypes() {
      artifactTypeOne = new ArtifactType( ARTIFACT_TYPE_ONE );
      artifactTypeTwo = new ArtifactType( ARTIFACT_TYPE_TWO );
   }
   
   private void lookUpRepositoriesAndFactories(){
      artifactTypeRepository = applicationContext.getRepository( ArtifactTypeRepository.class );
   }
}
