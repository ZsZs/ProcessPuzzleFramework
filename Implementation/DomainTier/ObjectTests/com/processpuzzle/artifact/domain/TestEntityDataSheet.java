/*
 * Created on Jul 12, 2006
 */
package com.processpuzzle.artifact.domain;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeNotFoundException;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.persistence.domain.TestEntityFactory;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

/**
 * @author zsolt.zsuffa
 */
public class TestEntityDataSheet extends Artifact<TestEntityDataSheet> {
   private TestEntity testEntity = null;

   protected TestEntityDataSheet() {}

   protected TestEntityDataSheet( ArtifactType type, TestEntity entity, User creator ) {
      super( entity.getName(), type, creator );
      this.testEntity = entity;
   }

   public static TestEntityDataSheet create( String entityName ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      ArtifactTypeRepository typeRepository = (ArtifactTypeRepository) ProcessPuzzleContext.getInstance().getRepository(
            ArtifactTypeRepository.class );
      ArtifactType type = typeRepository.findArtifactTypeByName( work, TestEntityDataSheet.class.getSimpleName() );
      if( type == null )
         throw new ArtifactTypeNotFoundException( TestEntityDataSheet.class.getSimpleName() );

      TestEntity testEntity = TestEntityFactory.createTestEntity( entityName );
      TestEntityDataSheet dataSheet = new TestEntityDataSheet( type, testEntity, null );

      DefaultArtifactRepository artifactRepository = (DefaultArtifactRepository) ProcessPuzzleContext.getInstance().getRepository(
            DefaultArtifactRepository.class );
      artifactRepository.add( work, dataSheet );
      work.finish();
      return dataSheet;
   }

   public TestEntity getTestEntity() {
      return testEntity;
   }

   public void update() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      DefaultArtifactRepository artifactRepository = (DefaultArtifactRepository) ProcessPuzzleContext.getInstance().getRepository(
            DefaultArtifactRepository.class );
      artifactRepository.update( work, this );
      work.finish();
   }

   public void delete() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      DefaultArtifactRepository artifactRepository = (DefaultArtifactRepository) ProcessPuzzleContext.getInstance().getRepository(
            DefaultArtifactRepository.class );
      artifactRepository.delete( work, this );
      work.finish();
   }

   public String getAsXml() {
      return super.getAsXml( TestEntityDataSheet.class, testEntity );
   }

   @Override
   public DefaultIdentityExpression<TestEntityDataSheet> getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }
}
