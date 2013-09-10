package com.processpuzzle.artifact_type_group.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.litest.template.RepositoryTestTemplate;
import com.processpuzzle.sharedfixtures.artifact.ArtifactLayerTestConfiguration;

public class ArtifactTypeGroupRepositoryTest extends RepositoryTestTemplate<ArtifactTypeGroupRepository, ArtifactTypeGroupRepositoryTestFixture, ArtifactTypeGroup> {

   public ArtifactTypeGroupRepositoryTest() {
      super( ArtifactLayerTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override @Test
   public void testAdd_ForOwnedAttributesAndComponents() {
      assertThat( databaseSpy.retrieveColumnFromRow( "T_ARTIFACT_TYPE_GROUP", root.getId(), String.class, "name" ), equalTo( root.getName() ));
   }

   @Override
   public void testAdd_ForReferencedAggregateRoots() {
      for( ArtifactType artifactType : root.getArtifactTypes() ){
         assertThat( databaseSpy.retrieveColumnFromRow( "T_ARTIFACT_TYPE", artifactType.getId(), Integer.class, "artifact_type_group_id" ), equalTo( root.getId() ));
      }
   }

   @Override
   public void testDelete_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindAll_ForResultCount() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById_ForEagerLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById_ForLazyLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindByQuery_ForComponentAttributes() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindByQuery_ForDirectAttributes() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testUpdate_ForOwnedAttributesAndComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testUpdate_ForReferencedAggregateRoots() {
   // TODO Auto-generated method stub

   }

}
