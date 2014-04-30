package com.processpuzzle.artifact_type.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.litest.template.FactoryTestTemplate;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class ArtifactTypeFactoryTest extends FactoryTestTemplate<ArtifactTypeFactory, ArtifactTypeFactoryTestFixture, ArtifactType> {
   private ArtifactType type;

   public ArtifactTypeFactoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   // Test cases
   @Override
   public void create_ForSuccess() {
      type = sut.createArtifactType( ArtifactTypeFactoryTestFixture.IMAGEFILE_TYPE_NAME, ArtifactTypeFactoryTestFixture.IMAGEFILE_GROUP );

      assertThat( type, notNullValue());
      assertThat( type.getGroup(), notNullValue() );
      assertThat( type.getGroup().getName(), equalTo( ArtifactTypeFactoryTestFixture.IMAGEFILE_GROUP ));
   }

   @Override
   public void create_ForCollision() {
      //SETUP:
      type = sut.createArtifactType( ArtifactTypeFactoryTestFixture.IMAGEFILE_TYPE_NAME, ArtifactTypeFactoryTestFixture.IMAGEFILE_GROUP );
      
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      ArtifactTypeRepository repository = (ArtifactTypeRepository) ProcessPuzzleContext.getInstance().getRepository( ArtifactTypeRepository.class );
      repository.addArtifactType( work, type );
      
      //EXCERCISE:
      sut.createArtifactType( ArtifactTypeFactoryTestFixture.IMAGEFILE_TYPE_NAME, ArtifactTypeFactoryTestFixture.IMAGEFILE_GROUP );
   }
}
