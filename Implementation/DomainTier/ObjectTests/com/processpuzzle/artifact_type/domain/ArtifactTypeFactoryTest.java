package com.processpuzzle.artifact_type.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.processpuzzle.artifact.domain.ImageFile;
import com.processpuzzle.litest.template.FactoryTestTemplate;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class ArtifactTypeFactoryTest extends FactoryTestTemplate<ArtifactTypeFactory, ArtifactTypeFactoryTestFixture, ArtifactType> {
   private ArtifactType type;
   private DefaultUnitOfWork work;

   public ArtifactTypeFactoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   // Test cases
   @Override
   public void create_ForSuccess() {
      type = sut.create( ArtifactTypeFactoryTestFixture.DOCUMENT_TYPE_NAME, ArtifactTypeFactoryTestFixture.DOCUMENT_GROUP );

      assertThat( type, notNullValue() );
      assertThat( type.getGroup(), notNullValue() );
      assertThat( type.getGroup().getName(), equalTo( ArtifactTypeFactoryTestFixture.DOCUMENT_GROUP ) );
   }

   @Override
   public void create_ForCollision() {
      // SETUP:
      templatedFixture.setUpImageFileArtifactType();

      // EXCERCISE:
      sut.create( ArtifactTypeFactoryTestFixture.IMAGEFILE_TYPE_NAME, ArtifactTypeFactoryTestFixture.IMAGEFILE_GROUP, ImageFile.class );
   }

   @Test
   public void createAndAddToGroup_addsTheNewArtifactToGroup() {
      work = new DefaultUnitOfWork( true );
      type = sut.createAndAddToGroup( work, ArtifactTypeFactoryTestFixture.IMAGEFILE_TYPE_NAME, ArtifactTypeFactoryTestFixture.IMAGEFILE_GROUP, ImageFile.class );
      work.finish();
      
      assertThat( templatedFixture.getImageTypeGroup().getArtifactTypes(), hasItem( type ));
   }
}
