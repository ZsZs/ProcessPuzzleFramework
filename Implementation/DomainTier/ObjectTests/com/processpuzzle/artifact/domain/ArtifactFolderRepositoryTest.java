package com.processpuzzle.artifact.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import com.processpuzzle.litest.template.RepositoryTestTemplate;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class ArtifactFolderRepositoryTest extends RepositoryTestTemplate<ArtifactFolderRepository, ArtifactFolderRepositoryTestFixture, ArtifactFolder> {
   
   public ArtifactFolderRepositoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   public void testAdd_ForOwnedAttributesAndComponents() throws Exception {
   }

   @Override
   public void testAdd_ForReferencedAggregateRoots() {
   }

   @Override
   public void testDelete_ForOwnedAttributesAndComponents() throws Exception {
   }

   @Override
   public void testFindAll_ForResultCount() {
   }

   public @Override void testFindById() {
      ArtifactFolder artifactFolder = repository.findById( testWork, templatedFixture.getArtifactFolder().getId() );
      Artifact<?> artifactOne = templatedFixture.getArtifactRepository().findById( templatedFixture.getArtifactOne().getId() );
      Artifact<?> artifactTwo = templatedFixture.getArtifactRepository().findById( templatedFixture.getArtifactTwo().getId() );
      
      assertThat( artifactFolder, notNullValue() );
//      assertThat( (Artifact)findChildArtifactOfFolder( artifactFolder, artifactOne.getName() ), equalTo( artifactOne ));
      assertThat( artifactOne.getContainingFolder(), equalTo( artifactFolder ));
      assertThat( artifactTwo.getContainingFolder(), equalTo( artifactFolder ));
   }

   @Override
   public void testFindById_ForEagerLoadedComponents() {
   }

   @Override
   public void testFindById_ForLazyLoadedComponents() {
   }

   @Override
   public void testFindByQuery_ForComponentAttributes() {
   }

   @Override
   public void testFindByQuery_ForDirectAttributes() {
   }

   @Override
   public void testUpdate_ForOwnedAttributesAndComponents() throws Exception {
   }

   @Override
   public void testUpdate_ForReferencedAggregateRoots() {
   }

}
