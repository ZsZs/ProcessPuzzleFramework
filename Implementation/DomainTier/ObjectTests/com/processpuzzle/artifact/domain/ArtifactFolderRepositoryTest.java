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
      // TODO Auto-generated method stub
      
   }

   @Override
   public void testAdd_ForReferencedAggregateRoots() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void testDelete_ForOwnedAttributesAndComponents() throws Exception {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void testFindAll_ForResultCount() {
      // TODO Auto-generated method stub
      
   }

   public @Override void testFindById() {
      ArtifactFolder artifactFolder = repository.findById( testWork, fixture.getArtifactFolder().getId() );
      Artifact<?> artifactOne = fixture.getArtifactRepository().findById( fixture.getArtifactOne().getId() );
      Artifact<?> artifactTwo = fixture.getArtifactRepository().findById( fixture.getArtifactTwo().getId() );
      
      assertThat( artifactFolder, notNullValue() );
//      assertThat( (Artifact)findChildArtifactOfFolder( artifactFolder, artifactOne.getName() ), equalTo( artifactOne ));
      assertThat( artifactOne.getContainingFolder(), equalTo( artifactFolder ));
      assertThat( artifactTwo.getContainingFolder(), equalTo( artifactFolder ));
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
   public void testUpdate_ForOwnedAttributesAndComponents() throws Exception {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void testUpdate_ForReferencedAggregateRoots() {
      // TODO Auto-generated method stub
      
   }

   private Artifact<?> findChildArtifactOfFolder( ArtifactFolder folder, String artifactName ) {
      for( Artifact<?> childArtifact : folder.getChildArtifacts() ) {
         if( childArtifact.getName().equals( artifactName )) return childArtifact;
      }
      return null;
   }

}
