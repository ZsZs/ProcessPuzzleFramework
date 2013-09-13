package com.processpuzzle.artifact.domain;


import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeFactory;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.litest.template.RepositoryTestEnvironment;
import com.processpuzzle.litest.template.RepositoryTestFixture;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class ArtifactRepositoryTestFixture extends RepositoryTestFixture<DefaultArtifactRepository, Artifact> {
   public static final String ARTIFACT_NAME = "Test artifact";
   private ArtifactTypeRepository artifactTypeRepository;
   private ArtifactTypeFactory artifactTypeFactory;
   private UserFactory userFactory;
   private UserRepository userRepository;
   private ArtifactType subClassType;
   private User creator;

   protected ArtifactRepositoryTestFixture( RepositoryTestEnvironment<DefaultArtifactRepository, RepositoryTestFixture<DefaultArtifactRepository,Artifact>> testEnvironment ) {
      super( testEnvironment );
   }
   
   //Properties
   public ArtifactType getSubClassType() { return subClassType; }

   //Protected, private helper methods.
   @Override
   protected void configureAfterSutInstantiation() {
      // TODO Auto-generated method stub
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      artifactTypeFactory = applicationContext.getEntityFactory( ArtifactTypeFactory.class );
      artifactTypeRepository = applicationContext.getRepository( ArtifactTypeRepository.class );
      userRepository = applicationContext.getRepository( UserRepository.class );
      
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      subClassType = artifactTypeFactory.createArtifactType( "ArtifactSubClass" );
      subClassType.setSystem( true );
      artifactTypeRepository.addArtifactType( work, subClassType );
      creator = userFactory.createUser( "testPerson", "password" );
      userRepository.add( work, creator );
      work.finish();
   }

   @Override
   protected Artifact<?> createNewAggregate() throws Exception {
      Artifact<?> artifact = new ArtifactSubClass( ARTIFACT_NAME, subClassType, creator );
      return artifact;
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
